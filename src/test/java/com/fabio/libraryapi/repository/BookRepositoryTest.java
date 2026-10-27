package com.fabio.libraryapi.repository;

import com.fabio.libraryapi.model.Author;
import com.fabio.libraryapi.model.Book;
import com.fabio.libraryapi.model.BookGender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void save() {
        Book book = new Book();
        book.setTitle("SOL NASCENTE");
        book.setIsbn("32892-99873");
        book.setPrice(BigDecimal.valueOf(149.90));
        book.setGender(BookGender.ROMANCE);
        book.setPublicationDate(LocalDate.of(1998, 7,25));

        Author author = authorRepository
                .findById(UUID.fromString("316d39e5-f6a6-40f4-855d-912e405ff8d7"))
                .orElse(null);

        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Test
    public void cascadeSave() {
        Book book = new Book();
        book.setTitle("UMA NOITE FRIA");
        book.setIsbn("11002-33443");
        book.setPrice(BigDecimal.valueOf(120));
        book.setGender(BookGender.AVENTURA);
        book.setPublicationDate(LocalDate.of(2007, 10,12));

        Author author = new Author();
        author.setName("CAMILA DE MORAES GUTIERREZ");
        author.setBirthday(LocalDate.of(2001, 4, 12));
        author.setNationality("Brasileira");

        book.setAuthor(author);
        bookRepository.save(book);
    }
}