package com.company.apelsin;

import com.company.apelsin.dto.CardDTO;
import com.company.apelsin.dto.ProfileDTO;
import com.company.apelsin.entity.ProfileEntity;
import com.company.apelsin.repository.ProfileRepository;
import com.company.apelsin.service.CardService;
import com.company.apelsin.service.ProfileService;
import com.company.apelsin.util.HttpUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.checkerframework.common.value.qual.BottomVal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ApelsinApplicationTests {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CardService cardService;

    private String uzcardUrl = "http://localhost:8080";

    @Test
    void contextLoads() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "/profile/2";

        ProfileDTO profileDTO = restTemplate.getForObject(uzcardUrl + url, ProfileDTO.class);
        System.out.println(profileDTO);

    }

    @Test
    void httpTesting() {

        ProfileDTO profileDTO = HttpUtil
                .get("http:/localhost:8080/profile/2", ProfileDTO.class);

        System.out.println(profileDTO);


    }

    @Test
    void editPasswordEncoding() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        List<ProfileEntity> list = profileRepository.findAll();
        list.forEach(p -> p.setPassword(DigestUtils.md5Hex(p.getLogin())));

        profileRepository.saveAll(list);

    }

}
