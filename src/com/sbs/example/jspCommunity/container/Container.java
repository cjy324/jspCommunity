package com.sbs.example.jspCommunity.container;

import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.service.ArticleService;

public class Container {

	public static ArticleService articleService;
	public static ArticleDao articleDao;

	static {
		articleDao = new ArticleDao();
		articleService = new ArticleService();
	}
}
