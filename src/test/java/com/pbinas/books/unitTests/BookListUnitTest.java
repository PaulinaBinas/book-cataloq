package com.pbinas.books.unitTests;

import com.pbinas.books.config.TestConfig;
import com.pbinas.books.model.entity.AuthorEntity;
import com.pbinas.books.model.entity.BookEntity;
import com.pbinas.books.model.entity.BookListEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class BookListUnitTest extends AbstractUnitTest {

    @Test
    public void saveList() {
        /* arrange */
        String title = "Title";
        String description = "This is a description of a book list.";
        BookListEntity list = new BookListEntity();
        list.setTitle(title);
        list.setDescription(description);

        when(this.testBookListRepository.save(any(BookListEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        /* act */
        this.testBookListService.saveList(list);

        /* assert */
        verify(this.testBookListRepository).save(argThat(l -> l == list));
    }

    @Test
    public void removeList() {
        /* arrange */
        long id = 11L;
        BookListEntity list = new BookListEntity();
        list.setId(id);
        BookEntity book1 = new BookEntity();
        book1.setLists(new ArrayList<>());
        book1.getLists().add(list);
        BookEntity book2 = new BookEntity();
        book2.setLists(new ArrayList<>());
        List<BookEntity> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        when(this.testBookRepository.findAll()).thenReturn(books);

        /* act */
        this.testBookListService.removeList(id);

        /* assert */
        verify(this.testBookRepository, atLeastOnce()).save(argThat(book -> book.equals(book)));
        verify(this.testBookListRepository).deleteById(longThat(i -> i == id));
    }

    @Test
    public void addBookToList() {
        /* arrange */
        long bookId = 11L;
        long listId = 12L;
        AuthorEntity author = new AuthorEntity();
        List<AuthorEntity> authors = new ArrayList<>();
        authors.add(author);
        BookEntity book = new BookEntity();
        book.setLists(new ArrayList<>());
        book.setAuthors(authors);
        BookListEntity bookList = new BookListEntity();
        bookList.setBooks(new ArrayList<>());

        when(this.testBookRepository.findDistinctById(bookId)).thenReturn(book);
        when(this.testBookListRepository.findDistinctById(listId)).thenReturn(bookList);

        /* act */
        this.testBookListService.addBookToList(bookId, listId);

        /* assert */
        verify(this.testBookListRepository).save(argThat(list -> list.getBooks().contains(book)));
        verify(this.testBookRepository).save(argThat(b -> b.getLists().contains(bookList)));
    }

    @Test
    public void getBookList() {
        /* arrange */
        long id = 11L;
        BookListEntity list = new BookListEntity();

        when(this.testBookListRepository.findDistinctById(id)).thenReturn(list);

        /* act */
        BookListEntity bookList = this.testBookListService.getBookList(id);

        /* assert */
        verify(this.testBookListRepository).findDistinctById(longThat(l -> l == id));
        assertThat(bookList).isEqualTo(list);
    }

    @Test
    public void getAllBookLists() {
        /* arrange */
        BookListEntity bookList = new BookListEntity();
        List<BookListEntity> lists = Collections.singletonList(bookList);
        when(this.testBookListRepository.findAll()).thenReturn(lists);

        /* act */
        List<BookListEntity> bookLists = this.testBookListService.getAllBookLists();

        /* assert */
        verify(this.testBookListRepository).findAll();
        assertThat(bookLists).isEqualTo(lists);
    }

    @Test
    public void removeBookFromList() {
        /* arrange */
        long bookId = 11L;
        long listId = 12L;
        List<BookEntity> books = new ArrayList<>();
        BookEntity book = new BookEntity();
        book.setId(bookId);
        books.add(book);
        BookListEntity bookList = new BookListEntity();
        bookList.setBooks(books);
        bookList.setId(listId);
        List<BookListEntity> lists = new ArrayList<>();
        lists.add(bookList);
        book.setLists(lists);

        when(this.testBookListRepository.findDistinctById(listId)).thenReturn(bookList);
        when(this.testBookRepository.findDistinctById(bookId)).thenReturn(book);

        /* act */
        this.testBookListService.removeBookFromList(bookId, listId);

        /* assert */
        verify(this.testBookListRepository).save(argThat(list -> list.getBooks().isEmpty()));
        verify(this.testBookRepository).save(argThat(b -> b.getLists().isEmpty()));
    }

    @Test
    public void renameList() {
        /* arrange */
        long id = 11L;
        String newTitle = "new Title";
        BookListEntity bookList = new BookListEntity();
        bookList.setTitle("old title");

        when(this.testBookListRepository.findDistinctById(id)).thenReturn(bookList);

        /* act */
        this.testBookListService.renameList(id, newTitle);

        /* assert */
        verify(this.testBookListRepository).save(argThat(list -> list.getTitle().equals(newTitle)));
    }

    @Test
    public void removeBook() {
        /* arrange */
        long bookId = 11L;
        List<BookListEntity> list = new ArrayList<>();
        List<BookEntity> books = new ArrayList<>();
        BookEntity book = new BookEntity();
        book.setId(bookId);
        books.add(book);
        BookListEntity bookList = new BookListEntity();
        bookList.setBooks(books);
        list.add(bookList);

        when(this.testBookListRepository.findAll()).thenReturn(list);

        /* act */
        this.testBookListService.removeBook(bookId);

        /* assert */
        verify(this.testBookListRepository).save(argThat(bl -> bl.getBooks().isEmpty()));
    }
}
