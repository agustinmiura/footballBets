/*
 * Copyright (C) 2010 Miura Agustín
 * 					  Rozanect Jose	 
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
package depoi.austral.model.jdo;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import depoi.austral.model.Team;

@PersistenceCapable
public class MatchData implements Serializable {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	private Long tournamentId;
	private Long localId;
	private Long guestId;
	private String timestamp;// [yyy/mm/dd]
	private int localScore;
	private int guestScore;

	public MatchData() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}

	public Long getTournamentId() {
		return tournamentId;
	}

	public void setLocalId(Long localId) {
		this.localId = localId;
	}

	public Long getLocalId() {
		return localId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setLocalScore(int localScore) {
		this.localScore = localScore;
	}

	public int getLocalScore() {
		return localScore;
	}

	public void setGuestScore(int guestScore) {
		this.guestScore = guestScore;
	}

	public int getGuestScore() {
		return guestScore;
	}
}
