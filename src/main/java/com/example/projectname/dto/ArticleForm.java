package com.example.projectname.dto;

import com.example.projectname.entity.Article;
import lombok.*;

// @Data 사용하면 Getter Setter ToString 자동생성됨.
// 하지만 JPA에서는 순환참조가 발생할 수 있으므로 DTO에서만 사용 권장
@Data
@AllArgsConstructor // 모든 args에 대해 생성자 자동생성
@NoArgsConstructor
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
