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
package depoi.austral.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.dao.memory.TournamentRamImplDao;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.other.BetUnit;
import depoi.austral.model.other.Result;
import depoi.austral.services.IRankingService;
import depoi.austral.services.IRankingUserService;
import depoi.austral.services.TournamentService;

public class RankingController extends MultiActionController{

	private IRankingService rankingService;
	private TournamentService tournamentService;

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	 public ModelAndView seeRanking(HttpServletRequest request,HttpServletResponse response){
		 String stringId=request.getParameter("id");
		 
		 List<User> userList=new ArrayList<User>();
		 List<Integer> pointList=new ArrayList<Integer>();
		 
		 ModelAndView mav=new ModelAndView("private/ranking/ranking");
		 
		 try {
			Long id=Long.parseLong(stringId);
			 Tournament tournament=tournamentService.getTournament(id.toString());
			 Map<User,Integer> mapUser=rankingService.getFor(tournament);
			 
			 java.util.Set<User> set=mapUser.keySet();
			 Iterator<User> iterator=set.iterator();
			 while(iterator.hasNext()){
				 User user=iterator.next();
				 user.setPoints(mapUser.get(user));
				 userList.add(user);
			 }
			 Collections.sort(userList,new UserComparator());
			 
 		 } catch (Exception e) {
			e.printStackTrace();
		}finally{
			mav.addObject("userList",userList);
			return mav;
		}		
	 }

	public IRankingService getRankingService() {
		return rankingService;
	}

	public void setRankingService(IRankingService rankingService) {
		this.rankingService = rankingService;
	}


	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}
}
