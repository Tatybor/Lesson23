package ru.IT.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.IT.DTO.AuthorDTO;
import ru.IT.DTO.BookDTO;
import ru.IT.entity.Book;
import ru.IT.entity.Genre;
import ru.IT.repository.BookRepository;
import ru.IT.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public Book addNewBook(Book book) {
        return bookRepository.save(book);
    }

    public Book addNewBook(BookDTO bookDTO) {
        Genre newBookGenre = genreRepository.getReferenceById(bookDTO.getId());
        Book newBook = new Book();
        newBook.setName(bookDTO.getName());
        newBook.setGenre(newBookGenre);
        return bookRepository.save(newBook);
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return convertToDTO(book);
    }

    private BookDTO convertToDTO(Book book) {

        List<AuthorDTO> authorDtoList = book.getAuthors()
                .stream()
                .map(author -> AuthorDTO.builder()
                        .surname(author.getSurname())
                        .name(author.getName())
                        .id(author.getId())
                        .build()
                ).toList();

        return BookDTO.builder()
                .authors(authorDtoList)
                .id(book.getId())
                .name(book.getName())
                //.genre(book.getGenre().getName())
                .build();
    }

    public Book updateBook(BookDTO bookDTO) {
        Book book = bookRepository.getById(bookDTO.getId());
        Optional.ofNullable(bookDTO.getName()).ifPresent(book::setName);
        return bookRepository.save(book);


    }

    public String deleteBook(Long id) {
        Book book = bookRepository.getById(id);
        try {
            bookRepository.delete(book);
            return "book with id " + id + " was successfully deleted";
        } catch (Exception e) {
            return "book with id " + id + " couldn't be deleted";
        }
    }
}
