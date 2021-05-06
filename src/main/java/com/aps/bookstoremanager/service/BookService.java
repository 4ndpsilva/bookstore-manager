package com.aps.bookstoremanager.service;

import com.aps.bookstoremanager.dto.BookDTO;
import com.aps.bookstoremanager.dto.MessageResponseDTO;
import com.aps.bookstoremanager.entity.Book;
import com.aps.bookstoremanager.mapper.BookMapper;
import com.aps.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    private BookMapper mapper = BookMapper.INSTANCE;

    public MessageResponseDTO save(final BookDTO bookDTO){
        final Book book = mapper.toEntity(bookDTO);
        final Book savedBook = repository.save(book);

        return MessageResponseDTO.builder()
                .message("Book created with ID "+savedBook.getId())
                .build();
    }

    public List<Book> findAll(){
        return repository.findAll();
    }
}