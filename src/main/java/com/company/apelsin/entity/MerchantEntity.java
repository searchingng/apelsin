package com.company.apelsin.entity;

import com.company.apelsin.entity.base.BaseEntity;
import com.company.apelsin.entity.base.BaseUUIDEntity;
import com.company.apelsin.entity.enums.MerchantStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity(name = "merchant")
@Setter
@Getter
@NoArgsConstructor
public class MerchantEntity extends BaseEntity {

    private String name;
    private String cardNumber;
    private MerchantStatus status;

}
