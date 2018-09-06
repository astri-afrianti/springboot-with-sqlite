package com.mitrais.blog.reporsitory;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.mitrais.blog.model.Blog;

@NoRepositoryBean
public interface CustomSearchRepository<T, ID extends Serializable> {
	
	/**
	 * Get a page of non-deleted Entity filter by a search term
	 * 
	 * @param search  term
	 * @param paging  details
	 * @return List Entity
	 */
	@Query("SELECT e FROM #{#entityName} e WHERE e.deleted = 0")
	List<T> findAllActive(Pageable pageable);
	
	/**
	 * Get a specific non-deleted Blog by id
	 * 
	 * @param id
	 * @return expected Blog
	 */
	@Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.deleted = 0")
	public Blog findActive(@Param("id") int id);	
}
