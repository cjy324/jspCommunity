package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class ArticleDao {

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
		if (boardId != 0) {
			sql.append("WHERE A.boardId = ?", boardId);
		}
		sql.append("ORDER BY A.id DESC");

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
	
	public void articleModify(Map<String, Object> modifyArgs) {
		SecSql sql = new SecSql();
		
		int id = (int) modifyArgs.get("id");
		String title = modifyArgs.get("title") != null ? (String) modifyArgs.get("title") : null;
		String body = modifyArgs.get("body") != null ? (String) modifyArgs.get("body") : null;
		
		sql.append("UPDATE article ");
		sql.append("SET ");
		sql.append("updateDate = NOW(), ");

		// 수정이 필요한지 여부 판단
		boolean needToModify = false;

		if (title != "") {
			// 만약, title값이 들어왔으면, 수정할 필요가 있음
			needToModify = true;
			sql.append("title = ? , ", title);
		}

		if (body != "") {
			// 만약, body값이 들어왔으면, 수정할 필요가 있음
			needToModify = true;
			sql.append("`body` = ? ", body);
		}
		
		sql.append("WHERE id = ? ", id);

		// 만약, needToModify값이 true이면 실행
		if (needToModify) {
			MysqlUtil.update(sql);
		}
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

	public int getArticlesCountByBoardId(int boardId) {
		SecSql sql = new SecSql();
		
		sql.append("SELECT COUNT(*) AS cnt");
		sql.append("FROM article AS A");
		
		if(boardId != 0) {
			sql.append("WHERE A.boardId = ?", boardId);
		}
		
		
		return MysqlUtil.selectRowIntValue(sql);
	}

}