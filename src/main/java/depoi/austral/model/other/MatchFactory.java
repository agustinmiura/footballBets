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
package depoi.austral.model.other;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import depoi.austral.dao.interfaces.TeamDAO;
import depoi.austral.dao.memory.TeamRamImplDao;
import depoi.austral.model.*;
import depoi.austral.services.RandomService;
import depoi.austral.system.DateUtils;

public class MatchFactory {
	private static final TeamDAO teamDao = new TeamRamImplDao();

	public static void main(String[] args) {
		try {
			List<Match> matchList = MatchFactory.getMini();
			int state = 99;
			state = 99;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Match> getMini() throws Exception {
		RandomService randomService = new RandomService();

		List<Match> matchList = new ArrayList<Match>();

		Team sAfrica = teamDao.loadByName("South Africa");
		Team mexico = teamDao.loadByName("Mexico");
		Team uruguay = teamDao.loadByName("Uruguay");

		Team[] localArray = new Team[] { mexico, mexico, uruguay };
		Team[] visitArray = new Team[] { sAfrica, uruguay, sAfrica };

		for (int i = 0; i < localArray.length; i++) {
			Match match = new Match();
			match.setLocal(localArray[i]);
			match.setGuest(visitArray[i]);
			match.setId(randomService.getRandomLong(2000));
			Date date = DateUtils.create(27, 10, 2010);
			match.setDate(date);
			match.setLocalScore(10);
			match.setGuestScore(0);
			matchList.add(match);
		}

		return matchList;
	}

	public static List<Match> get2010() throws Exception {
		return getMatches(0);
	}

	public static List<Match> getAllEditables() throws Exception {
		return getMatches(1);
	}

	public static List<Match> getNoEditables() throws Exception {
		return getMatches(-1);
	}

	// -1=no editables; 0=merged; 1=all editables
	private static List<Match> getMatches(int merged) throws Exception {
		RandomService randomService = new RandomService();

		List<Match> matchList = new ArrayList<Match>();
		Team sAfrica = teamDao.loadByName("South Africa");
		Team mexico = teamDao.loadByName("Mexico");
		Team uruguay = teamDao.loadByName("Uruguay");
		Team france = teamDao.loadByName("France");
		Team sKorea = teamDao.loadByName("South Korea");
		Team greece = teamDao.loadByName("Greece");
		Team argentine = teamDao.loadByName("Argentine");
		Team nigeria = teamDao.loadByName("Nigeria");

		Team[] localArray = new Team[] { sAfrica, uruguay, sAfrica, france,
				mexico, france };
		Team[] visitArray = new Team[] { mexico, france, uruguay, mexico,
				uruguay, sAfrica };

		Team[] localArray2 = new Team[] { sKorea, argentine, argentine, greece,
				nigeria, greece };
		Team[] visitArray2 = new Team[] { greece, nigeria, sKorea, nigeria,
				sKorea, argentine };

		int year = 2010 + merged;
		int month = 7;
		int day = 20;
		for (int i = 0; i < localArray.length; i++) {
			Match match = new Match();
			match.setLocal(localArray[i]);
			match.setGuest(visitArray[i]);
			match.setId(randomService.getRandomLong(2000));
			match.setDate(DateUtils.create(day, month, year));
			matchList.add(match);
			day++;
		}

		month = 3;
		for (int i = 0; i < localArray2.length; i++) {
			Match match = new Match();
			match.setLocal(localArray2[i]);
			match.setGuest(visitArray2[i]);
			match.setId(randomService.getRandomLong(2000));
			match.setDate(DateUtils.create(day, month, year));
			matchList.add(match);
			month++;
			day++;
		}
		return matchList;
	}
}
