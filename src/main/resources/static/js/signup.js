window.addEventListener("load", function(e) {
	let sendButton = document.getElementById("send_email");
	let authButton = document.getElementById("auth_code");


	let orgLogin = document.querySelector(".org_login_confirm");
	orgLogin.disabled = true;

	let pwdAuth = false;


	//비밀번호
	let password1 = document.getElementById("pwd");
	let password2 = document.getElementById("pwd2");
	var error = document.getElementById("error");
	console.log(password1.tagName);
	console.log(password2.tagName);
	password2.addEventListener("input", (e) => {
		if (password1.value != e.target.value) {
			error.innerText = "일치하지 않음"
			error.classList.remove("blue");
			error.classList.add("red");
			pwdAuth = false;
		}
		else {
			error.innerText = "맞음"
			error.classList.remove("red");
			error.classList.add("blue");
			pwdAuth = true;
		}

		password1.addEventListener("input", (e) => {
			console.log('s')
			if (password2.value != e.target.value) {
				error.innerText = "일치하지 않음"
				error.classList.remove("blue");
				error.classList.add("red");
				pwdAuth = false;
			}
			else {
				error.innerText = "맞음"
				error.classList.remove("red");
				error.classList.add("blue");
				pwdAuth = true;
			}




		});
	});



	let code = null;
	let interval = null;

	let timer = document.querySelector(".timer");
	let minutes = 3;
	let seconds = 0;
	let emailAuth = false;

	function updateTimer() {
		// 시간을 2자리 숫자로 표시하기 위해 leadingZero 함수를 사용
		let displayMinutes = leadingZero(minutes);
		let displaySeconds = leadingZero(seconds);

		// 타이머 텍스트 업데이트
		timer.textContent = `${displayMinutes}:${displaySeconds}`;

		// 1초씩 감소
		if (seconds > 0) {
			seconds--;
		} else if (minutes > 0) {
			minutes--;
			seconds = 59;
		} else {
			// 타이머 종료 시 동작
			clearInterval(interval);
			// 원하는 동작 수행
			alert("타이머 종료");
			code = null;
		}
	}

	// leadingZero 함수: 숫자가 10보다 작을 경우 앞에 0을 추가하여 반환
	function leadingZero(number) {
		return number < 10 ? `0${number}` : number;
	}

	sendButton.onclick = function(e) {
		e.preventDefault();
		alert("메일 전송@");
		//이메일 인증


		let email = document.getElementById("email_input");
		let to = email.value;
		//타이머 시작

		updateTimer();
		interval = setInterval(updateTimer, 1000);
		//-------------------------------------------------



		fetch(`/api/email?email=${to}`)
			.then(result =>
				result.json()
			)
			.then(c => {
				code = c;
			})
	}

	authButton.onclick = (e) => {
		e.preventDefault();
		let inputCode = document.getElementById("code_input");

		if (code == inputCode.value) {
			alert("정답☆");
			authButton = null;
			emailAuth = true;
		}
		else {
			alert("오답");
			emailAuth = false;

		}
		
		console.log("sss");
		console.log(emailAuth);
		console.log(pwdAuth);
		
		if (pwdAuth&&emailAuth)
			orgLogin.disabled = false;
	
	}

})