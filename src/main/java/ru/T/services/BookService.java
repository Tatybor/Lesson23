package ru.IT.services;

import ru.IT.DTO.BookDTO;

public interface BookService {
    BookDTO getBookById (Long id);
}
