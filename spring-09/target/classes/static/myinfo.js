$('#table').bootstrapTable(
		{
			url : "/gethomework",
			columns : [
					{
						field : 'id',
						title : '课程编号'
					},
					{
						field : 'classid',
						title : '班级',
						formatter : function(value, row, index) {
							if (value == 1) {
								return "全栈课程";
							}
							if (value == 2) {
								return "中间课程";
							}
						}
					},
					{
						field : 'lessionid',
						title : '课程',
						formatter : function(value, row, index) {
							return value + "期";
						}
					},
					{
						field : 'homeworkfilepath',
						title : '作业',
						formatter : function(value, row, index) {
							return "<a target='_blank' href='uploadfile/"
									+ value + "\'>" + value + "</a>";
						}
					}, {
						field : 'starcount',
						title : '好评数'
					}, {
						field : 'negativecount',
						title : '差评数'
					}, {
						field : 'correctflag',
						title : '是否修改 ',
						formatter : function(value, row, index) {
							if (value==1) {
								return "是";
							} else {
								return "否";
							}
						}
					}, {
						field : 'bestflag',
						title : '优秀？',
						formatter : function(value, row, index) {
							if (value) {
								return "是";
							} else {
								return "否";
							}
						}
					}, {
						field : 'createdate',
						title : '日期'
					} ]
		});