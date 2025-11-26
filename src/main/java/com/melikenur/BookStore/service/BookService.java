package com.melikenur.BookStore.service;

import com.melikenur.BookStore.entity.Book;
import com.melikenur.BookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void save(Book book){
        bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int id){
        return bookRepository.findById(id);
    }

    public void deleteById(int id){
        bookRepository.deleteById(id);
    }

}
