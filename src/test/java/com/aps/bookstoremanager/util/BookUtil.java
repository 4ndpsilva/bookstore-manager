package com.aps.bookstoremanager.util;

import com.aps.bookstoremanager.dto.BookDTO;
import com.aps.bookstoremanager.entity.Book;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;

public class BookUtil {
    private static final Faker FAKER = Faker.instance();

    public static BookDTO createBookDTO(){
        return BookDTO.builder()
                .id(FAKER.number().randomNumber())
                .name(FAKER.book().title())
                .chapters(FAKER.number().numberBetween(0, 20))
                .pages(FAKER.number().numberBetween(1, 200))
                .isbn("0-555-88888-6")
                .publishName(FAKER.book().publisher())
                .author(AuthorUtil.createAuthorDTO())
                .build();
    }

    public static Book createBook(){
        return Book.builder()
                .id(FAKER.number().randomNumber())
                .name(FAKER.book().title())
                .chapters(FAKER.number().numberBetween(0, 20))
                .pages(FAKER.number().numberBetween(1, 200))
                .isbn("0-555-88888-6")
                .publishName(FAKER.book().publisher())
                .author(AuthorUtil.createAuthor())
                .build();
    }

    public static Book createBookFromDTO(final BookDTO bookDTO){
        return Book.builder()
                .id(bookDTO.getId())
                .name(bookDTO.getName())
                .chapters(bookDTO.getChapters())
                .pages(bookDTO.getPages())
                .isbn(bookDTO.getIsbn())
                .publishName(bookDTO.getPublishName())
                .author(AuthorUtil.createAuthorFromDTO(bookDTO.getAuthor()))
                .build();
    }

    public static String asJsonString(BookDTO bookDTO) {
        try{
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(bookDTO);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}