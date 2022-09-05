package com.notificationservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendSmsRequest {

    @JsonProperty("phone_number")
    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^\\+91[0-9]{10}$", message = "Invalid phone number passed!")
    private String phoneNumber;

    @JsonProperty("message")
    @NotBlank(message = "message is required")
    private String message;
}