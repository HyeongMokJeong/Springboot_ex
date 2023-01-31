package com.example.projectname.repository;

import com.example.projectname.entity.Article;
import com.example.projectname.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터
            Long articleId = 4L;

            // 실제
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            Article article = new Article(4L, "좋아하는 영화는?", "댓글부탁");
            Comment a = new Comment(1L, article, "ㅇㅇ", "영화1");
            Comment b = new Comment(2L, article, "ㅇㅇ2", "영화2");
            Comment c = new Comment(3L, article, "ㅇㅇ3", "영화3");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 비교
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력");
        }

        /* Case 2: 1번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터
            Long articleId = 1L;

            // 실제
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            Article article = new Article(1L, "가가가", "111");
            List<Comment> expected = Arrays.asList();

            // 비교
            assertEquals(expected.toString(), comments.toString(), "1번 글의 모든 댓글 출력");
        }

        /* Case 3: 9번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터
            Long articleId = 9L;

            // 실제
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            List<Comment> expected = Arrays.asList();

            // 비교
            assertEquals(expected.toString(), comments.toString(), "9번 글의 모든 댓글 출력");
        }

        /* Case 4: -1번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터
            Long articleId = -1L;

            // 실제
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            List<Comment> expected = Arrays.asList();

            // 비교
            assertEquals(expected.toString(), comments.toString(), "-1번 글의 모든 댓글 출력");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: "ㅇㅇ"의 모든 댓글 조회*/
        {
            // 입력 데이터
            String nickname = "ㅇㅇ";

            // 실제
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상
            Comment a = new Comment(1L, new Article(4L, "좋아하는 영화는?", "댓글부탁"), nickname, "영화1");
            Comment b = new Comment(4L, new Article(5L, "좋아하는 음식은?", "댓글ㄱㄱ"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "자주하는 게임은?", "댓글"), nickname, "롤");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 비교
            assertEquals(expected.toString(), comments.toString(), "ㅇㅇ의 모든 댓글 출력");
        }

        /* Case 2: "ㅇㅇ3"의 모든 댓글 조회*/
        {
            // 입력 데이터
            String nickname = "ㅇㅇ3";

            // 예상
            Comment a = new Comment(3L, new Article(4L, "좋아하는 영화는?", "댓글부탁"), nickname, "영화3");
            Comment b = new Comment(6L, new Article(5L, "좋아하는 음식은?", "댓글ㄱㄱ"), nickname, "초밥");
            List<Comment> expected = Arrays.asList(a, b);

            // 실제
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 비교
            assertEquals(expected.toString(), comments.toString(), "ㅇㅇ3의 모든 댓글 조회");
        }

        /* Case 3: null의 모든 댓글 조회*/
        {
            // 입력 데이터
            String nickname = null;

            // 예상
            List<Comment> expected = Arrays.asList();

            // 실제
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 비교
            assertEquals(expected, comments, "ㅇㅇ3의 모든 댓글 조회");
        }

        /* Case 4: "i"가 들어간 닉네임의 모든 댓글 조회*/
        {
            // 입력 데이터
            String nickname = "%i%";

            // 예상
            Comment a = new Comment(5L, new Article(5L, "좋아하는 음식은?", "댓글ㄱㄱ"), "ㅇiㅇ2", "피자");
            Comment b = new Comment(8L, new Article(6L, "자주하는 게임은?", "댓글"), "ㅇiㅇ2", "피파");
            Comment c = new Comment(9L, new Article(6L, "자주하는 게임은?", "댓글"), "ㅇiㅇ3", "배그");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 실제
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 비교
            assertEquals(expected.toString(), comments.toString(), "닉네임에 i가 들어간 모든 댓글 조회");
        }
    }
}