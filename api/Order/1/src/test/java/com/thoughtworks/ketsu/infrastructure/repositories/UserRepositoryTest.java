package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.Users;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(DatabaseTestRunner.class)
public class UserRepositoryTest {
    @Inject
    Users userRepository;

    @Test
    public void should_find_user() throws Exception {
        Optional<User> user = userRepository.findById(1);

        assertThat(user.isPresent(), is(true));
    }

    @Test
    public void should_save_and_find_user() throws Exception {
        User user = userRepository.createUser(TestHelper.userMap("aaa@www.com", "pass"));

        assertThat(user.getEmail().contains("aaa@www.com"), is(true));
    }

    @Test
    public void should_find_user_by_email() throws Exception {
        Optional<User> user = userRepository.findBy("admin@example.com");

        assertThat(user.isPresent(), is(true));
    }
}
