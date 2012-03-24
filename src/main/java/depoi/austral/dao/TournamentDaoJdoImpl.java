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
package depoi.austral.dao;

import java.util.List;

import javax.jdo.PersistenceManagerFactory;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.jdo.support.JdoDaoSupport;

import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.model.Tournament;

public class TournamentDaoJdoImpl extends JdoDaoSupport implements
		TournamentDAO {

	private PersistenceManagerFactory persistenceManagerFactory;

	@Override
	public Tournament loadTournament(Tournament tournament)
			throws DataAccessException {
		Long id = new Long(tournament.getId());
		Tournament tournamet = (Tournament) getJdoTemplate().getObjectById(
				Tournament.class, id);
		if (tournament == null) {
			throw new RuntimeException("Worker " + tournament.getId()
					+ " not found");
		}
		return (Tournament) getPersistenceManager().detachCopy(tournament);
	}

	@Override
	public void makePersistent(Tournament tournament)
			throws DataAccessException {
		// TODO Auto-generated method stub
		getJdoTemplate().makePersistent(tournament); // hace el setter de
														// PersistenceManagerFactory;
														// hace todo automatico.

	}

	@Override
	public void remove(Tournament tournament) throws DataAccessException {
		getPersistenceManager().deletePersistent(tournament);

	}

	@Override
	public List<Tournament> retrieveCurrentTournaments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyTournament(Tournament tournament)
			throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Tournament byName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exist(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tournament byId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
