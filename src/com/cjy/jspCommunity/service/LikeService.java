package com.cjy.jspCommunity.service;

import com.cjy.jspCommunity.container.Container;
import com.cjy.jspCommunity.dao.LikeDao;
import com.cjy.jspCommunity.dto.Article;
import com.cjy.jspCommunity.dto.Member;
import com.cjy.jspCommunity.dto.Reply;

public class LikeService {

	private LikeDao likeDao;

	public LikeService() {
		likeDao = Container.likeDao;
	}

	// 좋아요 가능한 회원인지 판별(기존 기록 없는지)
	public boolean actorCanLike(Object object, Member actor) {
		String relTypeCode = "";
		int relId = 0;

		if (object instanceof Article) { // article 객체로 치환 가능할 경우
			relTypeCode = "article";
			Article article = (Article) object;
			relId = article.getId();
		}

		if (object instanceof Reply) { // reply 객체로 치환 가능할 경우
			relTypeCode = "reply";
			Reply reply = (Reply) object;
			relId = reply.getId();
		}

		return likeDao.getPoint(relTypeCode, relId, actor.getId()) == 0;
	}

	// 좋아요 취소 가능한 회원인지 판별
	public boolean actorCanCancelLike(Object object, Member actor) {

		String relTypeCode = "";
		int relId = 0;

		if (object instanceof Article) {
			relTypeCode = "article";
			Article article = (Article) object;
			relId = article.getId();
		}

		if (object instanceof Reply) {
			relTypeCode = "reply";
			Reply reply = (Reply) object;
			relId = reply.getId();
		}

		return likeDao.getPoint(relTypeCode, relId, actor.getId()) > 0;
	}

	// 싫어요 가능한 회원인지 판별
	public boolean actorCanDislike(Object object, Member actor) {

		String relTypeCode = "";
		int relId = 0;

		if (object instanceof Article) {
			relTypeCode = "article";
			Article article = (Article) object;
			relId = article.getId();
		}

		if (object instanceof Reply) {
			relTypeCode = "reply";
			Reply reply = (Reply) object;
			relId = reply.getId();
		}
		return likeDao.getPoint(relTypeCode, relId, actor.getId()) == 0;
	}

	// 싫어요 취소 가능한 회원인지 판별
	public boolean actorCanCancelDislike(Object object, Member actor) {

		String relTypeCode = "";
		int relId = 0;

		if (object instanceof Article) {
			relTypeCode = "article";
			Article article = (Article) object;
			relId = article.getId();
		}

		if (object instanceof Reply) {
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
