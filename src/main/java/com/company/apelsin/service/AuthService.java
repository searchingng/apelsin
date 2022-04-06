package com.company.apelsin.service;

import com.company.apelsin.config.jwt.JwtUtil;
import com.company.apelsin.dto.AuthDTO;
import com.company.apelsin.dto.ProfileDTO;
import com.company.apelsin.entity.ProfileEntity;
import com.company.apelsin.entity.enums.ProfileStatus;
import com.company.apelsin.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final ProfileRepository profileRepository;

    public ProfileDTO registration(ProfileDTO dto){

        if (profileRepository.existsByPhone(dto.getPhone())){
            log.warn("Phone is already registrated");
            throw new RuntimeException("Phone is already registrated");
        }

        String password = DigestUtils.md5Hex(dto.getPassword());
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String password = encoder.encode(dto.getPassword());

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPhone(dto.getPhone());
        entity.setLogin(dto.getLogin());
        entity.setPassword(password);

        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole("USER_ROLE");

        profileRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setPassword(password);
        log.debug("New User signed up: {}", dto);
        return dto;
    }

    public ProfileDTO login(AuthDTO dto){

        String password = DigestUtils.md5Hex(dto.getPassword());

        ProfileEntity profile = profileRepository
                .findByLoginAndPassword(dto.getLogin(), password)
                .orElseThrow(() -> new UsernameNotFoundException("Login or Password is not valid."));

        String jwt = JwtUtil.generateJwt(profile.getId(), profile.getLogin());

        ProfileDTO profileDTO = new ProfileDTO();
                profileDTO.setName(profile.getName());
                profileDTO.setSurname(profile.getSurname());
                profileDTO.setPhone(profile.getMiddleName());
                profileDTO.setLogin(profile.getLogin());
                profileDTO.setJwt(jwt);

        log.debug("One user signed in: {}", profile);
        return profileDTO;

    }

}
