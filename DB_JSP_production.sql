USE jspCommunity;


SELECT * FROM `member`;
SELECT * FROM board;
SELECT * FROM `article`;
SELECT * FROM attr;

# 기존 회원 비밀번호 암호화 비교 조회
SELECT loginPw, SHA2(loginPw, 256)
FROM `member`
WHERE id != 14;

# 기존 회원 비밀번호 암호화 수행
UPDATE `member`
SET loginPw = SHA2(loginPw, 256)
WHERE id != 14;

#게시물 랜덤 생성
INSERT INTO `article`
SET `regDate` = NOW(),
`updateDate` = NOW(),
`title` = CONCAT('제목입니다._',RAND()),
`body` = CONCAT('내용입니다._',RAND()),
`boardId` = FLOOR(RAND()*2) + 1,
`memberId` = FLOOR(RAND()*2) + 1;

#attr 테이블 expireDate 컬럼속성 수정
ALTER TABLE attr MODIFY COLUMN `expireDate` DATETIME NULL;