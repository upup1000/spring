$(document).ready(function() {
	//默认跳转到我的信息界面
	$("#bodymain").load("myinfo.html");
	$("ul.navbar-nav li").each(function() {
		$(this).on("click", function(e) {
			var newPage = $(this).attr("data-page");
			navi2page(newPage);
		})
	})
})

//退出方法
function logOut()
{
	$.ajax({url:"/logoutsys",success:function(data){
		 if(data.state==0)
		    {
		      window.location.href=data.toUrl;
		    }
    }});
}
