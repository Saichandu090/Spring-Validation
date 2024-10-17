package com.example.springvalidators.service;

import com.example.springvalidators.dto.BookDTO;
import com.example.springvalidators.entity.Book;
import com.example.springvalidators.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class BookService
{
    @Autowired
    private BookRepository repository;

    public BookDTO addBook(BookDTO bookDTO)
    {
        Book book=new Book();
        book.setName(bookDTO.getName());
        book.setStock(bookDTO.getStock());
        book.setDate(LocalDate.now());
        book.setEmail(bookDTO.getEmail());
        repository.save(book);
        return setBookDTO(book);
    }

    private BookDTO setBookDTO(Book book)
    {
        BookDTO bookDTO=new BookDTO();
        bookDTO.setName(book.getName());
        bookDTO.setStock(book.getStock());
        bookDTO.setEmail(book.getEmail());
        return bookDTO;
    }

    public List<BookDTO> getBooks()
    {
        List<Book> bookList=repository.findAll();
        List<BookDTO> dtoList=new ArrayList<>();
        for(Book i : bookList)
        {
            dtoList.add(setBookDTO(i));
        }
        return dtoList;
    }

    public String deleteBook(int id)
    {
        findById(id);
        repository.deleteById(id);
        return "Deleted Successfully";
    }

    public BookDTO findById(int id)
    {
        Book book=repository.findById(id).orElseThrow(()->new RuntimeException("Invalid book id "));
        return setBookDTO(book);
    }

    public BookDTO updateBook(int id, BookDTO bookDTO)
    {
        findById(id);
        Book book=repository.findById(id).get();
        book.setName(bookDTO.getName());
        book.setStock(bookDTO.getStock());
        book.setEmail(bookDTO.getEmail());
        return setBookDTO(repository.save(book));
    }
}
