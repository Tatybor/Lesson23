package ru.IT.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.IT.entity.Author;
import ru.IT.services.AuthorServiceImpl;

@RestController
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorServiceImpl authorServiceImpl;

    @GetMapping("/v2/authors")
    public ResponseEntity getAuthorBySurnameV2(@RequestParam("surname") String surname) {
        try {
            return ResponseEntity.ok(authorServiceImpl.getAuthorBySurnameV2(surname));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no such author in db");
        }
    }

    @GetMapping("/v3/authors")
    public ResponseEntity getAuthorBySurnameV3(@RequestParam("surname") String surname) {
        try {
            return ResponseEntity.ok(authorServiceImpl.getAuthorBySurnameV3(surname));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no such author in db");
        }
    }
    @PostMapping ("/authors")
    public Author addNewAuthor(@RequestBody Author author) {
        return authorServiceImpl.addNewAuthor (author);
    }
}


