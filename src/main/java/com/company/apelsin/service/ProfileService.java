package com.company.apelsin.service;

import com.company.apelsin.dto.ProfileDTO;
import com.company.apelsin.entity.ProfileEntity;
import com.company.apelsin.entity.enums.ProfileStatus;
import com.company.apelsin.repository.ProfileRepository;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileDTO toDto(ProfileEntity entity){
        if (entity == null)
            return null;

        ProfileDTO dto = new ProfileDTO();

        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setMiddleName(entity.getMiddleName());
        dto.setBirthDate(entity.getBirthDate());
        dto.setPhone(entity.getPhone());
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }

    public ProfileDTO create(ProfileDTO dto){

        if (profileRepository.existsByPhone(dto.getPhone())){
            log.warn("Phone is already exists");
            throw new RuntimeException("Phone is already exists");
        }

        String password = DigestUtils.md5Hex(dto.getPassword());
//        String password = dto.getPassword();

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPhone(dto.getPhone());
        entity.setLogin(dto.getLogin());
        entity.setPassword(password);

        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());

        profileRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setPassword(password);
        log.debug("New User added: {}", dto);
        return dto;
    }

    public ProfileEntity get(Long id){
        if (id == null){
            log.debug("get() profileId is null");
            return null;
        }
        log.debug("get profile that id = {}", id);
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile Not Found"));
    }

    public List<ProfileDTO> getAll(){
        return profileRepository.findAll().stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    public ProfileDTO getById(Long id){
        return toDto(get(id));
    }

    public void deleteById(Long id) {
        profileRepository.deleteById(id);
    }

    public void updateById(ProfileDTO dto, Long id){
        ProfileEntity entity = get(id);

        if (dto.getName() != null){
            entity.setName(dto.getName());
        }

        if (dto.getSurname() != null){
            entity.setSurname(dto.getSurname());
        }

        if (dto.getMiddleName() != null){
            entity.setMiddleName(dto.getMiddleName());
        }

        if (dto.getBirthDate() != null){
            entity.setBirthDate(dto.getBirthDate());
        }

        if (dto.getPhone() != null){
            entity.setPhone(dto.getPhone());
        }

        if (dto.getLogin() != null){
            entity.setLogin(dto.getLogin());
        }

        if (dto.getPassword() != null){
            entity.setPassword(dto.getPassword());
        }

        profileRepository.save(entity);
    }

}
