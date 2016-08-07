$(document).foundation();

$(function () {


    /* register functions */
    $("#batSignIn").on("submit", batHandleSignIn);
    $(window).on("load resize", batHandleLoad);
    $(window).on("open.zf.reveal", batshowQuiz);
    $(".batmods").on("click", batSubmitQuiz);
    $("#userId").on("keypress", removeErrorClass);
    $("#userPasssword").on("keypress", removeErrorClass);
    $(".addCourse").on("click", createCourseList);
    $(".confirm").on("click", showCourseList);

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
        $("#userId").focus();
    }

    function batHandleSignIn(event) {
        event.preventDefault();
        /* clear errors */
        clearError();


        var id = $("#userId").val();
        var pwd = $("#userPasssword").val();
        var type = $('input[name=userType]:checked').val();

        if (id.length !== 0 && pwd.length !== 0) {
            $(".loader").removeClass("hide").addClass("show");
            $("section.landing").addClass("loading");
            $.ajax({
                method: "POST",
                url: "../BatAcademy/signin",
                data: {id: id, password: pwd, userType: type}
            })
                    .done(function (data) {
                        $(".loader").removeClass("show").addClass("hide");
                        $("section.landing").removeClass("loading");
                        if (data === "error") {
                        	clearError();
                            showError("You have either entered wrong Id or Password");
                        } else if (data === "student") {
                            window.location = '../BatAcademy/StudentDetailsController';
                        } else if (data === "faculty") {
                            window.location = '../BatAcademy/FacultyDetailsController';
                        }
                    });
        } else {

            if (id.length === 0) {
                showError("Id field is empty");
                $("#userId").addClass('error');
            }
            if (pwd.length == 0) {
                showError("Password field is empty");
                $("#userPasssword").addClass('error');
            }

        }
    }

    function showError(errorTxt) {
        var ulEle = $("#errorCont");
        if (ulEle.length === 0) {
            ulEle = $("<ul id='errorCont'></ul>");         
        }
        ulEle.append("<li>" + errorTxt + "</li>");
        $("#error").html(ulEle);

    }

    function batshowQuiz() {
        var num = getRandomArbitrary(1, 3);
        var id = "bat" + num;
        $(".batmods").removeClass("show").addClass("hide");
        $("#" + id).removeClass("hide").addClass("show");
        $("#" + id + " input[type='radio']").removeAttr("checked");
        setTimeout(function () {
            window.location = '../BatAcademy/HandleError.jsp';
        }, 20000);
    }

    function batSubmitQuiz() {
        var id = $(".batmods.show").attr("id");
        var radioValue = $("input[name='" + id + "']:checked").val();
        if (radioValue) {
            if (id === "bat1" && radioValue === "3") {
                window.location = '../BatAcademy/SignUp.jsp';
            } else if (id === "bat2" && radioValue === "42") {
                window.location = '../BatAcademy/SignUp.jsp';
            } else {
                window.location = '../BatAcademy/HandleError.jsp';
            }
        } else {
            window.location = '../BatAcademy/HandleError.jsp';
        }

    }

    function createCourseList(event){
        event.preventDefault();
        $(this).find('img').toggle();
        var parent = $(this).parent();
        var parentParent = $(this).parent().parent();
        var table = $("#selectedCousesTable");
        var list = $("#courseList");

        $("#error").removeClass("show").addClass("hide");
        parentParent.toggleClass("selected");

        if(list.find(".selected").length <= 2) {
            if(parentParent.hasClass("selected")) {
                var tr = $("<tr></tr>");
                tr.append("<td>"+parent.data("cid")+"</td>");
                tr.append("<td>"+parent.data("cname")+"</td>");
                tr.append("<td>"+parent.data("faculty")+"</td>");
                tr.append("<td></td>");
                tr.append("<td></td>");
                tr.append("<td></td>");

                table.append(tr);
            }
        } else {
            $("#error").removeClass("hide").addClass("show");
        }
        
    }

    function showCourseList(event){
        event.preventDefault();
        var tr = $("#courseList").find("tr.selected");
        if(tr.length >0 && tr.length <= 2 ) {
            $("#courseList").removeClass("show").addClass("hide");
            $("#selectedCouses").removeClass("hide").addClass("show");
            $("#confirmation").hide();
        } else {
            $("#error").removeClass("hide").addClass("show");
        }
    }

    function getRandomArbitrary(min, max) {
        return Math.floor(Math.random() * (max - min) + min);
    }

    function clearError() {
    	$("#error").empty();
        $("#userId").removeClass('error');
        $("#userPasssword").removeClass('error');
    }

    function removeErrorClass() {
        $(this).removeClass('error');
    }


}());