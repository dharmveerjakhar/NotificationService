package com.notificationservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "black_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneNumber implements Serializable {

    @Id
    @Column(name = "phone_number")
    private String phoneNumber;
}


