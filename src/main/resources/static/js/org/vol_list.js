function volListLoad(url) {

	let volList = document.querySelector(".vol_list");


	fetch(url)
		.then(response => response.json())
		.then(list => {
			console.log(list);
console.log(volList.childNodes)
			volList.innerHTML = "";
console.log(volList)
			for (let vol of list) {

				let itemTemplate =
					`	<li>
					<div id="modal" class="d-none">
					<div id="delete">삭제하기</div>
				</div>
				<img class="vol_list_img" src="../img/org/org_vol_list_img.png" alt="모집공고이미지" />
				<div class="vol_list_info">
					<div class="vol_section">
						<span class="vol_id" hidden>${vol.id}</span>
						<span class="vol_regdate"
							>${vol.regdate}</span>
						<span>${vol.title}<br /></span>
						<div class="vol_date_section">
							<span>봉사일자 : </span>
							<span class="vol_date">${vol.date}</span>
						</div>
					</div>
					<ul class="vol_list_btn_box_org">
						<li><a class="vol_list_detail_btn" href="./vol_post_detail?id=${vol.id}">상세보기</a></li>
						<li><a class="vol_list_detail_btn"href="./vol_post_edit?id=${vol.id}">수정하기</a></li>
					</ul>
					<!--<img class="vol_list_edit_btn" src="../img/icon/icon_vol_list_more.png" alt="모집공고_아이콘" />-->
				</div>
			</li>`
				volList.insertAdjacentHTML("beforeend", itemTemplate);
			};

		})
}

window.addEventListener("load", function(e) {

	let status = document.querySelector(".vol_status_section");
	status.onclick = function(e) {
		let el = e.target;
		let selected = status.querySelector(".status-button-selected")
		if (!e.target.classList.contains("status-button"))
			return;
		e.preventDefault();
		
		console.log(el);
		let s = e.target.dataset.s
		console.log(s);
		if (el.classList.contains("status-button-selected")){
		volListLoad(`http://localhost:8080/api/org-vols?o=1&s=${s}`);
			return;
		}
		else {
			volListLoad(`http://localhost:8080/api/org-vols?o=1&s=${s}`);
			el.classList.add("status-button-selected");
			selected.classList.remove("status-button-selected");
			console.log(el.classList);
		}
	}	
});

document.addEventListener('DOMContentLoaded', function() {
	const modal = document.getElementById("modal");
	const modalButtons = document.querySelectorAll(".vol_list_edit_btn");
	function modalOn() {
		modal.style.display = 'flex';
	}

	modalButtons.forEach(function(button) {
		button.addEventListener('click', function() {
			modalOn();
		});
	});
});


/*function modalOff() {
	modal.style.display = 'none';
}
modal.addEventListener('click', e => {
	const evTarget = e.target;
	if (!(evTarget.classList.contains("modal"))) {
		modalOff();
	}
})
close_btn.addEventListener('click', () => {
	modalOff();
})

*/