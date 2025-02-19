package ru.IT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.IT.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
