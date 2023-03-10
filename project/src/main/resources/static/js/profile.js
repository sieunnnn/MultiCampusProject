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