package com.company.apelsin.dto;

import com.company.apelsin.entity.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {

    private Long id;
    private LocalDateTime createdAt;

    private String name;

    private String surname;

    private String middleName;

    private LocalDate birthDate;

    private String phone;

    private String login;

    private String password;
    private String role;

    private ProfileStatus status;

    private String jwt;

}
