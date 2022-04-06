package com.company.apelsin.entity;

import com.company.apelsin.entity.base.BaseEntity;
import com.company.apelsin.entity.enums.ProfileStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity(name = "profile")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProfileEntity extends BaseEntity {

    private String name;

    private String surname;

    private String middleName;

    private LocalDate birthDate;

    private String phone;

    private String login;

    private String password;

    private String role;

    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

}
