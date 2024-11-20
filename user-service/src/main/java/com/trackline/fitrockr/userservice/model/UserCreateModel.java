package com.trackline.fitrockr.userservice.model;

import com.trackline.fitrockr.userservice.db.entity.Country;
import com.trackline.fitrockr.userservice.db.entity.Language;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserCreateModel (
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED) String name,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED) String email,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED) Country country,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED) Language language) {}