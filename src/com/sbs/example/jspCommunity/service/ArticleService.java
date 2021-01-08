package com.sbs.example.jspCommunity.service;

import java.util.List;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getArticlesForPrintByBoardId(int boardId) {
		return articleDao.getArticlesForPrintByBoardId(boardId);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
		
	}

	public int add(int boardId, String title, String body) {
		return articleDao.add(boardId, title, body);
	}

	public void articleModify(int id, String title, String body) {
		articleDao.articleModify(id, title, body);
		
	}

	public void articleDelete(int id) {
		articleDao.articleDelete(id);
		
	}
}
