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
    <div id="header">
        <div id="title"></div>
        <h1 class="bigTitle">I bet DPOI project</h1>
        <h2 class="subBigTitle">A web system to make bets about sports</h2>
        <a class="credential" href="<c:out value="${parameter.loginUrl}"/>">Login</a>
    </div>
    <div id="introResults">
        <h3 id="resultTitle">Login panel</h3>
    </div>
    <div id="intro">
        <h3 id="introTitle">About the project</h3>
        <p>This WebApp was created by Agustin Miura and Jose Rozanec. We use
            the following technologies</p>
        <ul>
            <li>Spring MVC 2.5.6</li>
            <li>JSTL</li>
            <li>JDO</li>
            <li>CSS for the look and feel</li>
            <li>XHTML</li>
        </ul>
        <p>Just enjoy it!</p>
    </div>

</body>
</html>