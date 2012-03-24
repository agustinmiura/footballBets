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
package depoi.austral.dao.interfaces;

import java.util.List;

import javax.jdo.PersistenceManagerFactory;

import org.springframework.dao.DataAccessException;

import depoi.austral.model.Match;

public interface MatchDAO{
	public void makePersistent(Match match)throws DataAccessException;
	
	public void remove(Match match)throws DataAccessException;
	
	public void modifyMatch(Match match)throws DataAccessException;
	
	public List<Match> getMatchesByTournamentId(Long tournamentId);
	
	public Match getMatch(Long id);
}
