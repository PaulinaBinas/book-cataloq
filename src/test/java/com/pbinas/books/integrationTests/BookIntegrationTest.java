package com.pbinas.books.integrationTests;

import com.pbinas.books.config.TestConfig;
import com.pbinas.books.model.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@WebAppConfiguration
public class BookIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void addBook() throws Exception {
        this.mockMvc.perform(post("/book")
                .contentType("application/json").content("{\n" +
                        "\t\"title\":\"ajax\",\n" +
                        "\t\"authors\":\n" +
                        "\t\t[\n" +
                        "\t\t\t{\n" +
                        "\t\t\t\t\"name\":\"aa\",\n" +
                        "\t\t\t\t\"surname\":\"vvvv\"\n" +
                        "\t\t\t}\n" +
                        "\t\t],\n" +
                        "\t\"description\":\"no\",\n" +
                        "\t\"isbn\":123456789,\n" +
                        "\t\"language\":\"de\",\n" +
                        "\t\"publicationYear\":1999,\n" +
                        "\t\"publisher\":\"me\"\n" +
                        "\t\n" +
                        "}"))
                .andDo(print()).andExpect(status().isOk());

        verify(this.testBookRepository).save(any());
    }

    @Test
    public void removeBook() throws Exception {
        this.mockMvc.perform(delete("/book").param("id", "11")
        ).andDo(print()).andExpect(status().isOk());

        verify(this.testBookRepository).deleteById(11L);
    }

    @Test
    public void getBook() throws Exception {
        String title = "test";
        BookEntity book = new BookEntity();
        book.setTitle(title);
        when(this.testBookRepository.findDistinctById(11L)).thenReturn(book);

        this.mockMvc.perform(get("/book").param("id", "11")
        ).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.title").value(title)).andReturn();
    }

    @Test
    public void getAllBooks() throws Exception {
        String title = "test";
        BookEntity book = new BookEntity();
        book.setTitle(title);
        List<BookEntity> books = Collections.singletonList(book);
        when(this.testBookRepository.findAll()).thenReturn(books);

        MvcResult result = this.mockMvc.perform(get("/book/all")
        ).andDo(print()).andExpect(status().isOk()).andReturn();

        assertThat(result).isNotNull();
        assertThat(result.getResponse().getContentAsString()).contains("\"title\":\"" + title +"\"");
    }

    @Test
    public void modifyBook() throws Exception {
        this.mockMvc.perform(put("/book/modify")
                .contentType("application/json").content("{\n" +
                "\t\"title\":\"modify\",\n" +
                "\t\"authors\":\n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\":\"aa\",\n" +
                "\t\t\t\t\"surname\":\"vvvv\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\"description\":\"no\",\n" +
                "\t\"isbn\":123456789,\n" +
                "\t\"language\":\"de\",\n" +
                "\t\"publicationYear\":1999,\n" +
                "\t\"publisher\":\"me\"\n" +
                "\t\n" +
                "}")).andDo(print()).andExpect(status().isOk());

        verify(this.testBookRepository).save(argThat(book -> book.getTitle().equals("modify")));
    }
}
