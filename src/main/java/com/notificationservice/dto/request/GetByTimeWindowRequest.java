package com.notificationservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetByTimeWindowRequest {

    @JsonProperty("phoneNumber")
    @NotBlank(message = "phone_number is required")
    @Pattern(regexp = "^\\+91[0-9]{10}$", message = "Invalid phone number(s) is passed")
    private String phoneNumber;

    @JsonProperty("startTime")
    @NotNull(message = "start_time is required")
    private Date startTime;

    @JsonProperty("endTime")
    @NotNull(message = "end_time is required")
    private Date endTime;

    @JsonProperty("pageNumber")
    @Min(value = 0)
    @NotNull(message = "page_number is required")
    private Integer pageNumber;

    @JsonProperty("pageSize")
    @Min(value = 1)
    @NotNull(message = "page_size is required")
    private Integer pageSize;
}
