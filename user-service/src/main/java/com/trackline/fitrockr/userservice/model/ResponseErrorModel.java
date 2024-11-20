package com.trackline.fitrockr.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseErrorModel {

    @Getter
    @Setter
    private String errorMessage;
}
