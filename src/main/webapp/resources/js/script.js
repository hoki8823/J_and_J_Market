var toogleBtn = document.querySelector('.navbar_toogleBtn');
var menu = document.querySelector('.navbar_menu');
var icons = document.querySelector('.navbar_icons');

toogleBtn.addEventListener('click',function(){
    menu.classList.toggle('active');
    icons.classList.toggle('active');
});