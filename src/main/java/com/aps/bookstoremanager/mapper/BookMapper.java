package com.aps.bookstoremanager.mapper;

import com.aps.bookstoremanager.dto.BookDTO;
import com.aps.bookstoremanager.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toEntity(final BookDTO dto);

    BookDTO toDTO(final Book entity);
}