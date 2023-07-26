/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', init);
            function init() {
                var input = document.querySelector('.input_img');
                var reader = new FileReader();

                // When the FileReader "load" event fires, update the image.
                reader.onload = function (e) {
                    document.getElementById("image").src = e.target.result;
//                    document.getElementById("file").src = e.target.result;
                };

                // Listen for when the input changes.
                input.addEventListener('change', onInputChange);
                function onInputChange(e) {
                    reader.readAsDataURL(this.files[0]);
                }
            }