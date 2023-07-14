window.addEventListener("load", function() {
	// 파일 업로드 input
	let orgVolPhoto = document.querySelector(".org_vol_photo");
	let label = document.querySelector(".custum-file-upload");
	//등록하기 버튼
	let btn = document.querySelector(".btn_single");
	let file = null;


	orgVolPhoto.oninput = function(e) {
		file = e.target.files[0];
		console.log(file);
		let reader = new FileReader();
		reader.onload = function(event) {
			label.innerHTML = "";
			let img = document.createElement("img");
			img.src = event.target.result; // 바이너리 이미지
			console.log(img.src);
			img.style.objectFit = "contain";
			img.style.width = "100%";
			img.style.height = "100%";

			label.appendChild(img);
			img.style.zIndex = "10";
		};
		reader.readAsDataURL(file);
	};


	btn.onclick = function(e) {
		//페이지 이동과 데이터 전송 충돌을 막기 위함
		e.preventDefault();
		console.log("버튼클릭");


		(async () => {
			let newOne = null;

			{
				let form = document.querySelector(".org_vol_form");
				let inputs = form.elements;

				//orgVol 엔티티들
				let volCategoryId = inputs["volCategoryId"].value;
				let title = inputs["title"].value;
				let content = inputs["content"].value;
				let capacity = inputs["capacity"].value;
				let roadAddress = inputs["roadAddress"].value;
				let address = inputs["address"].value;
				let date = inputs["date"].value;
				let deadLine = inputs["deadline"].value;
				let orgId = inputs["org_id"].value;

				let formData = { title, date, capacity, content, roadAddress, address, orgId, volCategoryId, deadLine };
				let jsonData = JSON.stringify(formData);
				console.log(jsonData);

				let response = await fetch("http://localhost:8080/api/org-vols", {
					method: 'POST',
					headers: {
						"Content-Type": "application/json",
					},
					body: jsonData
				});

				// 그 결과가 완료 
				newOne = await response.json();
			}

			if (newOne.id) {
				if (!file)
					return;

				let formData = new FormData();
				formData.append("file", file);
				console.log(file);

				// api 매칭 경로
				let response = await fetch(`http://localhost:8080/api/file/org/${newOne.id}/image`, {
					method: 'PUT',
					body: formData
				});
			}
			 window.location.href = `http://localhost:8080/org/vol_post_detail?id=${newOne.id}`;
		})();
	};

});
