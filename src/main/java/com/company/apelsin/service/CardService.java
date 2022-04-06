package com.company.apelsin.service;

import com.company.apelsin.config.SecurityUtil;
import com.company.apelsin.dto.CardDTO;
import com.company.apelsin.dto.ProfileDTO;
import com.company.apelsin.entity.CardEntity;
import com.company.apelsin.entity.ProfileEntity;
import com.company.apelsin.repository.CardRepository;
import com.company.apelsin.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class CardService {

    private final CardRepository cardRepository;
    private final ProfileService profileService;

    @Value("${uzcard.server.url}")
    private String uzcardUrl;

    public CardService(CardRepository cardRepository, ProfileService profileService) {
        this.cardRepository = cardRepository;
        this.profileService = profileService;
    }

    public CardDTO create(CardDTO dto){
        CardEntity entity = new CardEntity();
        LocalDate expDate = LocalDate.parse(dto.getExpDate() + "/01",
                DateTimeFormatter.ofPattern("MM/yy/dd"));

        Long balance = dto.getBalance() == null ? 0L : dto.getBalance();

        entity.setNumber(dto.getNumber());
        entity.setExpDate(expDate);
        entity.setBalance(balance);
        entity.setProfileId(dto.getProfileId());

        cardRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public CardDTO addCard(CardDTO dto){

        CardDTO card = HttpUtil.post(uzcardUrl + "/card/app/get", dto, CardDTO.class);

        if (card == null){
            throw new RuntimeException("This card is not belong you");
        }

        Long profileId = card.getProfileId();
        ProfileDTO profileDTO = HttpUtil
                .get(uzcardUrl + "/profile/" + profileId, ProfileDTO.class);

        ProfileEntity currentUser = SecurityUtil.getCurrentUser();

        if (profileDTO == null || !profileDTO.getPhone().equals(currentUser.getPhone())){
            throw new RuntimeException("This card is not belong you");
        }
        // SMS sending
        dto.setProfileId(currentUser.getId());
        return create(dto);
    }

    private CardDTO toDto(CardEntity entity) {
        if (entity == null)
            return null;
        CardDTO dto = new CardDTO();
        dto.setExpDate(entity.getExpDate()
                .format(DateTimeFormatter.ofPattern("MM/yy")));

        dto.setNumber(entity.getNumber());
        dto.setBalance(entity.getBalance());
        dto.setProfileId(entity.getProfileId());
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }

}
