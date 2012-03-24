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
package depoi.austral.dao.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.jdo.PersistenceManagerFactory;

import org.springframework.dao.DataAccessException;

import depoi.austral.dao.interfaces.TeamDAO;
import depoi.austral.model.Persistable;
import depoi.austral.model.Team;
import depoi.austral.model.Tournament;
import depoi.austral.services.RandomService;

public class TeamRamImplDao implements TeamDAO {

	private RandomService randomService;

	private String[] nameList = new String[] { "South Africa", "Mexico",
			"Uruguay", "France", "South Korea", "Greece", "Argentine",
			"Nigeria" };
	private String[] shortNameList = new String[] { "sd", "mx", "uy", "fr",
			"kr", "gr", "ar", "ng" };

	private Map<String, Team> map;

	public static void main(String[] args) {

		try {
			TeamDAO teamDao = new TeamRamImplDao();
			Team team = teamDao.loadByName("Mexico");
			int a = 33;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TeamRamImplDao() {
		randomService = new RandomService();
		map = new HashMap<String, Team>();
		for (int i = 0; i < nameList.length; i++) {
			Team team = new Team();
			team.setId(randomService.getRandomLong(200));
			team.setName(nameList[i]);
			team.setShortName(shortNameList[i]);
			map.put(team.getName(), team);
		}
	}

	@Override
	public Team loadTeam(Team team) throws Exception {
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		Team answer = null;
		while (iterator.hasNext()) {
			Team team2 = map.get(iterator.next());
			if (team.equals(team2)) {
				answer = team2;
			}
		}
		return answer;
	}

	@Override
	public void makePersistent(Team team) {
		if (map.containsKey(team.getName())) {
			throw new IllegalArgumentException("already persist this class");
		} else {
			Long id = team.getId();
			id = randomService.getRandomLong(400);
			team.setId(id);
			map.put(team.getName(), team);
		}
	}

	@Override
	public void modifyTeam(Team team) throws Exception {
		if (map.containsKey(team.getName())) {
			Team teamInMap = map.get(team.getName());
			teamInMap.setName(team.getName());
			map.remove(team.getName());
			map.put(teamInMap.getName(), team);
		} else {
			throw new IllegalArgumentException("i dont have that team");
		}
	}

	@Override
	public void remove(Team team) {
		if (map.containsKey(team.getName())) {
			map.remove(team.getName());
		} else {
			throw new IllegalArgumentException("i dont have that team");

		}
	}

	@Override
	public List<Team> retrieveAll() {
		List<Team> teamList = new ArrayList<Team>();
		Set<String> stringSet = map.keySet();
		Iterator<String> iterator = stringSet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			teamList.add(map.get(key));
		}
		return Collections.unmodifiableList(teamList);
	}

	public RandomService getRandomService() {
		return randomService;
	}

	public void setRandomService(RandomService randomService) {
		this.randomService = randomService;
	}

	@Override
	public Team loadByName(String name) throws Exception {
		return map.get(name);
	}
}
