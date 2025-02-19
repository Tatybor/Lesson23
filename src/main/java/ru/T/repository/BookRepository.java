package ru.IT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.IT.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
