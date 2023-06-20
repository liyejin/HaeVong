function volListLoad(url) {

	let volList = document.querySelector(".vol_list");


	fetch(url)
		//`http://localhost:8080/api/org?c=${id}`
		.then(response => response.json())
		.then(list => {
			console.log(list);

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
	category.onclick = function(e) {
		let id = e.target.value
		console.log(id);
		
   volListLoad(`http://localhost:8080/api/org?c=${id}`);
	}
});
