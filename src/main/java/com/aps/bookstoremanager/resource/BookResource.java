package com.aps.bookstoremanager.resource;

import com.aps.bookstoremanager.dto.BookDTO;
import com.aps.bookstoremanager.dto.MessageResponseDTO;
import com.aps.bookstoremanager.entity.Book;
import com.aps.bookstoremanager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookResource {
    private final BookService service;

    @PostMapping
    public MessageResponseDTO save(@RequestBody @Valid final BookDTO dto){
        final Book book = Book.builder().
                name(dto.getName())
                .pages(dto.getPages())
                .chapters(dto.getChapters())
                //.author(dto.getAuthorDTO())
                .build();
        return service.save(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}