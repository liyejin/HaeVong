let volList = document.querySelector(".vol_list");
function orgVolListLoad(url) {
	let userId = null;
	if (document.querySelector("user-id") != null)
		userId = document.querySelector("user-id").value;

	fetch(url)
		.then(reponse => reponse.json())
		.then(orgVolList => {

			//비우기
			volList.innerHTML = "";

			//채우기
			for (let vol of volList) {
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
                           data-orgVol-id="${vol.id}"
						   data-user-id="${userId}"
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






volList.onclick = function(e) {
	let el = e.target;

	if (!el.classList.contains("bookmark"))
		return;


	if (el.classList.contains("active"))
		el.classList.remove("active");
	else
		el.classList.add("active");


	let { orgVolId, userId } = el.dataset; // destructuring
	orgVolId = el.getAttribute("data-orgvol-id");
	console.log(orgVolId);
	console.log(userId);
	e.preventDefault();

	// Like 삭제
	if (el.classList.contains("active")) {
		//let data = {orgVolId,userId};
		//let jsonData = JSON.stringify(data);
		fetch(`/api/wish/${orgVolId}/user/${userId}`, {
			method: "DELETE"
		})
			.then(response => response.json())
			.then(result => {
				if (json.success) {
					// 하트 불 끄기
					el.classList.remove("active");
				}
			});
	}
	
	
	// Like 추가
	else {
		let data = `oi=${orgVolId}&ui=${userId}`
		fetch("/api/wish", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			body: new URLSearchParams(data)
		})
			.then(response => response.json())
			.then(result => {
				if (result.success) {
					// 하트 불 밝히기
					el.classList.add("active");
				}
			});
	}
	
	
	
}