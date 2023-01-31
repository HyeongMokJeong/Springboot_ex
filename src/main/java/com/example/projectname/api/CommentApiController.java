package com.example.projectname.api;

import com.example.projectname.annotation.RuningTime;
import com.example.projectname.dto.CommentDto;
import com.example.projectname.entity.Comment;
import com.example.projectname.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{id}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long id) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(id);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {
        CommentDto createdDto = commentService.create(articleId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentDto> update(@PathVariable Long commentId, @RequestBody CommentDto dto) {
        // 서비스에게 위임
        CommentDto updatedDto = commentService.update(commentId, dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 댓글 삭제
    @RuningTime
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long commentId) {
        // 서비스에게 위임
        CommentDto deletedDto = commentService.delete(commentId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
