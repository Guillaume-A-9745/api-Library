package com.myLibrary.web;

import com.myLibrary.entites.Book;
import com.myLibrary.entites.Category;
import com.myLibrary.service.LibraryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class CategoryController {

    @Autowired
    private LibraryServiceImpl libraryService;

    @GetMapping("/categories")
    public List<Category> allCategories() { return libraryService.getCategories(); }
}
