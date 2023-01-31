INSERT INTO article(id, title, content) VALUES (1, '가가가', '111');
INSERT INTO article(id, title, content) VALUES (2, '나나나', '222');
INSERT INTO article(id, title, content) VALUES (3, '다다다', '333');
INSERT INTO article(id, title, content) VALUES (4, '좋아하는 영화는?', '댓글부탁');
INSERT INTO article(id, title, content) VALUES (5, '좋아하는 음식은?', '댓글ㄱㄱ');
INSERT INTO article(id, title, content) VALUES (6, '자주하는 게임은?', '댓글');

INSERT INTO comment(id, article_id, nickname, body) VALUES (1, 4, 'ㅇㅇ', '영화1');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2, 4, 'ㅇㅇ2', '영화2');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3, 4, 'ㅇㅇ3', '영화3');

INSERT INTO comment(id, article_id, nickname, body) VALUES (4, 5, 'ㅇㅇ', '치킨');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5, 5, 'ㅇiㅇ2', '피자');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6, 5, 'ㅇㅇ3', '초밥');

INSERT INTO comment(id, article_id, nickname, body) VALUES (7, 6, 'ㅇㅇ', '롤');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8, 6, 'ㅇiㅇ2', '피파');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9, 6, 'ㅇiㅇ3', '배그');