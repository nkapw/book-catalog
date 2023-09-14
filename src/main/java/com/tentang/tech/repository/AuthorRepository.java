package com.tentang.tech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tentang.tech.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

	//method name convention
	//find+keyword
	//sql -> select * from Author a where a.id= :authorId
	public Optional<Author> findById(Long id);
	public Optional<Author> findBySecureId(String id);

	public  List<Author> findBySecureIdIn(List<String> listAuthorId);
	//where id = :id AND deleted=false
	public Optional<Author> findByIdAndDeletedFalse(Long id);

	
	//sql -> select a from Author a where a.author_name = :authorName
	public List<Author> findByNameLike(String authorName);
	
	
}
