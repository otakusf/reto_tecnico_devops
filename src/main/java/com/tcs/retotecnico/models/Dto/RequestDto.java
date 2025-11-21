package com.tcs.retotecnico.models.Dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    String message;
    String to;
    String from;
    int timeToLifeSec;
}
