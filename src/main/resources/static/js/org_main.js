function infoEditPage() {
  window.location.href = 'http://127.0.0.1:5501/html/org/info_edit.html';
}

window.onload = function () {
  function together() {
    let together = document.querySelector('.d_day_cnt1');
    
    let nowDate = new Date();
    let today = new Date(org.signUpDate);

    let gap = nowDate - today;
    console.log(gap);
    let result = Math.floor(gap / (1000 * 60 * 60 * 24));
    let togetherCount = `${result}`;
console.log(result);

    together.innerHTML = togetherCount;
  }
  
   function orgListLoad(url) {
      let orgList = document.querySelector(".ing_container");
   fetch(url)
      .then(response => response.json())
      .then(org => {
         console.log(org);
/*
         //비우기
         menuList.innerHTML = "";*/

         //채우기
         for (let menu of list) {
            let itemTemplate = `<section class="menu" >

                  <div>
                     <a href="detail">
                        <img src="/image/${menu.img}" alt="에스프레소" />
                     </a>
                  </div>
                  <h1>${menu.korName}</h1>
                  <h2>${menu.engName}</h2>
                  <div>${menu.price}</div>
                  <div>
                     <a href="detail?id=${menu.id}" class="icon icon-heart" ${menu.like?'icon-heart-fill':''}" ></a>
                     	<span th:text ="${menu.likeCount}">0</span>
                  </div>
                  <div>
                     <a href="#" class="icon icon-plus icon-lg">추가</a>
                     <a href="#" class="icon icon-cart icon-lg">장바구니</a>
                  </div>
               </section>`;
            menuList.insertAdjacentHTML("beforeend", itemTemplate);
         };
      });
}
  
  
  together();
};

