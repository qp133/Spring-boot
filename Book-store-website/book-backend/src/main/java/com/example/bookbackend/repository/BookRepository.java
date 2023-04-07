package com.example.bookbackend.repository;

import com.example.bookbackend.entity.Book;

import com.example.bookbackend.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Collection;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("""
            select b from Book b inner join b.categories category
            where (:term is null or upper(b.title) like upper(concat('%', :term, '%')))  and (:categoryName is null or upper(category.name) = upper(:categoryName))
            group by b.id
            order by b.createdAt desc 
            """)
    Page<Book> findByTitleContainsIgnoreCaseAndCategories_NameIgnoreCase(@Param("term") String term, @Param("categoryName") String categoryName, Pageable pageable);

    Page<Book> findByOrderByCreatedAtDesc(Pageable pageable);


    List<Book> findByAuthors_IdIn(Collection<Integer> ids);



}