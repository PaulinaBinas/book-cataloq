package com.pbinas.books.unitTests;

import com.pbinas.books.config.TestConfig;
import com.pbinas.books.model.entity.AuthorEntity;
import com.pbinas.books.model.entity.BookEntity;
import com.pbinas.books.model.entity.BookListEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class BookUnitTest extends AbstractUnitTest {

    @Test
    public void findAllBooks() {
        /* arrange */
        List<BookEntity> books = new ArrayList<>();

        when(this.testBookRepository.findAll()).thenReturn(books);

        /* act */
        List<BookEntity> foundBooks = this.testBookService.findAll();

        /* assert */
        assertThat(foundBooks).isEqualTo(books);
    }

    @Test
    public void findBookById() {
        /* arrange */
        long id = 11L;
        BookEntity book = new BookEntity();

        when(this.testBookRepository.findDistinctById(id)).thenReturn(book);

        /* act */
        BookEntity foundBook = this.testBookService.findById(id);

        /* assert */
        verify(this.testBookRepository).findDistinctById(longThat(i -> i == id));
        assertThat(foundBook).isEqualTo(book);
    }

    @Test
    public void addBook() {
        /* arrange */
        BookEntity book = new BookEntity();
        AuthorEntity author = new AuthorEntity();
        book.setAuthors(Collections.singletonList(author));

        /* act */
        this.testBookService.addBook(book);

        /* assert */
        verify(this.testBookRepository).save(argThat(b -> b.equals(book)));
        verify(this.testAuthorRepository).save(argThat(a -> a.equals(author)));
    }

    @Test
    public void removeBook() {
        /* arrange */
        long id = 11L;
        BookEntity book = new BookEntity();
        book.setId(id);
        List<BookListEntity> lists = new ArrayList<>();
        BookListEntity bookList = new BookListEntity();
        List<BookEntity> books = new ArrayList<>();
        books.add(book);
        bookList.setBooks(books);
        lists.add(bookList);

        when(this.testBookListRepository.findAll()).thenReturn(lists);

        /* act */
        this.testBookService.removeBook(id);

        /* assert */
        verify(this.testBookRepository).deleteById(longThat(bookId -> bookId == id));
        verify(this.testBookListRepository).save(argThat(bl -> bl.getBooks().isEmpty()));
    }

    @Test
    public void modifyBook() {
        /* arrange */
        BookEntity oldBook = new BookEntity();
        oldBook.setId(11L);
        oldBook.setTitle("old title");
        oldBook.setDescription("old description");
        oldBook.setLanguage("en");
        BookEntity newBook = new BookEntity();
        newBook.setTitle("new title");
        newBook.setDescription("new description");
        newBook.setLanguage("de");
        newBook.setIsbn(971111111L);

        when(this.testBookRepository.findDistinctById(oldBook.getId())).thenReturn(oldBook);

        /* act */
        this.testBookService.modifyBook(newBook);

        /* assert */
        verify(this.testBookRepository).save(argThat(b -> b.equals(newBook)));
    }

    @Test
    public void removeListFromBook() {
        /* arrange */
        long listId = 11L;
        long bookId = 12L;

        BookEntity book = new BookEntity();
        BookListEntity bookList = new BookListEntity();
        bookList.setId(listId);
        List<BookListEntity> lists = new ArrayList<>();
        lists.add(bookList);
        book.setLists(lists);

        when(this.testBookRepository.findDistinctById(bookId)).thenReturn(book);

        /* act */
        this.testBookService.removeList(bookId, listId);

        /* assert */
        verify(this.testBookRepository).save(argThat(b -> b.getLists().isEmpty()));
    }

    @Test
    public void removeListFromAllBooks() {
        /* arrange */
        long listId = 11L;
        List<BookEntity> books = new ArrayList<>();
        BookEntity book1 = new BookEntity();
        BookEntity book2 = new BookEntity();
        BookListEntity bookList = new BookListEntity();
        bookList.setId(listId);
        List<BookListEntity> bookLists = new ArrayList<>();
        bookLists.add(bookList);
        book1.setLists(Collections.emptyList());
        book2.setLists(bookLists);
        books.add(book1);
        books.add(book2);

        when(this.testBookRepository.findAll()).thenReturn(books);

        /* act */
        this.testBookService.removeList(listId);

        /* assert */
        verify(this.testBookRepository, times(2)).save(argThat(book -> !book.getLists().contains(bookList)));
    }

    @Test
    public void addListToBook() {
        /* arrange */
        long bookId = 11L;
        BookEntity book = new BookEntity();
        book.setLists(new ArrayList<>());
        BookListEntity bookList = new BookListEntity();

        when(this.testBookRepository.findDistinctById(bookId)).thenReturn(book);

        /* act */
        this.testBookService.addList(bookId, bookList);

        /* assert */
        verify(this.testBookRepository).save(argThat(b -> b.getLists().contains(bookList)));
    }
}
