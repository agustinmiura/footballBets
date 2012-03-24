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
                            <span class="specialButton"><a
                                class="whiteFont"
                                href="<c:out value="${parameter.logoutUrl}" />">Click
                                    here to logout user</a> </span>
                        </div>
                    </div>
                </div>
                <div id="results">
                    <div id="resultTitle">
                        <h3>Modify the tournament "${tournamentName}"</h3>
                    </div>

                    <div class="messages">

                        <div class="message3">
                            <div class="author">
                                <form>
                                    <fieldset>
                                        <legend>Create a match here</legend>
                                        <div id="formMail"
                                            class="separateTopBottom">
                                            <label class="label" for="localTeam">Local
                                                team:</label> <select id="localTeam"
                                                name="localTeam">
                                                <option value="1"
                                                    selected="selected">Buenos
                                                    Aires</option>
                                                <option value="2">Salta</option>
                                                <option value="3">Chaco</option>
                                            </select>
                                        </div>

                                        <div id="formPassword"
                                            class="separateTopBottom">
                                            <label class="label" for="guestTeam">Guest
                                                team</label> <select id="guestTeam"
                                                name="guestTeam">
                                                <option value="1"
                                                    selected="selected">Buenos
                                                    Aires</option>
                                                <option value="2">Salta</option>
                                                <option value="3">Chaco</option>
                                            </select>
                                        </div>

                                        <div id="formCheckPass"
                                            class="separateTopBottom">
                                            <button type="submit" value="Verify"
                                                class="button">
                                                <img src="../img/check_icon.PNG"
                                                    alt="" />Create a match
                                                here
                                            </button>
                                        </div>

                                    </fieldset>
                                </form>
                            </div>
                        </div>


                    </div>


                    <div class="messages">

                        <c:forEach items="${matchList}" var="match">
                            <div class="matchHolder">
                                <div id="formContainer">
                                    <form id="form1" name="form1" method="post"
                                        action="">
                                        <input type="hidden" name="matchId"
                                            value="${match.id}" />
                                        <div class="col1">
                                            <label for="label" class="myLabel">${match.local}</label>
                                            <input type="text" name="textfield2"
                                                id="label" /> <label
                                                for="Submit"></label> <input
                                                name="Submit" type="submit"
                                                id="Modify" value="Modify"
                                                class="myButton" />
                                        </div>

                                        <div class="col2">
                                            <label for="textfield"
                                                class="myLabel">${match.guest}</label>
                                            <input type="text" name="textfield"
                                                id="textfield" /> <input
                                                name="Submit" type="submit"
                                                id="Delete" value="Delete"
                                                class="myButton" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <jsp:include page="/WEB-INF/jsp/include/menu.jsp" /></div>
        </div>
    </div>
</body>

</html>