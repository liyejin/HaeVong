package kr.co.heabong.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyOrgVol {

	private Integer orgVolId;
	private Integer userId;
	private int status;
	private LocalDateTime date;

}
