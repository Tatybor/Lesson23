package ru.IT.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.IT.DTO.AuthorDTO;
import ru.IT.DTO.BookDTO;
import ru.IT.DTO.UserOfLibraryDTO;
import ru.IT.entity.Author;
import ru.IT.entity.UserOfLibrary;
import ru.IT.repository.AuthorRepository;
import ru.IT.repository.UserOfLibraryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserOfLibraryServiceImpl implements UserOfLibraryService {
    @Autowired
    UserOfLibraryRepository userOfLibraryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

public UserOfLibraryDTO addNewUser(UserOfLibrary userOfLibrary){
    String passwordEncode = passwordEncoder.encode(userOfLibrary.getPassword());
    userOfLibrary.setPassword(passwordEncode);
    UserOfLibrary newUserOfLibrary = userOfLibraryRepository.save(userOfLibrary);
    UserOfLibraryDTO userOfLibraryDTO = new ObjectMapper(). convertValue(newUserOfLibrary, UserOfLibraryDTO.class);
    return userOfLibraryDTO;
}

















    @Override
    public UserOfLibraryDTO getUserOfLibraryByEmail(String email) {
        UserOfLibrary userOfLibrary = userOfLibraryRepository.findUserOfLibraryByEmail(email);
        return new ObjectMapper().convertValue(userOfLibrary, UserOfLibraryDTO.class);
    }

}










