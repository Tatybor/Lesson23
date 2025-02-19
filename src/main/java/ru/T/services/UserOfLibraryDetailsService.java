package ru.IT.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.IT.entity.UserOfLibrary;
import ru.IT.repository.UserOfLibraryRepository;
@Service
@RequiredArgsConstructor
public class UserOfLibraryDetailsService implements UserDetailsService {
    private final UserOfLibraryRepository userOfLibraryRepository;

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        UserOfLibrary userOfLibrary = userOfLibraryRepository.findUserOfLibraryByEmail(email);
       return  org.springframework.security.core.userdetails.User.builder().username(userOfLibrary.getEmail())
                .password(userOfLibrary.getPassword())
                .roles(userOfLibrary.getRole().name())
                .build();
    }
}
