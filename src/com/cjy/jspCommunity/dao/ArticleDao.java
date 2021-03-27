package com.cjy.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cjy.jspCommunity.dto.Article;
import com.cjy.jspCommunity.dto.Board;
import com.cjy.jspCommunity.dto.Member;
import com.cjy.jspCommunity.dto.Reply;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class ArticleDao {

	// 해당 게시판에 해당하는 게시물 리스트 가져오기(검색x)
	public List<Article> getArticlesForPrintByBoardId(int boardId) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra_memberName");
		sql.append(", M.nickname AS extra_memberNickname");
		sql.append(", B.name AS extra_boardName");
		sql.append(", B.code AS extra_boardCode");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra_likePoint");
		sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra_likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra_dislikeOnlyPoint");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'article'");
		sql.append("AND A.id = L.relId");
		sql.append("WHERE 1");

		if (boardId != 0) {
			sql.append("AND A.boardId = ?", boardId);
		}
		;

		sql.append("GROUP BY A.id");

		sql.append("ORDER BY A.id DESC");

		List<Map<String, Object>> articlesMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articlesMap : articlesMapList) {
			Article article = new Article(articlesMap);

			articles.add(article);

		}
		// Collections.reverse(articles);
		return articles;
	}

	// 해당 게시판에 해당하는 검색된 게시물 리스트 가져오기
	public List<Article> getArticlesForPrintByBoardId(int boardId, int pageLimitStartIndex, int articlesInAPage,
			String searchKeywordType, String searchKeyword) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.id AS extra_memberId");
		sql.append(", M.name AS extra_memberName");
		sql.append(", M.nickname AS extra_memberNickname");
		sql.append(", B.name AS extra_boardName");
		sql.append(", B.code AS extra_boardCode");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra_likePoint");
		sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra_likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra_dislikeOnlyPoint");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'article'");
		sql.append("AND A.id = L.relId");
		sql.append("WHERE 1");

		if (boardId != 0) {
			sql.append("AND A.boardId = ?", boardId);
		}

		if (searchKeyword != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,
						searchKeyword);
			}
		}

		sql.append("GROUP BY A.id");

		sql.append("ORDER BY A.id DESC");

		if (articlesInAPage != -1) {
			sql.append("LIMIT ?, ?", pageLimitStartIndex, articlesInAPage);
		}

		List<Map<String, Object>> articlesMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articlesMap : articlesMapList) {
			Article article = new Article(articlesMap);

			articles.add(article);

		}
		// Collections.reverse(articles);
		return articles;
	}

	// 해당 게시판의 검색된 게시물 수 가져오기
	public int getArticlesCountByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*) AS cnt");
		sql.append("FROM article AS A");
		sql.append("WHERE 1");

		if (boardId != 0) {
			sql.append("AND A.boardId = ?", boardId);
		}

		if (searchKeyword != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,
						searchKeyword);
			}
		}

		return MysqlUtil.selectRowIntValue(sql);
	}

	// 검색어를 포함한 게시물 수 가져오기
	public int getArticlesCountBySearchKeyword(String searchKeywordType, String searchKeyword) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*) AS cnt");
		sql.append("FROM article AS A");
		sql.append("WHERE 1");

		if (searchKeyword != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,
						searchKeyword);
			}
		}

		return MysqlUtil.selectRowIntValue(sql);
	}

	// 검색어를 포함한 모든 게시물 리스트 가져오기
	public List<Article> getArticlesForPrintBySearchKeyword(int pageLimitStartIndex, int articlesInAPage,
			String searchKeywordType, String searchKeyword) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.id AS extra_memberId");
		sql.append(", M.name AS extra_memberName");
		sql.append(", M.nickname AS extra_memberNickname");
		sql.append(", B.name AS extra_boardName");
		sql.append(", B.code AS extra_boardCode");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra_likePoint");
		sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra_likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra_dislikeOnlyPoint");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'article'");
		sql.append("AND A.id = L.relId");
		sql.append("WHERE 1");

		if (searchKeyword != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,
						searchKeyword);
			}
		}

		sql.append("GROUP BY A.id");

		sql.append("ORDER BY A.id DESC");

		if (articlesInAPage != -1) {
			sql.append("LIMIT ?, ?", pageLimitStartIndex, articlesInAPage);
		}

		List<Map<String, Object>> articlesMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articlesMap : articlesMapList) {
			Article article = new Article(articlesMap);

			articles.add(article);

		}
		// Collections.reverse(articles);
		return articles;
	}

	// 게시물번호로 게시물 가져오기
	public Article getArticleById(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT A.*");
		sql.append(", M.id AS extra_memberId");
		sql.append(", M.name AS extra_memberName");
		sql.append(", M.nickname AS extra_memberNickname");
		sql.append(", B.name AS extra_boardName");
		sql.append(", B.code AS extra_boardCode");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra_likePoint");
		sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra_likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra_dislikeOnlyPoint");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'article'");
		sql.append("AND A.id = L.relId");
		sql.append("WHERE A.id = ?", id);
		sql.append("GROUP BY A.id");

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		if (articleMap.isEmpty()) {
			return null;
		}

		return new Article(articleMap);
	}

	// 게시물 추가
	public int add(int boardId, String title, String body, int memberId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("body = ?,", body);
		sql.append("boardId = ?,", boardId);
		sql.append("memberId = ?", memberId);

		return MysqlUtil.insert(sql);
	}

	// 게시물 수정
	public void articleModify(Map<String, Object> args) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");

		boolean needToUpdate = false;

		if (args.get("title") != null) {
			needToUpdate = true;
			sql.append(", title = ?", args.get("title"));
		}

		if (args.get("body") != null) {
			needToUpdate = true;
			sql.append(", body = ?", args.get("body"));
		}

		if (args.get("boardId") != null) {
			needToUpdate = true;
			sql.append(", boardId = ?", args.get("boardId"));
		}

		if (args.get("memberId") != null) {
			needToUpdate = true;
			sql.append(", memberId = ?", args.get("memberId"));
		}

		if (args.get("hitsCount") != null) {
			needToUpdate = true;
			sql.append(", hitsCount = ?", args.get("hitsCount"));
		}

		if (args.get("likesCount") != null) {
			needToUpdate = true;
			sql.append(", likesCount = ?", args.get("likesCount"));
		}

		if (args.get("unLikesCount") != null) {
			needToUpdate = true;
			sql.append(", unLikesCount = ?", args.get("unLikesCount"));
		}

		if (args.get("repliesCount") != null) {
			needToUpdate = true;
			sql.append(", repliesCount = ?", args.get("repliesCount"));
		}

		if (needToUpdate == false) {
			return;
		}

		sql.append("WHERE id = ?", args.get("id"));

		MysqlUtil.update(sql);

	}

	// 게시물 삭제
	public void articleDelete(int id) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);

		MysqlUtil.delete(sql);

	}

	// 게시판 리스트 가져오기
	public List<Board> getBoardsForPrint() {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM board");

		List<Board> boards = new ArrayList<>();
		List<Map<String, Object>> boardsMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> boardsMap : boardsMapList) {
			Board board = new Board(boardsMap);

			boards.add(board);
		}

		return boards;
	}

	// 게시판 번호와 게시물 번호가 일치하는 게시물 가져오기
	public Article getArticleByIdAndBoardId(int id, int boardId) {
		SecSql sql = new SecSql();

		sql.append("SELECT A.*");
		sql.append(", M.name AS extra_memberName");
		sql.append(", M.nickname AS extra_memberNickname");
		sql.append(", B.name AS extra_boardName");
		sql.append(", B.code AS extra_boardCode");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		sql.append("WHERE A.id = ?", id);
		sql.append("AND A.boardId = ?", boardId);

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		if (articleMap.isEmpty()) {
			return null;
		}

		return new Article(articleMap);
	}

	// 조회수 증가
	public void addView(int id) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO view");
		sql.append("SET");
		sql.append("viewArticleId = ?", id);

		MysqlUtil.insert(sql);
	}

	// 조회수 가져오기
	public int getViewCount(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(viewCount)");
		sql.append("FROM view");
		sql.append("WHERE viewArticleId = ?", id);

		return MysqlUtil.selectRowIntValue(sql);
	}

	// 게시물에 조회수 정보 업데이트
	public void addArticleHitsCount(Map<String, Object> args) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET");
		sql.append("hitsCount = ?", args.get("hitsCount"));
		sql.append("WHERE id = ?", args.get("id"));

		MysqlUtil.update(sql);

	}

	// 해당 회원이 좋아요한 게시물의 좋아요 수 가져오기
	public int getMemberArticleLikesCount(int memberId, int articleId) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(id)");
		sql.append("FROM `like`");
		sql.append("WHERE relTypeCode = ?", "article");
		sql.append("AND relId = ?", articleId);
		sql.append("AND memberId = ?", memberId);

		return MysqlUtil.selectRowIntValue(sql);
	}

	// 좋아요 수 증가
	public void addLikesCount(Map<String, Object> args) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `like`");
		sql.append("SET");
		sql.append("regDate = NOW()");
		sql.append(", updateDate = NOW()");

		if (args.get("relTypeCode") != null) {
			sql.append(", relTypeCode = ?", args.get("relTypeCode"));
		}

		if (args.get("relId") != null) {
			sql.append(", relId = ?", args.get("relId"));
		}

		if (args.get("memberId") != null) {
			sql.append(", memberId = ?", args.get("memberId"));
		}

		if (args.get("point") != null) {
			sql.append(", point = ?", args.get("point"));
		}

		MysqlUtil.insert(sql);

	}

	// 좋아요한 회원 정보 삭제
	public void removeLikeMember(int memberId, int articleId) {
		SecSql sql = new SecSql();

		sql.append("DELETE");
		sql.append("FROM `like`");
		sql.append("WHERE 1");
		sql.append("AND relTypeCode = ?", "article");
		sql.append("AND relId = ?", articleId);
		sql.append("AND memberId = ?", memberId);

		MysqlUtil.delete(sql);

	}

	// 해당 게시물의 좋아요 수 가져오기
	public int getArticleLikesCount(int articleId) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(id)");
		sql.append("FROM `like`");
		sql.append("WHERE relTypeCode = ?", "article");
		sql.append("AND relId = ?", articleId);
		sql.append("AND point = ?", 1);

		return MysqlUtil.selectRowIntValue(sql);
	}

	// 해당 게시물의 싫어요 수 가져오기
	public int getArticleUnLikesCount(int articleId) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(id)");
		sql.append("FROM `like`");
		sql.append("WHERE relTypeCode = ?", "article");
		sql.append("AND relId = ?", articleId);
		sql.append("AND point = ?", -1);

		return MysqlUtil.selectRowIntValue(sql);
	}

	// 댓글 추가
	public int addReply(int id, int memberId, String relTypeCode, String replyBody) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO reply");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", memberId = ?", memberId);
		sql.append(", relTypeCode = ?", relTypeCode);
		sql.append(", relId = ?", id);
		sql.append(", body = ?", replyBody);

		return MysqlUtil.insert(sql);

	}

	// 해당 게시물에 달린 댓글 리스트 가져오기
	public List<Reply> getArticleReplies(int id) {
		List<Reply> replies = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT R.*");
		sql.append(", M.nickname AS extra_memberNickname");
		sql.append("FROM reply AS R");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON R.memberId = M.id");
		sql.append("WHERE 1");
		sql.append("AND relTypeCode = ?", "article");
		sql.append("AND relId = ?", id);
		sql.append("ORDER BY R.id DESC");

		List<Map<String, Object>> repliesMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> repliesMap : repliesMapList) {
			Reply reply = new Reply(repliesMap);

			replies.add(reply);

		}
		// Collections.reverse(articles);
		return replies;
	}

	// 해당 게시물에 달린 댓글 수 가져오기
	public int getRepliesCountByArticleId(int id, String relTypeCode) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*) AS cnt");
		sql.append("FROM reply AS R");
		sql.append("WHERE 1");

		if (relTypeCode != null) {
			sql.append("AND R.relTypeCode = ?", relTypeCode);
		}

		if (id != 0) {
			sql.append("AND R.relId = ?", id);
		}

		return MysqlUtil.selectRowIntValue(sql);
	}

	// 정보가 추가된 댓글 리스트 가져오기
	public List<Reply> getRepliesForPrintByArticleId(int id, String relTypeCode, int pageLimitStartIndex,
			int repliesInAPage) {
		List<Reply> replies = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT R.*");
		sql.append(", M.nickname AS extra_memberNickname");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra_likePoint");
		sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra_likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra_dislikeOnlyPoint");
		sql.append("FROM reply AS R");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON R.memberId = M.id");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'reply'");
		sql.append("AND R.id = L.relId");
		sql.append("WHERE 1");

		if (relTypeCode != null) {
			sql.append("AND R.relTypeCode = ?", relTypeCode);
		}

		if (id != 0) {
			sql.append("AND R.relId = ?", id);
		}

		sql.append("GROUP BY R.id");
		sql.append("ORDER BY R.id DESC");

		if (repliesInAPage != -1) {
			sql.append("LIMIT ?, ?", pageLimitStartIndex, repliesInAPage);
		}

		List<Map<String, Object>> repliesMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> repliesMap : repliesMapList) {
			Reply reply = new Reply(repliesMap);

			replies.add(reply);

		}
		// Collections.reverse(articles);
		return replies;
	}

	// 댓글 번호로 댓글 정보 가져오기
	public Reply getReplyById(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT R.*");
		sql.append(", M.nickname AS extra_memberNickname");
		sql.append("FROM reply AS R");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON R.memberId = M.id");
		sql.append("WHERE R.id = ?", id);

		Map<String, Object> replyMap = MysqlUtil.selectRow(sql);

		if (replyMap.isEmpty()) {
			return null;
		}

		return new Reply(replyMap);
	}

	// 댓글 삭제
	public void replyDelete(int id) {
		SecSql sql = new SecSql();

		sql.append("DELETE");
		sql.append("FROM `reply`");
		sql.append("WHERE id = ?", id);

		MysqlUtil.delete(sql);

	}

	// 댓글 수정
	public void replyModify(Map<String, Object> args) {
		SecSql sql = new SecSql();

		sql.append("UPDATE reply");
		sql.append("SET updateDate = NOW()");

		boolean needToUpdate = false;

		if (args.get("body") != null) {
			needToUpdate = true;
			sql.append(", body = ?", args.get("body"));
		}

		if (args.get("memberId") != null) {
			needToUpdate = true;
			sql.append(", memberId = ?", args.get("memberId"));
		}

		if (needToUpdate == false) {
			return;
		}

		sql.append("WHERE id = ?", args.get("id"));

		MysqlUtil.update(sql);

	}

	// 해당 게시물에 달린 댓글 수 가져오기
	public int getArticleRepliesCount(int articleId) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(id)");
		sql.append("FROM `reply`");
		sql.append("WHERE relTypeCode = ?", "article");
		sql.append("AND relId = ?", articleId);

		return MysqlUtil.selectRowIntValue(sql);

	}

	// relTypeCode에 맞는 댓글 리스트 가져오기
	public List<Reply> getRepliesForPrintByRelTyopeCode(String relTypeCode, Member loginedMember) {
		List<Reply> replies = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT R.*");
		sql.append(", M.nickname AS extra_memberNickname");
		sql.append(", IFNULL(SUM(L.point), 0) AS extra_likePoint");
		sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra_likeOnlyPoint");
		sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra_dislikeOnlyPoint");
		sql.append("FROM reply AS R");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON R.memberId = M.id");
		sql.append("LEFT JOIN `like` AS L");
		sql.append("ON L.relTypeCode = 'reply'");
		sql.append("AND R.id = L.relId");
		sql.append("WHERE 1");

		if (relTypeCode != null) {
			sql.append("AND R.relTypeCode = ?", relTypeCode);
		}

		sql.append("GROUP BY R.id");

		sql.append("ORDER BY R.id DESC");

		List<Map<String, Object>> repliesMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> repliesMap : repliesMapList) {
			Reply reply = new Reply(repliesMap);

			replies.add(reply);

		}
		// Collections.reverse(articles);
		return replies;
	}

}