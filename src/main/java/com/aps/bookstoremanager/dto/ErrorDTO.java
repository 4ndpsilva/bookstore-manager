package com.aps.bookstoremanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private String field;
    private Integer statusCode;
    private LocalDateTime timestamp;
}