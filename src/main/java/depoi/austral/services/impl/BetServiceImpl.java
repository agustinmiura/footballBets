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
package depoi.austral.services.impl;

import java.util.List;

import depoi.austral.dao.IBetUnitDataDao;
import depoi.austral.dao.interfaces.IBetDao2;
import depoi.austral.dao.interfaces.IUserDao;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.model.other.BetUnit;
import depoi.austral.services.BetService;

public class BetServiceImpl implements BetService {
	IBetUnitDataDao betUnitDataDao;
	private IBetDao2 betDao;
	private IUserDao userDao;

	@Override
	public void add(BetUnit betUnit) throws Exception {
		betDao.add(betUnit);
	}

	@Override
	public List<BetUnit> forUserInTournament(User user, Tournament tournament)
			throws Exception {
		return betDao.forUserInTournament(user, tournament);
	}

	@Override
	public void modify(Long id, BetUnit newParams) throws Exception {
		betDao.modify(id, newParams);
	}

	public IBetDao2 getBetDao() {
		return betDao;
	}

	public void setBetDao(IBetDao2 betDao) {
		this.betDao = betDao;
	}

	@Override
	public BetUnit getById(Long id) throws Exception {
		return betDao.getById(id);
	}

	@Override
	public boolean canBetUser(BetUnit betUnit, User user) throws Exception {
		// TODO Auto-generated method stub
		BetUnit betUnitTemp = betDao.getById(betUnit.getId());

		String userMail = user.getMail();

		boolean answer = false;

		if (betUnitTemp != null) {
			BetUnitData betUnitData = betUnitDataDao.getById(betUnit.getId());
			String otherMail = betUnitData.getUserMail();
			boolean check = (otherMail.compareTo(userMail) == 0);
			if (check) {
				answer = true;
			}
		}
		return answer;
	}

	public IBetUnitDataDao getBetUnitDataDao() {
		return betUnitDataDao;
	}

	public void setBetUnitDataDao(IBetUnitDataDao betUnitDataDao) {
		this.betUnitDataDao = betUnitDataDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
