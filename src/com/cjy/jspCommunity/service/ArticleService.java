package com.cjy.jspCommunity.service;

import java.util.List;
import java.util.Map;

import com.cjy.jspCommunity.container.Container;
import com.cjy.jspCommunity.dao.ArticleDao;
import com.cjy.jspCommunity.dto.Article;
import com.cjy.jspCommunity.dto.Board;
import com.cjy.jspCommunity.dto.Member;
import com.cjy.jspCommunity.dto.Reply;

public class ArticleService {

	private ArticleDao articleDao;
	private LikeService likeService;

	public ArticleService() {
		articleDao = Container.articleDao;
		likeService = Container.likeService;
	}

	public List<Article> getArticlesForPrintByBoardId(int boardId, int pageLimitStartIndex, int articlesInAPage,
			String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesForPrintByBoardId(boardId, pageLimitStartIndex, articlesInAPage, searchKeywordType,
				searchKeyword);
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
		int memberLikesCount = articleDao.getMemberArticleLikesCount(memberId, articleId);

		if (memberLikesCount != 0) {
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

	public int addReply(int id, int memberId, String relTypeCode, String replyBody) {
		return articleDao.addReply(id, memberId, relTypeCode, replyBody);

	}

	public List<Reply> getArticleReplies(int id) {
		return articleDao.getArticleReplies(id);
	}

	public void removeLikeMember(int memberId, int articleId) {
		articleDao.removeLikeMember(memberId, articleId);

	}

	public int getRepliesCountByArticleId(int id, String relTypeCode) {
		return articleDao.getRepliesCountByArticleId(id, relTypeCode);
	}

	public List<Reply> getRepliesForPrintByArticleId(int id, String relTypeCode, int pageLimitStartIndex,
			int repliesInAPage, Member actor) {
		List<Reply> replies = articleDao.getRepliesForPrintByArticleId(id, relTypeCode, pageLimitStartIndex,
				repliesInAPage);

		if (actor != null) {
			for (Reply reply : replies) {
				updateInfoForPrint(reply, actor);
			}
		}

		return replies;
	}

	public Reply getReplyById(int id) {
		return articleDao.getReplyById(id);
	}

	public void replyDelete(int id) {
		articleDao.replyDelete(id);

	}

	public void replyModify(Map<String, Object> args) {
		articleDao.replyModify(args);

	}

	public int getArticleRepliesCount(int articleId) {
		return articleDao.getArticleRepliesCount(articleId);
	}

	public Article getForPrintArticleById(int id, Member actor) {
		Article article = articleDao.getArticleById(id);

		if (article == null) {
			return null;
		}

		if (actor != null) {
			updateInfoForPrint(article, actor);
		}

		return article;
	}

	private void updateInfoForPrint(Object object, Member actor) {

		if (object instanceof Article) {
			Article article = (Article) object;
			boolean actorCanLike = likeService.actorCanLike(article, actor);
			boolean actorCanCancelLike = likeService.actorCanCancelLike(article, actor);
			boolean actorCanDislike = likeService.actorCanDislike(article, actor);
			boolean actorCanCancelDislike = likeService.actorCanCancelDislike(article, actor);

			article.getExtra().put("actorCanLike", actorCanLike);
			article.getExtra().put("actorCanCancelLike", actorCanCancelLike);
			article.getExtra().put("actorCanDislike", actorCanDislike);
			article.getExtra().put("actorCanCancelDislike", actorCanCancelDislike);
		}

		if (object instanceof Reply) {
			Reply reply = (Reply) object;
			boolean actorCanLike = likeService.actorCanLike(reply, actor);
			boolean actorCanCancelLike = likeService.actorCanCancelLike(reply, actor);
			boolean actorCanDislike = likeService.actorCanDislike(reply, actor);
			boolean actorCanCancelDislike = likeService.actorCanCancelDislike(reply, actor);

			reply.getExtra().put("actorCanLike", actorCanLike);
			reply.getExtra().put("actorCanCancelLike", actorCanCancelLike);
			reply.getExtra().put("actorCanDislike", actorCanDislike);
			reply.getExtra().put("actorCanCancelDislike", actorCanCancelDislike);
		}

	}

	public int getArticlesCountBySearchKeyword(String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesCountBySearchKeyword(searchKeywordType, searchKeyword);
	}

	public List<Article> getArticlesForPrintBySearchKeyword(int pageLimitStartIndex, int articlesInAPage,
			String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesForPrintBySearchKeyword(pageLimitStartIndex, articlesInAPage, searchKeywordType,
				searchKeyword);
	}

	public List<Reply> getRepliesForPrintByRelTyopeCode(String relTypeCode, Member actor) {
		List<Reply> replies = articleDao.getRepliesForPrintByRelTyopeCode(relTypeCode, actor);

		if (actor != null) {
			for (Reply reply : replies) {
				updateInfoForPrint(reply, actor);
			}
		}

		return replies;
	}

}
