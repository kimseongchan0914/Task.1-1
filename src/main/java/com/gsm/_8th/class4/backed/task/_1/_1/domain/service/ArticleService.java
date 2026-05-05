package com.gsm._8th.class4.backed.task._1._1.domain.service;


import com.gsm._8th.class4.backed.task._1._1.domain.dto.ArticleRequestDto;
import com.gsm._8th.class4.backed.task._1._1.domain.dto.ArticleResponseDto;
import com.gsm._8th.class4.backed.task._1._1.domain.dto.ArticleUpdateRequestDto;
import com.gsm._8th.class4.backed.task._1._1.domain.entity.Article;
import com.gsm._8th.class4.backed.task._1._1.domain.repository.ArticleRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleResponseDto> getAllArticles() {
        return articleRepository.findAll() //Article 전체 불러오기
                .stream() //List에 있는걸 컨베이어벨트로 바꾸기
                .map(ArticleResponseDto::new) //컨베이어벨트 위 Article을 하나씩 ArticleResponseDto로 변환
                .collect(Collectors.toList());//변환된 것들을 다시 리스트에 담기
    }
    public ArticleResponseDto getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Article not found")); //예외 던지기
        return new ArticleResponseDto(article);
    }
    @Transactional
    public ArticleResponseDto createArticle(ArticleRequestDto dto) {
        Article article = Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        return new ArticleResponseDto(articleRepository.save(article));
    }
    @Transactional
    public ArticleResponseDto updateArticle(Long id, ArticleUpdateRequestDto dto) {
        Article article = articleRepository.findById(id) //수정할 Article 찾기 없으면 404예외 던지기
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
        article.update(dto.getTitle(), dto.getContent()); //update 메서드 호출
        return new ArticleResponseDto(article);
    }
    @Transactional
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
        articleRepository.delete(article);
    }
}