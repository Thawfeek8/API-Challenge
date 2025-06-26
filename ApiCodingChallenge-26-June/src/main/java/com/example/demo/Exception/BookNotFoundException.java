package com.example.demo.Exception;

public class BookNotFoundException extends RuntimeException {

	 public BookNotFoundException(String isbn) {
		 super("Book with ISBN '" + isbn + "' not found.");
	 }
	
	

}
