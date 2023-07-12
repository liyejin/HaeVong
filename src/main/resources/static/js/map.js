
let mapContainer = document.getElementById("map"), // 지도를 표시할 div

	mapOption = {
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level: 3, // 지도의 확대 레벨
	};


// 지도를 생성합니다
let map = new kakao.maps.Map(mapContainer, mapOption);

// 주소-좌표 변환 객체를 생성합니다
let geocoder = new kakao.maps.services.Geocoder();
let markers = [];
if (Array.isArray(oData)) {

	let draggableElement = document.getElementById("map_category_box_title");
	let box = document.getElementById("map_category_box");

	let offsetX, offsetY;
	let isDragging = false;
	draggableElement.addEventListener("mousedown", function(event) {
		isDragging = true;
		/*- draggableElement.offsetLeft;*/
		offsetY = event.clientY;
		/*    - draggableElement.offsetTop;*/
	});

	document.addEventListener("mousemove", function(event) {
		if (isDragging) {
			let y = 887 - event.clientY;
			if (y <= 700 && y >= 100) box.style.height = y + "px";
		}
	});

	document.addEventListener("mouseup", function() {
		isDragging = false;
	});
	/*드래그 이벤트*/


	oData.forEach(function(element) {
		console.log(element);
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(element.roadAddress, function(result, status) {
			console.log(kakao.maps.services.Status);
			// 정상적으로 검색이 완료됐으면
			if (status === kakao.maps.services.Status.OK) {
				let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				// 결과값으로 받은 위치를 마커로 표시합니다
				let marker = new kakao.maps.Marker({
					map: map,
					position: coords,
				});
				//마커 배열에 삽입(삭제 위함)
				markers.push(marker);
				/*let iwContent =document.getElementById("map_box");*/
				let iwContent = `<a href="/main?name=${element.name}">
					<div class="map_box" id="map_box">
						<ul>
							<li>${element.title}</li>
							<li>${element.address}</li>
						</ul>
					</div>
				</a>`;

				let infowindow = new kakao.maps.InfoWindow({
					content: iwContent,
				});

				kakao.maps.event.addListener(marker, "mouseover", function() {
					infowindow.open(map, marker);
				});

				kakao.maps.event.addListener(marker, "mouseout", function() {
					infowindow.close();
				});

				kakao.maps.event.addListener(marker, "click", function() { });

				/*infowindow.open(map, marker);*/

				// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				map.setCenter(coords);

			}
		});
	});
} else {
	console.log(oData);
	// 주소로 좌표를 검색합니다
	console.log(oData + "is one ");
	geocoder.addressSearch(oData.roadAddress, function(result, status) {
		console.log(kakao.maps.services.Status);
		// 정상적으로 검색이 완료됐으면
		if (status === kakao.maps.services.Status.OK) {
			let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			let marker = new kakao.maps.Marker({
				map: map,
				position: coords,
			});
			/*let iwContent =document.getElementById("map_box");*/
			let iwContent = `<a href="/main?name=${oData.name}">
					<div class="map_box" id="map_box">
						<ul>
							<li>${oData.name}</li>
							<li>${oData.address}</li>
						</ul>
					</div>
				</a>`;

			let infowindow = new kakao.maps.InfoWindow({
				content: iwContent,
			});

			infowindow.open(map, marker);

			/*infowindow.open(map, marker);*/

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		}
	});
}
let metropol = null;
let orgVolBox = document.querySelector(".org_vol_box");

