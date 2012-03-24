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
package depoi.austral.dao.jdo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.dao.DataAccessException;

import depoi.austral.dao.interfaces.MatchDAO;
import depoi.austral.model.Tournament;
import depoi.austral.model.jdo.TournamentData;

public class TournamentDataJdoDao {
	private PersistenceManagerFactory persistenceManagerFactory;
	private MatchDAO matchDao;

	public TournamentData loadTournament(Tournament tournament)
			throws DataAccessException {
		PersistenceManager pm = getPersistenceManager();
		TournamentData data = null;
		Long id = new Long(tournament.getId());
		try {
			data = (TournamentData) pm.getObjectById(TournamentData.class, id);
		} finally {
			pm.close();
			return data;
		}
	}

	public void makePersistent(Tournament tournament)
			throws DataAccessException {
		PersistenceManager pm = persistenceManagerFactory
				.getPersistenceManager();
		try {
			pm.makePersistent(convert(tournament));
		} finally {
			pm.close();
		}
	}

	public void remove(Tournament tournament) throws DataAccessException {
		getPersistenceManager().deletePersistent(convert(tournament));
	}

	public List<TournamentData> retrieveCurrentTournaments() {
		List<TournamentData> tournaments = new ArrayList<TournamentData>();
		PersistenceManager pm = persistenceManagerFactory
				.getPersistenceManager();
		Query query = pm.newQuery(TournamentData.class);
		try {
			List<TournamentData> tempList = (List<TournamentData>) query
					.execute();
			Iterator<TournamentData> iterator = tempList.iterator();
			while (iterator.hasNext()) {
				tournaments.add(iterator.next());
			}
		} finally {
			query.closeAll();
			pm.close();
			return tournaments;
		}
	}

	public void modifyTournament(Tournament tournament)
			throws DataAccessException {
		PersistenceManager pm = persistenceManagerFactory
				.getPersistenceManager();
		try {
			TournamentData toGet = pm.getObjectById(TournamentData.class,
					tournament.getId());
			toGet.setDescription(tournament.getDescription());
			toGet.setTournamentName(tournament.getTournamentName());
			toGet.setDescription(tournament.getDescription());
		} finally {
			pm.close();
		}
	}

	public TournamentData byName(String nameParam) throws Exception {
		PersistenceManager pm = persistenceManagerFactory
				.getPersistenceManager();
		List<TournamentData> tournament = new ArrayList<TournamentData>();
		String filter = "name==param";
		Query query = pm.newQuery(TournamentData.class, filter);
		try {
			query.declareParameters("String param");
			tournament = (List<TournamentData>) query.execute(nameParam);
		} finally {
			query.closeAll();
		}
		if (tournament.size() > 0) {
			return tournament.get(0);
		}
		return null;
	}

	public PersistenceManagerFactory getPersistenceManagerFactory() {
		return persistenceManagerFactory;
	}

	public void setPersistenceManagerFactory(
			PersistenceManagerFactory persistenceManagerFactory) {
		this.persistenceManagerFactory = persistenceManagerFactory;
	}

	public PersistenceManager getPersistenceManager() {
		return persistenceManagerFactory.getPersistenceManager();
	}

	private TournamentData convert(Tournament tournament) {
		TournamentData tournamentData = new TournamentData();
		tournamentData.setDescription(tournament.getDescription());
		tournamentData.setId(tournament.getId());
		tournamentData.setTournamentName(tournament.getTournamentName());
		return tournamentData;
	}

	public void setMatchDao(MatchDAO matchDao) {
		this.matchDao = matchDao;
	}

	public MatchDAO getMatchDao() {
		return matchDao;
	}
}
