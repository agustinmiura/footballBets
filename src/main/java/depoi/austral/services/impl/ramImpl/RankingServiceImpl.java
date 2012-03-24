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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import depoi.austral.dao.interfaces.IBetDao2;
import depoi.austral.dao.interfaces.IRankingDao;
import depoi.austral.dao.interfaces.IUserDao;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.other.BetUnit;
import depoi.austral.services.BetService;
import depoi.austral.services.IRankingService;
import depoi.austral.system.DateUtils;

public class RankingServiceImpl implements IRankingService {

	private BetService betService;
	private IUserDao userDao;

	public void setBetService(BetService betService) {
		this.betService = betService;
	}

	public BetService getBetService() {
		return betService;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Map<User, Integer> getFor(Tournament tournament) throws Exception {
		List<BetUnit> betList = null;
		List<User> userList = new ArrayList<User>();
		try {
			userList = userDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator<User> iterator = userList.iterator();
		Map<User, Integer> userPoints = new HashMap<User, Integer>();

		while (iterator.hasNext()) {
			User user = iterator.next();
			betList = new ArrayList<BetUnit>();
			int points = 0;
			try {
				betList = betService.forUserInTournament(user, tournament);
				for (int j = 0; j < betList.size(); j++) {

					if (DateUtils.hasPlayedMatch(betList.get(j))) {
						if (DateUtils.hasUserGuess(betList.get(j).getMatch(),
								betList.get(j))) {
							points++;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			userPoints.put(user, points);
		}
		return userPoints;
	}

	@Override
	public void setRankingDao(IRankingDao rankingDao) {
	}
}
