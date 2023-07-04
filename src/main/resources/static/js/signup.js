window.addEventListener("load", function(e){
	let sendButton = document.getElementById("send_email");
	let timer = document.querySelector(".timer");
	
	let code = null;
	
	sendButton.onclick = function(e){
	e.preventDefault();
	
	//이메일 인증


	let to=email.value;
	let email = document.getElementById("email_input");

		
	fetch(`/api/email?email=${to}`)
	.then(result =>{
		result.json();
	})
	.then(c =>{
		console.log("c = " +  c);
	})

	
	
	
		
		
		
		
		
		
		
		
		
	}
	
	
})