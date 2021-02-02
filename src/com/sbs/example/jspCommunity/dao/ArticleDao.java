package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class ArticleDao {

	public List<Article> getArticlesForPrintByBoardId(int boardId, int pageLimitStartIndex, int articlesInAPage, String searchKeywordType, String searchKeyword) {
		List<Article> articles = new ArrayList<>();

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
		sql.append("WHERE 1");

		if (boardId != 0) {
			sql.append("AND A.boardId = ?", boardId);
		};

		if (searchKeyword != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword, searchKeyword);
			}
		};
		
		sql.append("ORDER BY A.id DESC");
		
		if ( articlesInAPage != -1 ) {
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

	public Article getArticleById(int id) {
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

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		if (articleMap.isEmpty()) {
			return null;
		}

		return new Article(articleMap);
	}

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

	public void articleModify(Map<String, Object> args) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		
		boolean needToUpdate = false;
		
		if(args.get("title") != null) {
			needToUpdate = true;
			sql.append(", title = ?", args.get("title"));
		}
		
		if(args.get("body") != null) {
			needToUpdate = true;
			sql.append(", body = ?", args.get("body"));
		}

		if(args.get("boardId") != null) {
			needToUpdate = true;
			sql.append(", boardId = ?", args.get("boardId"));
		}
		
		if(args.get("memberId") != null) {
			needToUpdate = true;
			sql.append(", memberId = ?", args.get("memberId"));
		}
		
		if(args.get("hitsCount") != null) {
			needToUpdate = true;
			sql.append(", hitsCount = ?", args.get("hitsCount"));
		}

		if(needToUpdate == false) {
			return;
		}

		sql.append("WHERE id = ?", args.get("id"));
		
		MysqlUtil.update(sql);
		
	}

	public void articleDelete(int id) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);

		MysqlUtil.delete(sql);

	}

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

	public int getArticlesCountByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*) AS cnt");
		sql.append("FROM article AS A");
		sql.append("WHERE 1");

		if (boardId != 0) {
			sql.append("AND A.boardId = ?", boardId);
		};

		if (searchKeyword != null) {
			if (searchKeywordType == null || searchKeywordType.equals("title")) {
				sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
			} else if (searchKeywordType.equals("titleAndBody")) {
				sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,
						searchKeyword);
			}
		};

		return MysqlUtil.selectRowIntValue(sql);
	}

	public List<Article> getArticlesForPrintByBoardId(int boardId) {
		List<Article> articles = new ArrayList<>();

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
		sql.append("WHERE 1");

		if (boardId != 0) {
			sql.append("AND A.boardId = ?", boardId);
		};
	
		sql.append("ORDER BY A.id DESC");

		List<Map<String, Object>> articlesMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articlesMap : articlesMapList) {
			Article article = new Article(articlesMap);

			articles.add(article);

		}
		// Collections.reverse(articles);
		return articles;
	}

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

	public void addView(int id) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO view");
		sql.append("SET");
		sql.append("viewArticleId = ?", id);

		MysqlUtil.insert(sql);
	}

	public int getViewCount(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(viewCount)");
		sql.append("FROM view");
		sql.append("WHERE viewArticleId = ?", id);
		
		return MysqlUtil.selectRowIntValue(sql);
	}

	public void addArticleHitsCount(Map<String, Object> args) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET");
		sql.append("hitsCount = ?", args.get("hitsCount"));
		sql.append("WHERE id = ?", args.get("id"));
		
		MysqlUtil.update(sql);
		
	}

	public void addLikeCount(int memberId, int articleId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO like");
		sql.append("SET");
		sql.append("likeArticleId = ?", articleId);
		sql.append(", likeMemberId = ?", memberId);

		MysqlUtil.insert(sql);
		
	}

	public int getLikesCountByMemberIdAndArticleId(int memberId, int articleId) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(likeCount)");
		sql.append("FROM like");
		sql.append("WHERE likeMemberId = ?", memberId);
		sql.append("AND likeArticleId = ?", articleId);
		
		return MysqlUtil.selectRowIntValue(sql);

	}

	public int getLikesCountByArticleId(int articleId) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(likeCount)");
		sql.append("FROM like");
		sql.append("WHERE likeArticleId = ?", articleId);
		
		return MysqlUtil.selectRowIntValue(sql);
	}

	public void addArticleLikesCount(Map<String, Object> args) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET");
		sql.append("likesCount = ?", args.get("likesCount"));
		sql.append("WHERE id = ?", args.get("id"));
		
		MysqlUtil.update(sql);
	}

}