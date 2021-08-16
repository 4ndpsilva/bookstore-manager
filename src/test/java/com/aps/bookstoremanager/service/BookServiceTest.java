package com.aps.bookstoremanager.service;

import com.aps.bookstoremanager.dto.BookDTO;
import com.aps.bookstoremanager.entity.Book;
import com.aps.bookstoremanager.exception.NotFoundException;
import com.aps.bookstoremanager.repository.BookRepository;
import com.aps.bookstoremanager.util.BookUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @Test
    public void whenGivenExistingIdThenReturnThisBook(){
        final BookDTO expectedBookDTO = BookUtil.createBookDTO();

        when(repository.findById(expectedBookDTO.getId())).thenReturn(Optional.of(BookUtil.createBookFromDTO(expectedBookDTO)));
        final Book book = service.findById(expectedBookDTO.getId());

        assertEquals(expectedBookDTO.getName(), book.getName());
    }

    @Test
    public void whenGivenNonExistingIdThenNotFindThrowAnException(){
        var invalidId = 10L;

        when(repository.findById(invalidId)).thenReturn(Optional.ofNullable(any(Book.class)));

        assertThrows(NotFoundException.class, () -> service.findById(invalidId));
    }
}