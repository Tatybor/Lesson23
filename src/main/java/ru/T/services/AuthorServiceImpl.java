package ru.IT.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.IT.DTO.AuthorDTO;
import ru.IT.DTO.BookDTO;
import ru.IT.entity.Author;
import ru.IT.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List <Author> authors = authorRepository.findAll();
        return authors.stream().map(this::convertAuthorToDTO).collect(Collectors.toList());
       //return authors.stream().map(author -> convertAuthorToDTO (author)).toList();
    }

    public Author addNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return convertAuthorToDTO(author);
    }

    private AuthorDTO convertAuthorToDTO(Author author) {

        List<BookDTO> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDTO.builder()
                        //.genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();

        return AuthorDTO.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    @Override
    public AuthorDTO getAuthorBySurnameV1(String surname) {
        Author author = authorRepository.findAuthorBySurname(surname).orElseThrow();
        return convertAuthorToDTO(author);
    }

    @Override
    public AuthorDTO getAuthorBySurnameV2(String surname) {
        Author author = authorRepository.findAuthorBySurnameV2(surname).orElseThrow();
        return convertAuthorToDTO(author);
    }

    @Override
    public AuthorDTO getAuthorBySurnameV3(String surname) {
        Specification<Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("surname"), surname);
            }
        });
        Author author = authorRepository.findOne(specification).orElseThrow();
        return convertAuthorToDTO(author);
    }
}









