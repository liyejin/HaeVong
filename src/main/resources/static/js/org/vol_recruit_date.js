window.onload = function () {
	console.log("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
function timeForToday() {
   let today = new Date();
   let timeValue = new Date(ovData.regdate);
   console.log(timeValue);
   let orgVolDate = document.getElementById("orgVolDate");

   let betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
   if (betweenTime < 1) {
      orgVolDate.innerText = '방금전';
   } else if (betweenTime < 60) {
      orgVolDate.innerText = `${betweenTime}분전`;
   } else {
      let betweenTimeHour = Math.floor(betweenTime / 60);
      if (betweenTimeHour < 24) {
         orgVolDate.innerText = `${betweenTimeHour}시간전`;
      } else {
         let betweenTimeDay = Math.floor(betweenTime / 60 / 24);
         if (betweenTimeDay < 365) {
            orgVolDate.innerText = `${betweenTimeDay}일전`;
         } else {
            orgVolDate.innerText = `${Math.floor(betweenTimeDay / 365)}년전`;
         }
      }
   }
}
timeForToday();
};



