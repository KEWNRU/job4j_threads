package ru.job4j.ref;

public class User {
    private final String name;

    public static User of(String name) {
        return new User(name);
    }

    public String getName() {
        return name;
    }

    public User(String name) {
        this.name = name;
    }

}
