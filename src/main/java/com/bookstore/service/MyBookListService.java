package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.MyBookList;
import com.bookstore.repository.MyBookListRepository;



@Service
public class MyBookListService {

	@Autowired
	private MyBookListRepository mybook;
	
	
	public void saveMyBooks(MyBookList myBookList)
	{
		mybook.save(myBookList);
	}
	
	
	public List<MyBookList> getAllMyBooks()
	{
		return mybook.findAll();
		
	}
	
	public void deleteById(int id)
	{
		mybook.deleteById(id);
	}
	
}
