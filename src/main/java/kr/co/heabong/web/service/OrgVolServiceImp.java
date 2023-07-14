package kr.co.heabong.web.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.heabong.web.entity.ApplyOrgVol;
import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.OrgVolAddressView;
import kr.co.heabong.web.entity.UserWishView;
import kr.co.heabong.web.repository.ApplyOrgVolRepository;
import kr.co.heabong.web.repository.OrgVolRepository;

@Service
public class OrgVolServiceImp implements OrgVolService {

	@Autowired
	private OrgVolRepository repository;
	@Autowired
	private ApplyOrgVolRepository applyOrgVolRepository;

	@Override
	public List<OrgVol> getAllList() {

		List<OrgVol> list = repository.findAllList();
		return list;
	}

	@Override
	public List<OrgVol> getList(Integer offset) {
		offset = (offset - 1) * 5;
		List<OrgVol> list = repository.findAll(offset);
		return list;
	}

	@Override
	public List<OrgVol> getOrgVolListByCategoryId(int categoryId, Integer offset) {
		offset = (offset - 1) * 5;
		List<OrgVol> list = repository.FindOrgVolListByCategoryId(categoryId, offset);
		return list;
	}

	@Override
	public List<OrgVol> getOrgVolListBySearch(Integer categoryId, String serchKeyword, Integer offset) {
		offset = (offset - 1) * 5;
		List<OrgVol> list = repository.FindOrgVolListBySearch(categoryId, serchKeyword, offset);
		return list;
	}

	@Override
	public List<OrgVol> getList(Integer offset, int size) {

		offset = (offset - 1) * 5;

		List<OrgVol> list = repository.findByOffsetAndSize(offset, 5);
		return list;

	}

	@Override
	public List<OrgVol> getListByAddress(String address) {

		List<OrgVol> list = repository.findByAddress(address);
		return list;
	}

	@Override
	public List<OrgVol> getList(String address) {
		return null;
	}

	@Override
	public OrgVol getById(int id) {
		OrgVol orgVol = repository.findById(id);

		return orgVol;

	}

	public List<OrgVol> getList(int orgId, String status) {
		// Org id와 상태로 OrgVol 리스트 가져오기
		List<OrgVol> list = repository.findByOrgIdAndStatus(orgId, status);

		return list;
	}

	@Override
	public int save(OrgVol orgVol) {
		int save = repository.save(orgVol);
		return save;
	}

	// My page category section ----------------------------
	@Override
	public List<OrgVol> getMyApplyOrgVolList(int userId) {
		List<OrgVol> list = repository.FindMyApplyOrgVolListById(userId);

		return list;
	}

	@Override
	public OrgVol get() {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public int delete(int id) {
		int delete = repository.delete(id);
		return delete;
	}

	@Override
	public int edit(OrgVol orgVol) {
		int edit = repository.update(orgVol);
		return edit;
	}

	/* 기관 사진 */
	@Override
	public List<String> getPhotoList(int orgId) {
		List<String> photoList = repository.getPhotoList(orgId);
		return photoList;
	}

	// 게시글 작성 시간
	@Override
	public int calculateRestDate(String dateString) {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate date = LocalDate.parse(dateString, formatter);

		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate nowDate = currentTime.toLocalDate();

		Period period = Period.between(nowDate, date);

		int restDate = period.getDays() + (period.getMonths() * 30);

		return restDate;
	}

	@Override
	public int calculateDeadLineDate(String dateString, String deadLine) {

		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate date = LocalDate.parse(dateString, formatter);
		LocalDate deadLineDate = LocalDate.parse(deadLine, formatter);

		// 날짜 차이 구해주는 메서드
		long daysDiff = ChronoUnit.DAYS.between(date, deadLineDate);

		int diff = (int) daysDiff;

		return diff;
	}

	@Override
	public int ingOrgVol(int orgId) {
		int ingCount = repository.findIngOrgVol(orgId);
		return ingCount;
	}

	// 로그인이 된상태에서 메인에서 검색
	@Override
	public List<UserWishView> getVolListBySearch(Integer userId, String searchKeyword, Integer offset) {

		offset = (offset - 1) * 6;

		return repository.findViewAll(userId, null, searchKeyword, null, offset);
	}

	// 비로그인 상태에서 메인에서 검색

	@Override
	public List<UserWishView> getVolListBySearchNotLogin(String searchKeyword, Integer offset) {
		offset = (offset - 1) * 6;

		return repository.findViewAll(null, null, searchKeyword, null, offset);
	}

	@Override
	public List<UserWishView> getView(Integer userId, Integer offset) {
		// TODO Auto-generated method stub
		offset = (offset - 1) * 6;
		return repository.findViewAll(userId, null, null, null, offset);
	}

	@Override
	public List<UserWishView> getView(Integer userId, Integer volCategoryId, String searchKeyword, Integer offset) {
		// TODO Auto-generated method stub
		offset = (offset - 1) * 6;
		return repository.findViewAll(userId, volCategoryId, searchKeyword, null, offset);
	}

	@Override
	public List<UserWishView> getViewOrgVolListByCategoryId(Integer userId, Integer categoryId, Integer offset) {
		// TODO Auto-generated method stub
		offset = (offset - 1) * 6;
		return repository.findViewAll(userId, categoryId, null, null, offset);
	}

	@Override
	public List<UserWishView> getViewOrgVolListBySearch(Integer categoryId, String searchKeyword, Integer offset) {
		// TODO Auto-generated method stub
		offset = (offset - 1) * 6;
		return repository.findViewAll(null, categoryId, searchKeyword, null, offset);
	}

	@Override
	public List<UserWishView> getViewOrgVolByOrgVolId(Integer userId, Integer orgVolId, Integer offset) {

		offset = (offset - 1) * 6;

		return repository.findViewAll(userId, null, null, orgVolId, offset);
	}

	@Override
	public UserWishView getViewById(Integer id) {
		// TODO Auto-generated method stub
		return repository.findViewById(id);
	}

	@Override
	public int getBooKmarkUser(int OrgVolId) {
		// TODO Auto-generated method stub
		return repository.countBookmarkUser(OrgVolId);
	}

	@Override
	public List<OrgVolAddressView> getListByRandom() {

		return repository.findOrgVolRand();
	}

	@Override
	public boolean checkApply(int id, int orgVolId) {

		ApplyOrgVol applyOrgVol = applyOrgVolRepository.get(orgVolId, id);

		if (applyOrgVol != null)
			return true;

		return false;
	}

	@Override
	public int getMetHaeVong(int orgId) {

		return repository.countHaeVong(orgId);
	}

}
