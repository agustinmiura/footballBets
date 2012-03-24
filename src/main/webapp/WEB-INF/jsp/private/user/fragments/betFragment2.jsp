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


<div id="${betUnit.id}">

    <div class="message3">
        <div class="author">
            <form id="formBet${betUnit.id}">


                <fieldset>
                    <legend>${betUnit.match}</legend>
                    <div id="formMail" class="separateTopBottom">
                        <label class="label" for="localTeam">${betUnit.match.local}:</label>

                        <select id="result" name="result">
                            <option value="1">Win</option>
                            <option value="0">Draw</option>
                            <option value="-1">Lose</option>
                            <option value="-2">Something i dont care</option>
                        </select>
                    </div>
                    <input type="hidden" value="${betUnit.id}" name="betUnitId" />

                    <div id="formCheckPass" class="separateTopBottom">

                        <input type="button" value="Bet here"
                            onclick="sendBet('<c:out value="${betUnit.id}"/>');"
                            class="button" />


                    </div>

                </fieldset>
            </form>
        </div>
    </div>

</div>
