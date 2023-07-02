async function applyAdd(url,json) {

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


async function applyDelete(url,json) {
	
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

	let jsonData = {

		//키 값 (객체생성)
		"userId": userId,
		"orgVolId": orgVolId
	}
	

	let json = JSON.stringify(jsonData);

	let applyBtn = document.querySelector(".applybtn");
	let cancleBtn = document.querySelector(".canclebtn");

	applyBtn.onclick = function(e) {
		console.log("끄릭");
		applyAdd(`http://localhost:8080/api/apply-org-vols`,json);
	}

	cancleBtn.onclick = function(e) {
		console.log("취소끄릭");
		applyDelete(`http://localhost:8080/api/apply-org-vols`,json);
	}


});