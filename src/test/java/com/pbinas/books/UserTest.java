package com.pbinas.books;

import com.pbinas.books.model.entity.UserEntity;
import com.pbinas.books.repository.UserRepository;
import com.pbinas.books.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserTest extends AbstractTest {

    @Test
    public void addNewUserTest() {
        /* arrange */
        String username = "Long name for new user";
        String password = "p@sSw0rd";

        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);

        /* act */
        this.userService.addUser(username, password);

        /* assert */
        Mockito.verify(this.userRepository, Mockito.times(1)).save(captor.capture());
        assertThat(captor.getValue()).isNotNull();
        assertThat(captor.getValue().getUsername()).isEqualTo(username);
        assertThat(captor.getValue().getPassword()).isEqualTo(password);
    }
}
