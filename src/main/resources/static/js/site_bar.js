export default class site_bar {
	constructor() {
		this.dlg = null;
		this.ok = null;
	}
	alert() {

		this.dlg = document.createElement("div");
		this.dlg.className = "screen";

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
            background-color: #FFFFFF;
            width: 50vw;
         	height: 100vh;
      
            transform: translateX(-50%px);
         }
         
         .content-panel>*{
          padding: 5px  10px;   
         
       }

         .content-title{
         border-bottom: 1px solid gainsboro;
          
       }
       
       .content-view{
         border-bottom: 1px solid gainsboro;
          
       }
       
       .content-command{
          display: flex;
          justify-content: center;
       }
       
       
`

		let template = `
		
			<div class = "content-panel">
				<div>제목</div>
				<div class="content-view">
					정말 닫으시겠습니까?
					ㄴㄴ~~
				</div>
				
				<div class="content-command">
					<button class="btn-ok">확인</button>
					<button class="btn-cancel">취소</button>
				</div>
			</div>
		          `;

		let styleEl = document.createElement("style");

		/*1. innerText ->텍스트만 / 주석포함*/
		/*2. textContent->텍스트만(정석)*/
		/*3. innerHTML		= "<div></div>"*/
		styleEl.textContent = style;
		this.dlg.innerHTML = template;
		
		document.head.appendChild(styleEl);
		document.body.appendChild(this.dlg);
		
		let btnOk = document.body.querySelector("btn-ok");
		btnOk,onclick = () => {
			if(this.onol != null)
			this.onok();
			document.body.lastChild.remove();
		}
	};
	confirm() {
	}
	show(url) { }

}