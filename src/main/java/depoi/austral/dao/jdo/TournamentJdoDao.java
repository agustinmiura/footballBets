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
package depoi.austral.dao.jdo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.dao.DataAccessException;

import depoi.austral.dao.interfaces.MatchDAO;
import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.model.Match;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.model.jdo.TournamentData;
import depoi.austral.model.other.TournamentComparator;
import depoi.austral.model.other.TournamentFactory;
import depoi.austral.services.RandomService;

public class TournamentJdoDao implements TournamentDAO {
	private PersistenceManagerFactory persistenceManagerFactory;
	private MatchDAO matchDao;
	private TournamentDataJdoDao tournamentDataDao;

	private Tournament prueba;
	private Tournament todosEditables;
	private Tournament ningunoEditable;
	private static List<Tournament> tournamentList;

	public static void main(String[] args) {
		try {
			TournamentDAO tDao = new TournamentJdoDao();

			int[] arr = new int[] { 10, 20, 30, -44, -11, 1111111 };

			for (int i = 0; i < arr.length; i++) {
				Long id = new Long(arr[i]);
				System.out.println(tDao.exist(id));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Comparator<Tournament> comparator;

	public TournamentJdoDao() {
		tournamentList = new ArrayList<Tournament>();
		comparator = new TournamentComparator();

		prueba = null;
		todosEditables = null;
		ningunoEditable = null;
		try {
			prueba = TournamentFactory.create("2010");
			prueba.setId(new Long(10));

			todosEditables = TournamentFactory.createEditables("1998");
			todosEditables.setId(new Long(20));

			ningunoEditable = TournamentFactory.createNoEditables("2002");
			ningunoEditable.setId(new Long(30));
		} catch (Exception e) {
			e.printStackTrace();
		}
		prueba.setDescription("South Africa 2010 WCup");
		todosEditables.setDescription("France 1998 WCup");
		ningunoEditable.setDescription("Corea-Japan 2002 WCup");

		tournamentList.add(prueba);
		tournamentList.add(todosEditables);
		tournamentList.add(ningunoEditable);
		Collections.sort(tournamentList, comparator);

	}

	@Override
	public Tournament loadTournament(Tournament tournament)
			throws DataAccessException {
		long first = tournament.getId();
		long second = prueba.getId();
		if (first == second) {
			return prueba;
		}
		second = todosEditables.getId();
		if (first == second) {
			return todosEditables;
		}
		return ningunoEditable;
		// return convert(tournamentDataDao.loadTournament(tournament));
	}

	@Override
	public void makePersistent(Tournament tournament)
			throws DataAccessException {
		PersistenceManager pm = persistenceManagerFactory
				.getPersistenceManager();
		RandomService randomService = new RandomService();
		if (tournament.getId().equals(new Long(0))) {
			tournament.setId(randomService.getRandomLongNotZero(900));
		}
		tournamentDataDao.makePersistent(tournament);
		Iterator<Match> matches = tournament.getMatches().values().iterator();
		Match match;
		while (matches.hasNext()) {
			match = matches.next();
			match.setTournamentId(tournament.getId());
			matchDao.makePersistent(match);
		}
	}

	@Override
	public void remove(Tournament tournament) throws DataAccessException {
		tournamentDataDao.remove(tournament);
	}

	@Override
	public List<Tournament> retrieveCurrentTournaments() {
		List<Tournament> tournaments = new ArrayList<Tournament>();
		tournaments.add(todosEditables);
		tournaments.add(ningunoEditable);
		tournaments.add(prueba);
		return tournaments;
	}

	@Override
	public void modifyTournament(Tournament tournament)
			throws DataAccessException {
		long first = tournament.getId();
		long second = prueba.getId();
		if (first == second) {
			prueba = tournament;
		}
		second = todosEditables.getId();
		if (first == second) {
			todosEditables = tournament;
		}
		second = ningunoEditable.getId();
		if (first == second) {
			ningunoEditable = tournament;
		}
	}

	@Override
	public Tournament byName(String nameParam) throws Exception {
		return convert(tournamentDataDao.byName(nameParam));
	}

	public PersistenceManagerFactory getPersistenceManagerFactory() {
		return persistenceManagerFactory;
	}

	@Override
	public void setPersistenceManagerFactory(
			PersistenceManagerFactory persistenceManagerFactory) {
		this.persistenceManagerFactory = persistenceManagerFactory;
	}

	public PersistenceManager getPersistenceManager() {
		return persistenceManagerFactory.getPersistenceManager();
	}

	public void setMatchDao(MatchDAO matchDao) {
		this.matchDao = matchDao;
	}

	public MatchDAO getMatchDao() {
		return matchDao;
	}

	public void setTournamentDataDao(TournamentDataJdoDao tournamentDataDao) {
		this.tournamentDataDao = tournamentDataDao;
	}

	public TournamentDataJdoDao getTournamentDataDao() {
		return tournamentDataDao;
	}

	private Tournament convert(TournamentData tournamentData) {
		Tournament tournament = new Tournament();
		tournament.setDescription(tournamentData.getDescription());
		tournament.setId(tournamentData.getId());
		tournament.setTournamentName(tournamentData.getTournamentName());
		return tournament;
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
			compareResult = this.comparator.compare(base, t);
			if (compareResult == 0) {
				answer = t;
				break;
			}
		}
		return answer;
	}
}
