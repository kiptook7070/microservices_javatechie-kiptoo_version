package com.eclectics.io.user_auth_service.utils.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntityResponse<T> {
    private String message;
    private T entity;
    private Integer statusCode;
}
