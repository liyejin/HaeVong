// 북마크가 추가되고 삭제되는 역할만 한다.
let volList = document.querySelector(".vol_list");

if(volList)
volList.onclick = function(e) {
   let el = e.target;

   if (!el.classList.contains("bookmark"))
      return;

   let { orgVolId, userId } = el.dataset; // destructuring
   orgVolId = el.getAttribute("data-orgvol-id");
   console.log(orgVolId);
   console.log(userId);
   
   
//userId가 null일 경우 , 불 켜지는걸 막고   el = 북마크 on off
//el.preventDefault(); 

  if(userId != null){
   	e.preventDefault();  // e는 페이지 이동 = a태그의 클릭이벤트 
   	}

   	
   	

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