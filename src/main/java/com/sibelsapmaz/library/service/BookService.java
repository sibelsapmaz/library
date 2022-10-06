package com.sibelsapmaz.library.service;

import com.sibelsapmaz.library.entity.Book;
import com.sibelsapmaz.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public long getTotalBookCount() {
        return bookRepository.count();
    }

    public Optional<Book> getBook(Long bookId) {
        return bookRepository.findById(bookId);
    }

}
