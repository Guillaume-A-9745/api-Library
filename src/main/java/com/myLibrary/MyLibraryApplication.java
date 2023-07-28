package com.myLibrary;

import com.myLibrary.dao.BookRepository;
import com.myLibrary.dao.CategoryRepository;
import com.myLibrary.dao.ComicRepository;
import com.myLibrary.entites.Book;
import com.myLibrary.entites.Category;
import com.myLibrary.entites.Comic;
import com.myLibrary.security.entities.AppRole;
import com.myLibrary.security.entities.AppUser;
import com.myLibrary.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class MyLibraryApplication implements CommandLineRunner {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ComicRepository comicRepository;
	@Autowired
	public AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(MyLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//generateUsersAndRoles();
		//generateData();
	}

	private void generateUsersAndRoles() {
		accountService.saveUser(new AppUser(null,"Guillaume","1234",new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"Jean","1234",new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"Marie","1234",new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"Louis","1234",new ArrayList<>()));

		accountService.saveRole(new AppRole(null,"ADMIN"));
		accountService.saveRole(new AppRole(null,"USER"));

		accountService.addRoleToUser("Guillaume","ADMIN");
		accountService.addRoleToUser("Guillaume","USER");
		accountService.addRoleToUser("Jean","USER");
		accountService.addRoleToUser("Marie","USER");
		accountService.addRoleToUser("Louis","USER");
	}

	private void generateData() {
		Category jeunesse = categoryRepository.save(new Category(null,"Jeunesse",null));
		Category sf = categoryRepository.save(new Category(null,"Science-fiction",null));
		Category police = categoryRepository.save(new Category(null,"Policier",null));
		Category horreur = categoryRepository.save(new Category(null,"Horreur",null));
		Category aventure = categoryRepository.save(new Category(null,"Aventure",null));
		Category fantaisie = categoryRepository.save(new Category(null,"Fantaisie",null));
		Category manga = categoryRepository.save(new Category(null,"Manga",null));
		Category bdj = categoryRepository.save(new Category(null,"Bande dessinée jeune",null));

		bookRepository.save(new Book(null,"Harry Potter à l'école des sorciers","Harry Potter",1,"Comming soon...","J.K.Rowling","Folio junior","",jeunesse));
		bookRepository.save(new Book(null,"Harry Potter et la chambre des secret","Harry Potter",2,"Comming soon...","J.K.Rowling","Folio junior","",jeunesse));
		bookRepository.save(new Book(null,"Harry Potter et le prisonnier d'Azkaban","Harry Potter",3,"Comming soon...","J.K.Rowling","Folio junior","",jeunesse));
		bookRepository.save(new Book(null,"Harry Potter et la coupe de feu","Harry Potter",4,"Comming soon...","J.K.Rowling","Folio junior","",jeunesse));

		comicRepository.save(new Comic(null,"Naruto","Naruto",1,"Comming Soon...","","","","",manga));
		comicRepository.save(new Comic(null,"Naruto","Naruto",2,"Comming Soon...","","","","",manga));
	}
}
