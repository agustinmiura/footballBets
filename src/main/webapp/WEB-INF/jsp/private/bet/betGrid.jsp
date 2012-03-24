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

<c:forEach items="${betList}" var="betUnit">
    <c:if test="${betUnit.closedBettingPeriod}">
        <div id="formBet${betUnit.id}">
            <span class="local"> <img
                src="/images/flags/${betUnit.match.local.shortName}.png"
                alt="${betUnit.match.local} flag" /> ${betUnit.match.local}
            </span> <span class="bet" id="result">${betUnit.result}</span> <span
                class="guest"> <img
                src="/images/flags/${betUnit.match.guest.shortName}.png"
                alt="${betUnit.match.guest} flag" /> ${betUnit.match.guest}
            </span> <span class="betFeedback">Result:
                ${betUnit.match.localScore} - ${betUnit.match.guestScore}</span>
        </div>
    </c:if>

    <c:if test="${!betUnit.closedBettingPeriod}">
        <form id="formBet${betUnit.id}">
            <label class="local" for="localTeam"> <img
                src="/images/flags/${betUnit.match.local.shortName}.png"
                alt="${betUnit.match.local} flag" /> ${betUnit.match.local}
            </label> <select class="bet" id="result" name="result"
                onchange="doAjax('formBet${betUnit.id}','/bet/do.htm','betUnitSuccess${betUnit.id}')">
                <c:if test="${betUnit.result.id==1}">
                    <option value="1" selected="selected">Win</option>
                </c:if>
                <c:if test="${betUnit.result.id!=1}">
                    <option value="1">Win</option>
                </c:if>
                <c:if test="${betUnit.result.id==0}">
                    <option value="0" selected="selected">Draw</option>
                </c:if>
                <c:if test="${betUnit.result.id!=0}">
                    <option value="0">Draw</option>
                </c:if>
                <c:if test="${betUnit.result.id==-1}">
                    <option value="-1" selected="selected">Lose</option>
                </c:if>
                <c:if test="${betUnit.result.id!=-1}">
                    <option value="-1">Lose</option>
                </c:if>
            </select> <input type="hidden" value="${betUnit.id}" name="betUnitId" /> <label
                class="guest" for="guestTeam"> <img
                src="/images/flags/${betUnit.match.guest.shortName}.png"
                alt="${betUnit.match.guest} flag" /> ${betUnit.match.guest}
            </label> <span class="betFeedback" id="betUnitSuccess${betUnit.id}">Status:
                ${betUnit.result}</span>
        </form>
    </c:if>
</c:forEach>

