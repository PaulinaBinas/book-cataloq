package com.pbinas.books.integrationTests;

import com.pbinas.books.config.TestConfig;
import com.pbinas.books.model.entity.BookEntity;
import com.pbinas.books.model.entity.BookListEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Long.parseLong;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@WebAppConfiguration
public class BookListIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void getAllBookLists() throws Exception {
        BookListEntity list = new BookListEntity();
        List<BookListEntity> lists = Collections.singletonList(list);

        MvcResult result = this.mockMvc.perform(get("/list/all")
        ).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void addBookList() throws Exception {
        this.mockMvc.perform(post("/list")
                .contentType("application/json")
                .content("{\n" +
                        "\t\"title\": \"Books to read\",\n" +
                        "\t\"description\": \"All the books I want to read later\",\n" +
                        "\t\"books\": []\n" +
                        "}"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        verify(this.testBookListRepository)
                .save(argThat(list -> list.getTitle().equals("Books to read")
                        && list.getDescription().equals("All the books I want to read later")));
    }

    @Test
    public void getBookList() throws Exception {
        String title = "test title";
        String desc = "test description";
        long id = 11L;
        BookListEntity bookList = new BookListEntity();
        bookList.setTitle(title);
        bookList.setDescription(desc);
        bookList.setId(id);

        when(this.testBookListRepository.findDistinctById(id)).thenReturn(bookList);

        this.mockMvc.perform(get("/list").param("id", String.valueOf(id))
        ).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.description").value(desc))
                .andExpect(jsonPath("$.id").value(String.valueOf(id)));
    }

    @Test
    public void removeBookList() throws Exception {
        long id = 11L;

        this.mockMvc.perform(delete("/list").param("id", String.valueOf(id)))
                .andDo(print()).andExpect(status().isOk());

        verify(this.testBookListRepository).deleteById(longThat(i -> i == id));
    }

    @Test
    public void renameBookList() throws Exception {
        long listId = 11L;
        String newTitle = "new title";

        when(this.testBookListRepository.findDistinctById(listId)).thenReturn(new BookListEntity());

        this.mockMvc.perform(patch("/list").param("id", String.valueOf(listId))
                .param("newName", newTitle))
                .andDo(print()).andExpect(status().isOk());

        verify(this.testBookListRepository).save(argThat(list -> list.getTitle().equals(newTitle)));
    }

    @Test
    public void addBookToBookList() throws Exception {
        long bookId = 11L;
        long listId = 12L;
        BookListEntity bookList = new BookListEntity();
        BookEntity bookToAdd = new BookEntity();
        bookToAdd.setId(bookId);
        bookList.setId(listId);
        bookList.setBooks(new ArrayList<>());
        bookToAdd.setLists(new ArrayList<>());

        when(this.testBookListRepository.findDistinctById(listId)).thenReturn(bookList);
        when(this.testBookRepository.findDistinctById(bookId)).thenReturn(bookToAdd);

        this.mockMvc.perform(post("/list/" + listId + "/addBook/" + bookId))
                .andDo(print()).andExpect(status().isOk());

        verify(this.testBookListRepository).save(argThat(list -> list.getBooks().stream().anyMatch(book -> book.getId() == bookId)));
        verify(this.testBookRepository).save(argThat(book -> book.getLists().stream().anyMatch(list -> list.getId() == listId)));
    }

    @Test
    public void removeBookFromBookList() throws Exception {
        long bookId = 11L;
        long listId = 12L;

        BookListEntity bookList = new BookListEntity();
        BookEntity book = new BookEntity();
        bookList.setBooks(new ArrayList<>());
        bookList.getBooks().add(book);
        book.setLists(new ArrayList<>());

        when(this.testBookListRepository.findDistinctById(listId)).thenReturn(bookList);
        when(this.testBookRepository.findDistinctById(bookId)).thenReturn(book);

        this.mockMvc.perform(delete("/list/" + listId + "/removeBook/" + bookId))
                .andDo(print()).andExpect(status().isOk());

        verify(this.testBookListRepository).save(argThat(list -> list.getBooks().stream().noneMatch(b -> b.getId() == bookId)));
    }
}
