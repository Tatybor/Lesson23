package ru.IT.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.IT.DTO.AuthorDTO;
import ru.IT.DTO.BookDTO;
import ru.IT.DTO.GenreDTO;
import ru.IT.entity.Genre;
import ru.IT.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public Genre addNewGenre(Genre genre) {
        return genreRepository.save(genre);
    }

   @Override
    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDTO(genre);
    }

    private GenreDTO convertToDTO(Genre genre) {
        List<BookDTO> bookDtoList = genre.getBooks()
                .stream()
                .map(book -> BookDTO.builder()
                        //.genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .authors(book.getAuthors().stream()  // dob avt
                                .map(author -> AuthorDTO.builder()
                                        .id(author.getId())
                                        .name(author.getName())
                                        .surname(author.getSurname())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return GenreDTO.builder()
                .books(bookDtoList)
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

}
