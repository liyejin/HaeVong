function volListLoad(url) {
  let volList = document.querySelector(".vol_list");

  fetch(url)
    //`http://localhost:8080/api/org?c=${id}`
    .then((response) => response.json())
    .then((list) => {
      console.log(list);

      volList.innerHTML = "";

	fetch(url)
		//`http://localhost:8080/api/org?c=${id}`
		.then(response => response.json())
		.then(list => {

			volList.innerHTML = "";

			for (let vol of list) {

				let itemTemplate =
					`<li>
				<img class="vol_list_img" src="/img/org/org_vol_list_img.png" alt="모집공고이미지" />
				<div class="vol_list_info">
						<span class="vol_title">${vol.title} <br /></span>
						<span class="vol_date">${vol.date}, 13:00~16:00</span>
						<span class="vol_write_date">${vol.regdate}</span>
						<ul class="vol_list_btn_box">
							<li><a class="vol_list_detail_btn" href="user/vol">상세보기</a></li>
						</ul>
					</div>
				</div>
				<img class="vol_list_edit_btn" src="/img/icon/icon_vol_list_more.png" alt="모집공고_아이콘" />
			</li>`
				volList.insertAdjacentHTML("beforeend", itemTemplate);
			};
		})
}






window.addEventListener("load", function(e) {

	let category = document.querySelector(".category");
	let submit = document.querySelector(".submit");
	
	
	category.onclick = function(e) {
		let id = e.target.value;
		console.log(id);
		let word = "";

		/*클릭 이벤트가 발생한 요소의 value 속성을 가져와,해당 값을 사용하여 서버로 http 요청을 보내는 역할*/
		volListLoad(`http://localhost:8080/api/org?c=${id}&s=${word}`);
	}

document.querySelector('form').addEventListener('submit', function(event) {
		event.preventDefault(); // 기본 동작인 폼 제출을 막음

		// 사용자가 검색한 내용을 가져오기 위해 입력 필드를 선택하고 값(value)을 읽어옴
		var searchInput = document.querySelector('input[name="sk"]');
		//사용자 검색어
		var searchValue = searchInput.value;

		// 검색어 값을 확인하기 위해 콘솔에 출력
		console.log('사용자가 검색한 내용:', searchValue);

		// 이후에 검색어 값을 활용하여 원하는 동작을 수행
		volListLoad(`http://localhost:8080/api/org?c=${id}&s=${searchValue}`);
		

	
	});
	
	
	
}








);
