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
<%@ page import="depoi.austral.model.other.Result;"%>

<c:forEach items="${matchList}" var="match">
    <form id="formMatch${match.id}">
        <label class="local" for="localTeam"> <img
            src="/images/flags/${match.local.shortName}.png"
            alt="${match.local} flag" /> ${match.local} <input class="input1"
            type="text" value="${match.localScore}" name="localScore" />
        </label> <label class="guest" for="guestTeam"> <input class="input2"
            type="text" value="${match.guestScore}" name="guestScore" /> <img
            src="/images/flags/${match.guest.shortName}.png"
            alt="${match.guest} flag" /> ${match.guest} <a class="submit"
            href="javascript:doAjax('formMatch${match.id}','/tournament/modify.htm','matchSuccess${match.id}')">Submit
                result!</a>
        </label> <input type="hidden" value="${tournament.id}" name="tournamentId" /> <input
            type="hidden" value="${match.id}" name="matchId" /> <span
            class="betFeedback" id="matchSuccess${match.id}">Status:
            ${match.localScore} - ${match.guestScore} </span>
    </form>

</c:forEach>

