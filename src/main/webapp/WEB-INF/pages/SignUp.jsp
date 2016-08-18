<%-- 
    Document   : SignUp
    Created on : Aug 2, 2016, 11:05:26 PM
    Author     : geeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="HandleError.jsp" %>

<section id="signup" class="details">
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
            </form>

            <div id="signUpStatus"></div>
        </div>
    </div>
    <div id="overlay"></div>
    <div class="loader hide"><div class="loadingAnime"></div></div>
</section>



<script src="js/vendor/jquery.js"></script>
<script src="js/vendor/foundation.min.js"></script>
<script src="js/app.js"></script>


