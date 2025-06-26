package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.BookNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	
	 // returning all the books 
	 public List<Book> getAllBooks() {
		 return bookRepo.findAll();
	 }
	 
	 // Returning a book by ISPN
	 public Book getBookByIsbn(String isbn) {
		 return bookRepo.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
	 }

	 
	 // Addin an book record
	 public Book addBook(Book book) {
		 return bookRepo.save(book); 
	 }
	 
	 public Book updateBook(String isbn, Book updatedBook) {
	        Book existingBook = bookRepo.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

	        existingBook.setTitle(updatedBook.getTitle());
	        existingBook.setAuthor(updatedBook.getAuthor());
	        existingBook.setPublicationYear(updatedBook.getPublicationYear());

	        return bookRepo.save(existingBook);
	 }
	 
	 	
	 public void deleteBook(String isbn) {
	        if (!bookRepo.existsById(isbn)) {
	            throw new BookNotFoundException(isbn);
	        }
	        bookRepo.deleteById(isbn);
	    }
	 

}
