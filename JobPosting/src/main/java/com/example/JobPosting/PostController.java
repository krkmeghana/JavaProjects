package com.example.JobPosting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

	@Autowired
	PostRepo repo;
	@Autowired
	searchRepo sRepo;
	
	@GetMapping("/posts")
	public List<Post> getAllPosts()
	{
		return repo.findAll();
	}
	
	@PostMapping("/post")
	public Post addPost(@RequestBody Post post)
	{
		return repo.save(post);
	}
	
	@GetMapping("/post/{text}")
	public List<Post> searchPost(@PathVariable String text)
	{
		return sRepo.findByText(text);
	}
}
