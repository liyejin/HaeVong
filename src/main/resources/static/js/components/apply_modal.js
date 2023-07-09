
let applybtn = document.querySelector(".applybtn");
let modalAlert = document.querySelector("modal-alert");
let canclebtn = document.querySelector(".canclebtn");

window.addEventListener("load", function() {
	console.log("산책존잼");

	applybtn.onclick = function(e) {
		e.preventDefault();
		modalAlert.show(true);
	};
});


class ModalAlertElement extends HTMLElement {
	constructor() {
		super();
		let userId = document.querySelector(".userId").value;
		let orgVolId = document.querySelector(".orgVolId").value;
		console.log(orgVolId);
		console.log(userId);

		let jsonData = {
			"userId": userId,
			"orgVolId": orgVolId
		};

		let json = JSON.stringify(jsonData);

		let style = `
      .screen {
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
      
      .content-panel {
         background-color: white;
         min-width: 200px;
         min-height: 100px;
         transform: translateY(-100px);
         border-radius: 10px;
      }
      
      .content-panel > * {
         padding: 5px 10px;
      }
      
      .content-title {
         border-bottom: 1px solid grey;
      }
      
      .content-view {
         border-bottom: 1px solid grey;
      }
      
      .content-command {
         display: flex;
         justify-content: center;
      }
      
      .content-command > button {
         border: 1px solid white;
         border-radius: 100px;
         box-shadow: 1px 1px 1px grey;
         margin-right: 5px;
         color: black;
      }
      
      .content-command > button:active {
         margin-left: 5px;
         margin-top: 5px;
         box-shadow: none;
      }
      `;

		let title = "알림";
		let content = "";
		if (this.hasAttribute("data-title")) {
			title = this.dataset.title;
		}

		if (this.hasAttribute("data-content")) {
			content = this.getAttribute("data-content");
		}

		let template = `
      <div class="content-panel">
         <div class="content-title">${title}</div>
         <div class="content-view">
            ${content}
            <slot name="content"></slot>
         </div>
         <div class="content-command">
            <button class="btn-ok">확인</button>
         </div>
      </div>
      `;

		let show = false;
		if (this.hasAttribute("data-show")) {
			show = JSON.parse(this.getAttribute("data-show"));
		}

		console.log(typeof this.getAttribute("data-show"));
		console.log(typeof show);

		if (show) {
			this.classList.remove("d-none");
		} else {
			this.classList.add("d-none");
		}

		const wrapper = document.createElement("div");
		wrapper.className = "screen";
		wrapper.innerHTML = template;

		let styleEl = document.createElement("style");
		styleEl.textContent = style;

		let shadow = this.attachShadow({ mode: "open" });

		shadow.appendChild(styleEl);
		shadow.appendChild(wrapper);

		let btnOk = wrapper.querySelector(".btn-ok");
		btnOk.onclick = (e) => {

			//패치가되야함
			fetch(`http://localhost:8080/api/apply-org-vols`, {
				method: "POST",
				headers: {
					"Content-Type": "application/json"
				},
				//누가 무슨 봉사를 신청하느냐?
				body: json
			})
			e.preventDefault();
			this.classList.add("d-none");

			applybtn.style.display = "none"; 	//확인
			canclebtn.style.display = "block"; //취소
		};
	}



	show(status) {
		if (status) {
			this.classList.remove("d-none");
		} else {
			this.classList.add("d-none");
		}
	}
}

customElements.define("modal-alert", ModalAlertElement);
