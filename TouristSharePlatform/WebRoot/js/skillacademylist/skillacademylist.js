$(function(){
	var top_list=$(".top_list li").not(".top_tit");
	for(var i=0;i<top_list.length;i++){
		var em=top_list[i].getElementsByTagName("em")[0];
		em.setAttribute("class","n"+parseInt(i+1));
		var nodeText=document.createTextNode(parseInt(i+1));
		em.appendChild(nodeText);
	}
});