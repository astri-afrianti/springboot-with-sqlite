package com.mitrais.blog.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.mitrais.blog.model.Blog;
import com.mitrais.blog.reporsitory.BlogRepository;

/**
 * This test class for CustomSearchRepository
 * as the interface is tagged as @NoRepositoryBean,
 * so we use BlogRepository as the provider
 * 
 * @author Astri_A332
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomSearchRepositoryTest {
	@Autowired
	private BlogRepository blogRepository;
	
	@Before
	public void prepareInitData() {				
		Date currentDate = new Date();
		List<Blog> sampleBlogs = new LinkedList<Blog>();
		
		Blog nonDeletedBlog = new Blog();
		nonDeletedBlog.setTitle("not deleted");
		nonDeletedBlog.setContent("This is not deleted's content");
		nonDeletedBlog.setCreated(currentDate);
		nonDeletedBlog.setLastUpdated(currentDate);
		nonDeletedBlog.setDeleted(false);
		sampleBlogs.add(nonDeletedBlog);
		
		Blog deletedBlog = new Blog();
		deletedBlog.setTitle("deleted");
		deletedBlog.setContent("This is deleted's content");
		deletedBlog.setCreated(currentDate);
		deletedBlog.setLastUpdated(currentDate);
		deletedBlog.setDeleted(true);
		sampleBlogs.add(deletedBlog);
		
		blogRepository.saveAll(sampleBlogs);
	}
	
	@Test
	public void findActiveWithActiveBlogId() {
		Blog nonDeletedBlog = blogRepository.findActive(1);
		assertNotNull(nonDeletedBlog);		
	}
	
	@Test
	public void findActiveWithNonActiveBlogId() {		
		Blog deletedBlog = blogRepository.findActive(2);
		assertNull(deletedBlog);		
	}
	
	@Test 
	public void findAllActivePageable() {
		Pageable pageable = PageRequest.of(0, 10);
		List<Blog> activeBlog = blogRepository.findAllActive(pageable);		
		assertEquals(1, activeBlog.size());
		
		Blog firstNotDeletedBlog = activeBlog.get(0);
		assertEquals(1, firstNotDeletedBlog.getId());
		assertEquals("not deleted", firstNotDeletedBlog.getTitle());		
	}

}
