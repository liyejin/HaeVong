package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.PostPhoto;
import kr.co.heabong.web.repository.PostPhotoRepository;

@Service
public class PostPhotoServiceImp implements PostPhotoService {
	@Autowired
	PostPhotoRepository postPhotoRepository;

	@Override
	public List<PostPhoto> getMyPostPhoto(int uid) {
		List<PostPhoto> myPostPhoto = postPhotoRepository.findByMyPostPhoto(uid);
		return myPostPhoto;
	}

}
