$(function() {
	$.ajax({
		url: "/rfWebService/webresources/serv/getQuestionGroups"
	}).done(function(data) {
		data = JSON.parse(data);
		var questionGroupTemplate = _.template($("#questionGroupTpl").html());
		
		var currentPage = -1;
		var totalPages = data.length;
		var saveCurrentPage = function(callback) {
			if (currentPage > -1) {
				var attributes = {};
				for (var i = 0; i < data[currentPage].questions.length; i++) {
					var question = data[currentPage].questions[i];
					var val = null;
					var val2 = null;
					if (question.metaData.type == "text") {
						val = $("#text" + question.id).val();
					} else if (question.metaData.type == "slider" && question.metaData.slidecount == "2") {
						val = $( "#slider" + question.id ).slider( "values", 0 );
						val2 = $( "#slider" + question.id ).slider( "values", 1 );
					} else if (question.metaData.type == "slider" && question.metaData.slidecount == "1") {
						val = $( "#slider" + question.id ).slider( "values", 0 );
					} else if (question.metaData.type == "yesno") {
						val = $("input[name='yesno" + question.id + "']:checked").val()
					} else if (question.metaData.type == "options") {
						val = $("#options" + question.id).val();
					}
					
					if (val) {
						var id = question.metaData.id;
						if (id == "city" || id == "state") {
							id = "loc." + id;
						}
						attributes[id] = val;
					}
					
					if (val2) {
						attributes[question.metaData.id2] = val2;
					}
				}
				
				//TODO: Update username
				var username = $("#hiddenuser").html();
				var mydata = "username=" + username + "&attributes=" + JSON.stringify(attributes);
				$.ajax({
					url: "/rfWebService/webresources/serv/updateAttributes",
					data: mydata
				}).always(function() {
					if (callback) {
						callback();
					}
				});
			}
		};
		
		var displayPage = function(page) {
			saveCurrentPage();
			currentPage = page;
			$("#questionsArea").html(questionGroupTemplate(data[currentPage]));
			
			for (var i = 0; i < data[currentPage].questions.length; i++) {
				var question = data[currentPage].questions[i];
				if (question.metaData && question.metaData.slidecount) {
					var middle = (parseInt(question.metaData.slidemin) + parseInt(question.metaData.slidemax)) / 2;
					var quarter = (parseInt(question.metaData.slidemax) - parseInt(question.metaData.slidemin)) / 2;
					if (question.metaData.slidecount == "2") {
						$( "#slider" + question.id ).slider({
					      range: true,
					      min: parseInt(question.metaData.slidemin),
					      max: parseInt(question.metaData.slidemax),
					      values: [ middle - quarter, middle + quarter ]
					    });
					} else if (question.metaData.slidecount == "1") {
						$("#slider" + question.id).slider({
							min: parseInt(question.metaData.slidemin),
							max: parseInt(question.metaData.slidemax)
						});
					}
				}
			}
			
			if (page < totalPages - 1) {
				$("#continueButtonText").html("Continue");
				if ($("#continueButton").hasClass("btn-success")) {
					$("#continueButton").removeClass("btn-success");
					$("#continueButton").addClass("btn-primary");
				}
				$("#continueButton").click(function() {
					displayPage(page + 1);
				});
			} else {
				$("#continueButtonText").html("Finish");
				if ($("#continueButton").hasClass("btn-primary")) {
					$("#continueButton").removeClass("btn-primary");
					$("#continueButton").addClass("btn-success");
				}
				
				$("#continueButton").click(function() {
					saveCurrentPage(function() {
						window.location = "explore.html";
					});
					currentPage = -1;
				});
			}
			
			if (page > 0) {
				$("#previousButton").css("display", "");
				$("#previousButton").click(function() {
					displayPage(page - 1);
				});
			} else {
				$("#previousButton").css("display", "none");
			}
		};
		
		displayPage(0);
		
		
	});
});