package com.joon.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  //생성자
@NoArgsConstructor
public class ReplySaveRequestDto {   //Reply에 필요한 정보를 한꺼번에 담기위한 클래스
  private int userId;
  private int boardId;
  private String content;
}
