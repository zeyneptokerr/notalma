$(document).ready(function(){
	
	getNote();
	
});

function getNote(){
	
	$("#note_title").attr("disabled", true);
	$("#note_detail").attr("disabled", true);
	
	$("#updateBtn").html("Güncelle");
	
	$.ajax({
		type:'POST',
		url:'../getNote',
		contentType:'text/plain',
		data:$("#id").val()+"",
		success:function(data){
			$("#note_title").val(data.title);
			$("#note_detail").val(data.content);
			
		},error:function(data){
			alert(data);
		}
	});
}

var updated = false;

function update() {
	
	if(!updated){
		$("#note_title").attr("disabled", false);
		$("#note_detail").attr("disabled", false);
		
		$("#updateBtn").html("Kaydet");
		updated = true;
	}else {
		updateNote();
		updated = false;
	}

}


function updateNote(){
	var param = {
			id:$("#id").val(),
			title:$("#note_title").val(),
			content:$("#note_detail").val()
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type:"POST",
		contentType:"application/json; charset=UTF8",
		url:"../updateNote",
		data:ser_data,
		success:function(data){
//			alert(data);
			getNote();
		},error:function(data){
			alert(data);
		}
	});
}


function deleteNote(){
	var param = {
			id:$("#id").val(),
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type:"POST",
		contentType:"application/json; charset=UTF8",
		url:"../deleteNote",
		data:ser_data,
		success:function(data){
			alert(data);
			window.history.back();
		},error:function(data){
			alert(data);
		}
	});
}