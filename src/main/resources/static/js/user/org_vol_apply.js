
async function applyAdd(url, json) {
	//신청이 됐을때 userid랑 orgid가 넘어와야함
	let response = await fetch(url, {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: json
	});
	console.log(response);
}

//신청확인 버튼을 누를시 패치가 작동됨


async function applyDelete(url, json) {
	let response = await fetch(url, {
		method: "DELETE",
		headers: {
			"Content-Type": "application/json"
		},
		body: json
	});
}


window.addEventListener("load", function(e) {
	let userId = document.querySelector(".userId").value;
	let orgVolId = document.querySelector(".orgVolId").value;
	console.log(orgVolId);
	console.log(userId);

	let jsonData = {
		//키 값 (객체생성)
		"userId": userId,
		"orgVolId": orgVolId
	}

	let json = JSON.stringify(jsonData);
	let applyBtn = document.querySelector(".applybtn");
	let cancleBtn = document.querySelector(".canclebtn");
	let bookmark = document.querySelector(".bookmark");

	applyBtn.onclick = function(e) {
		console.log("끄릭");
		applyAdd(`http://localhost:8080/api/apply-org-vols`, json);
	}

	cancleBtn.onclick = function(e) {
		console.log("취소끄릭");
		applybtn.style.display = "block"; 	//확인
		canclebtn.style.display = "none"; //취소
		applyDelete(`http://localhost:8080/api/apply-org-vols`, json);
	}



	/*북마크 버튼*/
	bookmark.onclick = function(e) {
		console.log("클릭클릭");

		let el = e.target;

		if (!el.classList.contains("bookmark"))
			return;

		console.log(orgVolId);
		console.log(userId);

		if (userId != null)
			e.preventDefault();

		// Like 삭제
		if (el.classList.contains("active")) {
			console.log("delete요청");
			//let data = {orgVolId,userId};
			//let jsonData = JSON.stringify(data);
			fetch(`/api/wish/${orgVolId}/user/${userId}`, {
				method: "DELETE"
			})
				.then(response => response.json())
				.then(result => {
					if (result) { // result 에 success 라는 속성이 없어서 falsy가 되니까 안탄거다.
						// 하트 불 끄기
						el.classList.remove("active");
						console.log("불끄기");
					}
				});
		}

		// bookmark 추가
		else {
			console.log("post요청");
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
					if (result) {
						// 하트 불 밝히기
						el.classList.add("active");
						console.log("불켜기");
					}
				});
		}

	}

}); //window