package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.Location;
import kr.co.heabong.web.entity.Post;
import kr.co.heabong.web.entity.PostCategory;
import kr.co.heabong.web.entity.PostCommentLikeView;
import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.repository.PostCommentLikeViewRepository;
import kr.co.heabong.web.repository.PostRepository;

@Service
public class PostServiceImp implements PostService{

	@Autowired
	private PostRepository repository;
	@Autowired
	private PostCommentLikeViewRepository postCommentLikeViewRepository;
	
	@Override
	public List<Post> getList() {
		List<Post> list = repository.findAll();
		return list;
	}

	@Override
	public Post get(int id) {
		Post post = repository.findbyId(id);
		return post;
	}

	@Override
	public Post write(Post post) {
		Post newOne = null;
		int id = post.getId();
		int result = repository.save(post);
		if(result ==1)
			newOne = repository.getLastOne();
		
		return newOne;
	}

	@Override
	public int delete(int id) {
		int result = repository.delete(id);
		return result;
	}

	@Override
	public Post update(Post post) {
		int update = repository.update(post);
		return repository.findbyId(post.getId());
	}

	@Override
	public List<Post> getListByCategory(PostCategory category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> likePost(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getListBySearch(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getListByLocation(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostCommentLikeView getPostCommentLikeViewByPostId(int postId) {
		PostCommentLikeView postCommentLikeView = postCommentLikeViewRepository.findByPostId(postId);
		return postCommentLikeView;
		
	}


	
}
