package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.dto.FileInfo;
import com.hoin.boardStudy.board.mapper.BoardMapper;
import com.hoin.boardStudy.util.FileConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileManager {

    private final BoardMapper boardMapper;
    private final FileConfig fileConfig;

    // 파일 저장
    @Transactional
    public void saveFile(FileInfo fileInfo, MultipartFile[] uploadFiles) throws IOException {

            int fileId = fileInfo.getFileId();
            int boardId = fileInfo.getBoardId();

            if (boardId != 0 && fileId != 0) {
                deleteFile(fileId, boardId);
            }

            for (MultipartFile uploadFile : uploadFiles) {

                // 파일 첨부
                String originalFileName = uploadFile.getOriginalFilename();
                String extension = FilenameUtils.getExtension(originalFileName).toLowerCase();
                String uploadPath = fileConfig.getPath();
                File saveFile;
                String saveFileName;
                long size;

                do {
                    saveFileName = UUID.randomUUID() + "." + extension; // UUID 는 유일한 값으로 중복을 피할 수 있다.
                    saveFile = new File(uploadPath + saveFileName);
                    size = uploadFile.getSize();
                } while (saveFile.exists());

                saveFile.getParentFile().mkdirs(); // mkdir - 만들고자하는 상위 디렉토리 존재 x 생성 x, mkdirs 상위 디렉토리 존재 x 상위 디렉토리까지 생성
                uploadFile.transferTo(saveFile); // 수신된 파일을 지정된 대상 파일로 전송. 이동, 복사, 저장 가능. 이미 있는 경우 삭제. 1회만 유효하다.

                fileInfo.setBoardId(fileInfo.getBoardId());
                fileInfo.setOriginalName(originalFileName);
                fileInfo.setSaveName(saveFileName);
                fileInfo.setExtension(extension);
                fileInfo.setSize(size);
                fileInfo.setRegDate(LocalDateTime.now());

                if (boardId != 0) boardMapper.modifyFile(fileInfo);
                if (boardId == 0 && fileId == 0) boardMapper.saveFile(fileInfo);
            }
    }

    // 파일 다운로드
    @Transactional
    public void fileDownload(int fileId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        FileInfo fileInfo = getFileInfo(fileId);

        // 파일이 업로드 된 경로로
       try {
            String savePath = fileConfig.getPath() + "/";
            String saveName = fileInfo.getSaveName();

            String originalName = fileInfo.getOriginalName();
            InputStream inputStream = null;
            OutputStream outputStream = null;
            File file = null;
            boolean skip = false;
            String client = "";

            // 파일을 읽어 스트림에 담기
            try {
                file = new File(savePath, saveName);
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException fe) {
                skip = true;
            }

            client = request.getHeader("User-Agent");

            // 파일 다운로드 헤더 지정
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Description", "JSP Generated Data");

            // 브라우저에 맞게 설정
            if (!skip) {
                // IE
                if (client.indexOf("MSIE") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(originalName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                    // IE 11 이상.
                } else if (client.indexOf("Trident") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(originalName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                } else {
                    // 한글 파일명 처리
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\"" + new String(originalName.getBytes("UTF-8"), "ISO8859_1") + "\"");
                    response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
                }
                response.setHeader("Content-Length", "" + file.length());
                outputStream = response.getOutputStream();

                byte b[] = new byte[(int) file.length()];
                int leng = 0;
                while ((leng = inputStream.read(b)) > 0) {
                    outputStream.write(b, 0, leng);
                }
            } else {
                response.setContentType("text/html;charset=UTF-8");
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {

       }
    }

    // 글에 등록된 파일 리스트
    @Transactional
    public List<FileInfo> getFiles(int boardId) {
       return boardMapper.getFiles(boardId);
    }
    
    // 파일 1개 정보
    @Transactional
    public FileInfo getFileInfo(int fileId) {
        return boardMapper.getFileInfo(fileId);
    }

    // 파일 삭제
    @Transactional
    public void deleteFile(int fileId, int boardId) throws IOException {
        FileInfo fileInfo = new FileInfo();
        fileInfo = boardMapper.getFileInfo(fileId);
        String saveName = fileInfo.getSaveName();
        String path_ = fileConfig.getPath() + saveName;
        Path path = Paths.get(path_);

        Files.delete(path);

        boardMapper.deleteFile(fileId);
    }


}
