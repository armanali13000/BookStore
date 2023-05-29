package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.Book;
import com.bookstore.entity.MyBookList;
import com.bookstore.service.BookService;
import com.bookstore.service.MyBookListService;



@Controller
public class BookController {

	
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService mybookservice;

	
	
	
	@GetMapping("/")
	public String home()
	{
		return "home";
		
	}
	
	
	
	
	
	@GetMapping("/book_Register")
	public String bookRegister()
	{
		return "bookRegister";
		
	}
	
	
	@GetMapping("/available_book")
	public ModelAndView getAllBook()
	{
		List<Book> list = service.getAllBooks();
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("booklist");
//		mav.addObject("book", list);
		return new ModelAndView("booklist", "book", list);
		
	}
	
	
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book book)
	{
		service.save(book);
		
		return "redirect:/available_book";
		
	}
	
	
	@GetMapping("/my_book")
	public String getMyBooks(Model model)
	{
		List<MyBookList> list = mybookservice.getAllMyBooks();
		model.addAttribute("book",list);
		
		return "myBooks";
		
	}
	
	
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id)
	{
		Book bs = service.getBookById(id);
		MyBookList myBookList = new MyBookList(bs.getId(), bs.getName(), bs.getAuthor(), bs.getPrice());
		mybookservice.saveMyBooks(myBookList);
		return "redirect:/my_book";
		
	}
	
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model)
	{
		Book bid = service.getBookById(id);
		model.addAttribute("book", bid);
		return "bookEdit";
		
	}
	
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id)
	{
		service.deleteById(id);
		return "redirect:/available_book";
		
	}
	
	

	@GetMapping("/about")
	public String aboutUs()
	{
		
		return "aboutUs";
		
	}
	
	
	
	@GetMapping("/contact")
	public String contact()
	{
		return "contact";
		
	}
	
}
