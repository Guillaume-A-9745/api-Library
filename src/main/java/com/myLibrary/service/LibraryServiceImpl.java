package com.myLibrary.service;

import com.myLibrary.dao.BookRepository;
import com.myLibrary.dao.CategoryRepository;
import com.myLibrary.dao.ComicRepository;
import com.myLibrary.entites.Book;
import com.myLibrary.entites.Category;
import com.myLibrary.entites.Comic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ComicRepository comicRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> readBook(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Comic saveComic(Comic comic) {
        return comicRepository.save(comic);
    }

    @Override
    public Optional<Comic> readComic(Long id) {
        return comicRepository.findById(id);
    }

    @Override
    public List<Comic> getComics() {
        return comicRepository.findAll();
    }

    @Override
    public void deleteComic(Long id) {
        comicRepository.deleteById(id);
    }

    public Category getCategory(Long id) {
        return categoryRepository.getById(id);
    }
}
