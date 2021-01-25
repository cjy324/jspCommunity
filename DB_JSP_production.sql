USE jspCommunity;


SELECT * FROM `member`;
SELECT * FROM board;
SELECT * FROM `article`;


# 기존 회원 비밀번호 암호화 비교 조회
SELECT loginPw, SHA2(loginPw, 256)
FROM `member`
WHERE id != 14;

# 기존 회원 비밀번호 암호화 수행
UPDATE `member`
SET loginPw = SHA2(loginPw, 256)
WHERE id != 14;