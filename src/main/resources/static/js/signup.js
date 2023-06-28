document.addEventListener("DOMContentLoaded", function() {
  var emailInput = document.getElementById("emailInput");
  var submitButton = document.getElementById("submitButton");

  submitButton.addEventListener("click", function() {
    var email = emailInput.value;
    sendEmailVerification(email);
  });

  function sendEmailVerification(email) {
    // 이메일 주소를 서버로 전송하는 로직 구현
    // 서버에서 이메일 인증을 처리하는 API 호출 등

    // 예시: 콘솔에 이메일 주소 출력
    console.log("이메일 주소:", email);
  }
});