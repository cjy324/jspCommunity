package com.sbs.example.jspCommunity.service;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.LikeDao;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.dto.Reply;

public class LikeService {
	
	private LikeDao likeDao;
	
	public LikeService() {
		likeDao = Container.likeDao;
	}

	public boolean actorCanLike(Object object, Member actor) {
		String relTypeCode = "";
		int relId = 0;
		
		if(object instanceof Article) {
			relTypeCode = "article";
			Article article = (Article) object;
			relId = article.getId();
		}
		
		if(object instanceof Reply) {
			relTypeCode = "reply";
			Reply reply = (Reply) object;
			relId = reply.getId();
		}
		
		return likeDao.getPoint(relTypeCode, relId, actor.getId()) == 0;
	}

	public boolean actorCanCancelLike(Object object, Member actor) {
		
		String relTypeCode = "";
		int relId = 0;
		
		if(object instanceof Article) {
			relTypeCode = "article";
			Article article = (Article) object;
			relId = article.getId();
		}
		
		if(object instanceof Reply) {
			relTypeCode = "reply";
			Reply reply = (Reply) object;
			relId = reply.getId();
		}
		
		
		return likeDao.getPoint(relTypeCode, relId, actor.getId()) > 0;
	}

	public boolean actorCanDislike(Object object, Member actor) {
		
		String relTypeCode = "";
		int relId = 0;
		
		if(object instanceof Article) {
			relTypeCode = "article";
			Article article = (Article) object;
			relId = article.getId();
		}
		
		if(object instanceof Reply) {
			relTypeCode = "reply";
			Reply reply = (Reply) object;
			relId = reply.getId();
		}
		return likeDao.getPoint(relTypeCode, relId, actor.getId()) == 0;
	}

	public boolean actorCanCancelDislike(Object object, Member actor) {
		
		String relTypeCode = "";
		int relId = 0;
		
		if(object instanceof Article) {
			relTypeCode = "article";
			Article article = (Article) object;
			relId = article.getId();
		}
		
		if(object instanceof Reply) {
			relTypeCode = "reply";
			Reply reply = (Reply) object;
			relId = reply.getId();
		}
		return likeDao.getPoint(relTypeCode, relId, actor.getId()) < 0;
	}

	public void setLikePoint(String relTypeCode, int relId, int actorId, int point) {
		if (point == 0) {
			likeDao.removePoint(relTypeCode, relId, actorId);
		} else {
			likeDao.setPoint(relTypeCode, relId, actorId, point);
		}
		
	}

}
