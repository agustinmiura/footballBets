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
<%@ page import="depoi.austral.model.Tournament"%>
<%@ page import="depoi.austral.model.Match"%>
<%@ page import="java.util.Iterator"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="tournamentMatchesHeader">
    <span class="tMatchesHeader">Local</span> <span>L</span> <span>E</span> <span>V</span>
    <span class="tMatchesHeader">Visitante</span>
</div>

<c:forEach var="match" items="${tournament.matches}">
    <div class="match">
        ${match.local.name} <input type="radio" name='${match.Id}'> <input
            type="radio" name='${match.Id}'> <input type="radio"
            name='${match.Id}'> ${match.guest.name}
    </div>
</c:forEach>