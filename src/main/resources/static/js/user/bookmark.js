/*window.addEventListener("load", function(e) {
  let bookmarkIcons = document.querySelectorAll(".bookmark");

  bookmarkIcons.forEach(function(bookmarkIcon) {
	bookmarkIcon.onclick = function() {
	  if (bookmarkIcon.classList.contains("bookmark-off")) {
		console.log("북마크");
		let bookmarkOnIcon = bookmarkIcon.nextElementSibling;
		bookmarkOnIcon.style.display = 'block';
	  }
	};
  });
});*/

let orgVolList = document.querySelector(".org_vol_list");
// 버튼 요소를 선택합니다.
const bookmarkButton = document.querySelector('.bookmark');


// 북마크 버튼 ------------------------------------------------------------
function orgVolListLoad(url) {
	let userId = null;
	if (document.querySelector("user-id") != null)
		userId = document.querySelector("user-id").value;

	fetch(url)
		.then(reponse => reponse.json())
		.then(orgVolList => {

			//비우기
			orgVolList.innerHTML = "";

			//채우기
			for (let vol of orgVolList) {
				let itemTemplate =
					`
						<ul class="vol_list">
				<li class="org_vol_list">
					<img class="vol_list_img" src="/img/org/org_vol/ ${vol.photo}}" alt="모집공고이미지" />
					<div class="vol_list_info">
						<span class="vol_title">${vol.title}<br></span>
						<span class="vol_date" >${vol.date}</span>
						<span class="vol_write_date">${vol.regdate}</span><br>
						
						<a href="/login">
						<button  
						type="button" 
						   data-user-id="${userId}"
                           data-orgVol-id="${vol.id}"
                           class="bookmark" ${vol.bookmark ? 'active' : ' '}" >
                          </button >
						 </a >

					<ul class="vol_list_btn_box">
						<li><a class="vol_list_detail_btn" href="/user/vol?id=${vol.id}">상세보기</a></li>
					</ul>
					</div >
		</div >
		</li >
		</ul >
					`;
				orgVolList.insertAdjacentHTML("beforeend", itemTemplate);
			}
		});
}






orgVolList.onclick = function(e) {
	let el = e.target;


	// 클릭 이벤트 리스너를 추가합니다.
	bookmarkButton.addEventListener('click', function() {
		console.log("ㅋㅋㅋㅋ");
		// 현재 버튼의 background-image 값을 확인합니다.
		const currentImage = getComputedStyle(bookmarkButton).backgroundImage;

		// 새로운 이미지 경로를 설정합니다.
		const newImage = 'url(/img/icon/boomark-fill.svg)';

		// 현재 이미지와 새로운 이미지를 비교하여 다를 경우에만 변경합니다.
		if (currentImage !== newImage) {
			bookmarkButton.style.backgroundImage = newImage;
		}
	});


	if (!el.classList.contains("bookmark"))
		return;

	let {orgVolId, userId} = el.dataset; // destructuring

	// 회원 아니면 return
	if (userId == 'null')
		return;
	e.preventDefault();

	// Like 삭제
	if (el.classList.contains("active")) {
		//let data = {orgVolId,userId};
		//let jsonData = JSON.stringify(data);
		fetch(`/ api / Wish / ${orgVolId} /user/${userId} `, {
			method: "DELETE",
		})
			.then(response => response.text())
			.then(value =>parseInt(value))
			.then(result => {
				if (result == 1) {
					// 하트 불 끄기
					el.classList.remove("active");

				}
			});
	}
	// Like 추가
	else {
		let data = `ui=${userId}&oi=${orgVolId}`
		fetch("/api/wish",{
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			body : new URLSearchParams(data)
		})
			.then(response => response.text())
			.then(value =>parseInt(value))
			.then(result => {
				if (result == 1) {
					// 하트 불 밝히기
					el.classList.add("active");
				}
			});
	}
}








