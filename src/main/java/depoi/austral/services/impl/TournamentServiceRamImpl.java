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
package depoi.austral.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import depoi.austral.dao.interfaces.IBetDao2;
import depoi.austral.dao.interfaces.IRankingDao;
import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.dao.memory.BetDaoRamImpl;
import depoi.austral.dao.memory.TournamentRamImplDao;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.services.IRankingService;
import depoi.austral.services.TournamentService;
import depoi.austral.services.impl.ramImpl.RankingServiceImpl;

public class TournamentServiceRamImpl implements TournamentService {

	private TournamentDAO tournamentDao;

	@Override
	public Tournament getTournament(String id) throws Exception {
		Tournament tournament = new Tournament();
		Long idN = new Long(id);
		tournament.setId(idN);
		return tournamentDao.loadTournament(tournament);
	}

	@Override
	public List<Tournament> listCurrentTournaments() throws Exception {
		return tournamentDao.retrieveCurrentTournaments();
	}

	@Override
	public Tournament loadTournament(Tournament tournament) throws Exception {
		return tournamentDao.loadTournament(tournament);

	}

	@Override
	public void modifyTournament(Tournament tournament) throws Exception {
		tournamentDao.modifyTournament(tournament);
	}

	@Override
	public void remove(Tournament tournament) throws Exception {
		tournamentDao.remove(tournament);
	}

	public TournamentDAO getTournamentDao() {
		return tournamentDao;
	}

	public void setTournamentDao(TournamentDAO tournamentDao) {
		this.tournamentDao = tournamentDao;
	}

	@Override
	public void makePersistant(Tournament tournament) throws Exception {
		tournamentDao.makePersistent(tournament);
	}

	@Override
	public boolean exist(Long id) throws Exception {
		// TODO Auto-generated method stub
		return tournamentDao.exist(id);
	}

	@Override
	public Tournament byId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return tournamentDao.byId(id);
	}
}
