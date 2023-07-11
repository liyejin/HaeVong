class CommunityTopHaevong extends HTMLElement {
	constructor() {
		super();
		let template = `
                <a href="#"
                  ><img
                    src="/img/user/community/community_planninvol.png"
                    alt="TOP참여해봉1"
                /></a>
                <span>very good</span>
					`
		let style = `
					.screen {
 					 margin-right: 20px;
					}

					.screen>span {
  					display: flex;
					  flex-direction: row;
					  overflow: auto;
					  color: orange;
					}	
					`
		const wrapper = document.createElement("li");
		wrapper.className = "screen";
		wrapper.innerHTML = template;
		let styleEl = document.createElement("style");
		styleEl.textContent = style;
		let shadow = this.attachShadow({ mode: "open" });
		shadow.appendChild(wrapper);
		shadow.appendChild(styleEl);
		let show = false;
		if (this.hasAttribute("data-show")) {
			show = JSON.parse(this.getAttribute("data-show"));
			console.log(show);
		}
		if (show) {
			this.classList.remove("d-none");
		}
		else
			this.classList.add("d-none");
	}
	show(status) {
		if (status) {
			this.dataset.show = true;
			this.classList.remove("d-none");
		}
		else {
			this.classList.add("d-none");
			this.dataset.show = false;
		}
	}
}
customElements.define("community-top-haevong", CommunityTopHaevong);