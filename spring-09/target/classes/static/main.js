var curPageUrl;
var prePageUrl;

function navi2page(newpage) {
	prePageUrl=curPageUrl;
	curPageUrl=newpage;
	//$('#bodymain').html('');
	$("#bodymain").load(newpage,null,loadOver(newpage));
	//$('#bodymain').append('<iframe name="testLoad" style="width:100%"></iframe>');
	//window.open(newpage,'testLoad');
}
function back2page() {
	curPageUrl=prePageUrl;
	$("#bodymain").load(curPageUrl);
}

function loadOver(newpage)
{
}
