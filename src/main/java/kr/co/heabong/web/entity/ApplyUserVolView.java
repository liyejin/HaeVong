package kr.co.heabong.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyUserVolView {

    private int id;
    private int title;
    private int regdate;
    private int date;
    private int capacity;
    private int place;
    private int roadAddress;
    private int address;
    private int userId;
    private int districtId;
    private int metropolId;
    private int volCategoryId;
    private int content;
    private int writer;
    private int applyDate;

}
