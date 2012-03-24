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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
    haha here dude

    <div id="testMod">
        The form to modify here the bet unit data you want
        <form name="input" action="/betData/modify.htm" method="post">
            <select id="result" name="id">
                <c:forEach items="${parameter}" var="betUnitData">
                    <option value="${betUnitData.id}">${betUnitData.id}</option>
                </c:forEach>
            </select> User mail (long):
            <input type="text" name="userMail" />
            <br /> Match id (long):
            <input type="text" name="matchId" />
            <br /> Result integer (integer):
            <input type="text" name="resultId" />
            <br /> Tournament id (integer):
            <input type="text" name="tournamentId" />
            <br />

            <input type="submit" value="Submit" />
        </form>
    </div>

    <div id="listContainer">

        <c:forEach items="${parameter}" var="parameter" varStatus="status">

            <div class="message">id es : ${parameter.id}/tournamentId es :
                ${parameter.tournamentId}/ result es:${parameter.resultId}/Match
                id:${parameter.matchId}/ user mail:${parameter.userMail}</div>
        </c:forEach>

    </div>

    <div id="listContainer2">
        Showing the users
        <c:forEach items="${userList}" var="user" varStatus="status">

            <div class="message">User id es : ${user.id}/ UserId is
                :${user.userId}/ user mail:${user.mail}/ user
                points:${user.points}/</div>
        </c:forEach>

    </div>
</body>
</html>