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
package depoi.austral.dao.jdo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jdo.Query;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import depoi.austral.dao.IBetUnitDataDao;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.services.RandomService;

public class BetUnitDataJdoDao implements IBetUnitDataDao{
	
	private PersistenceManagerFactory persistenceManagerFactory;
	
	@Override
	public void add(BetUnitData betUnitData) throws Exception {
		//creates a random betunitdata here 
		PersistenceManager pm=persistenceManagerFactory.getPersistenceManager();
		RandomService randomService=new RandomService();
		if(betUnitData.getId().equals(new Long(0))){
			betUnitData.setId(randomService.getRandomLongNotZero(900));
		}
		try {
			pm.makePersistent(betUnitData);
		}finally{
			pm.close();
		}
	}

	@Override
	public List<BetUnitData> forUserInTournament(User user,Tournament tournament) throws Exception {
		PersistenceManager pm=persistenceManagerFactory.getPersistenceManager();
		
		List<BetUnitData> answer = new ArrayList<BetUnitData>();
		String filter="tournamentId == tId && userMail == uMail";
		Query query = pm.newQuery(BetUnitData.class,filter);
	   
		try {
			query.declareParameters("Long tId, String uMail");
			answer = (List<BetUnitData>) query.execute(tournament.getId(),user.getMail());
		} finally {
			query.closeAll();
		}    
		return answer;
	}

	@Override
	public List<BetUnitData> listAll() throws Exception {
		List<BetUnitData> answer=new ArrayList<BetUnitData>();		
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
		Query query = pm.newQuery(BetUnitData.class);
		try {	
			List<BetUnitData> tempList=(List<BetUnitData>) query.execute();
			
			Iterator<BetUnitData> iterator=tempList.iterator();
			while(iterator.hasNext()){
				answer.add(iterator.next());	
			}
		}finally{
			query.closeAll();
			pm.close();
			return answer;
		}	
	}

	@Override
	public void modify(Long id, BetUnitData newParams) throws Exception {		  
		PersistenceManager pm=persistenceManagerFactory.getPersistenceManager();
		try{
			BetUnitData toGet=pm.getObjectById(BetUnitData.class,id);
			toGet.setMatchId(newParams.getMatchId());
			toGet.setResultId(newParams.getResultId());
			toGet.setTournamentId(newParams.getTournamentId());
			toGet.setUserMail(newParams.getUserMail());
		}finally{
			pm.close();
		}
	}

	public PersistenceManagerFactory getPersistenceManagerFactory() {
		return persistenceManagerFactory;
	}

	public void setPersistenceManagerFactory(
			PersistenceManagerFactory persistenceManagerFactory) {
		this.persistenceManagerFactory = persistenceManagerFactory;
	}

	@Override
	public BetUnitData getById(Long id) throws Exception {
		PersistenceManager pm=persistenceManagerFactory.getPersistenceManager();
		BetUnitData toGet=null;
		try{
			toGet=pm.getObjectById(BetUnitData.class,id);
		}finally{
			pm.close();
			return toGet; 
		}
	}	
}
