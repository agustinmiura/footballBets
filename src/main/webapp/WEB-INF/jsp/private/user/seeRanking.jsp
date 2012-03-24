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
<%@ page contentType="text/html;charset=UTF-8" language="java"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


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
                        <div class="credential"></div>
                    </div>
                </div>
                <div id="results">
                    <div id="resultTitle">
                        <h3>Ranking panel</h3>
                    </div>
                    <div class="messages">

                        <c:forEach items="${userList}" var="user"
                            varStatus="status">

                            <div class="message">
                                <div class="user">User mail:"${user.mail}"
                                </div>
                                <div class="date">User
                                    points:"${user.points}"</div>
                            </div>
                        </c:forEach>

                    </div>
                </div>

                <jsp:include page="/WEB-INF/jsp/include/menu.jsp" />

            </div>
        </div>
    </div>
</body>

</html>