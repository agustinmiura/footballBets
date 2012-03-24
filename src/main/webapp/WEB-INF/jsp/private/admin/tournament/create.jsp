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
<%@ include file="/WEB-INF/jsp/include/include.jsp"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="java.util.logging.Level"%>
<%@ page import="java.util.logging.Logger"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"    
		    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
<title>I bet depoi in google app engine</title>


<link rel="stylesheet" type="text/css" href="/styles/index/pageLayout.css"
    media="screen" />
<link rel="stylesheet" type="text/css" href="/styles/index/style.css"
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
                            <span class="specialButton"><a
                                class="whiteFont"
                                href="<c:out value="${parameter.logoutUrl}" />">Click
                                    here to logout user</a> </span>
                        </div>
                    </div>
                </div>
                <div id="results">
                    <div id="resultTitle">
                        <h3>Creation panel here</h3>
                    </div>
                    <div class="messages">
                        <div class="message">

                            <div class="author">Create a tournament here
                                please</div>

                            <form action="/tournament/create/action.htm"
                                method="post">
                                <fieldset>
                                    <legend></legend>
                                    <div id="formPassword"
                                        class="separateTopBottom">
                                        <label class="label"
                                            for="tournamentName">Enter
                                            the tournament name please</label> <input
                                            type="text" id="tournamentName"
                                            name="tournamentName" />
                                    </div>
                                    <div id="buttonArray"
                                        class="separateTopBottom">
                                        <button type="submit" value="Create"
                                            class="button">Send</button>

                                        <button type="reset" value="Cancel">Cancel</button>
                                    </div>

                                </fieldset>
                            </form>

                        </div>

                        <div class="message">

                            <div class="author">Create a team here please</div>

                            <form action="/team/create/action.htm" method="post">
                                <fieldset>
                                    <legend></legend>
                                    <div id="formPassword"
                                        class="separateTopBottom">
                                        <label class="label" for="teamName">Enter
                                            the team name please</label> <input
                                            type="text" id="teamName"
                                            name="teamName" />
                                    </div>
                                    <div id="buttonArray"
                                        class="separateTopBottom">
                                        <button type="submit" value="Create"
                                            class="button">Send</button>

                                        <button type="reset" value="Cancel">Cancel</button>
                                    </div>

                                </fieldset>
                            </form>

                        </div>


                    </div>
                </div>

                <jsp:include page="/WEB-INF/jsp/include/mockMenu.jsp" /></div>
        </div>
    </div>
</body>

</html>