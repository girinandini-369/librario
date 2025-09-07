package com.librario.repository;

import com.librario.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    // Intentionally empty. JpaRepository + JpaSpecificationExecutor cover CRUD + search.
}
