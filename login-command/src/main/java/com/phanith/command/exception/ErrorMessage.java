package com.phanith.command.exception;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private String message;
    private int statusCode;
    private Date timestamp;
    private String description;

}
