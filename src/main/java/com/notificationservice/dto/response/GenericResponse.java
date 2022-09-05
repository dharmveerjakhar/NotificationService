package com.notificationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {

    @JsonProperty("data")
    private T data;

    @JsonProperty("error")
    private List<String> error;

    @JsonProperty("error_details")
    private List<ErrorDetails> errorDetails;
}
