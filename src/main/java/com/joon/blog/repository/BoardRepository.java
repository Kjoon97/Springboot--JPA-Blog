package com.joon.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joon.blog.model.Board;
import com.joon.blog.model.User;

public interface BoardRepository extends JpaRepository<Board,Integer>{

 
} //BoardRepository 비어있지만 JpaRepository의 기능을 모두 상속받아서 가지고 있음. 