/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function add1() {
    const contain = document.querySelector(".content_bot_target");
    const inputTag = document.createElement('input');
    //style="width: 750px; height: 40px; margin-bottom: 10px" type="text" name="answer" value=""
    inputTag.style.width = '550px';
    inputTag.style.height = '26px';
    inputTag.style.marginBottom = '10px';
    inputTag.type = "text";
    inputTag.name = "target";
    inputTag.className = "target";
    contain.appendChild(inputTag);

}
function remove1() {
    const select = document.querySelector(".content_bot_target");
    var numOfInputTag = document.getElementsByClassName("target");
    
    if (numOfInputTag.length > 1) {
        select.removeChild(select.lastChild);
    }
}



function add2() {
    const contain = document.querySelector(".content_bot_chapter");
    const inputTag = document.createElement('input');
    //style="width: 750px; height: 40px; margin-bottom: 10px" type="text" name="answer" value=""
    inputTag.style.width = '550px';
    inputTag.style.height = '26px';
    inputTag.style.marginBottom = '10px';
    inputTag.style.marginRight='260px';
    inputTag.type = "text";
    inputTag.name = "chapter";
    inputTag.className = "chapter";
    contain.appendChild(inputTag);

}
function remove2() {
    const select = document.querySelector(".content_bot_chapter");
    var numOfInputTag = document.getElementsByClassName("chapter");
    
    if (numOfInputTag.length > 1) {
        select.removeChild(select.lastChild);
    }
}

function add3() {
    const contain = document.querySelector(".content_bot_requiment");
    const inputTag = document.createElement('input');
    //style="width: 750px; height: 40px; margin-bottom: 10px" type="text" name="answer" value=""
    inputTag.style.width = '550px';
    inputTag.style.height = '26px';
    inputTag.style.marginBottom = '10px';
    inputTag.type = "text";
    inputTag.name = "requiment";
    inputTag.className = "requiment";
    contain.appendChild(inputTag);

}
function remove3() {
    const select = document.querySelector(".content_bot_requiment");
    var numOfInputTag = document.getElementsByClassName("requiment");
    
    if (numOfInputTag.length > 1) {
        select.removeChild(select.lastChild);
    }
}