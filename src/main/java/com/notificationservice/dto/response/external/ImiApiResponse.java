package com.notificationservice.dto.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImiApiResponse {

    @JsonProperty("response")
    private List<ImiApiResponseDetails> imiApiResponseDetails;
}
