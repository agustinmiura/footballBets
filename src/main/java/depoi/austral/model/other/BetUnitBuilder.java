/*
 * Copyright (C) 2010 Miura Agustín
 * 					  Rozanec Jose	 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package depoi.austral.model.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import depoi.austral.model.Match;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.jdo.BetUnitData;

public class BetUnitBuilder implements IBetUnitBuilder {

	private List<BetUnitData> betUnitDataList;

	private Tournament tournament;

	private User user;

	private Map<Long, Match> matchMap;

	public BetUnitBuilder() {
		matchMap = new TreeMap<Long, Match>();
	}

	@Override
	public List<BetUnit> build() throws Exception {
		// TODO Auto-generated method stub
		List<BetUnit> answer = new ArrayList<BetUnit>();

		// first build the match map to use
		List<Match> matchList = tournament.getAll();

		for (Match match : matchList) {
			matchMap.put(match.getId(), match);
		}
		// now build the bets
		BetUnit betUnit;
		Match match;
		for (BetUnitData betUnitData : betUnitDataList) {
			betUnit = new BetUnit();
			// set id
			betUnit.setId(betUnitData.getId());

			// set the user
			betUnit.setUser(user);

			// set the tournament
			betUnit.setTournament(tournament);

			// set the match
			match = matchMap.get(betUnitData.getMatchId());
			betUnit.setMatch(match);

			// set the result
			Result result = Result.getResult(betUnitData.getResultId());
			betUnit.setResult(result);

			// add to list
			answer.add(betUnit);
		}

		return answer;
	}

	@Override
	public void setBetUnitData(List<BetUnitData> betUnitDataList)
			throws Exception {
		// TODO Auto-generated method stub
		this.betUnitDataList = betUnitDataList;
	}

	@Override
	public void setTournament(Tournament tournament) throws Exception {
		// TODO Auto-generated method stub
		this.tournament = tournament;
	}

	@Override
	public void setUser(User user) throws Exception {
		// TODO Auto-generated method stub
		this.user = user;
	}

}
