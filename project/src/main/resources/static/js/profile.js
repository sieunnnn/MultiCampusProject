$(function(){
    $("#confirm").click(function(){
        modalClose(); //모달 닫기 함수 호출

        //컨펌 이벤트 처리
    });
    $("#close").click(function(){
        modalClose(); //모달 닫기 함수 호출
    });
    function modalClose(){
        $("#popup").fadeOut(); //페이드아웃 효과
    }
});
function modalOpen(){
    $("#popup").css('display','flex').hide().fadeIn();
    //팝업을 flex속성으로 바꿔준 후 hide()로 숨기고 다시 fadeIn()으로 효과
}

var submit = document.getElementById('confirm');
submit.onclick = showImage;     //확인 버튼 클릭시 이미지 보여주기

function showImage() {
    var newImage = document.getElementById('image-show').lastElementChild;
    newImage.style.visibility = "visible";

    // document.getElementById('image-upload').style.visibility = 'hidden';
    // var formData = new FormData();
    var formData = new FormData();
    formData.append('uploadFile', $("#chooseFile")[0].files[0]) ;

//    $.ajax({
//        type : "POST",
//        url : "/profile/saveImg",
//        processData: false,
//        contentType: false,
//        data : formData,
//        success : function(data) {
//            alert('업로드 성공');
//
//
//        },
//        error : function(xhr, status, error) {
//            alert(error);
//        }
//    });


    document.getElementById('fileName').textContent = null;     //기존 파일 이름 지우기
}


function loadFile(input) {
    var file = input.files[0];

    var name = document.getElementById('fileName');
    name.textContent = file.name;

    var newImage = document.createElement("img");
    newImage.setAttribute("class", 'img');

    newImage.src = URL.createObjectURL(file);

    newImage.style.width = "280px";
    newImage.style.height = "280px";
    newImage.style.borderRadius = "50%";
    newImage.style.visibility = "hidden";   //버튼을 누르기 전까지는 이미지 숨기기
    // newImage.style.objectFit = "contain";

    $('#image-show').empty().append(newImage);


};