
/*console.log(oData);
*/
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
	mapOption = {
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level: 3, // 지도의 확대 레벨
	};
// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);



// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
oData.forEach(function(element) {

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(element.roadAddress, function(result, status) {
		console.log(kakao.maps.services.Status);
		// 정상적으로 검색이 완료됐으면
		if (status === kakao.maps.services.Status.OK) {
			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords,
			});
			/*var iwContent =document.getElementById("map_box");*/
			var iwContent = 
				`<a href="/main?name=${element.name}">
					<div class="map_box" id="map_box">
						<ul>
							<li>${element.name}</li>
							<li>${element.roadAddress}</li>
						</ul>
					</div>
				</a>`
			// 인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new kakao.maps.InfoWindow({
				/*content: `<div style="width:150px;text-align:center;padding:6px 0;">${element.name}</div>`*/
				content: iwContent
			});
			infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		}
	});

})

var title = document.querySelector('.map_category_box_title');
var box = document.querySelector('.map_category_box');
title.addEventListener("drag",(e)=>{
	
	
	console.log("dragging");
	console.log(e.x);
	if(e.x>=100)
		box.style.height = e.offsetX+"px";
	
});






