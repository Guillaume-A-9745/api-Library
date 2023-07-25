package com.myLibrary.dao;

import com.myLibrary.entites.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic,Long> {
}
