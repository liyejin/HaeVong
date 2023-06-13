package kr.co.heabong.web.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ApplyOrgVol {
	
	private Integer orgVolId;
	private Integer userId;
	private Integer status;
	private LocalDateTime date;

}
