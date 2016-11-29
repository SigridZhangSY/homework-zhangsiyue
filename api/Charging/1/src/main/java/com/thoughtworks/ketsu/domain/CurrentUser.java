package com.thoughtworks.ketsu.domain;

public interface CurrentUser {
    boolean isUserHimself(long id);
    boolean isAdmin();
}
