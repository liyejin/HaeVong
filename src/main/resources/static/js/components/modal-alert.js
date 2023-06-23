//a=> html element  =>el => Node 상속
// html el => el => Node 상속

class ModalAlertElement extends HTMLElement {
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
            background-color: lightpink;
            min-width: 200px;
			min-height: 100px;
      
            transform: translateY(-100px);
             border-radius: 10px; 
            
         }
         
         .content-panel>*{
			 padding: 5px  10px;	
			
		 }

         .content-title{
			border-bottom: 1px solid grey;
			 
		 }
		 
	    .content-view{
			border-bottom: 1px solid grey;
			 
		 }
		 
		 .content-command{
			 display: flex;
			 justify-content: center;
		 }
		 
		 .content-command>button{
			  border: 1px solid white;
			  border-radius: 100px;
			  margin-right: 5px;
			  color: lightpink;
			  
		 }`;

    let title = "수정하시겠습니까?";
    let content = "";
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
					  <button class="btn-ok">확인</button>
					  <button class="btn-cancel">취소</button>
					  </div>
			  	</div>
			  	`;

    const wrapper = document.createElement("div");
    wrapper.className = "screen";
    wrapper.innerHTML = template;

    let styleEl = document.createElement("style");
    styleEl.textContent = style;

    let shadow = this.attachShadow({ mode: "open" });

    shadow.appendChild(styleEl);
    shadow.appendChild(wrapper);

    let btnOk = wrapper.querySelector(".btn-ok");
    btnOk.onclick = () => {
      this.remove(); //this가 modal-alert
    };
  }
}

//전역객체로, 내가만든 걸 태그로 갖다쓸수잇게 이름지어주기 / 태그명을 지정할때 대쉬를 꼭 넣어줘야함 ~!~!~!~!~~!~!~
customElements.define("modal-alert", ModalAlertElement);
