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

import depoi.austral.model.Match;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.jdo.BetUnitData;

public class BetUnit implements Comparable<BetUnit> {

	private Long id;

	private Tournament tournament;
	// who is betting
	private User user;
	private Match match;
	// what is betting the user about the local team vs guest team
	private Result result;
	private boolean closedBettingPeriod;

	private BetUnitData betUnitData;

	public BetUnit() {
		id = new Long(-1);
		result = Result.DRAW;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public boolean equals(Object object) {
		return (id == ((BetUnit) object).getId());

	}

	public int hashCode() {

		return id.intValue();
	}

	@Override
	public int compareTo(BetUnit o) {
		return (int) (this.id - o.getId());
	}

	public BetUnitData getBetUnitData() {
		if (betUnitData == null) {
			betUnitData = new BetUnitData();
			betUnitData.setId(id);
			betUnitData.setMatchId(match.getId());
			betUnitData.setResultId(result.getId());
			betUnitData.setTournamentId(tournament.getId());
			betUnitData.setUserMail(user.getMail());
		}
		return betUnitData;
	}

	public void setBetUnitData(BetUnitData betUnitData) {
		this.betUnitData = betUnitData;
	}

	@Override
	public String toString() {
		return "BetUnit [betUnitData=" + betUnitData + ", id=" + id
				+ ", match=" + match + ", result=" + result + ", tournament="
				+ tournament + ", user=" + user + "]";
	}

	public void setClosedBettingPeriod(boolean closedBettingPeriod) {
		this.closedBettingPeriod = closedBettingPeriod;
	}

	public boolean isClosedBettingPeriod() {
		return closedBettingPeriod;
	}
}
