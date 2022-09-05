package com.notificationservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetByTextRequest {

    @JsonProperty("text")
    @NotBlank(message = "text is required")
    private String text;

    @JsonProperty("pageNumber")
    @Min(value = 0)
    @NotNull(message = "page_number is required")
    private Integer pageNumber;

    @JsonProperty("pageSize")
    @Min(value = 1)
    @NotNull(message = "page_size is required")
    private Integer pageSize;
}
