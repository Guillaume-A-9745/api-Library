package com.myLibrary;

import com.myLibrary.dao.BookRepository;
import com.myLibrary.dao.CategoryRepository;
import com.myLibrary.dao.ComicRepository;
import com.myLibrary.entites.Book;
import com.myLibrary.entites.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MyLibraryApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ComicRepository comicRepository;

	@Test
	void addInBdd() {

		Category test = categoryRepository.save(new Category(null,"Test",null));

		bookRepository.save(new Book(null,"Harry Potter à l'école des sorciers","Harry Potter",1,"Comming soon...","J.K.Rowling","Folio junior","",test));
	}

	@Test
	void findInBdd() {
		bookRepository.findById(1L);
		bookRepository.findByTitleContains("Harry");
	}

}
