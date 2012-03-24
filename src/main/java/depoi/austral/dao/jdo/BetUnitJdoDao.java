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
import java.util.List;
import java.util.Set;

import depoi.austral.dao.IBetUnitDataDao;
import depoi.austral.dao.interfaces.IBetDao2;
import depoi.austral.dao.interfaces.IUserDao;
import depoi.austral.dao.interfaces.MatchDAO;
import depoi.austral.model.BetUnitDataFactory;
import depoi.austral.model.Match;
import depoi.austral.model.Team;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.model.other.BetFactory;
import depoi.austral.model.other.BetUnit;
import depoi.austral.model.other.BetUnitBuilder;
import depoi.austral.model.other.IBetUnitBuilder;
import depoi.austral.model.other.Result;
import depoi.austral.services.IUserService;
import depoi.austral.services.TeamService;
import depoi.austral.services.TournamentService;
import depoi.austral.services.impl.GoogleUserService;

public class BetUnitJdoDao implements IBetDao2{
	private IBetUnitDataDao betUnitDataDao;	
	private TournamentService tournamentService;
	private MatchDAO matchDao;
	private IUserDao userDao;
	
	@Override
	public void add(BetUnit betUnit) throws Exception {
		BetUnitDataFactory betUnitDataFactory=new BetUnitDataFactory();
		BetUnitData bUnitData=betUnitDataFactory.fromBetUnit(betUnit);
		betUnitDataDao.add(bUnitData);
	}

	@Override
	public List<BetUnit> forUserInTournament(User user, Tournament tournament) throws Exception {
		List<BetUnitData> betUnitDataListTemp=betUnitDataDao.forUserInTournament(user, tournament);
		List<BetUnitData> betUnitDataList=new ArrayList<BetUnitData>();
		
		//check if the user has bets for the tournament
		//if he never bets we create new bets for the user
		if(betUnitDataListTemp.size()==0){
			List<BetUnit> toAdd=BetFactory.forUser(user, tournament);
			for(BetUnit bUnit:toAdd){
				BetUnitData bUnitData=bUnit.getBetUnitData();
				//add to the list to work here
				betUnitDataList.add(bUnitData);
				//add to the database
				betUnitDataDao.add(bUnitData);			
			}		
		}else{
			betUnitDataList = betUnitDataListTemp;
		}

		//get the tournament here 
		Tournament tournamentTemp=tournamentService.getTournament(tournament.getId().toString());
		//get all matches here 
		List<Match> matchList=tournamentTemp.getAll();
		
		IUserService userService=new GoogleUserService();
		User user1=userService.getCurrentUser();		
		user1=userDao.getByMail(user1.getMail());
		
		IBetUnitBuilder betUnitBuilder=new BetUnitBuilder();
		betUnitBuilder.setBetUnitData(betUnitDataList);
		betUnitBuilder.setTournament(tournament);
		betUnitBuilder.setUser(user1);
		
		List<BetUnit> answer=betUnitBuilder.build();
		return answer;
	}

	@Override
	public Set<User> getUsers() throws Exception {
		return null;
	}

	@Override
	public void modify(Long id, BetUnit newParams) throws Exception {
		BetUnitDataFactory betUnitDataFactory=new BetUnitDataFactory();
		BetUnitData bUnitData=betUnitDataFactory.fromBetUnit(newParams);
		System.out.println("in betUnit jdo dao the modify , the user "+newParams.getUser().getMail());
		betUnitDataDao.modify(id,bUnitData);
	}
	
	@Override
	public BetUnit getById(Long id) throws Exception {
		BetUnit answer=new BetUnit();
		BetUnitData betUnitData=betUnitDataDao.getById(id);
		
		if(betUnitData==null){
			throw new IllegalArgumentException("The bet unit with id "+id+"doesnt exist");
		}
		
		Tournament tournament=tournamentService.getTournament(betUnitData.getTournamentId().toString());
		
		Match match=tournament.getMatchById(betUnitData.getMatchId());
		Result result=Result.getResult(betUnitData.getResultId());
		
		answer.setId(betUnitData.getId());
		answer.setTournament(tournament);
		answer.setMatch(match);
		answer.setResult(result);
		
		return answer;
	}

	public IBetUnitDataDao getBetUnitDataDao() {
		return betUnitDataDao;
	}

	public void setBetUnitDataDao(IBetUnitDataDao betUnitDataDao) {
		this.betUnitDataDao = betUnitDataDao;
	}

	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	public void setMatchDao(MatchDAO matchDao) {
		this.matchDao = matchDao;
	}

	public MatchDAO getMatchDao() {
		return matchDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	
}
