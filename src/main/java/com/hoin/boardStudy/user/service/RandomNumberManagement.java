package com.hoin.boardStudy.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RandomNumberManagement {

    public int randomNumber() {
        int randomNumber = (int)((Math.random() * (99999 - 10000 + 1)) + 10000);
        return randomNumber;
    }

}
