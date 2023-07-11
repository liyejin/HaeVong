function volListLoad(url) {
  let volList = document.querySelector(".vol_list");
  fetch(url)
    .then((response) => response.json())
    .then((list) => {
      volList.innerHTML = "";

      for (let vol of list) {
        let itemTemplate = `<li>
            <img
              class="vol_list_img"
              src="/img/org/org_vol/${vol.photo}"
              alt="모집공고이미지"
            />
            <div class="vol_list_info">
              <span class="vol_title">${vol.title}<br /></span>
              <span class="vol_date"> ${vol.date}</span>
    
              <ul class="vol_list_btn_box">
                <li>
                <button onclick="location.href='vol?id=${vol.id}'" class="vol_list_detail_btn">
                <span>상세보기</span>
              </button>
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
  let category = document.querySelector(".vol_category");
  let volDetailBtn = document.querySelector(".vol_list_detail_btn");

  category.onclick = function (e) {
    let status = null;

    if (e.target.className === "my_apply_vol") {
      status = 0;
      volListLoad(`/api/apply-org-vols?s=${status}`);
    } else if (e.target.className === "my_attend_vol") {
      status = 1;
      volListLoad(`/api/apply-org-vols?s=${status}`);
    }
  };
}); //addEventListener
