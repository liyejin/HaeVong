package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.Location;
import kr.co.heabong.web.entity.Post;
import kr.co.heabong.web.entity.VolCategory;

@Service
public class PostServiceImp implements PostService{

	@Override
	public List<Post> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getListByCategory(VolCategory category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post get(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post update(Post Post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> wishPost() {
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

	
}
