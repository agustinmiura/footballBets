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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import depoi.austral.dao.interfaces.IBetDao2;
import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.other.BetFactory;
import depoi.austral.model.other.BetUnit;
import depoi.austral.model.other.Result;
import depoi.austral.model.other.TournamentFactory;

public class BetDaoRamImpl implements IBetDao2 {

	private Map<User, List<BetUnit>> userMailData;

	private static final String[] mailArray = new String[] { "ozzy1@gmail.com",
			"foo@gmail.com" };

	public BetDaoRamImpl() {

		userMailData = new TreeMap<User, List<BetUnit>>();
		// create a sample data with user pepe1 and foo
		try {
			createTestData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<BetUnit> forUser(User user) throws Exception {
		List<BetUnit> answer = null;
		answer = userMailData.get(user);

		if (answer == null) {
			answer = new ArrayList<BetUnit>();
			userMailData.put(user, answer);
		}
		return answer;

	}

	public List<BetUnit> forUserInTournament(User user, Tournament tournament)
			throws Exception {

		List<BetUnit> userBets = forUser(user);
		// if the user never used the app add the
		// create storage space for the user
		if (userBets == null || userBets.size() == 0) {
			userBets = new ArrayList<BetUnit>();
			userMailData.put(user, userBets);
		}

		// check if the has bets for that tournament
		List<BetUnit> answer = new ArrayList<BetUnit>();
		for (BetUnit bUnit : userBets) {
			Tournament otherT = bUnit.getTournament();
			if (otherT.equals(tournament)) {
				answer.add(bUnit);
			}

		}
		// we dont have bets for that tournament and the user
		// never accessed the app
		if (answer.size() == 0) {
			List<BetUnit> toAdd = BetFactory.forUser(user, tournament);
			for (BetUnit bUnit : toAdd) {
				userBets.add(bUnit);
			}
		}

		return userBets;

	}

	private List<BetUnit> subGet(User user, Tournament tournament)
			throws Exception {

		List<BetUnit> answer = new ArrayList<BetUnit>();
		List<BetUnit> userBets = forUser(user);
		for (BetUnit bUnit : userBets) {
			Tournament otherT = bUnit.getTournament();
			if (otherT.equals(tournament)) {
				answer.add(bUnit);
			}

		}
		return answer;
	}

	@Override
	public void add(BetUnit betUnit) throws Exception {
		// TODO Auto-generated method stub
		User user = betUnit.getUser();
		List<BetUnit> bList = forUser(user);
		bList.add(betUnit);

	}

	@Override
	public void modify(Long id, BetUnit bUnit) throws Exception {
		// TODO Auto-generated method stub
		User user = bUnit.getUser();
		List<BetUnit> bList = forUser(user);
		BetUnit toModify = null;
		for (BetUnit tempBet : bList) {
			if (tempBet.compareTo(bUnit) == 0) {
				toModify = tempBet;
				break;
			}

		}

		if (toModify == null) {

			throw new IllegalArgumentException("CAnt find the bet with id "
					+ bUnit.getId());
		} else {
			toModify.setResult(bUnit.getResult());

		}

	}

	/***
	 * if returns null we have didnt found the object
	 * 
	 * @param id
	 * @param bUnit
	 * @return
	 * @throws Exception
	 */
	private BetUnit get(Long id, BetUnit bUnit) throws Exception {
		User user = bUnit.getUser();
		List<BetUnit> bList = forUser(user);
		BetUnit answer = null;
		for (BetUnit bTemp : bList) {
			if (bTemp.getId() == id) {
				answer = bTemp;
				break;
			}

		}

		return answer;

	}

	@Override
	public Set<User> getUsers() throws Exception {
		// TODO Auto-generated method stub
		return userMailData.keySet();
	}

	private void createTestData() throws Exception {
		TournamentRamImplDao tournamentDao = new TournamentRamImplDao();
		Tournament t = tournamentDao.byName("2000");

		List<User> userList = new ArrayList<User>();
		for (int i = 0; i < mailArray.length; i++) {
			User user = new User();
			user.setMail(mailArray[i]);
			user.setUserId(mailArray[i]);
			userList.add(user);
		}

		for (int i = 0; i < userList.size(); i++) {
			List<BetUnit> list = this.forUserInTournament(userList.get(i), t);
			for (int j = 0; j < list.size(); j++) {
				list.get(j).setResult(Result.getRandom());
			}

		}
	}

	@Override
	public BetUnit getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported");
	}

}
