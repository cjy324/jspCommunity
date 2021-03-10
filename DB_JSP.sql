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
    cellphoneNo CHAR(20) NOT NULL,
    loginId CHAR(50) NOT NULL UNIQUE,
    loginPw VARCHAR(200) NOT NULL,
    authLevel TINYINT(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '0=탈퇴/1=로그인정지/2=일반/3=인증된/4=관리자'
);

# 관리자 계정 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`name` = "ADMIN",
`nickname` = "관리자",
`email` = "juy32400@gmail.com",
cellphoneNo = "01041308397",
loginId = "admin",
loginPw = "Admin8397",
authLevel = 4;

# 회원 비밀번호 암호화 수행
UPDATE `member`
SET loginPw = SHA2(loginPw, 256)
WHERE id != 14;

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
`name` = 'NOTICE';

# NEWS 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'news',
`name` = 'NEWS';

# Tip게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'tip',
`name` = 'TIP`s';


# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL,
    title CHAR(100) NOT NULL,
    `body` LONGTEXT NOT NULL,
    hitsCount INT(10) UNSIGNED NOT NULL DEFAULT 0,
    repliesCount INT(10) UNSIGNED NOT NULL DEFAULT 0
);

# 테스트 게시물 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = 'GetIT 공지사항 게시판입니다.',
`body` = '안녕하세요. 이곳은 GetIT 공지사항 게시판입니다. 이곳에선 GetIT사이트 관련 알림사항들을 전달드립니다.';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 2,
title = 'GetIT NEWS 게시판입니다.',
`body` = '안녕하세요. 이곳은 GetIT NEWS 게시판입니다. 이곳에선 아이폰, 갤럭시, 아이패드, 갤럭시탭 등 관련 최신 소식들을 공유하는 공간입니다.';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 3,
title = 'GetIT TIP`s 게시판입니다.',
`body` = '안녕하세요. 이곳은 GetIT TIP`s 게시판입니다. 이곳에선 아이폰, 갤럭시, 아이패드, 갤럭시탭 등과 관련된 유용한 팁들을 자유롭게 공유하는 공간입니다.';

SELECT * FROM article;
SELECT * FROM `member`;


# 21.01.30 attr 테이블 추가
CREATE TABLE attr(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    relTypeCode CHAR(20) NOT NULL,
    relId INT(10) UNSIGNED NOT NULL,
    typeCode CHAR(30) NOT NULL,
    type2Code CHAR(30) NOT NULL,
    `value` TEXT NOT NULL,
    `expireDate` DATETIME NULL
);

# attr 유니크 인덱스 걸기
## 중복변수 생성금지
## 변수찾는 속도 최적화
ALTER TABLE `attr` ADD UNIQUE INDEX (`relTypeCode`, `relId`, `typeCode`, `type2Code`); 

## 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서
ALTER TABLE `attr` ADD INDEX (`relTypeCode`, `typeCode`, `type2Code`);

SELECT * FROM attr;

# 21.01.30 View 테이블 추가
CREATE TABLE `view`(
    viewCount INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    viewArticleId INT(10) UNSIGNED NOT NULL
);

SELECT * FROM `view`;


# 21.02.02 like 테이블 추가
CREATE TABLE `like`(
    id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    relTypeCode CHAR(30) NOT NULL,
    relId INT(10) UNSIGNED NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    `point` SMALLINT(1)  # 좋아요 시 +1, 싫어요 시 -1 등 가능
);

SELECT * FROM `like`;

# like 인덱스 걸기
## 중복변수 생성금지
## 변수찾는 속도 최적화
ALTER TABLE `jspCommunity`.`like` ADD INDEX (`relTypeCode`, `relId`, `memberId`);


# 21.02.02 댓글 테이블 추가
CREATE TABLE `reply`(  
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    relTypeCode CHAR(30) NOT NULL,     #rel=관련된
    relId INT(10) UNSIGNED NOT NULL,   
    `body` TEXT NOT NULL   
);

# reply 인덱스 걸기
## 중복변수 생성금지
## 변수찾는 속도 최적화
ALTER TABLE `jspCommunity`.`reply` ADD INDEX (`relTypeCode`, `relId`, `memberId`);
