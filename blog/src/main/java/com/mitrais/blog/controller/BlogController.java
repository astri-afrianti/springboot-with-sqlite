package com.mitrais.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.blog.model.Blog;
import com.mitrais.blog.service.BlogService;

@RestController
public class BlogController {

	@Autowired
	BlogService blogService;

	@GetMapping("/blog")
	public List<Blog> index() {		
		return blogService.findAll(PageRequest.of(0, 10));
	}
	
	@GetMapping("/blog/{id}")
	public Blog show(@PathVariable String id) {
		int blogId = Integer.parseInt(id);
		return blogService.find(blogId);
	}

	@PostMapping("/blog/search")
	public List<Blog> search(@RequestBody Map<String, String> body) {
		String searchTerm = body.get("text");
		
		List<Blog> resutls = blogService.findBySearchTerm(searchTerm, PageRequest.of(0, 10));
		return resutls;
	}

	@PostMapping("/blog")
	public Blog create(@RequestBody Map<String, String> body) {
		String title = body.get("title");
		String content = body.get("content");

		return blogService.createBlog(new Blog(title, content));
	}

	@PutMapping("/blog/{id}")
	public Blog update(@PathVariable String id, @RequestBody Map<String, String> body) {
		int blogId = Integer.parseInt(id);		
		String title = body.get("title");
		String content = body.get("content");

		Blog toBeUpdatedBlog = new Blog(blogId, title, content);

		return blogService.updateBlog(toBeUpdatedBlog);
	}

	@DeleteMapping("/blog/{id}")
	public boolean delete(@PathVariable String id) {
		int blogId = Integer.parseInt(id);
		
		return blogService.deleteBlog(blogId);
	}
}
