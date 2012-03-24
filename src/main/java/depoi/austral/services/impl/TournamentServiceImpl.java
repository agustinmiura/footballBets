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

import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.model.Tournament;
import depoi.austral.services.TournamentService;

import java.util.List;

public class TournamentServiceImpl implements TournamentService {
	public List<Tournament> listCurrentTournaments() {
		return null;

	}

	public Tournament getTournament(String id) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public Tournament loadTournament(Tournament tournament) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyTournament(Tournament tournaament) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Tournament tournament) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void makePersistant(Tournament tournament) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTournamentDao(TournamentDAO tDao) {
		// TODO Auto-generated method stub

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
