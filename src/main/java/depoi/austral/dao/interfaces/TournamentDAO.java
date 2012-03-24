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
package depoi.austral.dao.interfaces;

import depoi.austral.model.Tournament;

import java.util.List;

import javax.jdo.PersistenceManagerFactory;

import org.springframework.dao.DataAccessException;

public interface TournamentDAO {
    List<Tournament> retrieveCurrentTournaments();
    
	public void setPersistenceManagerFactory(PersistenceManagerFactory persistenceManagerFactory);

	public void makePersistent(Tournament tournament)throws DataAccessException;
	
	public void remove(Tournament tournament)throws DataAccessException;
	
	public Tournament loadTournament(Tournament tournament)throws DataAccessException;
	
	public void modifyTournament(Tournament tournament)throws DataAccessException;
	
	public Tournament byName(String name)throws Exception;

	public boolean exist(Long id)throws Exception;
	
	public Tournament byId(Long id)throws Exception;
}
