let homeBtn = document.querySelector(".home-button");
let main = document.querySelector(".community_main");
let sectionPlanningVol = document.querySelector(".section_planning_vol");
let communityTopHaevongs = document.querySelectorAll("community-top-haevong");
homeBtn.onclick = (e) => {
	console.log("홈버튼");
	sectionPlanningVol.innerHTML = "";
	for (communityTopHaevong of communityTopHaevongs) {
		sectionPlanningVol.appendChild(communityTopHaevong);
		//나중에 배열에서 하나씩 꺼내서 넣어주면 되겠다
		communityTopHaevong.show(true);
	}
}