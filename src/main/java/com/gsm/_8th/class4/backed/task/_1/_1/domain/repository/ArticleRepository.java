package com.gsm._8th.class4.backed.task._1._1.domain.repository;

import com.gsm._8th.class4.backed.task._1._1.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository  extends JpaRepository<Article, Long>{

}