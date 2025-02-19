package ru.IT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.IT.entity.Book;
import ru.IT.entity.UserOfLibrary;

public interface UserOfLibraryRepository extends JpaRepository<UserOfLibrary, Long> {
public UserOfLibrary findUserOfLibraryByEmail(String email);
}
