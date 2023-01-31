package com.example.projectname.entity;

import com.example.projectname.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        if (dto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패 - id가 없습니다.");
        }
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패 - id가 잘못되었습니다.");

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패 - id가 잘못되었습니다.");

        // 객체 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();

        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
