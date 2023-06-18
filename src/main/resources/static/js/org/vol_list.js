document.addEventListener('DOMContentLoaded', function() {
  const modal = document.getElementById("modal");
  const modalButtons = document.querySelectorAll(".vol_list_edit_btn");
  function modalOn() {
    modal.style.display = 'flex';
  }

  modalButtons.forEach(function(button) {
    button.addEventListener('click', function() {
      modalOn();
    });
  });
});


function modalOff() {
    modal.style.display = 'none';
}
modal.addEventListener('click', e => {
    const evTarget = e.target;
    if (!(evTarget.classList.contains("modal"))){ 
    modalOff();
    }
})
close_btn.addEventListener('click', ()=>{
    modalOff();
})

