package com.trackline.fitrockr.userservice.controller;

import com.trackline.fitrockr.userservice.model.ResponseErrorModel;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ApiResponse(responseCode = "200", useReturnTypeSchema = true)
@ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ResponseErrorModel.class)))
@ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ResponseErrorModel.class)))
@ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ResponseErrorModel.class)))
public @interface ResponseTypes {}