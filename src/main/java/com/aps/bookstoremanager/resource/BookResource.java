package com.aps.bookstoremanager.resource;

import com.aps.bookstoremanager.dto.MessageResponseDTO;
import com.aps.bookstoremanager.entity.Book;
import com.aps.bookstoremanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookResource {
    private final BookRepository repository;

    @PostMapping
    public MessageResponseDTO save(@RequestBody final Book book){
        final Book savedBook = repository.save(book);
        return MessageResponseDTO
                .builder()
                .message("Book created with ID "+savedBook.getId())
                .build();
    }

    @GetMapping
    public List<Book> findAll(){
        return repository.findAll();
    }
}