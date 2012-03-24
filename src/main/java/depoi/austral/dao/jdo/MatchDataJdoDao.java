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

import org.springframework.dao.DataAccessException;

import com.google.appengine.repackaged.com.google.common.base.Converter;

import depoi.austral.model.Match;
import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.model.jdo.MatchData;
import depoi.austral.model.other.MatchFactory;
import depoi.austral.services.RandomService;
import depoi.austral.system.DateUtils;

public class MatchDataJdoDao {
	
	private PersistenceManagerFactory persistenceManagerFactory;
	private List<Match> matches;
	private int count;
	
	public MatchDataJdoDao(){
		try {
			count = 0;
			matches = new MatchFactory().get2010();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void makePersistent(MatchData match) throws DataAccessException {
		PersistenceManager pm=persistenceManagerFactory.getPersistenceManager();
		RandomService randomService=new RandomService();
		if(match.getId().equals(new Long(0))){
			match.setId(randomService.getRandomLongNotZero(900));
		}
		try {
			pm.makePersistent(match);
		}finally{
			pm.close();
		}
	}

	public void modifyMatch(MatchData match) throws DataAccessException {
		PersistenceManager pm=persistenceManagerFactory.getPersistenceManager();
		try{
			MatchData toGet=pm.getObjectById(MatchData.class,match.getId());
			toGet.setTimestamp(match.getTimestamp());
			toGet.setGuestId(match.getGuestId());
			toGet.setGuestScore(match.getGuestScore());
			toGet.setLocalId(match.getLocalId());
			toGet.setLocalScore(match.getLocalScore());
		}finally{
			pm.close();
		}	
	}
	
	public MatchData getById(Long id) throws Exception {			
			matches.get(count).setId(id);
			MatchData data = new MatchData();
			data.setGuestId(matches.get(count).getGuest().getId());
			data.setGuestScore(matches.get(count).getGuestScore());
			data.setId(matches.get(count).getId());
			data.setLocalId(matches.get(count).getLocal().getId());
			data.setLocalScore(matches.get(count).getLocalScore());
			data.setTimestamp(DateUtils.getTimestamp(matches.get(count).getDate()));
			data.setTournamentId(matches.get(count).getTournamentId());
			makePersistent(data);			
			return data;			
	}
	
	public List<MatchData>getByTournamentId(Long tId){
		PersistenceManager pm=persistenceManagerFactory.getPersistenceManager();
		
		List<MatchData> answer = new ArrayList<MatchData>();
		String filter="tournamentId == tId";
		Query query = pm.newQuery(MatchData.class,filter);
	   
		try {
			query.declareParameters("Long tId");
			answer = (List<MatchData>) query.execute(tId);
		} finally {
			query.closeAll();
		}    
		return answer;
	}

	public void remove(MatchData match) throws DataAccessException {
		getPersistenceManager().deletePersistent(match);
	}

	public PersistenceManagerFactory getPersistenceManagerFactory() {
		return persistenceManagerFactory;
	}
	
	public void setPersistenceManagerFactory(
			PersistenceManagerFactory persistenceManagerFactory) {	
		this.persistenceManagerFactory = persistenceManagerFactory;
	}
	
	public PersistenceManager getPersistenceManager(){
		return persistenceManagerFactory.getPersistenceManager();
	}
}
