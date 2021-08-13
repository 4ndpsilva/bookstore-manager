package com.aps.bookstoremanager.resource;

import com.aps.bookstoremanager.dto.BookDTO;
import com.aps.bookstoremanager.dto.MessageResponseDTO;
import com.aps.bookstoremanager.mapper.BookMapper;
import com.aps.bookstoremanager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookResource {
    private final BookService service;
    private BookMapper mapper = BookMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> save(@RequestBody @Valid final BookDTO dto){
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable final Long id){
        final BookDTO bookDTO = mapper.toDTO(service.findById(id));
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll(){
        return ResponseEntity.ok(mapper.toListDTO(service.findAll()));
    }
}