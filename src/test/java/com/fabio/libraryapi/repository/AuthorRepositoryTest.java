package com.fabio.libraryapi.repository;

import com.fabio.libraryapi.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void save() {
        Author author = new Author();
        author.setName("LUCAS EMANUEL SANTOS MAIA");
        author.setBirthday(LocalDate.of(1967, 12, 7));
        author.setNationality("Brasileiro");
        var savedAuthor = authorRepository.save(author);
        System.out.println("ID do autor: " + savedAuthor.getId());
    }

    @Test
    public void update() {
        var id = UUID.fromString("dd5ee714-1e87-4179-aeb1-165fb99ce73e");
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            Author authorFinded = author.get();
            System.out.println("NOME: " + authorFinded.getName());
            authorFinded.setBirthday(LocalDate.of(1500,1,1));
            authorRepository.save(authorFinded);
        }
    }
}
