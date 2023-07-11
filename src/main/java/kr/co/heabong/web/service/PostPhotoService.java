package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.PostPhoto;

public interface PostPhotoService {
	List<PostPhoto> getMyPostPhoto(int uid);

}
