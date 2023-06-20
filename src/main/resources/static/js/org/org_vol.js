

window.addEventListener("load",function(e){
	let category = document.querySelector(".category");
	
	category.onclick = function(e){
		let id = e.target.value
		console.log(id);
		
		fetch(`http://localhost:8080/api/org?c=${id}`)
		.then(response=>response.json())
		.then(list=>{console.log(list)})
		
	};
});