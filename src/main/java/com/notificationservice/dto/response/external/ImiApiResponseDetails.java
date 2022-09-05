package com.notificationservice.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImiApiResponseDetails {

    @JsonProperty("code")
    private String code;

    @JsonProperty("transid")
    private String transId;

    @JsonProperty("description")
    private String description;
}
