package com.example.projectname.controller;

import com.example.projectname.dto.ArticleForm;
import com.example.projectname.dto.CommentDto;
import com.example.projectname.entity.Article;
import com.example.projectname.repository.ArticleRepository;
import com.example.projectname.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        // System.out.println(form.toString()); -> 로깅으로 대체
        log.info(form.toString());
        
        // 1. dto를 entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. repository에게 entity를 db에 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        // 1. id로 데이터 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2. 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        // 3. 보여줄 뷰를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 articles 가져옴
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 list를 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        // 3. 보여줄 뷰를 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터 db에서 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 가져온 Article Entity object를 model에 등록
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        // 1. dto 데이터를 entity화
        Article articleEntity = form.toEntity();

        // 2. entity 오브젝트를 repository를 이용해서 db에 업데이트
        // 2-1. db에서 기존 데이터를 가져옴
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터의 값을 갱신
        if (target != null) {
            articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        // 1. 삭제 대상을 가져온다
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 대상을 삭제한다.
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }

        // 3. 결과 페이지로 리다이렉트

        return "redirect:/articles";
    }


}