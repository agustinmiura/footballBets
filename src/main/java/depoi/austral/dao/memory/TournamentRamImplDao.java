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
package depoi.austral.dao.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.jdo.PersistenceManagerFactory;

import org.springframework.dao.DataAccessException;

import depoi.austral.dao.interfaces.TeamDAO;
import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.model.Match;
import depoi.austral.model.Team;
import depoi.austral.model.Tournament;
import depoi.austral.model.other.TournamentComparator;
import depoi.austral.model.other.TournamentFactory;

public class TournamentRamImplDao implements TournamentDAO {

	private String[] nameList = new String[] { "2010", "1994" };
	private List<Tournament> tournamentList;
	private static final int[] tournamentId = new int[] { 10, 20, 30 };
	private Comparator<Tournament> byIdComp;

	public TournamentRamImplDao() {
		try {
			subConstructor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void subConstructor() throws Exception {
		tournamentList = new ArrayList<Tournament>();
		Tournament tournament0 = TournamentFactory.create("2010");
		tournament0.setDescription("South Africa 2010 tournament");
		tournamentList.add(tournament0);

		tournament0.setId(new Long(tournamentId[0]));

		Tournament tournament1 = TournamentFactory.create("1994");
		tournament1.setDescription("Usa 1994");
		tournamentList.add(tournament1);

		tournament1.setId(new Long(tournamentId[1]));

		tournament1 = TournamentFactory.createMini("2000");
		tournament1.setId(new Long(0));
		tournamentList.add(tournament1);

		tournament1.setId(new Long(tournamentId[2]));

		byIdComp = new TournamentComparator();

	}

	private boolean existTournament(Tournament tournament) {
		boolean check = tournamentList.contains(tournament);
		return check;
	}

	@Override
	public Tournament loadTournament(Tournament tournament)
			throws DataAccessException {
		Tournament answer = null;
		for (int i = 0; i < tournamentList.size(); i++) {
			Tournament tournamentInList = tournamentList.get(i);
			if (tournamentInList.equals(tournament)) {
				answer = tournamentInList;
				break;
			}
		}
		return answer;
	}

	@Override
	public void makePersistent(Tournament tournament)
			throws DataAccessException {
		boolean check = existTournament(tournament);
		if (check) {
			throw new IllegalArgumentException("Object already addded");
		} else {
			tournamentList.add(tournament);
		}
	}

	@Override
	public void remove(Tournament tournament) throws DataAccessException {
		boolean check = existTournament(tournament);
		if (check) {
			int index = tournamentList.indexOf(tournament);
			tournamentList.remove(index);
		} else {
			throw new IllegalArgumentException("Object already addded");
		}
	}

	@Override
	public List<Tournament> retrieveCurrentTournaments() {
		return Collections.unmodifiableList(tournamentList);
	}

	@Override
	public void setPersistenceManagerFactory(
			PersistenceManagerFactory persistenceManagerFactory) {
	}

	@Override
	public void modifyTournament(Tournament tournament)
			throws DataAccessException {
		boolean check = existTournament(tournament);
		if (check) {
			int index = tournamentList.indexOf(tournament);
			Tournament inDao = tournamentList.get(index);
			tournamentList.remove(index);
			tournamentList.add(tournament);

		} else {
			throw new IllegalArgumentException("Object already addded");
		}
	}

	public Tournament byName(String name) throws Exception {
		Tournament answer = null;
		for (Tournament tournament : tournamentList) {
			String current = tournament.getTournamentName();
			if (current.equals(name)) {
				answer = tournament;
				break;
			}
		}
		return answer;
	}

	@Override
	public boolean exist(Long id) throws Exception {
		// TODO Auto-generated method stub
		return (byId(id) != null);
	}

	@Override
	public Tournament byId(Long id) throws Exception {
		// TODO Auto-generated method stub

		int index = -1;
		Tournament answer = null;
		Tournament base = new Tournament();
		base.setId(id);
		int compareResult;
		for (Tournament t : tournamentList) {
			compareResult = this.byIdComp.compare(base, t);
			if (compareResult == 0) {
				answer = t;
				break;
			}
		}
		return answer;
	}
}
