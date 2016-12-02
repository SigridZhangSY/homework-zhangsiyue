package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.Users;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(DatabaseTestRunner.class)
public class MyBatisUserRepositoryTest {
    @Inject
    Users userRepository;

    @Test
    public void should_create_and_get_user() throws Exception {
        Optional<User> user = userRepository.findById(1);

        assertThat(user.isPresent(), is(true));
    }
}
