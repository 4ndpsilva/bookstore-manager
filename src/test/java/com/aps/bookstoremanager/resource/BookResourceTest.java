package com.aps.bookstoremanager.resource;

import com.aps.bookstoremanager.dto.BookDTO;
import com.aps.bookstoremanager.dto.MessageResponseDTO;
import com.aps.bookstoremanager.service.BookService;
import com.aps.bookstoremanager.util.BookUtil;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookResourceTest {
    private MockMvc mockMvc;

    private final String URL = "/api/v1/books";

    @Mock
    private BookService service;

    @InjectMocks
    private BookResource resource;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(resource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void testWhenPostIsCalledThenBookShouldBeCreated() throws Exception{
        final BookDTO bookDTO = BookUtil.createBookDTO();
        final MessageResponseDTO expectedResponse = MessageResponseDTO.builder()
                .message("Book created with ID "+bookDTO.getId())
                .build();

        when(service.save(bookDTO)).thenReturn(expectedResponse);

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(BookUtil.asJsonString(bookDTO)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.message", Is.is(expectedResponse.getMessage())));
    }

    @Test
    public void testWhenPostIsCalledThenBookShouldBeReturnBadRequest() throws Exception{
        final BookDTO bookDTO = BookUtil.createBookDTO();
        bookDTO.setIsbn("");

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BookUtil.asJsonString(bookDTO)))
                            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnNoContentWhenCalledDelete() throws Exception{
        final BookDTO bookDTO = BookUtil.createBookDTO();
        service.save(bookDTO);

        mockMvc.perform(delete(URL+"/"+bookDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BookUtil.asJsonString(bookDTO)))
                            .andExpect(status().isNoContent());
    }
}