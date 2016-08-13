<%-- 
    Document   : SignUp
    Created on : Aug 2, 2016, 11:05:26 PM
    Author     : geeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="HandleError.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up</title>
        <link rel="stylesheet" href="css/foundation.css">
        <link rel="stylesheet" href="css/foundation-icon.css">
        <link rel="stylesheet" href="css/app.css">
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon-16x16.png">
        <link rel="icon" type="image/png" sizes="32x32" href="images/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="images/favicon-96x96.png">
    </head>
    <body>
        <section id="facultyDetails" class="details">
            <header class="row">
                <div class="large-12 columns large-text-center medium-text-center">
                    <h1>Bat Register</h1>
                </div>
            </header>

            <div class="row">
                <div class="large-6 medium-6 columns">
                    <form id="batSignUp">
                            <div>
                                <input name="firstName" type="text" id="firstName" placeholder="Enter First Name" pattern="[a-zA-Z]+" title="Only a-z or A-Z characters"/>
                            </div>
                            <div>
                                <input name="lastName" type="text" id="lastName" placeholder="Enter Last Name" pattern="[a-zA-Z]+" title="Only a-z or A-Z characters" />
                            </div>
                            <div>
                                <input name="password" type="password" id="password" placeholder="Enter password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$" title="Please enter 8 char pwd with one upper case letter and one lower case letter and one digit with one special char" />
                            </div>
                            <div>
                                <input name="cpwd" type="password" id="cpwd" placeholder="Confirm password" />
                            </div>
                            <div>
                                <input name="phno" type="text" id="phno" placeholder="Enter Phone number (000-000-0000)" pattern="^[0-9]{3}-[0-9]{3}-[0-9]{4}$" title="Enter in this format 000-000-0000"/>
                            </div>
                            <div>
                                <input type="radio" name="gender" value="M" checked="checked" id="male"><label for="male">Male</label>
                                <input type="radio" name="gender" value="F" id="female"><label for="female">Female</label>
                            </div>
                            <div id="errorSignUp"></div>
                            <div class="signupBtns">
                                <input type="submit" value="Sign Up" class="button secondary">
                                <input type="reset" value="Clear" class="button secondary">
                            </div> 

                            <div id="signUpStatus"></div>
                        </form>
                </div>
            </div>

        </section>

        <div class="loader hide"><div class="loadingAnime"></div></div>

        <%@include file="footer.html" %>

        <script src="js/vendor/jquery.js"></script>
        <script src="js/vendor/foundation.js"></script>
        <script src="js/app.js"></script>
    </body>
</html>
