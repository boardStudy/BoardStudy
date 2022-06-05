package com.hoin.boardStudy.board.client;

import com.hoin.boardStudy.board.dto.AlarmRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="alarm", url = "https://"+"${domain.alarm}")
public interface AlarmClient {

    @PostMapping("/alarm")
    void alarmByEmail(AlarmRequest alarmRequest);
}
