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
  
   
  together();
};

