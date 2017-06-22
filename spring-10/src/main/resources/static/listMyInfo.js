$().ready(function() {
	var name = $.getUrlParam('name');
	var age = $.getUrlParam('age');
	var sessionId = $.getUrlParam('sessionid');
	if(name!=null)
	{
		 settable(name,age,sessionId);
	}else
	{
		$.get("/userinfo",
				  function(data){
		    	   if(data.code=="1")
				    {
		    		location.href="index.html";
		    		return;
				    }
			       var name = data.msg["name"];
			       var age = data.msg["age"];
			       var sessionId = data.msg["sessionId"];
			       settable(name,age,sessionId);
	     }); 
	}
});

function settable(p1,p2,p3)
{
	$('#table').bootstrapTable({  
        data: [{  
            "name": p1,  
            "age": p2,
            "sessionId":p3
        }]  
    });  	
}
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);