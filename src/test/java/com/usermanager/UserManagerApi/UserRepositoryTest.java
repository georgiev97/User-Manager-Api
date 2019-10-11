package com.usermanager.UserManagerApi;

import com.usermanager.UserManagerApi.model.user.User;
import com.usermanager.UserManagerApi.repository.user.UserRepository;
import com.usermanager.UserManagerApi.service.user.UserService;
import com.usermanager.UserManagerApi.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User user = new User();
        user.setEmail("test@mail.com");

        userRepository.save(user);
        Mockito.when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(user);
    }

    @Test
    public void whenValidEmail_thenUserShouldBeFound() {
        String email = "test@mail.com";

        User found = userRepository.findByEmail(email);

        assertThat(found.getEmail())
                .isEqualTo(email);
    }
}
