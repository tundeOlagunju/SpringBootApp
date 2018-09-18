'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

var textUploadForm = document.querySelector('#textUploadForm');
var textInput = document.querySelector('#textInput');
var textUploadError = document.querySelector('#textUploadError');
var textUploadSuccess = document.querySelector('#textUploadSuccess');

function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadFile");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

function uploadText(text) {
	var jsonData = {id: 1, text: text}; 
	var formData = JSON.stringify(jsonData); 

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadText");
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.onload = function() {
        console.log(xhr.responseText);
        if(xhr.status == 200) {
            textUploadError.style.display = "none";
            var content = "<p>Text Uploaded Successfully</p>";
            textUploadSuccess.innerHTML = content;
            textUploadSuccess.style.display = "block";
        } else {
        	textUploadSuccess.style.display = "none";
        	textUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);


textUploadForm.addEventListener('submit', function(event){
    var text = textInput.value;
    if(text.length === 0) {
        textUploadError.innerHTML = "Please enter text";
        textUploadError.style.display = "block";
    }
    uploadText(text);
    event.preventDefault();
}, true);

