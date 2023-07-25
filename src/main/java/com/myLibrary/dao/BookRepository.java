package com.myLibrary.dao;

import com.myLibrary.entites.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    public List<Book> findByTitleContains(String str);
}
