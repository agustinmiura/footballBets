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

<link rel="stylesheet" type="text/css" href="/styles/index/pageLayout.css" />
<link rel="stylesheet" type="text/css" href="/styles/index/style.css" />
<link rel="stylesheet" type="text/css" href="/styles/index/matchGrid.css" />

<!--
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.3.2.js"></script>
-->
<script type="text/javascript" src="../js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="../js/jquery/jquery.corner.js"></script>
<script type="text/javascript" src="../js/bets.js"></script>
</head>
<body>
    <div id="header">
        <div id="title"></div>
        <h1 class="bigTitle">I bet DPOI project</h1>
        <h2 class="subBigTitle">A web system to make bets about sports</h2>
        <span class="credential">${parameter.userMail} <a
            href="<c:out value="${parameter.logoutUrl}"/>">Logout</a></span>
    </div>

    <div id="backImage"></div>
    <div id="results">
        <h3 id="resultTitle">
            Set results! <a class="resultTitleItem" href="/hello.htm">View
                messages</a>
            <div id="test"></div>
        </h3>
        <c:forEach items="${tournamentList}" var="tournament" varStatus="status">
            <div class="message">
                <div class="author">${tournament.description}</div>
                <a class="link"
                    href="javascript:bringHTML('${links[status.index]}','match${tournament.id}', 'collapseTop${tournament.id}')">Set
                    results</a> <a class="link"
                    href="javascript:collapse('match${tournament.id}', '${tournament.id}')"
                    id="collapseTop${tournament.id}"></a>
                <div class="betContainer" id="match${tournament.id}"></div>
            </div>
        </c:forEach>
        <div id="hiddenColor" />
    </div>
</body>
</html>