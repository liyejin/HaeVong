package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.entity.Location;
import kr.co.heabong.web.entity.Post;
import kr.co.heabong.web.entity.PostCategory;
import kr.co.heabong.web.entity.User;

public interface PostService {

	// 하나의 커뮤니티글 정보 가져오기
	Post get(int id);

	// 커뮤니티글 작성하기
	Post write(Post post);

	// 커뮤니티글 삭제하기
	int delete(int id);

	// 커뮤니티글 수정하기
	Post update(Post Post);

	// 봉사 커뮤니티글 리스트 가져오기
	List<Post> getList();

	// 카테고리 별로 커뮤니티글 리스트 가져오기
	List<Post> getListByCategory(PostCategory category);

	// 좋아요 한 커뮤니티글 리스트 가져오기
	List<Post> likePost(int userId);

	// 검색어 기준으로 커뮤니티글 리스트 가져오기
	List<Post> getListBySearch(String search);

	// 위치 기준으로 커뮤니티글 리스트 가져오기
	List<Post> getListByLocation(Location location);


}
