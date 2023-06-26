window.addEventListener("load", function(e) {
	let boxes = document.querySelectorAll(".status-select-box");
	let orgVolId = document.querySelector(".org-vol-id").dataset.orgVolId;
	/*	let status = 1;*/
	console.log(`orgVolId : ${orgVolId} `);

	for (let box of boxes) {
		box.onchange = function(e) {
			let userId = e.target.dataset.userId;
			const selectedOption = box.options[box.selectedIndex];
			console.log('Selected Option:', selectedOption.value);
			let status = selectedOption.value;
			let data = `orgVolId=${orgVolId}&userId=${userId}&status=${status}`;
			fetch(`http://localhost:8080/api/apply-org-vols`, {
				method: "PUT",
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				body: new URLSearchParams(data)
			})
				.then(response => response.text())
				.then(value => parseInt(value))
				.then(result => {
					console.log(`result:${result}`);
					let orgStatusParent = e.target.previousElementSibling.childNodes;
					let orgStatus = orgStatusParent[1];
					let orgStatusText = orgStatusParent[2];
					console.log(orgStatusParent);
					orgStatus.className = ""
					orgStatusText.innerText = "";
					if (result == 0) {
						console.log("대기상태로")
						orgStatus.classList.add(".org_status_waiting")
						orgStatus.innerText = "";
						orgStatus.innerText = "대기";
						console.log(orgStatusText.innerText);
					} else if (result == 1) {
						console.log("승인상태로")
						orgStatus.classList.add("org_status_approved");
						orgStatus.innerText = "";
						orgStatus.innerText = "승인";
						console.log(orgStatusText.innerText);

					} else if (result == 2) {
						console.log("거절상태로")
						orgStatus.classList.add("org_status_refused");
						orgStatus.innerText = "";
						orgStatus.innerText = "거절";
						console.log(orgStatusText.innerText);
					} else {
						console.log("저장실패");
					}
				})


		};
	}

})

