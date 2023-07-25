package com.myLibrary.web;

import com.myLibrary.entites.Book;
import com.myLibrary.exception.RecordNotFoundException;
import com.myLibrary.service.LibraryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@Slf4j
public class BookController {

    @Autowired
    private LibraryServiceImpl libraryService;

    @GetMapping("/books")
    public List<Book> allBooks() { return libraryService.getBooks(); }

    @PostMapping("/books")
    public ResponseEntity<Book> saveBook(@RequestBody Book newBook) {
        newBook.setCategory(libraryService.getCategory(newBook.getCategory().getId()));
        Book book = libraryService.saveBook(newBook);
        if(Objects.isNull(book)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id")
                .buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/book")
    public ResponseEntity<Book> update(@RequestBody Book b) {
        Book book = libraryService.readBook(b.getId()).get();
        book.setTitle(b.getTitle());
        book.setSeries(b.getSeries());
        book.setNumber(b.getNumber());
        book.setSummary(b.getSummary());
        book.setAuthor(b.getAuthor());
        book.setPublisher(b.getPublisher());
        book.setCategory(libraryService.getCategory(b.getCategory().getId()));

        if(Objects.isNull(libraryService.saveBook(book))) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id")
                .buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        try {
            libraryService.deleteBook(id);
        }
        catch (Exception e) {
            log.error("Problème avec la suppression du livre d'id : {}", id);
            return ResponseEntity.internalServerError().body(e.getCause());
        }
        log.info("Suppression du livre d'id : {}", id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return libraryService.readBook(id).orElseThrow( () -> new RecordNotFoundException("Id du livre " + id + " n'existe pas!"));
    }

    @GetMapping(path = "photo/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> getPhoto(@PathVariable("id") Long id) throws IOException {
        byte[] file = null;
        try {
            Book book = libraryService.readBook(id).get();
            if(book.getPhoto() == null) book.setPhoto("unknown.png");
            file = Files.readAllBytes(Paths.get(System.getProperty("user.home") + "" + book.getPhoto()));
        }
        catch (Exception e) {
            log.error("Problème avec le chargement de l'image correspondant au livre d'id : {}", id);
            return ResponseEntity.internalServerError().body(e.getCause());
        }
        return ResponseEntity.ok().body(file);
    }
    @PostMapping(path="/photo/{id}")
    public ResponseEntity<?> uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception {
        try {
            Book book = libraryService.readBook(id).get();
            book.setPhoto(file.getOriginalFilename());
            Files.write(Paths.get(System.getProperty("user.home")+"" + book.getPhoto()),file.getBytes());
            libraryService.saveBook(book);
        }
        catch(Exception e) {
            log.error("Problème avec l'enregistrement de l'image correspondant au livre d'id : {}",id);
            return ResponseEntity.internalServerError().body(e.getCause());
        }
        log.info("file upload ok {}",id);
        return ResponseEntity.ok().build();
    }
}
