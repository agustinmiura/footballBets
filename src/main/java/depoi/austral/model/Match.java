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
package depoi.austral.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import depoi.austral.model.other.Result;

@PersistenceCapable
public class Match implements Persistable, Comparable<Match> {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private Team local;
	@Persistent
	private Team guest;
	@Persistent
	private int localScore;
	@Persistent
	private int guestScore;
	@Persistent
	private int localEndPenalties;
	@Persistent
	private int guestEndPenalties;
	private Date date;

	private Long tournamentId;

	public Match() {
		localScore = guestScore = localEndPenalties = guestEndPenalties = 0;
		date = new Date();
	}

	// los puntos refieren a 2 si gano, 1 si empato o 0 si perdio;
	public int getLocalPoints() {
		if (localScore > guestScore) {
			return 2;
		}
		if (localScore == guestScore) {
			if (localEndPenalties > guestEndPenalties) {
				return 2;
			}
			if (localEndPenalties < guestEndPenalties) {
				return 0;
			}
			return 1;
		}
		return 0;
	}

	public int getGuestPoints() {
		return (int) Math.abs(2 - getLocalPoints());
	}

	public Team getLocal() {
		return local;
	}

	public void setLocal(Team local) {
		this.local = local;
	}

	public Team getGuest() {
		return guest;
	}

	public void setGuest(Team guest) {
		this.guest = guest;
	}

	public String toString() {
		return local.toString() + " vs " + guest.toString();
	}

	public int getLocalScore() {
		return localScore;
	}

	public void setLocalScore(int localScore) {
		this.localScore = localScore;
	}

	public int getGuestScore() {
		return guestScore;
	}

	public void setGuestScore(int guestScore) {
		this.guestScore = guestScore;
	}

	public int getLocalEndPenalties() {
		return localEndPenalties;
	}

	public void setLocalEndPenalties(int localEndPenalties) {
		this.localEndPenalties = localEndPenalties;
	}

	public int getGuestEndPenalties() {
		return guestEndPenalties;
	}

	public void setGuestEndPenalties(int guestEndPenalties) {
		this.guestEndPenalties = guestEndPenalties;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Result getResult() {

		int goals = localScore - guestScore;
		Result result = null;

		if (goals > 0) {
			result = Result.WIN;

		} else if (goals == 0) {
			result = Result.DRAW;

		} else {
			result = Result.LOSS;
		}
		return result;
	}

	public int hashCode() {
		return id.intValue();
	}

	public boolean equals(Object object) {
		Long myId = id;
		Long otherId = ((Match) object).getId();
		return (myId.equals(otherId));
	}

	@Override
	public int compareTo(Match o) {
		return (int) (id - o.getId());
	}

	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}

	public Long getTournamentId() {
		return tournamentId;
	}
}
