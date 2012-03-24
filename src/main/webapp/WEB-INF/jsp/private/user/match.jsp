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
<%@ page import="depoi.austral.model.Match"%>
<%@ page import="depoi.austral.model.Tournament"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
    Tournament tournament = new Tournament();
    Iterator<Match> matches = tournament.getMatches().values().iterator();
    Match match;
%>
<div class="tournamentMatches">
    <div class="tournamentMatchesHeader">
        <span class="tMatchesHeader">local</span> <span>L</span> <span>E</span>
        <span>V</span> <span class="tMatchesHeader">visitante</span>
    </div>
    <%
        while (matches.hasNext()) {
            match = matches.next();
    %>
    <div class="match">
        <%=match.getLocal()%>
        <input type="radio" name='<%=match.getId()%>'> <input
            type="radio" name='<%=match.getId()%>'> <input type="radio"
            name='<%=match.getId()%>'>
        <%=match.getGuest()%>
    </div>
    <%
        }
    %>
</div>


<%--ver de aplicar un selector de jQuery para que asigne una funcion de js--%>
<%--a cada uno de los radioButtons--%>