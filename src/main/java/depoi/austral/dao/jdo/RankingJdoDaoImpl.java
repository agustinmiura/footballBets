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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import depoi.austral.dao.interfaces.IBetDao2;
import depoi.austral.dao.interfaces.IRankingDao;
import depoi.austral.dao.interfaces.IUserDao;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.other.BetUnit;
import depoi.austral.system.DateUtils;

public class RankingJdoDaoImpl implements IRankingDao{

	private IBetDao2 betDao;	
	private IUserDao userDao;
	
	private Set<User> fromList(List<User> userList)throws Exception{
		Set<User> answer=new TreeSet<User>();
		Iterator<User> iterator=userList.iterator();
		while(iterator.hasNext()){
			answer.add(iterator.next());
		}
		return answer;
	}
	
	@Override
	public Map<User, Integer> getFor(Tournament tournament) throws Exception {
		List<BetUnit> betList=null;
		
		//Set<User> userSet=betDao.getUsers();
		List<User> userList=new ArrayList<User>();
		try {
			userList = userDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set<User> userSet=fromList(userList); 
		
	 	Iterator<User> iterator=userSet.iterator();
		
		Map<User,Integer> userPoints=new HashMap<User,Integer>(userSet.size()*3);
		
		while(iterator.hasNext()){
			User user=iterator.next();
			betList=betDao.forUserInTournament(user, tournament);
			Integer points=0;
			for(int i=0;i<betList.size();i++){
				BetUnit bUnit=betList.get(i);
				boolean check=DateUtils.hasPlayedMatch(bUnit);
				if(check){
					boolean guess=DateUtils.hasUserGuess(bUnit.getMatch(),bUnit);
					if(guess){
						points++;
					}			
				}		
			}	
			userPoints.put(user,points);		
		}	
		return userPoints;
	}

	public IBetDao2 getBetDao() {
		return betDao;
	}
	
	public void setBetDao(IBetDao2 betDao) {
		this.betDao = betDao;
	}
	
	public IUserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
}
