console.log(oData);
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };

var draggableElement = document.getElementById("map_category_box_title");
var box = document.getElementById("map_category_box");

var offsetX, offsetY;
var isDragging = false;

draggableElement.addEventListener("mousedown", function (event) {
  isDragging = true;
  /*- draggableElement.offsetLeft;*/
  offsetY = event.clientY;
  /*    - draggableElement.offsetTop;*/
});

document.addEventListener("mousemove", function (event) {
  if (isDragging) {
    var y = 887 - event.clientY;
    if (y <= 700 && y >= 100) box.style.height = y + "px";

    console.log(y);
  }
});

document.addEventListener("mouseup", function () {
  isDragging = false;
});

/*드래그 이벤트*/
// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

if (Array.isArray(oData)) {
  oData.forEach(function (element) {
    console.log(element);
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(element.roadAddress, function (result, status) {
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
        var iwContent = `<a href="/main?name=${element.name}">
					<div class="map_box" id="map_box">
						<ul>
							<li>${element.title}</li>
							<li>${element.address}</li>
						</ul>
					</div>
				</a>`;

        var infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
        });

        kakao.maps.event.addListener(marker, "mouseover", function () {
          infowindow.open(map, marker);
        });

        kakao.maps.event.addListener(marker, "mouseout", function () {
          infowindow.close();
        });

        kakao.maps.event.addListener(marker, "click", function () {});

        /*infowindow.open(map, marker);*/

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
      }
    });
  });
} else {
  console.log(oData);
  // 주소로 좌표를 검색합니다
  geocoder.addressSearch(oData.roadAddress, function (result, status) {
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
      var iwContent = `<a href="/main?name=${oData.name}">
					<div class="map_box" id="map_box">
						<ul>
							<li>${oData.name}</li>
							<li>${oData.address}</li>
						</ul>
					</div>
				</a>`;

      var infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
      });

      infowindow.open(map, marker);

      /*infowindow.open(map, marker);*/

      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      map.setCenter(coords);
    }
  });
}

function filter(x) {
  let url = "?name=" + element.innerText;
  location.href = url;
}

let selectCategoryTabSection = document.querySelector(".select_category_tab");
let leftTab = document.querySelector(".select_category.left");
let rightTab = document.querySelector(".select_category.right");

var categoryList = document.querySelector(".category_list");
var orgVolList = document.querySelector(".org_vol_list");

var selectElement = document.querySelector(".select_category_tab select");

selectCategoryTabSection.addEventListener("click", function (event) {
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
    categoryList.style.display = "grid";
    orgVolList.style.display = "none";
  }
});
