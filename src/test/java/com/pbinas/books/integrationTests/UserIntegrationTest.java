package com.pbinas.books.integrationTests;

import com.pbinas.books.config.TestConfig;
import com.pbinas.books.model.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@WebAppConfiguration
public class UserIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void addUser() throws Exception {
        this.mockMvc.perform(post("/user")
                .param("username", "integrationTestUser")
                .param("password","p@sSw0rd")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getUser() throws Exception {
        when(this.testUserRepository.findDistinctById(11L)).thenReturn(new UserEntity());

        this.mockMvc.perform(get("/user")
                .param("id", "11")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllUsers() throws Exception {
        List<UserEntity> users = Collections.singletonList(new UserEntity());
        when(this.testUserRepository.findAll()).thenReturn(users);

        this.mockMvc.perform(get("/user/all")).andDo(print())
                .andExpect(status().isOk());
    }
}
