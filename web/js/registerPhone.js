/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* global firebaseConfig, firebase, phoneInput */
const firebaseConfig = {
    apiKey: "AIzaSyCBxmSsivZUsvHuILRElwTROat0MFkG8DA",
    authDomain: "f8demo-9e6de.firebaseapp.com",
    projectId: "f8demo-9e6de",
    storageBucket: "f8demo-9e6de.appspot.com",
    messagingSenderId: "1062836074174",
    appId: "1:1062836074174:web:3d4d996ca52634bcfad0ef",
    measurementId: "G-9D2RS9QDST"
};
firebase.initializeApp(firebaseConfig);

var confirmationResult; // Biến confirmationResult để lưu trữ kết quả xác thực

function sendVerificationCode() {
    phoneInput.setNumber(document.getElementById("phone").value);
    var formattedPhoneNumber = phoneInput.getNumber();
    var phoneNumber = document.getElementsByName("phone").value;
    var appVerifier = new firebase.auth.RecaptchaVerifier("registration-form-" + countRecaptcha);
    console.log(formattedPhoneNumber);

    firebase.auth().signInWithPhoneNumber(formattedPhoneNumber, appVerifier)
            .then(function (result) {
                confirmationResult = result; // Lưu kết quả xác thực vào biến confirmationResult

                console.log("Mã xác thực đã được gửi đi thành công");
            })
            .catch(function (error) {
                console.error("Lỗi gửi mã xác thực", error);
            });
    add_child();
}


function verifyCode() {
    var verificationCode = document.getElementsByName("authetication_code")[0].value;

    var name = document.getElementById("name").value;
    
    var phone = document.getElementById("phone").value.replaceAll(" ","");
    
    confirmationResult.confirm(verificationCode)
            .then(function (result) {
                console.log("Xác thực mã xác thực thành công", result);
                window.location = 'registerPhone?name=' + name + '&phone=' + phone;
                // Thực hiện các hành động tiếp theo
            })
            .catch(function (error) {
                console.error("Lỗi xác thực mã xác thực", error);
            });
}
var countRecaptcha = 0;
function add_child() {
    countRecaptcha++;
    var p = document.createElement("div");
    p.setAttribute("id", "registration-form-" + countRecaptcha);
    if (countRecaptcha > 1) {
        disCount = countRecaptcha - 1;
        var divRecaptcha = document.getElementById("registration-form-" + disCount);
        divRecaptcha.style.display = "none";
    }
    var div = document.getElementById("registration-form");
    div.appendChild(p);
}
function hideRecaptcha() {
    var divRecaptcha = document.getElementById("recaptcha");
    divRecaptcha.style.display = "none";
}

document.getElementsByClassName("submit")[0].addEventListener("click", function (event) {
    event.preventDefault();
    verifyCode();
});
