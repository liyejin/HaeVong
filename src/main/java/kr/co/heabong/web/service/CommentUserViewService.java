package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.CommentUserView;

public interface CommentUserViewService {
	List<CommentUserView> getListByPostId(int postId);
	List<CommentUserView> getReplyListByParentId(int parentId);
	CommentUserView getLastOne();
	List<Integer> getReplyCounts(int postId,int parentId);
}
