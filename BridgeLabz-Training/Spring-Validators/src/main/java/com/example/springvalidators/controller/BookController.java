package com.example.springvalidators.controller;

import com.example.springvalidators.dto.BookDTO;
import com.example.springvalidators.entity.Book;
import com.example.springvalidators.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController
{
    @Autowired
    private BookService service;

    @PostMapping("/addBook")
    public BookDTO addBook(@Valid @RequestBody BookDTO bookDTO)
    {
        return service.addBook(bookDTO);
    }

    @GetMapping("/*")
    public List<BookDTO> getBooks()
    {
        return service.getBooks();
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteBook(@PathVariable int id)
    {
        return service.deleteBook(id);
    }

    @GetMapping("/findById/{id}")
    public BookDTO findBook(@PathVariable int id)
    {
        return service.findById(id);
    }

    @PutMapping("/updateBook/{id}")
    public BookDTO updateBook(@PathVariable int id,@RequestBody BookDTO bookDTO)
    {
        return service.updateBook(id,bookDTO);
    }
}
