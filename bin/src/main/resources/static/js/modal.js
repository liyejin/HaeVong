const more = document.getElementById('more_btn');
const modal = document.getElementById('modal');
const main = document.getElementById('main');
const close_btn = document.getElementById('close_btn');
function modalOn() {
    modal.style.display = 'flex';
}
function isModalOn() {
    return modal.style.display === 'flex';
}
function modalOff() {
    modal.style.display = 'none';
}
more.addEventListener('click', () => {
    modalOn();
});
modal.addEventListener('click', e => {
    const evTarget = e.target;
    if (!(evTarget.classList.contains("modal"))){ 
    modalOff();
    }
})
close_btn.addEventListener('click', ()=>{
    modalOff();
})

