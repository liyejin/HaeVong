package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.Post;
import kr.co.heabong.web.entity.User;

public interface WishService {

	void add(User user, Post post);

	void remove(User user, Post post);

	boolean isWishedByUser(User user, Post post);

	List<Post> getWishedPosts(User user);

	List<Post> getWishedUser(Post post);

}
