function addNote(){
	var param = {
			title:$("#note_title").val(),
			content:$("#note_detail").val()
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type:"POST",
		contentType:"application/json; charset=UTF8",
		url:"addNote",
		data:ser_data,
		success:function(data){
			$(location).attr('href','http://localhost:8080/notalma/index');
		},error:function(data){
			alert(data);
		}
	});
}