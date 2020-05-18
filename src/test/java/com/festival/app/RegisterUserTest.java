package com.festival.app;

import com.festival.app.model.ApplicationUser;
import com.festival.app.repository.ApplicationUserRepository;
import com.festival.app.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RegisterUserTest {

    @Autowired
    private ApplicationUserRepository userRepo;

    @Test
    void savedUserDoesSave() {
        ApplicationUser user = new ApplicationUser();
        user.setUsername("testUser");
        user.setPassword("123456");
        ApplicationUser savedUser = userRepo.save(user);
        assertThat(user == savedUser);
    }

}
