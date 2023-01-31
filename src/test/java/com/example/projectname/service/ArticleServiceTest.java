package com.example.projectname.service;

import com.example.projectname.dto.ArticleForm;
import com.example.projectname.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 부트와 연동되어 테스팅됨
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {
        // 예상 시나리오
        Article a = new Article(1L, "가가가", "111");
        Article b = new Article(2L, "나나나", "222");
        Article c = new Article(3L, "다다다", "333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제 결과
        List<Article> articles = articleService.index();

        // 비교 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공() {
        // 예상
        Long id = 1L;
        String title = "가가가";
        String context = "111";
        Article expected = new Article(id, title, context);

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패() {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional // 조회를 제외한 테스트에는 롤백 필요
    void create_성공() {
        // 테스트 데이터
        String title = "test title";
        String context = "test context";
        ArticleForm dto = new ArticleForm(null, title, context);

        // 예상
        Article expected = new Article(7L, title, context);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패() {
        // 테스트 데이터
        ArticleForm dto = new ArticleForm(4L, "test", "test");

        // 예상
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공___존재하는_id와_title_context_가_있는_dto() {
        // 테스트 데이터
        Long id = 1L;
        String title = "test";
        String context = "test";
        ArticleForm dto = new ArticleForm(id, title, context);

        // 예상
        Article expected = new Article(id, title, context);

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공___존재하는_id와_title만_있는_dto() {
        // 테스트 데이터
        Long id = 1L;
        String title = "test";
        ArticleForm dto = new ArticleForm(id, title, null);
        Article target = articleService.show(id);

        // 예상
        Article expected = new Article(id, title, target.getContent());

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_실패___존재하지_않는_id의_dto() {
        // 테스트 데이터
        Long id = -1L;
        ArticleForm dto = new ArticleForm(id, "test", "test");

        // 예상
        Article expected = null;

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id() {
        // 테스트 데이터
        Long id = 2L;

        // 예상
        Article expected = new Article(id, "나나나", "222");

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id() {
        Long id = -2L;

        // 예상
        Article expected = null;

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected, article);
    }
}