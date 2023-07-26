/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let timeOut = null;
let slideIndex = 0;
showSlides();
function showSlides() {
    let i;
    let slides = document.getElementsByClassName("Slideshow_item__zjVUA");
    let dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "flex";
    dots[slideIndex - 1].className += " active";
    timeOut = setTimeout(showSlides, 4000); // Change image every 2 seconds
}
function dot0() {
    slideIndex = 0;
    clearTimeout(timeOut);
    showSlides();
}
function dot1() {
    slideIndex = 1;
    clearTimeout(timeOut);
    showSlides();
}
function dot2() {
    slideIndex = 2;
    clearTimeout(timeOut);
    showSlides();
}
function nextLeft() {
    if (slideIndex === 1)
        slideIndex = 2;
    else
        slideIndex -= 2;
    clearTimeout(timeOut);
    showSlides();
}
function nextRight() {
    if (slideIndex === 3)
        slideIndex = 0;
    clearTimeout(timeOut);
    showSlides();
}

