package com.example.spring_data_jpa_mysql_book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveBook() {
        // Create a new book entity
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");

        // Save the book
        Book savedBook = bookRepository.save(book);

        // Verify that the book is saved successfully
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Test Book");
        assertThat(savedBook.getAuthor()).isEqualTo("Test Author");
    }

    @Test
    public void testFindBookById() {
        // Save a book to the database
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        bookRepository.save(book);

        // Retrieve the saved book by ID
        Optional<Book> optionalBook = bookRepository.findById(book.getId());

        // Verify that the book is found
        assertThat(optionalBook).isPresent();
        Book foundBook = optionalBook.get();
        assertThat(foundBook.getTitle()).isEqualTo("Test Book");
        assertThat(foundBook.getAuthor()).isEqualTo("Test Author");
    }

    @Test
    public void testUpdateBook() {
        // Save a book to the database
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        bookRepository.save(book);

        // Update the book's title
        book.setTitle("Updated Title");
        bookRepository.save(book);

        // Retrieve the updated book
        Book updatedBook = bookRepository.findById(book.getId()).orElse(null);

        // Verify that the book's title is updated
        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    public void testDeleteBook() {
        // Save a book to the database
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        bookRepository.save(book);

        // Delete the book
        bookRepository.delete(book);

        // Verify that the book is deleted
        assertThat(bookRepository.findById(book.getId())).isEmpty();
    }
}
