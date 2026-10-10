window.csdn.loginBox=undefined;
function traverse(node) {
	if (node.nodeType === Node.ELEMENT_NODE) {
		node.style.setProperty('user-select', 'auto', 'important');
		console.log(node);
		for (let i = 0; i < node.childNodes.length; i++) {
			traverse(node.childNodes[i]);
		}
	}
}
traverse(document.documentElement);
