$(function() {
	$("#loginButton").click(function(e) {
		e.preventDefault();
		var username = $("#username").val();
		var password = $("#password").val();
		
		$.ajax({
			url: "/rfWebService/webresources/serv/isValidUser",
			type: "GET",
			data: {
				username:username,
				password:password
			}
		}).done(function(data) {
			if (!data || data == "false") {
				var errorTemplate = _.template($("#errorTpl").html());
				$("#loginMessage").html(errorTemplate({
					errors: ["Invalid Login"]
				}));
			} else {
				$("#loginForm").submit();
			}
		});
		
		return false;
	});
});