$(document).foundation();

$(function(){


	/* register functions */
	$("#batSignIn").on("submit", batHandleSignIn);
	$(window).on("load resize", batHandleLoad);
	$(window).on("open.zf.reveal", batshowQuiz);
	$(".batmods").on("click", batSubmitQuiz);


	/* functions */
	function batHandleLoad() {
	    var footer = $("#footer");
	    var pos = footer.position();
	    var height = $(window).height();
	    height = height - pos.top;
	    height = height - footer.height();
	    if (height > 0) {
	        footer.css({
	            'margin-top': height + 'px'
	        });
	    }
	}

	function batHandleSignIn(event) {
		event.preventDefault();
		var id = $("#userId").val();
		var pwd = $("#userPasssword").val();
		if(id.length !== 0 && pwd.length !== 0) {
			$.ajax({
			  method: "POST",
			  url: "../BatAcademy/signin",
			  data: { id: id, password: pwd }
			})
			  .done(function( data ) {
			  	if(data === "error") {

			  	} else if(data === "student") {
			  		window.location = '../BatAcademy/UserDetailsStudent.jsp';
			  	} else if(data === "faculty") {
			  		window.location = '../BatAcademy/UserDetailsFaculty.jsp';
			  	}
			  });
		} else {
			alert("error");
		}
	}

	function batshowQuiz() {
		var num = getRandomArbitrary(1, 3);
		var id = "bat"+num;
		$(".batmods").removeClass("show").addClass("hide");
		$("#"+id).removeClass("hide").addClass("show");
		$("#"+id+" input[type='radio']").removeAttr("checked");
		setTimeout(function() {
			window.location = '../BatAcademy/HandleError.jsp';
		}, 10000);
	}

	function batSubmitQuiz() {
		var id = $(".batmods.show").attr("id");
		var radioValue = $("input[name='"+id+"']:checked"). val();
		if(radioValue) {
			if(id === "bat1" && radioValue === "3"){
				window.location = '../BatAcademy/SignUp.jsp';
			} else if(id === "bat2" && radioValue === "42"){
				window.location = '../BatAcademy/SignUp.jsp';
			} else {
				window.location = '../BatAcademy/HandleError.jsp';
			}
		} else {
			window.location = '../BatAcademy/HandleError.jsp';
		}
		
	}

	function getRandomArbitrary(min, max) {
	    return Math.floor(Math.random() * (max - min) + min);
	}


}());