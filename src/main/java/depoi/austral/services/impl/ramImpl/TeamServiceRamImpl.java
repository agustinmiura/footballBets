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
package depoi.austral.services.impl.ramImpl;

import java.util.List;

import depoi.austral.dao.interfaces.TeamDAO;
import depoi.austral.model.Team;
import depoi.austral.services.TeamService;

public class TeamServiceRamImpl implements TeamService {

	private TeamDAO teamDao;

	@Override
	public Team getTeam(String id) throws Exception {
		Team team = new Team();
		team.setId(new Long(id));
		return teamDao.loadTeam(team);
	}

	@Override
	public List<Team> listAll() throws Exception {
		return teamDao.retrieveAll();
	}

	@Override
	public Team loadTeam(Team team) throws Exception {
		return teamDao.loadTeam(team);
	}

	@Override
	public void makePersistant(Team team) throws Exception {
		teamDao.makePersistent(team);

	}

	@Override
	public void modifyTeam(Team team) throws Exception {
		teamDao.modifyTeam(team);
	}

	@Override
	public void remove(Team team) throws Exception {
		teamDao.remove(team);
	}

	public TeamDAO getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDAO teamDao) {
		this.teamDao = teamDao;
	}

	@Override
	public Team getByName(String name) throws Exception {
		return teamDao.loadByName(name);
	}
}
