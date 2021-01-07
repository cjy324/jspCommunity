DROP DATABASE IF EXISTS jspCommunity;
CREATE DATABASE jspCommunity;
USE jspCommunity;

# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(50) NOT NULL,
    `nickname` CHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    loginId CHAR(50) NOT NULL UNIQUE,
    loginPw VARCHAR(200) NOT NULL,
    adminLevel TINYINT(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '0=탈퇴/1=로그인정지/2=일반/3=인증된/4=관리자'
);

# 회원1 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`name` = "김민수",
`nickname` = "강바람",
`email` = "jangka512@gmail.com",
loginId = "user1",
loginPw = "user1";

# 회원2 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`name` = "김미소",
`nickname` = "이또한지나가리라",
`email` = "jangka512@gmail.com",
loginId = "user2",
loginPw = "user2";

# 게시판 테이블 생성
CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `code` CHAR(10) NOT NULL UNIQUE,
    `name` CHAR(10) NOT NULL UNIQUE
);

# 공지사항 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';

# 방명록 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'guestBook',
`name` = '방명록';

# 자유게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free',
`name` = '자유';

# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL,
    title CHAR(100) NOT NULL,
    `body` LONGTEXT NOT NULL,
    hitsCount INT(10) UNSIGNED NOT NULL DEFAULT 0
);

# 테스트 게시물 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '제목3',
`body` = '내용3';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 1,
title = '제목4',
`body` = '내용4';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 1,
title = '제목5',
`body` = '내용5';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 2,
title = '제목5',
`body` = '내용5';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 3,
title = '제목5',
`body` = '내용5';

SELECT * FROM article;