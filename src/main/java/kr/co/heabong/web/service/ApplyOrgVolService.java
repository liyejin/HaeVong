package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.Post;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.VolCategory;

public interface ApplyOrgVolService {
	 void add(User user, Post post);
	    List <ApplyUser> getList(Post post);
}
