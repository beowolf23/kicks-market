package com.beowolf23.kicksmarket.exception;

import lombok.Builder;

import java.util.Date;

@Builder
public record Error(
        Date timestamp,
        String status,
        String[] errors,
        String path
) {
}
