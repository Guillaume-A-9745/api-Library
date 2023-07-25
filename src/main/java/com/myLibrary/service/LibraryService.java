package com.myLibrary.service;

import com.myLibrary.entites.Book;
import com.myLibrary.entites.Comic;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    Book saveBook(Book book);
    Optional<Book> readBook(Long id);
    List<Book> getBooks();
    void deleteBook(Long id);


    Comic saveComic(Comic comic);
    Optional<Comic> readComic(Long id);
    List<Comic> getComics();
    void deleteComic(Long id);
}
