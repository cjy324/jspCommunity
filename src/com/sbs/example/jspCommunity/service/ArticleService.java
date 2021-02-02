package com.sbs.example.jspCommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.util.Util;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getArticlesForPrintByBoardId(int boardId, int pageLimitStartIndex, int articlesInAPage, String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesForPrintByBoardId(boardId, pageLimitStartIndex, articlesInAPage, searchKeywordType, searchKeyword);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);

	}

	public int add(int boardId, String title, String body, int memberId) {
		return articleDao.add(boardId, title, body, memberId);
	}

	public void articleModify(Map<String, Object> args) {
		articleDao.articleModify(args);

	}

	public void articleDelete(int id) {
		articleDao.articleDelete(id);

	}

	public List<Board> getBoardsForPrint() {
		return articleDao.getBoardsForPrint();
	}

	public int getArticlesCountByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);
	}

	public List<Article> getArticlesForPrintByBoardId(int boardId) {
		return articleDao.getArticlesForPrintByBoardId(boardId);
	}

	public Article getArticleByIdAndBoardId(int id, int boardId) {
		return articleDao.getArticleByIdAndBoardId(id, boardId);
	}

	public void addView(int id) {
		articleDao.addView(id); 
	}

	public int getViewCount(int id) {
		return articleDao.getViewCount(id);
	}

	public void addArticleHitsCount(Map<String, Object> args) {
		articleDao.addArticleHitsCount(args);
	}

	public boolean isAlreadylikeMember(int memberId, int articleId) {
		int memberLikesCount = articleDao.getMemberArticleLikesCount(memberId,articleId);
		
		if(memberLikesCount != 0) {
			return true;
		}

		return false;
	}

	public void addLikesCount(Map<String, Object> args) {
		articleDao.addLikesCount(args);
	}

	public int getArticleLikesCount(int articleId) {
		return articleDao.getArticleLikesCount(articleId);
	}

	public int getArticleUnLikesCount(int articleId) {
		return articleDao.getArticleUnLikesCount(articleId);
	}


	


}
