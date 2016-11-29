package com.thoughtworks.ketsu.domain;

import java.util.Optional;

public interface Cards {
    Optional<Card> getCardById(long id);
}
