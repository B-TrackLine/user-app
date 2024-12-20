package com.trackline.fitrockr.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseUsersModel {
    
    @Getter
    @Setter
    private List<UserReadModel> data = new ArrayList<>();
    
}
