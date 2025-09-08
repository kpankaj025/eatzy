package com.foodie.eatzy.dto;

import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    String message;
    HttpStatus status;

}
