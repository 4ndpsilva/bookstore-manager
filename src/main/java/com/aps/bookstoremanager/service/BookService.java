package com.aps.bookstoremanager.service;

import com.aps.bookstoremanager.dto.MessageResponseDTO;
import com.aps.bookstoremanager.entity.Book;
import com.aps.bookstoremanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public MessageResponseDTO save(final Book book){
        final Book savedBook = repository.save(book);
        return MessageResponseDTO
                .builder()
                .message("Book created with ID "+savedBook.getId())
                .build();
    }

    public List<Book> findAll(){
        return repository.findAll();
    }
}