function filter(x) {
	let cateListDist = document.querySelector(".category_list_district");

	metropol = x.innerText;
	let metId = x.dataset.id;
	console.log(metId);

	fetch(`/api/metropolView/${metId}`)
		.then(response => response.json())
		.then(list => {
			console.log(list);

			//비우기
			cateListDist.innerHTML = "";

			//채우기
			for (let mv of list) {

				let itemTemplate = `<li data-id="${mv.id}" class="distName"
				 onclick="OrgVolLoad(this)">${mv.districtName}</li>`;

				cateListDist.insertAdjacentHTML("beforeend", itemTemplate);
			};
		});

}
function OrgVolLoad(x) {

	let address = x.innerText;
	console.log(address);
	fetch(`/api/org-vols/${address}`)
		.then(response => response.json())
		.then(list => {
			console.log(list);

			//마커 삭제

			for (let marker of markers) {
				marker.setMap(null);
			}

			//마커 등록
			for (let ov of list) {
				geocoder.addressSearch(ov.roadAddress, function(result, status) {
					console.log(kakao.maps.services.Status);
					// 정상적으로 검색이 완료됐으면
					if (status === kakao.maps.services.Status.OK) {
						let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
						console.log("기관봉사 id : " + ov.id);
						// 결과값으로 받은 위치를 마커로 표시합니다
						let marker = new kakao.maps.Marker({
							map: map,
							position: coords,
							title: ov.id
						});

						console.log(marker.Gb);
						

						markers.push(marker);
						/*let iwContent =document.getElementById("map_box");*/
						let iwContent = `<a href="/main?name=${ov.title}">
					<div class="map_box" id="map_box">
						<ul>
							<li>${ov.title}</li>
							<li>${ov.roadAddress}</li>
						</ul>
					</div>
				</a>`;

						let infowindow = new kakao.maps.InfoWindow({
							content: iwContent,
						});

						infowindow.open(map, marker);
						/*infowindow.open(map, marker);*/

						kakao.maps.event.addListener(marker, 'click', function() {
							//org_vol_box 생성
						    if(orgVolBox.classList.contains("d-none"))
						    	orgVolBox.classList.remove("d-none");
						    	
						    	
						});
						
						// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
						map.setCenter(coords);
					}
				});


			};

		});
}

let selectCategoryTabSection = document.querySelector(".select_category_tab");
let leftTab = document.querySelector(".select_category.left");
let rightTab = document.querySelector(".select_category.right");

let categoryList = document.querySelector(".category_list");
let orgVolList = document.querySelector(".org_vol_list");

let selectElement = document.querySelector(".select_category_tab select");

selectCategoryTabSection.addEventListener("click", function(event) {
	console.log(event.target);
	let cl = event.target.classList;
	console.log(cl);
	if (cl.contains("right")) {
		rightTab.style.backgroundColor = "#FFFcF5";
		leftTab.style.backgroundColor = "#c1c1c1";
		categoryList.style.display = "none";
		orgVolList.style.display = "flex";

		selectElement.disabled = false;
	} else if (cl.contains("left")) {
		rightTab.style.backgroundColor = "#c1c1c1";
		leftTab.style.backgroundColor = "#FFFcF5";
		selectElement.disabled = true;
		categoryList.style.display = "flex";
		orgVolList.style.display = "none";
	}
});

//org_vol_box 보류(기능 물어봐야할듯)
let orgVolTemplate = `
					<div id="modal" class="d-none">
						<div id="delete">삭제하기</div>
					</div>
					<img class="vol_list_img" src="../img/org/org_vol_list_img.png" alt="모집공고이미지" />
					<div class="vol_list_info">
						<div class="vol_section">
							<span class="vol_id" hidden >id</span>
							<span class="vol_regdate">${ov.regdate}</span>
							<span class="vol_title">${ov.title}</span>
							<div class="vol_date_section">
								<span>봉사일자 : </span>
								<span class="vol_date">${ov.date}</span>
							</div>
						</div>
						<ul class="vol_list_btn_box_org">
							<li><a class="vol_list_detail_btn" href="#"
									th:href="@{./vol_post_detail(id=${vol.id})}">상세보기</a></li>
							<li><a class="vol_list_detail_btn" href="#" th:href="@{./vol_edit(id=${vol.id})}">수정하기</a>
							</li>
						</ul>
					</div>`;