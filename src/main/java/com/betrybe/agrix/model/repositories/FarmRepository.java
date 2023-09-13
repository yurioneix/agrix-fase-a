package com.betrybe.agrix.model.repositories;

import java.awt.print.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da classe Farm.
 */
@Repository
public interface FarmRepository extends JpaRepository<Book, Long> {

}