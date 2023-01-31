package com.example.projectname.service;

import com.example.projectname.dto.CommentDto;
import com.example.projectname.entity.Article;
import com.example.projectname.entity.Comment;
import com.example.projectname.repository.ArticleRepository;
import com.example.projectname.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long id) {
//        // 댓글 목록 조회
//        List<Comment> comments = commentRepository.findByArticleId(id);
//        // 엔티티 -> dto로 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (Comment comment : comments) {
//            CommentDto dto = CommentDto.createCommentDto(comment);
//            dtos.add(dto);
//        }

        // 반환
        return commentRepository.findByArticleId(id)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패"));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 엔티티를 db에 저장
        Comment created = commentRepository.save(comment);

        // dto로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패 - 대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // 수정된 댓글 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 dto로 변환하여 리턴
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long commentId) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패 - 대상이 없습니다."));

        // 댓글 삭제
        commentRepository.delete(target);

        // 삭제 댓글을 dto로 리턴
        return CommentDto.createCommentDto(target);
    }
}
