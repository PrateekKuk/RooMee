$( document ).ready(function() {
	console.log("here");
	var username = $("#hiddenuser").html();
	$.ajax({
		url: "/rfWebService/webresources/serv/getMatchesForUser",
		type: "GET",
		data: {
			username:username
		}
	}).done(function(resp) {
		var data = eval(resp);
		if (!data || data == "false") {
			
			var errorTemplate = _.template($("#errorTpl").html());
			$("#loginMessage").html(errorTemplate({
				errors: ["Error"]
			}));
		} else {
			console.log(data);
			for(var i = 0; i < data.length; i++){
				var person = data[i];
				console.log(person);
				var html = "";
				html += "<li class='span3'>";
			    html += "<div class='thumbnail'>";
			    html += "<img src='"+person.pic+"'>";
			    html += "<h3>"+person.name+"</h3>";
			    html += "<h5>"+person.score+"</h5>";
			    html += "<p>"+person.other+"</p>";
			    html += "</div>";
			    html += "</li>";
			    
			    if(i < 4){
			    	$("#top-row").append(html);
			    } else {
			    	$("#bottom-row").append(html);
			    }
			}
		}
	});
});