package com.joon.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joon.blog.model.Board;
import com.joon.blog.model.User;

public interface BoardRepository extends JpaRepository<Board,Integer>{

 
} 