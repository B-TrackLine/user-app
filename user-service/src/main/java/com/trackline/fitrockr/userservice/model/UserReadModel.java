package com.trackline.fitrockr.userservice.model;

import com.trackline.fitrockr.userservice.db.entity.Country;
import com.trackline.fitrockr.userservice.db.entity.Language;
import org.springframework.lang.NonNull;

import java.util.Objects;

public record UserReadModel(
        @NonNull Long id,
        @NonNull String name,
        @NonNull String email,
        @NonNull Country country,
        @NonNull Language language) {

    public UserReadModel {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
        Objects.requireNonNull(country);
        Objects.requireNonNull(language);
    }
}