$(document).foundation();

$(window).on("load resize", function () {
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
});

/* landing */

$(function(){


	/* register functions */
	$("#batSignIn").on("click", batHandleSignIn);

	/* functions */
	function batHandleSignIn() {
		var id = $("#userId").val();
		var pwd = $("#userPasssword").val();
		//check for empty values
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
		return false;
	}


}());