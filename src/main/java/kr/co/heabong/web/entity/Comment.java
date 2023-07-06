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
public class Comment {
	private int id;
	private LocalDateTime date;
	private String content;
	private int userId;
	private int postId;
	private Integer parentId;
}
