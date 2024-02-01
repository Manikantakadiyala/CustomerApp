package com.customerapp.Exception;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Data
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String details;
}