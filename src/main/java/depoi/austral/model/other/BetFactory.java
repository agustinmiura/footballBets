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
import java.util.List;

import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.dao.memory.TournamentRamImplDao;
import depoi.austral.model.*;
import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.services.RandomService;

/**
 * 
 * @author Administrador
 * 
 */
public class BetFactory {

	public static int[] indexArr = new int[] { -1, 0, 1 };

	public static void main(String[] args) {
		try {
			TournamentDAO tournamentDao = new TournamentRamImplDao();
			Tournament tournament = tournamentDao.byName("2010");
			User user = new User();
			user.setMail("pepe@gmail.com");
			user.setUserId("pepe@gmail.com");
			List<BetUnit> bList = BetFactory.forUser(user, tournament);
			for (BetUnit bUnit : bList) {
				bUnit.setId(new Long(22));
				System.out.println(bUnit);
			}
			int a = 10;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<BetUnit> forMini(User user, Tournament tournament)
			throws Exception {
		List<BetUnit> answer = new ArrayList<BetUnit>();
		List<Match> matchList = tournament.getAll();
		RandomService randomService = new RandomService();

		for (int i = 0; i < matchList.size(); i++) {
			BetUnit betUnit = new BetUnit();
			betUnit.setId(randomService.getRandomLong(2000));
			betUnit.setMatch(matchList.get(i));
			betUnit.setResult(Result.getRandom());
			betUnit.setTournament(tournament);
			betUnit.setUser(user);
			answer.add(betUnit);

		}
		BetUnit bTemp = answer.get(0);
		bTemp.setId(new Long(0));

		return answer;

	}

	/**
	 * creates a set of bets for a user betting in a tournament
	 * 
	 * @param user
	 * @param tournament
	 * @return
	 * @throws Exception
	 */
	public static List<BetUnit> forUser(User user, Tournament tournament)
			throws Exception {
		List<BetUnit> answer = new ArrayList<BetUnit>();
		List<Match> matchList = tournament.getAll();
		RandomService randomService = new RandomService();

		for (int i = 0; i < matchList.size(); i++) {
			BetUnit betUnit = new BetUnit();
			betUnit.setId(randomService.getRandomLong(2000));
			betUnit.setMatch(matchList.get(i));
			betUnit.setResult(Result.NOT_BET);
			betUnit.setTournament(tournament);
			betUnit.setUser(user);
			answer.add(betUnit);
		}
		BetUnit bTemp = answer.get(0);
		bTemp.setId(new Long(0));

		return answer;
	}

	public static BetUnit fromBetUnitData(BetUnitData betUnitData) {
		BetUnit bUnit = new BetUnit();
		bUnit.setId(betUnitData.getId());
		return bUnit;

	}

	public static List<BetUnit> fromBetUnitDataList(List<BetUnitData> info)
			throws Exception {
		List<BetUnit> answer = new ArrayList<BetUnit>();
		return answer;
	}

}
