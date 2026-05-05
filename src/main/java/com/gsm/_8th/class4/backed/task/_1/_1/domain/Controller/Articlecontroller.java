package com.gsm._8th.class4.backed.task._1._1.domain.Controller;

import com.gsm._8th.class4.backed.task._1._1.domain.dto.ArticleRequestDto;
import com.gsm._8th.class4.backed.task._1._1.domain.dto.ArticleResponseDto;
import com.gsm._8th.class4.backed.task._1._1.domain.dto.ArticleUpdateRequestDto;
import com.gsm._8th.class4.backed.task._1._1.domain.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //controller라고 정의 --> 반환값 자동 JSON
@RequestMapping("/articles") // 모든 메서드 URL 앞에 /articles가 자동으로 붙음
@RequiredArgsConstructor
public class Articlecontroller {
    private final ArticleService articleService; //서비스를 세번째 어노테이션으로 인해 주입 받음

    @GetMapping //GET요청 받고 /articles가 자동으로 붙음
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles() { //응답을 감싸는 클래스 HTTP 상태코드 데이터를 같이 반환
        return ResponseEntity.ok(articleService.getAllArticles()); //200 OK 상태코드와 함께 데이터를 반환
    }

    @GetMapping("/{articleId}") // /articles/1같은 URL을 받는다(?)
    public ResponseEntity<ArticleResponseDto> getArticle(@PathVariable Long articleId) { // {articleId}값을 꺼내서 매개변수에 넣어줌
        return ResponseEntity.ok(articleService.getArticle(articleId));
    }

    @PostMapping //POST 요청 받기
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody ArticleRequestDto dto) { //클라이언트가 보낸 JSON을 DTO로 변환
        return ResponseEntity.status(201).body(articleService.createArticle(dto)); //201 Created 상태코드와 함께 데이터를 반환
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponseDto> updateArticle(
            @PathVariable Long articleId,
            @RequestBody ArticleUpdateRequestDto dto) {
        return ResponseEntity.ok(articleService.updateArticle(articleId, dto));
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId) { //반환할 데이터가 없음
        articleService.deleteArticle(articleId);
        return ResponseEntity.noContent().build(); //204 No Content(요청 처리는 성공 했으나 응답 본문에 반환할 데이터가 없음) 상태코드 반환
    }
}