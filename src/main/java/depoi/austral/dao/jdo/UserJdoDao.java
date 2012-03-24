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
package depoi.austral.dao.jdo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import depoi.austral.dao.interfaces.IUserDao;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.services.RandomService;

public class UserJdoDao implements IUserDao {

	private PersistenceManagerFactory persistenceManagerFactory;

	@Override
	public void addUser(User user) throws Exception {
		PersistenceManager pm = persistenceManagerFactory
				.getPersistenceManager();
		RandomService randomService = new RandomService();
		if (user.getId().equals(new Long(0))) {
			user.setId(randomService.getRandomLongNotZero(900));
		}
		try {
			pm.makePersistent(user);
		} finally {
			pm.close();
		}
	}

	@Override
	public boolean exist(User user) throws Exception {
		User tempUser = getByMail(user.getMail());
		boolean check = (tempUser != null);
		return check;
	}

	/**
	 * coded this
	 */
	public List<User> getAll() throws Exception {
		List<User> answer = new ArrayList<User>();
		PersistenceManager pm = persistenceManagerFactory
				.getPersistenceManager();
		Query query = pm.newQuery(User.class);
		try {
			List<User> tempList = (List<User>) query.execute();

			Iterator<User> iterator = tempList.iterator();
			while (iterator.hasNext()) {
				answer.add(iterator.next());
			}
		} finally {
			query.closeAll();
			pm.close();
			return answer;
		}
	}

	@Override
	public User getByMail(String param) throws Exception {
		User userAnswer = null;
		PersistenceManager pm = persistenceManagerFactory
				.getPersistenceManager();

		List<User> answer = new ArrayList<User>();
		String filter = "mail==param";
		Query query = pm.newQuery(User.class, filter);

		try {
			query.declareParameters("String param");
			answer = (List<User>) query.execute(param);
		} finally {
			query.closeAll();
		}
		if (answer.size() > 0) {
			userAnswer = answer.get(0);
		}
		return userAnswer;
	}

	public PersistenceManagerFactory getPersistenceManagerFactory() {
		return persistenceManagerFactory;
	}

	public void setPersistenceManagerFactory(
			PersistenceManagerFactory persistenceManagerFactory) {
		this.persistenceManagerFactory = persistenceManagerFactory;
	}
}
