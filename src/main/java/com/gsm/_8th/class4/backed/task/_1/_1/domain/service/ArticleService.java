package com.gsm._8th.class4.backed.task._1._1.domain.service;

import com.gsm._8th.class4.backed.task._1._1.domain.dto.ArticleRequestDto;
import com.gsm._8th.class4.backed.task._1._1.domain.dto.ArticleResponseDto;
import com.gsm._8th.class4.backed.task._1._1.domain.dto.ArticleUpdateRequestDto;
import com.gsm._8th.class4.backed.task._1._1.domain.entity.Article;
import com.gsm._8th.class4.backed.task._1._1.domain.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleResponseDto> getAll() {
        return articleRepository.findAll()
                .stream()
                .map(ArticleResponseDto::fromEntity)
                .toList();
    }

    public ArticleResponseDto getById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));
        return ArticleResponseDto.fromEntity(article);
    }

    @Transactional
    public ArticleResponseDto create(ArticleRequestDto request) {
        Article article = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(request.getAuthor())
                .build();
        Article saved = articleRepository.save(article);
        return ArticleResponseDto.fromEntity(saved);
    }

    @Transactional
    public ArticleResponseDto update(Long id, ArticleUpdateRequestDto request) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));

        String currentEmail = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal().toString();

        if (!article.getAuthor().equals(currentEmail)) {
            throw new IllegalArgumentException("권한이 없습니다");
        }

        article.update(request.getTitle(), request.getContent());
        return ArticleResponseDto.fromEntity(article);
    }

    @Transactional
    public void delete(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));

        String currentEmail = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal().toString();

        if (!article.getAuthor().equals(currentEmail)) {
            throw new IllegalArgumentException("권한이 없습니다");
        }

        articleRepository.delete(article);
    }
}