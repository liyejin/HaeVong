package kr.co.heabong.web.entity;

import java.time.LocalDate;
import java.util.Date;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyOrgVolView {

    private int id;
    private String title;
    private Date regdate;
    private LocalDate date;
    private int capacity;
    private String content;
    private String roadAddress;
    private String address;
    private int orgId;
    private int districtId;
    private int metropolId;
    private int volCategoryId;
    private String photo;
    private int userId;
    private Date applyDate;
    private int status;

}
