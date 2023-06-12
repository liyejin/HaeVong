package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.entity.Location;
import kr.co.heabong.web.entity.Post;
import kr.co.heabong.web.entity.User;
@Service
public interface PostService {

	// 하나의 구인글 정보 가져오기
	Post get(Post post);

	// 구인글 작성하기
	void write(Post post);

	// 구인글 삭제하기
	void delete(Post post);

	// 구인글 수정하기
	Post update(Post Post);

	// 봉사 구인글 리스트 가져오기
	List<Post> getList();

	// 카테고리 별로 구인글 리스트 가져오기
	List<Post> getListByCategory(VolCategory category);

	// 좋아요 한 구인글 리스트 가져오기
	List<Post> wishPost();

	// 검색어 기준으로 구인글 리스트 가져오기
	List<Post> getListBySearch(String search);

	// 위치 기준으로 구인글 리스트 가져오기
	List<Post> getListByLocation(Location location);


}
