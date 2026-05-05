package com.gsm._8th.class4.backed.task._1._1.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity //DB테이블과 연결
@Getter //필드값을 가져오는 메서드 만들어줌
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 생성 외부에서 Article을 만드는걸 방지하기위해 PROTEXTED사용
public class Article extends BaseIdxEntity {
    @Column(nullable = false) //필드 추가 : DB컬럼과 연결 널값 x
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT") //위와 동일 TEXT타입으로 좀 더 긴글 수용 가능
    private String content;

    @Builder //코드 순서가 상관 없어지는것
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        if (title != null) this.title = title; //title 보냈을 때만 수정
        if (content != null) this.content = content; //content 보냈을 때만 수정
    }
}