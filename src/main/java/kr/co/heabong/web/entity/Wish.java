package kr.co.heabong.web.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wish {

	private int userId;
	private int orgVolId;
	
}
