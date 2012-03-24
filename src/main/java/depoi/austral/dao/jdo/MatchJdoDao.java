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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.dao.DataAccessException;

import depoi.austral.dao.interfaces.MatchDAO;
import depoi.austral.model.Match;
import depoi.austral.model.Tournament;
import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.model.jdo.MatchData;
import depoi.austral.services.RandomService;
import depoi.austral.services.TeamService;
import depoi.austral.system.DateUtils;

public class MatchJdoDao implements MatchDAO{
	private MatchDataJdoDao matchDataDao; 
	private TeamService teamService;
	
	@Override
	public void makePersistent(Match match) throws DataAccessException {
		RandomService randomService=new RandomService();
		if(match.getId().equals(new Long(0))){
			match.setId(randomService.getRandomLongNotZero(900));
		}
		matchDataDao.makePersistent(convert(match));
	}

	@Override
	public void modifyMatch(Match match) throws DataAccessException {
		matchDataDao.modifyMatch(convert(match));	
	}

	@Override
	public void remove(Match match) throws DataAccessException {
		matchDataDao.remove(convert(match));
	}
	
	private MatchData convert(Match match){
		MatchData data = new MatchData();
		data.setGuestId(match.getGuest().getId());
		data.setGuestScore(match.getGuestScore());
		data.setId(match.getId());
		data.setLocalId(match.getLocal().getId());
		data.setLocalScore(match.getLocalScore());
		data.setTimestamp(DateUtils.getTimestamp(match.getDate()));
		data.setTournamentId(match.getTournamentId());
		return data;
	}
	
	public void setMatchDataDao(MatchDataJdoDao matchDataDao){
		this.matchDataDao = matchDataDao;
	}

	public MatchDataJdoDao getMatchDataDao(){
		return matchDataDao;
	}

	@Override
	public List<Match> getMatchesByTournamentId(Long tournamentId) {
		List<MatchData>dataList=matchDataDao.getByTournamentId(tournamentId);
		List<Match>matches = new ArrayList<Match>();
		for(int j=0;j<dataList.size();j++){
			matches.add(convert(dataList.get(j)));
		}
		return matches;
	}
	
	public Match convert(MatchData data){
		Match match = new Match();
		System.out.println(""+data);
//		try {
//			
//			match.setDate(DateUtils.getDate(data.getTimestamp()));
//			match.setGuest(teamService.getTeam(""+data.getGuestId()));			
//			match.setLocal(teamService.getTeam(""+data.getLocalId()));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//		match.setGuestScore(data.getGuestScore());
//		match.setId(data.getId());
//		match.setLocalScore(data.getLocalScore());
//		match.setTournamentId(data.getTournamentId());
		return match;
	}

	@Override
	public Match getMatch(Long id) {
		try {
			MatchData data = matchDataDao.getById(id);
			if(data!=null){
				return convert(data);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
