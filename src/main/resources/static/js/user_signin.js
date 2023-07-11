window.addEventListener("load", () => {

	let userLoginForm = document.querySelector(".user_login_form");
	let orgLoginForm = document.querySelector(".org_login_form");

	/*	let errorTemplate = `<div th:if="${param.error}" >
							아이디 또는 비밀번호가 일치하지않습니다.
						</div> `;*/

	let tabBox = document.querySelector(".tab_box");
	tabBox.onclick = function(e) {

		if (e.target.classList.contains("user_tab")) {
			if (e.target.classList.contains("d-select"))
				e.target.classList.remove("d-select");
				console.log(e.target);
				e.target.style.borderColor="black black #FFFCF5 black";
			e.target.nextElementSibling.style.borderColor="#cccccc #cccccc black #cccccc";
			e.target.nextElementSibling.classList.add("d-select");
			userLoginForm.classList.remove("d-none");
			orgLoginForm.classList.add("d-none");
		} else if (e.target.classList.contains("org_tab")) {
			if (e.target.classList.contains("d-select"))
				e.target.classList.remove("d-select");
				e.target.style.borderColor="black black #FFFCF5 black";
			e.target.previousElementSibling.style.borderColor="#cccccc #cccccc black #cccccc";
			e.target.previousElementSibling.classList.add("d-select");
			orgLoginForm.classList.remove("d-none");
			userLoginForm.classList.add("d-none");
		}
	}

})


