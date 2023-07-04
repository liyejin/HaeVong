package kr.co.heabong.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.PostCommentLikeView;


@Mapper
public interface PostCommentLikeViewRepository{
	PostCommentLikeView findByPostId(int postId);

}