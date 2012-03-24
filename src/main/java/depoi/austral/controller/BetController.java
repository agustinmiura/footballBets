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
package depoi.austral.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import depoi.austral.dao.IBetUnitDataDao;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.model.other.BetUnit;
import depoi.austral.model.other.Result;
import depoi.austral.services.BetService;
import depoi.austral.services.IUserService;
import depoi.austral.services.RandomService;
import depoi.austral.services.TournamentService;
import depoi.austral.system.DateUtils;

public class BetController extends MultiActionController{

	private BetService betService;
	private TournamentService tournamentService;
	private RandomService randomService;
	private IUserService userService;
	private IBetUnitDataDao betUnitDataDao;
	
	public BetController(){}	
	
	public ModelAndView doBet(HttpServletRequest request,HttpServletResponse response){
		//fail answer here
		ModelAndView mav=new ModelAndView("private/user/fragments/notifyErrorFragment");
		
		String betUnitIdString=request.getParameter("betUnitId");
		String resultString=request.getParameter("result");		
		Long id=new Long(betUnitIdString);			
		Integer resultType = Integer.parseInt(resultString);
		BetUnit betUnit=null;
			
		try {
			betUnit=betService.getById(id);
			User userTemp=new User();
			userTemp.setMail(userService.getCurrentUser().getMail());
			boolean check=betService.canBetUser(betUnit,userTemp);
			
			if(check){
				Result result=Result.getResult(resultType);
				System.out.println("the result choosen is "+result);
				betUnit=betService.getById(id);
				System.out.println("en bet controller antes de modficar es "+betUnit);
				
				if(allowBettingCondition(betUnit.getMatch().getDate())){
					betUnit.setResult(result);
					betUnit.setUser(userService.getCurrentUser());
					betService.modify(id,betUnit);
					mav=new ModelAndView("private/user/fragments/successBetFragment");
					mav.addObject("status",result.toString());
					System.out.println(result);
					System.out.println("here good");
					betUnit=betService.getById(id);
					System.out.println("ahora despues de modificar es "+betUnit);
				}		
			}else{
				mav=new ModelAndView("private/user/fragments/notifyErrorFragment");
				mav.addObject("status","user cant bet there error ");
				System.out.println("here bad");
				
				}
			
		} catch (Exception e) {
			mav=new ModelAndView("private/user/fragments/notifyErrorFragment");
			mav.addObject("status",betUnit.getResult().toString());
			System.out.println("here bad");
			e.printStackTrace();
		}finally{			
			return mav;
		}
	 }
	
	public ModelAndView showBets(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView("private/bet/betGrid");
		String stringId=request.getParameter("id");
		User user=userService.getCurrentUser();
		Tournament tournament=new Tournament();
		List<BetUnit> betList=new ArrayList<BetUnit>();

		try {
			tournament=new Tournament();
			Long id=Long.parseLong(stringId);
			tournament.setId(id);
			boolean check=tournamentService.exist(id);
			
			if(check){
				tournament = tournamentService.getTournament(stringId);
				betList=betService.forUserInTournament(user, tournament);
				
				for(int j=0;j<betList.size();j++){				
					if(allowBettingCondition(betList.get(j).getMatch().getDate())){
						betList.get(j).setClosedBettingPeriod(false);
					}else{
						betList.get(j).setClosedBettingPeriod(true);
					}
				}	
				
			}else{
				mav.setViewName("private/user/fragments/notifyErrorFragment");
			}
				
			mav.addObject("betList",betList);
			mav.addObject("t",tournament);
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("private/user/fragments/notifyErrorFragment");
		}finally{
			return mav;
		}
	}
	
	public ModelAndView goToChooseTournament(HttpServletRequest request,HttpServletResponse response){
		List<Tournament> tournamentList=null;	
		Map parameter=new HashMap<String,Object>(13);	
		ModelAndView mav=new ModelAndView("private/bet/choose","parameter",parameter);
		String logoutUrl=userService.createLogoutURL("/hello.htm");
  		parameter.put("logoutUrl",logoutUrl);
  		parameter.put("userMail", userService.getCurrentUser().getMail());
		List<String> links=new ArrayList<String>();
		try {
			tournamentList=tournamentService.listCurrentTournaments();
			String link="/bet/show.htm?id=";
			for(int i=0;i<tournamentList.size();i++){
				Tournament tournament=tournamentList.get(i);
				Long id=tournament.getId();
				links.add(link+id.toString());
			}			
			List<String> seeList=createRankingLinks(tournamentList);
			
			mav.addObject("links", links);
			mav.addObject("tournamentList",tournamentList);
			mav.addObject("see",seeList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return mav;
		}
	}
	
	private List<String> createRankingLinks(List<Tournament> tournamentList)throws Exception{
		List<String> answer=new ArrayList<String>();
		for(int i=0;i<tournamentList.size();i++){
			Long id=tournamentList.get(i).getId();
			String link="/ranking/see.htm?id="+id;
			answer.add(link);
		}
		return answer;
	}	
	
	private boolean allowBettingCondition(Date matchDate){
		Date dayBeforeMatch = DateUtils.getDayBefore(matchDate);
//		return !matchDate.before(dayBeforeMatch);
		return !matchDate.before(new Date());
	}	
	
	public BetService getBetService() {
		return betService;
	}
	public void setBetService(BetService betService) {
		this.betService = betService;
	}
	public RandomService getRandomService() {
		return randomService;
	}
	public void setRandomService(RandomService randomService) {
		this.randomService = randomService;
	}
	 public TournamentService getTournamentService() {
			return tournamentService;
	}
	public void setTournamentService(TournamentService tournamentService) {
			this.tournamentService = tournamentService;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IBetUnitDataDao getBetUnitDataDao() {
		return betUnitDataDao;
	}
	public void setBetUnitDataDao(IBetUnitDataDao betUnitDataDao) {
		this.betUnitDataDao = betUnitDataDao;
	}	
}
