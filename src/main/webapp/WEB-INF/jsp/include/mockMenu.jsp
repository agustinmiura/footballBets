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
<div id="menu" >
	<ul >
	<li><a href="/hello.htm">View messages</a></li>
	<li><a href="/goToCreateMessage.htm">Create message</a></li>
<!--	Pass new to the form so knows that it has to persist the entity in the db-->
	<li><a href="/goToCreatePerson.htm?new=true">Go to create person</a></li>
<!--	Pass new to the form so knows that it has to modify the entity in the db-->
	<li><a href="/modifyPerson.htm?new=false">Go to modify person</a></li>
	<li><a href="/goToAjaxExample.htm">Go to ajax example</a></li>
	<li><a href="/ranking/see.htm">See user ranking</a></li>
	
<!--links for the admin-->
	<li><a href="/tournament/see.htm">see all the tournaments</a></li>
	<li><a href="/tournament/create.htm">Create a tournament</a></li>

<!--links for the admin to admin the team-->	
	<li><a href="/tournament/see.htm">see all the tournaments</a></li>
	<li><a href="/tournament/create.htm">Create a tournament</a></li>
	<li><a href="/team/see.htm">see all the teams</a></li>
<!--links for the the bets here-->		
	<li><a href="/bet/choose.htm">Choose tournament to bet</a></li>
	<li><a href="/bet/do.htm">Doing the bet</a></li>

<!--links for the the bet data testing-->		

	<li><a href="/betData/see.htm">seeAllBetData</a></li>
		<li><a href="/betData/create.htm">createRandomBetData</a></li>
		<li><a href="/betData/modify.htm">modify a bet data</a></li>
		<li><a href="/betData/forTournament.htm?tournamentId=1&&userMail=test@gmail.com">list for tournament here</a></li>
	</ul>

</div>