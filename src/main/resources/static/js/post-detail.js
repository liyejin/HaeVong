window.addEventListener("load", function(e) {
	console.log("로드완료");
	let commentInput = document.querySelector(".comment-input");
	let submitBtn = document.querySelector(".comment-submit");
	// TODO comment-outer 에 afterbegin으로 html추가하기
	let commentOuter = document.querySelector(".comment-outer");
	let replySections = document.querySelectorAll(".reply-section");
	let replyOuters = document.querySelectorAll(".reply-outer");
	console.log(replyOuters);
	let replyOpenBtns = document.querySelectorAll(".reply-open-button");
	let replyCountTexts = document.querySelectorAll(".comment-replies");
	console.log(replyOpenBtns);
	let comments = document.querySelectorAll(".comment");
	console.log(comments);

	for (let i = 0 ; i<replyOpenBtns.length; i++) {
		let isOpen = false;
		//let commentOuter = commentOuters[i];
		let replyOpenBtn = replyOpenBtns[i];
		let replyOuter = replyOuters[i];
		let replySection = replySections[i];
		let childCountText = replyCountTexts[i].innerText;
		console.log(childCountText);
		console.log("childCountText : " + childCountText)
		replyOpenBtn.onclick = async (e) => {
			e.preventDefault();
			console.log("답글 보기");
			console.log(e.target);
			let id = e.target.dataset.commentId;
			console.log(id);
			if (isOpen) {
				 // removeReply 호출
				 await removeReply(e,childCountText,replySection);
				isOpen = false; // 상태 변경
			} else {
				let replyList = await loadReply(e,id, replySection); // loadReply 호출
				isOpen = true; // 상태 변경
			}
		};
	}


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
				newOne = await response.json();
			}
			if (newOne) {
				console.log(newOne);
				let commentTemplate =
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
				let todayCommentTemplate =
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
								<span class="comment-replies"> 답글 0개 ·</span>
								<span class="comment-reply"> 답글 달기</span>
							</div>
						</div>
					`	;
				if (newOne.daysAgo !== 0)
					commentOuter.insertAdjacentHTML("afterbegin", commentTemplate);
				else
					commentOuter.insertAdjacentHTML("afterbegin", todayCommentTemplate);

			};
		})();
	}

});

async function loadReply(e,id, outer) {
	response = await fetch(`http://localhost:8080/api/comments/${id}/replies`);
	list = await response.json();

	for (let reply of list) {
		let template = `
				<div class="reply-outer">
					<div class="reply">
						<div class="comment-writer">
							<img src="/img/user/community/${reply.profilePhoto}" alt="프로필이미지">
							<span class="comment-name">${reply.nickname}</span>
						</div>
						<div class="comment-body">
							${reply.content}
						</div>
						<div class="comment-info">
							<span class="comment-days">${reply.daysAgo}일 전</span>
						</div>
					</div>
					<img src="/img/icon/icon_header_more.png" alt="더보기아이콘">
				</div>
					`
		outer.insertAdjacentHTML("beforeend", template);
	e.target.innerText="댓글 접기";
	}
}
async function removeReply(e,childCountText,outer) {
	// 해당 답글을 감싸는 부모 요소를 찾습니다.
	outer.innerHTML="";
	e.target.innerText=childCountText;
}

