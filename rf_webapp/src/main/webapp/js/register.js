$(function() {
	$("#registerButton").click(function() {
		var errors = [];
		if ($("#password").val() != $("#password2").val()) {
			errors.push("Your passwords must match");
		}
		
		if (errors.length > 0) {
			var errorTemplate = _.template($("#errorTpl").html());
			$("#registerMessage").html(errorTemplate({
				errors: errors
			}));
		} else {
			var username = $("#username").val();
			var password = $("#password").val();
			var name = $("#name").val();
			var email = $("#email").val();
			var phone = $("#phone").val();
			var age = $("#age").val();
			
			$.ajax({
				url: "/rfWebService/webresources/serv/addUser",
				type: "GET",
				data: {
					username:username,
					password:password,
					name: name,
					email:email,
					phone:phone,
					age:age
				}
			}).done(function(data) {
				$("#registerForm").submit();
			});
		}
	});
});