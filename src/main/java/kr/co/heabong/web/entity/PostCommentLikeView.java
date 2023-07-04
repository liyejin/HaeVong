package kr.co.heabong.web.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor 
public class PostCommentLikeView {
	
	private int postId;
	private LocalDateTime postDate;
	private int userId;				
	private String userNickname;
	private String userProfilePhoto;
	private String postTitle;
	private String postContent;
	private int postCategoryId;
	private String postCategoryName;
	private int commentsCount;
	private int likesCount;
}
