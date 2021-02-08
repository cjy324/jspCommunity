package com.sbs.example.jspCommunity.service;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.LikeDao;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Member;

public class LikeService {
	
	private LikeDao likeDao;
	
	public LikeService() {
		likeDao = Container.likeDao;
	}

	public boolean actorCanLike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getId(), actor.getId()) == 0;
	}

	public boolean actorCanCancelLike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getId(), actor.getId()) > 0;
	}

	public boolean actorCanDislike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getId(), actor.getId()) == 0;
	}

	public boolean actorCanCancelDislike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getId(), actor.getId()) < 0;
	}

	public void setLikePoint(String relTypeCode, int relId, int actorId, int point) {
		if (point == 0) {
			likeDao.removePoint(relTypeCode, relId, actorId);
		} else {
			likeDao.setPoint(relTypeCode, relId, actorId, point);
		}
		
	}

}
