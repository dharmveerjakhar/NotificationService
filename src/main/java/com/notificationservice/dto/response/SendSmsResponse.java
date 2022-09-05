package com.notificationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendSmsResponse {

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("comments")
    private String comments;
}
