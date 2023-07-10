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
			        <div class="content-view"> ${content}	
						  <slot name ="content"></slot>
				    	</div>
      </div>
			`;

    let show = false;
    if (this.hasAttribute("data-show"));
    show = JSON.parse(this.getAttribute("data-show"));
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
  }

  show(status) {
    if (status) this.classList.remove("d-none");
    else this.classList.add("d-none");
  }
}

//전역객체로, 내가만든 걸 태그로 갖다쓸수잇게 이름지어주기 / 태그명을 지정할때 대쉬를 꼭 넣어줘야함 ~!~!~!~!~~!~!~
customElements.define("modal-panel", ModalPanelElement);
