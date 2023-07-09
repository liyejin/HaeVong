//a=> html element  =>el => Node 상속
// html el => el => Node 상속

class ModalPanelElement extends HTMLElement {
  constructor() {
    super();

    let style = ` 
		.screen{
            position: fixed;
            left: 0;
            top: 0;
            
            width: 100vw;
            height: 100vh;
            z-index: 1300;
            background-color: #000000a0;
            
            display: flex;
            align-items: center;
 			      justify-content: center;           
         }
         
         .content-panel{
            background-color: white;
            min-width: 200px;
			      min-height: 100px;
      
            transform: translateY(-100px);
             border-radius: 40px; 
            
         }
         
         .content-panel>*{
			    padding: 5px  10px;	
			
		     }

     .content-title{
      display: flex;
      justify-content: center;
      font-size : 16px;
      padding : 14px;
      border-bottom: 1px solid #c5c5c5;
     
     }

     .content-panel{
      border-bottom: 1px solid #c5c5c5;
     }

		 .content-command{
			 display: flex;
			 justify-content: center;
       padding : 14px;
       border-top: 1px solid #c5c5c5;

      }
		 
		 .content-command>button {
			  border: 1px solid white;
			  border-radius: 100px;
        box-shadow : 1px 1px 1px grey ;
			  margin-right: 5px;
			  color: black;
		 }

     .content-command>button:active {
      margin-left: 5px;
      margin-top : 5px;
      box-shadow : none;
   }

    .profile-img{
      width: 50px;
      height: 50px;
      border-radius: 50%;
    }
     
     `;

    let title = "";
    let content = "";
    let submmitValue = "신청";
    if (this.hasAttribute("data-submmit")) submmitValue = this.dataset.submmit;
    if (this.hasAttribute("data-title")) title = this.dataset.title;

    if (this.hasAttribute("data-content"))
      content = this.getAttribute("data-content");

    let template = `
			<div class="content-panel">
		            <div class="content-title">${title}</div>
			        <div class="content-view">
                        ${content}	
						<slot name ="content"></slot>
					</div>
					<div class="content-command">

          <button class="btn-cancel">취소</button>
					<button onclick="tmp()" class="btn-submmit" type="submit">${submmitValue}</button>
					</div>
			  	</div>
			  	`;

    let show = false;
    if (this.hasAttribute("data-show"));
    show = JSON.parse(this.getAttribute("data-show"));

    console.log(typeof this.getAttribute("data-show"));
    console.log(typeof show);

    if (show) this.classList.remove("d-none");
    else this.classList.add("d-none");

    const wrapper = document.createElement("div");
    wrapper.className = "screen";
    wrapper.innerHTML = template;

    let styleEl = document.createElement("style");
    styleEl.textContent = style;

    let shadow = this.attachShadow({ mode: "open" });

    shadow.appendChild(styleEl);
    shadow.appendChild(wrapper);

    let btnCancel = wrapper.querySelector(".btn-cancel");
    btnCancel.onclick = () => {
      // this.remove(); //this가 modal-alert
      this.classList.add("d-none");
    };

    let btnSubmmit = wrapper.querySelector(".btn-submmit");
    btnSubmmit.onclick = () => {
      // 프로필 사진 변경
      let profileImgInput = document.querySelector("#profile-img-input");

      let file = profileImgInput.files[0];

      let formData = new FormData();

      formData.append("file", file);

      fetch(`/api/file/user/4/image`, {
        method: "PUT",
        body: formData,
      }).then(() => {
        location.reload(); // 페이지 새로고침
      });

      // 닉네임 변경
      // let nicknameInput = document.querySelector("#profile-nickname-input");

      // nicknameInput.onchange = function (e) {
      //   let newNickname = e.target.value;
      //   console.log("새로운 닉네임:", newNickname);
      // };

      // this.classList.add("d-none");
    };
  }

  show(status) {
    if (status) this.classList.remove("d-none");
    else this.classList.add("d-none");
  }
}

//전역객체로, 내가만든 걸 태그로 갖다쓸수잇게 이름지어주기 / 태그명을 지정할때 대쉬를 꼭 넣어줘야함 ~!~!~!~!~~!~!~
customElements.define("modal-panel", ModalPanelElement);
