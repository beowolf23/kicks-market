package com.beowolf23.kicksmarket.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse<T> {

    private T data;
    private boolean success;
    private int statusCode;
}
