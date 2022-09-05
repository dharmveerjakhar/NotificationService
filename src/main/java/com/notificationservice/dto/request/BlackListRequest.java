package com.notificationservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlackListRequest {

    @JsonProperty(value = "phoneNumbers")
    @NotNull(message = "phone_number(s) is mandatory")
    @NotEmpty
    private List<@Pattern(regexp = "^\\+91[0-9]{10}$", message = "Invalid phone number(s) is passed") String> phoneNumbers;
}
