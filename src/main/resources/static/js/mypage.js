function volListLoad(url) {
  let volList = document.querySelector(".vol_list");
  fetch(url)
    .then((response) => response.json())
    .then((list) => {
      console.log(list);

      volList.innerHTML = "";

      for (let vol of list) {
        let itemTemplate = `<li>
            <img
              class="vol_list_img"
              src="/img/org/org_vol_list_img.png"
              alt="모집공고이미지"
            />
            <div class="vol_list_info">
              <span class="vol_title">${vol.title}<br /></span>
              <span class="vol_date"> ${vol.date}, 13:00~16:00</span>
              <span class="vol_write_date">${vol.regdate}</span>
              <ul class="vol_list_btn_box">
                <li>
                <a class="vol_list_detail_btn" href="user/vol">상세보기</a>
                </li>
              </ul>
            </div>

            <img
              class="vol_list_edit_btn"
              src="/img/icon/icon_vol_list_more.png"
              alt="모집공고_아이콘"
            />
          </li>`;

        volList.insertAdjacentHTML("beforeend", itemTemplate);
      }
    });
} //volListLoad

window.addEventListener("load", function (e) {
  let category = document.querySelector(".category");
  category.onclick = function (e) {
    let status = null;
    if (e.target.className === "my_apply_vol") {
      status = 0;
      console.log(status);
    } else if (e.target.className === "my_attend_vol") {
      status = 1;
      console.log(status);
    }
    volListLoad(`/api/apply-org-vols?s=${status}`);
  };
}); //addEventListener
