package com.priya.springboot.bootdemo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priya.springboot.bootdemo.model.User1;
import com.priya.springboot.bootdemo.repository.UserRepository;

@RestController
@RequestMapping(value="/rest/user1")

public class UserResource {
	
	@Autowired
	UserRepository ur;
	
	@GetMapping(value="/all")
	public List<User1> getAll(){
	   	
	   return ur.findAll();
	  
	 
	}
	@PostMapping
	public List<User1> persist(@RequestBody final User1 user1){
		ur.save(user1);
		return ur.findAll();
		
	}

}
