window.addEventListener("load", function(e) {
	console.log("로드완료");
	let commentInput = document.querySelector(".comment-input");
	let submitBtn = document.querySelector(".comment-submit");
	// TODO comment-outer 에 afterbegin으로 html추가하기
	let commentOuter = document.querySelector(".comment-outer");



	submitBtn.onclick = (e) => {
		let content = commentInput.value;
		let userId = e.target.dataset.userId;
		let postId = e.target.dataset.postId;
		let data = {
			"content": content,
			"userId": userId,
			"postId": postId
		}
		let json = JSON.stringify(data);

		(async () => {
			let newOne = null;
			console.log("async 함수  실행");
			{
				let response = await fetch(`http://localhost:8080/api/comments`, {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: json
				})
				newOne=await response.json();
			}
			if(newOne)
			{
				console.log(newOne);
				let commentTemplate=
					`
						<div class="comment-content">
							<div class="comment-writer">
								<img src="/img/user/community/${newOne.profilePhoto}유저 프로필 사진.png" alt="프로필이미지&nbsp;&nbsp;">
								<span class="comment-name" >${newOne.nickname}&nbsp;&nbsp;</span>
								<span class="comment-mycomment">내 댓글</span>
							</div>
							<div class="comment-body" >
								${newOne.content}
							</div>
	
							<div class="comment-info">
								<span class="comment-days" >${newOne.daysAgo}</span>
								<span class="comment-replies"> 답글 1개 ·</span>
								<span class="comment-reply"> 답글 달기</span>
							</div>
						</div>
					`;
				let todayCommentTemplate=
				`
						<div class="comment-content">
							<div class="comment-writer">
								<img src="/img/user/community/${newOne.profilePhoto}유저 프로필 사진.png" alt="프로필이미지&nbsp;&nbsp;">
								<span class="comment-name" >${newOne.nickname}&nbsp;&nbsp;</span>
								<span class="comment-mycomment">내 댓글</span>
							</div>
							<div class="comment-body" >
								${newOne.content}
							</div>
	
							<div class="comment-info">
								<span class="comment-days" >오늘</span>
								<span class="comment-replies"> 답글 1개 ·</span>
								<span class="comment-reply"> 답글 달기</span>
							</div>
						</div>
					`	;
				if(newOne.daysAgo!==0)
					commentOuter.insertAdjacentHTML("afterbegin",commentTemplate);
				else 
					commentOuter.insertAdjacentHTML("afterbegin",todayCommentTemplate);
					
			};
		})();
	}


});
