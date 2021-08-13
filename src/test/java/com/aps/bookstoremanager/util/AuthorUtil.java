package com.aps.bookstoremanager.util;

import com.aps.bookstoremanager.dto.AuthorDTO;
import com.aps.bookstoremanager.entity.Author;
import com.github.javafaker.Faker;

public class AuthorUtil {
    private static final Faker FAKER = Faker.instance();

    public static AuthorDTO createAuthorDTO(){
        return AuthorDTO.builder()
                .id(FAKER.number().randomNumber())
                .name(FAKER.book().author())
                .age(FAKER.number().numberBetween(0, 100))
                .build();
    }

    public static Author createAuthor(){
        return Author.builder()
                .id(FAKER.number().randomNumber())
                .name(FAKER.book().author())
                .age(FAKER.number().numberBetween(0, 100))
                .build();
    }

    public static Author createAuthorFromDTO(final AuthorDTO authorDTO){
        return Author.builder()
                .id(authorDTO.getId())
                .name(authorDTO.getName())
                .age(authorDTO.getAge())
                .build();
    }
}