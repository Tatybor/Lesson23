package ru.IT.services;

import ru.IT.DTO.AuthorDTO;
import ru.IT.DTO.UserOfLibraryDTO;

import java.util.List;

public interface UserOfLibraryService {
    public UserOfLibraryDTO getUserOfLibraryByEmail (String email);

}
