console.log('s')
	  
  	let orgLogin=document.querySelector(".org_login_confirm");
 

  orgLogin.disabled= true;
  
  
  	let password1 = document.getElementById("pwd");
    let password2 = document.getElementById("pwd2");
    var error = document.getElementById("error");
    console.log('s')
    console.log('s')
    console.log(password1.tagName);
  	console.log(password2.tagName);
  	password2.addEventListener("input", (e)=>{
		  console.log('s')
	    if(password1.value != e.target.value){
			error.innerText = "일치하지 않음"
	    	error.classList.remove("blue");
	    	error.classList.add("red");
	    	orgLogin.disabled = true;
	    	}
	    	else{
			error.innerText = "맞음"
			error.classList.remove("red");
	    	error.classList.add("blue");
	    	orgLogin.disabled = false;
			}
			
			password1.addEventListener("input", (e)=>{
		  console.log('s')
	    if(password2.value != e.target.value){
			error.innerText = "일치하지 않음"
	    	error.classList.remove("blue");
	    	error.classList.add("red");
	    	orgLogin.disabled = true;
	    	}
	    	else{
			error.innerText = "맞음"
			error.classList.remove("red");
	    	error.classList.add("blue");
	    	orgLogin.disabled = false;
			}
			
			
			
			
  	});
			
			
  	});
  document.querySelector(".pwd");