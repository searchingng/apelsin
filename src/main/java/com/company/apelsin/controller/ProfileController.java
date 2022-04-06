package com.company.apelsin.controller;

import com.company.apelsin.config.SecurityUtil;
import com.company.apelsin.config.detail.CustomUserDetails;
import com.company.apelsin.dto.ProfileDTO;
import com.company.apelsin.entity.ProfileEntity;
import com.company.apelsin.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO dto){

        dto = profileService.create(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        ProfileEntity profile = userDetails.getProfile();
        return ResponseEntity.ok(profileService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable Long id,
                                              @AuthenticationPrincipal final CustomUserDetails userDetails){

        return ResponseEntity.ok(profileService.getById(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteById(Long id) {

        profileService.deleteById(id);
        return ResponseEntity.ok("Successfully DELETED");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@RequestBody ProfileDTO dto,
                                             @PathVariable Long id){

        ProfileEntity currentUser = SecurityUtil.getCurrentUser();

        profileService.updateById(dto, id);
        return ResponseEntity.ok("Successfully Saved");

    }

}
