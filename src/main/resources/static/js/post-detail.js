window.addEventListener("load", function(e) {
	console.log("로드완료");
	let commentSection = document.querySelector(".comment-section");
	let commentInput = document.querySelector(".comment-input");
	let submitBtn = document.querySelector(".comment-submit");
	let userId = submitBtn.dataset.userId;
	let postId = submitBtn.dataset.postId;
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
	let commentReplies = document.querySelectorAll(".comment-replies");

	let replyInputOuters = document.querySelectorAll(".reply-input-outer");
	let replyInputOpenBtns = document.querySelectorAll(".reply-input-open-button");
	// 댓글 각 요소들에 대해 해야할 것들
	for (let i = 0; i < replyOpenBtns.length; i++) {
		let isOpen = false;
		//let commentOuter = commentOuters[i];
		let replyOpenBtn = replyOpenBtns[i];
		let replyOuter = replyOuters[i];
		let replySection = replySections[i];
		let childCountText = replyCountTexts[i].innerText;
		let replyInputOuter = replyInputOuters[i];
		let replyInputOpenBtn = replyInputOpenBtns[i];
		let commentReply = commentReplies[i];
		let comment = comments[i];
		let commentId = comment.dataset.commentId;
		console.log("commentId" + commentId);
		console.log(childCountText);
		console.log("childCountText : " + childCountText)
		replyOpenBtn.onclick = async (e) => {
			e.preventDefault();
			console.log("답글 보기");
			console.log(e.target);
			if (isOpen) {
				// removeReply 호출
				await removeReply(e, childCountText, replySection);
				isOpen = false; // 상태 변경
			} else {
				let replyList = await loadReply(e, commentId, replySection); // loadReply 호출
				isOpen = true; // 상태 변경
			}
		};
		replyInputOpenBtn.onclick = (e) => {
			showReplyInput(e, replyInputOuter, commentId);
			let replyInputCancelBtn = replyInputOuter.querySelector(".reply-input-cancel");
			replyInputCancelBtn.onclick = () => {
				replyInputOuter.innerHTML = "";
			}
			let replyInputSubmitBtn = replyInputOuter.querySelector(".reply-input-submit");
			replyInputSubmitBtn.onclick = () => {

				let replyInputText = replyInputOuter.querySelector("#replyInput").value;
				let data = {
					"userId": userId,
					"postId": postId,
					"parentId": commentId,
					"content": replyInputText
				}
				let json = JSON.stringify(data);
				console.log(json);
				addComment(json, replyInputOuter);

			}
		}
	}

	// 댓글 달기
	submitBtn.onclick = (e) => {
		let content = commentInput.value;
		let data = {
			"content": content,
			"userId": userId,
			"postId": postId
		}
		let json = JSON.stringify(data);
		addComment(json, commentOuter);

	}
});

// 댓글 달기 함수 addComment
function addComment(json, outer) {
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
			console.log(newOne);
		}
		if (!newOne.parentId) {
			console.log(newOne);
			let todayCommentTemplate =
				`
							<div class="comment-wrapper">
								<section class="comment" data-comment-id=${newOne.id}>
									<div class="comment-content">
										<div class="comment-writer">
											<img src="/img/user/community/유저 프로필 사진.png" alt="프로필이미지&nbsp;&nbsp;">
											<span class="comment-name" >${newOne.nickname}&nbsp;&nbsp;</span>
											<span class="comment-mycomment">내 댓글</span>
										</div>
										<div class="comment-body" >
											${newOne.content}
										</div>
	
										<div class="comment-info">
											<span class="comment-days">오늘 ·</span>
											<a href="#" class="reply-open-button"><span class="comment-replies"
												data-comment-id="${newOne.id}"> 답글 ${newOne.childCount}개
												·</span></a>
											<span class="reply-input-open-button"> 답글 달기</span>
											<span data-comment-id="${newOne.id}"></span>
											<div class="reply-input-outer">
											</div>
										</div>
									</div>
									<img src="/img/icon/icon_header_more.png" alt="더보기아이콘">
								</section>
								<div class="reply-section">
	
								</div>
							</div>
						`	;
			outer.insertAdjacentHTML("afterbegin", todayCommentTemplate);

		} else {
			let replyTemplate = `
								<div class="reply-outer">
					<div class="reply">
						<div class="comment-writer">
							<img src="/img/user/community/${newOne.profilePhoto}" alt="프로필이미지">
							<span class="comment-name">${newOne.nickname}</span>
						</div>
						<div class="comment-body">
							${newOne.content}
						</div>
						<div class="comment-info">
							<span class="comment-days">오늘 </span>
						</div>
					</div>
					<img src="/img/icon/icon_header_more.png" alt="더보기아이콘">
				</div>
								`
			outer.insertAdjacentHTML("afterbegin", replyTemplate);
		}
		let newOneId = newOne.id;
		let replyInputOuter = outer.querySelector(".reply-input-outer");
		console.log(replyInputOuter);
		let replyOpenBtn = document.querySelector(".reply-input-open-button");
		console.log(replyOpenBtn);
		replyOpenBtn.onclick = (e) => showReplyInput(e, replyInputOuter, newOneId)
	})();
}
// 답글 불러오기
async function loadReply(e, id, outer) {
	response = await fetch(`http://localhost:8080/api/comments/${id}/replies`);
	list = await response.json();

	for (let reply of list) {
		console.log(reply);
		if (reply.daysAgo != 0) {
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
			e.target.innerText = "댓글 접기";
		}
		else {
			let todayTemplate = `
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
								<span class="comment-days">오늘</span>
							</div>
						</div>
						<img src="/img/icon/icon_header_more.png" alt="더보기아이콘">
					</div>
						`
			outer.insertAdjacentHTML("beforeend", todayTemplate);
			e.target.innerText = "댓글 접기";
		}
	}
}
// 답글 접기
async function removeReply(e, childCountText, outer) {
	// 해당 답글을 감싸는 부모 요소를 찾습니다.
	outer.innerHTML = "";
	e.target.innerText = childCountText;
}

// 답글 입력 열기
function showReplyInput(e, outer, commentId) {
	//outer.innerHTML = "";
	let template = `
					 <textarea name="reply" id="replyInput" cols="30" rows="10" placeholder="답글을 입력하세요"></textarea>
                     <a class="reply-input-cancel">취소</a>    
                     <a class="reply-input-submit">등록</a>   
					`;
	outer.insertAdjacentHTML("beforeend", template);
	let replyInputCancel = outer.querySelector(".reply-input-cancel");
	replyInputCancel.onclick = (e) => {
		//outer.innerHTML = "";
	}
	let replyInputSubmit = outer.querySelector(".reply-input-submit");
	replyInputSubmit.onclick = (e) => {
		let replyInputText = outer.querySelector("#replyInput").value;
		let submitBtn = document.querySelector(".comment-submit");
		let userId = submitBtn.dataset.userId;
		let postId = submitBtn.dataset.postId;
		let data = {
			"content": replyInputText,
			"userId": userId,
			"postId": postId,
			"parentId": commentId
		}
		console.log(data);
		let json = JSON.stringify(data);
		addComment(json, outer);
		/*outer.innerHTML = "";*/
	}

}

// 답글 입력 닫기
function replyInputCancel(outer) {
	outer.innerHTML = "";
}
