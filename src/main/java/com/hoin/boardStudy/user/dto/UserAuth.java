package com.hoin.boardStudy.user.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth implements Serializable {
    private String userEmail;
    private String authKey;
}
