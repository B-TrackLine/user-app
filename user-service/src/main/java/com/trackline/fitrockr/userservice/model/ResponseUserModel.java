package com.trackline.fitrockr.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserModel {

    @Getter
    @Setter
    private UserReadModel data;
}
