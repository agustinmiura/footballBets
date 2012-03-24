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

import java.util.List;

import depoi.austral.model.Team;
import depoi.austral.model.Tournament;

public interface TeamService {
	List<Team> listAll() throws Exception;

	public Team getTeam(String id) throws Exception;

	public void remove(Team team) throws Exception;

	public Team loadTeam(Team team) throws Exception;

	public void modifyTeam(Team team) throws Exception;

	public void makePersistant(Team team) throws Exception;

	public Team getByName(String name) throws Exception;

}
