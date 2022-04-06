package com.company.apelsin.entity;

import com.company.apelsin.entity.base.BaseUUIDEntity;
import com.company.apelsin.entity.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "transaction")
@Setter
@Getter
@NoArgsConstructor
public class TransactionEntity extends BaseUUIDEntity {

    private String uzcardTransId;

    @ManyToOne
    @JoinColumn(name = "from_card_id", insertable = false, updatable = false)
    private CardEntity fromCard;

    @ManyToOne
    @JoinColumn(name = "to_card_id", insertable = false, updatable = false)
    private CardEntity toCard;

    @Column(name = "from_card_id")
    private Long fromCardId;

    @Column(name = "to_card_id")
    private Long toCardId;

    private Long amount;

    private TransactionType type;


}
