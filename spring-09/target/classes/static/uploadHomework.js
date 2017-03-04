$(document).ready(function() {
	$('#uploadfile').on('change', function(evt) {
		var size = this.files[0].size / (5 * 1024 * 1024);
		var str = this.files[0].name;
		var atts = str.split(".");
		if (size > 5) {
			alert("文件必须小于5M");
			this.value = null;
			return;
		}
		if (!(atts[1] == "pdf" || atts[1] == "ppf")) {
			alert("文件格式只能是pdf或者ppt");
			this.value = null;
			return;
		}
	});
});

function sumbit() {
	var formData = new FormData();
	var name = $("#homework").val();
	formData.append("homework", name);
	formData.append("file", $("#uploadfile")[0].files[0]);
	$.ajax({
		type : "post",
		url : '/upload',
		data : formData,
		processData : false,
		contentType : false,
		error : function(data) {
		},
		success : function(data) {
			if (data.state == 0) {
				alert("上传成功");
			} else {
				alert(data.errorinfo)
			}
		}
	});
}