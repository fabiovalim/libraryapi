package com.fabio.libraryapi.repository;

import com.fabio.libraryapi.model.Author;
import com.fabio.libraryapi.model.Book;
import com.fabio.libraryapi.model.BookGender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void save() {
        Author author = new Author();
        author.setName("FERNANDO HENRIQUE CARDOSO");
        author.setBirthday(LocalDate.of(1943, 6, 29));
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

    @Test
    public void getAllNames() {
        List<Author> list = authorRepository.findAll();
        for(Author author : list) {
            System.out.println("NOME: " + author.getName());
        }
    }

    @Test
    public void count() {
        System.out.println("QUANTIDADE DE AUTORES: " + authorRepository.count());
    }

    @Test
    public void deleteById() {
        var id = UUID.fromString("488ee3e4-7189-46b2-a160-012ff37f9f43");
        authorRepository.deleteById(id);
    }

    @Test
    public void saveAuthorWithBooks() {
        Author author = new Author();
        author.setName("GABRIEL PEREIRA DA SILVA");
        author.setBirthday(LocalDate.of(1994, 5, 28));
        author.setNationality("Brasileiro");

        Book book1 = new Book();
        book1.setTitle("RISADA NOSSA DE CADA DIA");
        book1.setIsbn("33948-20394");
        book1.setPrice(BigDecimal.valueOf(99.90));
        book1.setGender(BookGender.COMEDIA);
        book1.setPublicationDate(LocalDate.of(2015, 2,11));
        book1.setAuthor(author);

        Book book2 = new Book();
        book2.setTitle("O ÚLTIMO VOO");
        book2.setIsbn("23213-94985");
        book2.setPrice(BigDecimal.valueOf(34.50));
        book2.setGender(BookGender.FICCAO);
        book2.setPublicationDate(LocalDate.of(2021, 10,11));
        book2.setAuthor(author);

        author.setBooks(new ArrayList<>());
        author.getBooks().add(book1);
        author.getBooks().add(book2);

        authorRepository.save(author);
        bookRepository.saveAll(author.getBooks());

        System.out.println("LISTA DE LIVROS DO AUTOR '" + author.getName() + "':");
        for(Book b : author.getBooks()) System.out.println(b.getTitle());
    }
}
