package com.gsm._8th.class4.backed.task._1._1.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleUpdateRequestDto {
    private String title;
    private String content; //수정 할떄는 title과 content가 선액할 수 있기 때문에 따로 만듬
}