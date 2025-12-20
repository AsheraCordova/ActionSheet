let links = ["css/actionsheet.css"];

for (var i=0;i<links.length;i++) {
	let link = document.createElement('link');
	link.setAttribute('rel', 'stylesheet');
	link.setAttribute('href', links[i]);
	link.setAttribute('type', "text/css");
	window.shadowRoot.appendChild(link);
}