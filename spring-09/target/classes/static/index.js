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
		url : '/loginsys',
		data : $('#form_login').serialize(),//
		async : false,
		error : function(data) {
		},
		success : function(data) {
			if (data.state == 0) {
				window.location.href = data.toUrl;
			} else {
				alert(data.errorinfo)
			}
		}
	});
}
