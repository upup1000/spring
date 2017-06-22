$(document).ready(function() {
	$("ul.navbar-nav li").each(function() {
		$(this).on("click", function(e) {
			var newPage = $(this).attr("data-page");
			navi2page(newPage);
		})
	})
})

$().ready(function() {
	$("#form_login").validate({
		rules : {
			uname : "required",
			pwd : "required"
		},
		messages : {
			uname : "请输入用户名",
			pwd : "请输入密码"
		},
		submitHandler : function(form) {
			sumbit();
		}
	});
});

function sumbit() {
	$.ajax({
		cache : true,
		type : "get",
		url : '/login',
		data : $('#form_login').serialize(),//
		async : false,
		error : function(data) {
		},
		success : function(data) {
			if(data.code=="0")
				{
				 location.href="listMyInfo.html?name="+data.msg["name"]+"&age="+data.msg["age"]+"&sessionid="+data.msg["sessionId"];
				}else
			    {
				  for(item in data.msg){
					  $("#" + item).append("<span class='msg'>"+data.msg[item]+"</span>"); 
				  }
			    }
		}
	});
}
