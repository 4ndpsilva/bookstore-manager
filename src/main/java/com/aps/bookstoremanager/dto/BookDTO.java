package com.aps.bookstoremanager.dto;

import com.aps.bookstoremanager.entity.Author;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
public class BookDTO {
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    private Integer chapters;

    @NotNull
    private Integer pages;

    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = "(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]))",
            message = "ISBN format must be a valid format")
    private String isbn;

    @NotBlank
    private String publishName;

    @Valid
    @NotNull
    private AuthorDTO authorDTO;
}