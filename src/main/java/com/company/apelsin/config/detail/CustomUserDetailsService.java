package com.company.apelsin.config.detail;

import com.company.apelsin.entity.ProfileEntity;
import com.company.apelsin.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        ProfileEntity profile = profileRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Auth Exception"));

        return new CustomUserDetails(profile);
    }
}
