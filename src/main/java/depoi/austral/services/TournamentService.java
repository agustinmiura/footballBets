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
package depoi.austral.services;

import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.model.Tournament;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface TournamentService {
	List<Tournament> listCurrentTournaments() throws Exception;

	Tournament getTournament(String id) throws Exception;

	public void remove(Tournament tournament) throws Exception;

	public Tournament loadTournament(Tournament tournament) throws Exception;

	public void modifyTournament(Tournament tournament) throws Exception;

	public void makePersistant(Tournament tournament) throws Exception;

	void setTournamentDao(TournamentDAO tDao);

	public boolean exist(Long id) throws Exception;

	public Tournament byId(Long id) throws Exception;
}
