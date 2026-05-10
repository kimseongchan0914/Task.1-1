package com.gsm._8th.class4.backed.task._1._1.domain.entity;

import com.gsm._8th.class4.backed.task._1._1.domain.entity.BaseIdxEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articles")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article extends BaseIdxEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Column(nullable = false)
    private String author;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}