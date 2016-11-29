package com.thoughtworks.ketsu.domain.card;

import java.util.Optional;

public interface Cards {
    Optional<Card> getCardById(long id);
}
