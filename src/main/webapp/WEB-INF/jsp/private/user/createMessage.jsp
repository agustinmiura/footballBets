<!--  
 Copyright (C) 2010 Miura Agustín
                    Rozanect Jose  
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
<?xml version="1.0" encoding="UTF-8"?>
<%@ include file="/WEB-INF/jsp/include/include.jsp"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"    
		    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
<title>I bet depoi in google app engine</title>


<link rel="stylesheet" type="text/css" href="/styles/index/pageLayout.css"
    media="screen" />
<link rel="stylesheet" type="text/css" href="/styles/index/style.css"
    media="screen" />
<link rel="stylesheet" type="text/css" href="/styles/index/style2.css"
    media="screen" />

</head>
<body>
    <div id="page">
        <div id="wrapper">
            <div id="header">
                <h1 class="bigTitle">IBet dpoi proyect</h1>
                <h2 class="subBigTitle">A web system to make bets about
                    sports</h2>
            </div>
            <div id="column">
                <div id="search">
                    <div id="cityInputDiv"></div>
                    <div id="searchContainer">
                        <div class="credential">
                            <%
    UserService userService = UserServiceFactory.getUserService();
	User user=userService.getCurrentUser();
	String redirect=userService.createLogoutURL("/index.jsp");
	String text="Sign out "+user.getNickname();
	
	%>
                            <span class="specialButton"><a
                                class="whiteFont" href="<%=redirect%>"><%=text%></a></span>


                        </div>
                    </div>
                </div>
                <div id="results">
                    <div id="resultTitle">
                        <h3>Send a message to the developers</h3>
                    </div>
                    <div id="contact-area">
                        <form action="/createMessage.htm" method="post">
                            <fieldset>
                                <legend></legend>
                                <div id="formPassword" class="separateTopBottom">
                                    <label class="label" for="publisher">Publisher</label>
                                    <input type="text" id="publisher"
                                        name="publisher" />
                                </div>

                                <div id="formTextArea" class="separateTopBottom">
                                    <label class="label" for="textArea">Enter
                                        the message here</label>
                                    <textarea rows="4" cols="60" id="textArea"
                                        name="message">
		    This text area is an example for doing a lot of things
	</textarea>
                                </div>

                                <div id="buttonArray" class="separateTopBottom">
                                    <button type="submit" value="Send"
                                        class="button">Send</button>

                                    <button type="reset" value="Cancel">Cancel</button>
                                </div>

                            </fieldset>
                        </form>
                    </div>
                </div>

                <jsp:include page="/WEB-INF/jsp/include/menu.jsp" />

            </div>
        </div>
    </div>

</body>
</html>