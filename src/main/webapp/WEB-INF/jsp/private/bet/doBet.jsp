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
<script type="text/javascript" src="/js/jquery/jquery.color.js"></script>
<script type="text/javascript" src="/js/jquery/js.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/js/bets.js"></script>

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
                        <h3>Do your bets for : "${t.description}"</h3>
                    </div>

                    <div class="messages">


                        <div class="message3">
                            <div class="author">
                                <form>
                                    <fieldset>
                                        <legend>Argentine vs greece</legend>
                                        <div id="formMail"
                                            class="separateTopBottom">
                                            <label class="label" for="localTeam">Argentine
                                                will :</label> <select id="localTeam"
                                                name="localTeam">
                                                <option value="1">Win</option>
                                                <option value="2">Draw</option>
                                                <option value="3">Lose</option>
                                                <option value="4">Something
                                                    i dont care</option>
                                            </select>
                                        </div>

                                        <div id="formCheckPass"
                                            class="separateTopBottom">
                                            <button type="submit" value="Verify"
                                                class="button"
                                                onclick="sendBet()">
                                                <img src="../img/check_icon.PNG"
                                                    alt="" />Do your bet here
                                            </button>
                                        </div>


                                    </fieldset>
                                </form>
                            </div>
                        </div>


                    </div>


                    <div class="messages">

                        <c:forEach items="${betList}" var="betUnit">
                            <div id="${betUnit.id}">

                                <div class="message3">
                                    <div class="author">
                                        <form id="formBet${betUnit.id}">


                                            <fieldset>
                                                <legend>${betUnit.match}</legend>
                                                <div id="formMail"
                                                    class="separateTopBottom">
                                                    <label class="label"
                                                        for="localTeam">${betUnit.match.local}:</label>

                                                    <select id="result"
                                                        name="result">
                                                        <option value="1">Win</option>
                                                        <option value="0">Draw</option>
                                                        <option value="-1">Lose</option>
                                                        <option value="-2">Something
                                                            i dont care</option>
                                                    </select>
                                                </div>
                                                <input type="hidden"
                                                    value="${betUnit.id}"
                                                    name="betUnitId" />

                                                <div id="formCheckPass"
                                                    class="separateTopBottom">

                                                    <input type="button"
                                                        value="Bet here"
                                                        onclick="sendBet('<c:out value="${betUnit.id}"/>');"
                                                        class="button" />


                                                </div>

                                            </fieldset>
                                        </form>
                                    </div>
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