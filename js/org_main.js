function infoEditPage() {
  window.location.href = 'http://127.0.0.1:5501/html/org/info_edit.html';
}

window.onload = function () {
  function together() {
    console.log('dday');
    let together = document.querySelector('.d_day_cnt1');
    let today = new Date();
    let dday = new Date('May 2,2023, 00:00:00').getTime();
    let gap = today - dday;
    let result = Math.floor(gap / (1000 * 60 * 60 * 24));
    let togetherCount = `${result}`;

    together.innerHTML = togetherCount;
  }
  together();
};
