package com.pbinas.books.unitTests;

import com.pbinas.books.config.TestConfig;
import com.pbinas.books.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class UserUnitTest extends AbstractUnitTest {

    @Test
    public void addNewUserTest() {
        /* arrange */
        String username = "Long name for new user";
        String password = "p@sSw0rd";

        when(this.testUserRepository.save(any(UserEntity.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        /* act */
        UserEntity user = this.testUserService.addUser(username, password);

        /* assert */
        verify(this.testUserRepository).save(any(UserEntity.class));
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    public void getUserById() {
        /* arrange */
        long id = 11L;
        when(this.testUserRepository.findDistinctById(id)).thenReturn(new UserEntity());

        /* act */
        UserEntity user = this.testUserService.getUser(id);

        /* assert */
        verify(this.testUserRepository).findDistinctById(longThat(userId -> userId == id));
        assertThat(user).isNotNull();
    }

    @Test
    public void getUserByUsername() {
        /* arrange */
        String username = "Username123";
        UserEntity testUser = new UserEntity();
        when(this.testUserRepository.findUserEntityByUsername(username)).thenReturn(testUser);

        /* act */
        UserEntity user = this.testUserService.getUser(username);

        /* assert */
        verify(this.testUserRepository).findUserEntityByUsername(argThat(name -> username.equals(name)));
        assertThat(user).isNotNull();
    }

    @Test
    public void getAllUsers() {
        /* arrange */
        when(this.testUserRepository.findAll()).thenReturn(Collections.singletonList(new UserEntity()));

        /* act */
        List<UserEntity> users = this.testUserService.getAllUsers();

        /* assert */
        verify(this.testUserRepository).findAll();
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void saveUser() {
        /* arrange */
        UserEntity user = new UserEntity();

        /* act */
        this.testUserService.saveUser(user);

        /* assert */
        verify(this.testUserRepository).save(argThat(u -> u == user));
    }
}
