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
public class CommentUserView {
	private int id;
	private LocalDateTime date;
	private LocalDateTime editDate;
	int isUpdated;
	private String content;
	private int userId;
	private int postId;
	private int parentId;
	private String nickname;
	private String profilePhoto;
	private int daysAgo;
	private int childCount;
}
