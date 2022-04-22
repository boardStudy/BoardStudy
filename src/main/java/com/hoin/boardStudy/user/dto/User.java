package com.hoin.boardStudy.user.dto;

import lombok.*;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter @Setter @ToString
@NoArgsConstructor // 파라메터가 없는 생성자를 생성한다. (기본 생성자)
@AllArgsConstructor // 클래스에 존재하는 모든 필드에 대한 생성자를 자동으로 생성한다.
public class User {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 2, max = 20, message = "아이디는 2자 이상 20자 이하로 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    private LocalDateTime regDate;
    private LocalDateTime withdDate;
    private LocalDateTime updDate;
    private int withdStatus;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String phone;

    private int gender;
}

