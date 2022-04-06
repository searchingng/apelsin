package com.company.apelsin.config;

import com.company.apelsin.config.detail.CustomUserDetails;
import com.company.apelsin.entity.ProfileEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static ProfileEntity getCurrentUser(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getProfile();
    }

}
