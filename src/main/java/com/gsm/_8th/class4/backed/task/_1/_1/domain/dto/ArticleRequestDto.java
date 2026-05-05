package com.gsm._8th.class4.backed.task._1._1.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //getTitle()과 getContent()메서드 자동 생성 dto 꺼낼때도 사용
@NoArgsConstructor //기본 생성자 자동 생성
public class ArticleRequestDto {
    private String title;
    private String content;
}