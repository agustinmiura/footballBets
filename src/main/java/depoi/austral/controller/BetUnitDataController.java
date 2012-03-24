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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import depoi.austral.dao.IBetUnitDataDao;
import depoi.austral.dao.interfaces.IUserDao;
import depoi.austral.model.BetUnitDataFactory;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.services.RandomService;

public class BetUnitDataController extends MultiActionController{
	
	private IUserDao userDao;
	
	public ModelAndView addRandomUser(HttpServletRequest request,HttpServletResponse response){
		RandomService randomService=new RandomService();
		User user=new User();
		user.setId(randomService.getRandomLongNotZero(300));
		user.setMail(randomService.getRandomString(5));
		user.setPoints(100);
		user.setUserId("100");
	   try {
		   userDao.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seeAllUsers(request,response);
	 }
	
	public ModelAndView seeAllUsers(HttpServletRequest request,HttpServletResponse response){
		 String viewToRedirect="test/test";
		 List<User> betUnitDataList=new ArrayList<User>();
		 ModelAndView modelAndView=new ModelAndView(viewToRedirect,"userList",betUnitDataList);
	   try {
			System.out.println("see all");
			List<User> tempList=userDao.getAll();
			System.out.println("the qty is ..."+tempList.size());
			modelAndView.addObject("parameter",tempList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	 }
	
	/**READY
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	 public ModelAndView seeAll(HttpServletRequest request,HttpServletResponse response){
		 String viewToRedirect="test/testBetUnitData";
		 List<BetUnitData> betUnitDataList=new ArrayList<BetUnitData>();
		 ModelAndView modelAndView=new ModelAndView(viewToRedirect,"parameter",betUnitDataList);
	   try {
			System.out.println("see all");
			List<BetUnitData> tempList=betUnitDao.listAll();
			System.out.println("the qty is ..."+tempList.size());
			modelAndView.addObject("parameter",tempList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	 }
	
	public ModelAndView createRandom(HttpServletRequest request,HttpServletResponse response){
		System.out.println("create random");
		try {
			BetUnitDataFactory betUnitDataFactory=new BetUnitDataFactory();
			List<BetUnitData> tempList=betUnitDataFactory.createRandom(20);
			
			for(BetUnitData betTemp:tempList){
				betUnitDao.add(betTemp);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return seeAll(request,response);
		
	 }
	
	public ModelAndView modify(HttpServletRequest request,HttpServletResponse response){
		String stringId=request.getParameter("id");
		String stringMatchId=request.getParameter("matchId");
		String stringResultId=request.getParameter("resultId");
		String stringTournamentId=request.getParameter("tournamentId");

		int state=233;
		
		Long id=Long.parseLong(stringId);
		Integer resultId=Integer.parseInt(stringResultId);
		String userMail=request.getParameter("userMail");
		Long matchId=Long.parseLong(stringMatchId);
		Long tournamentId=Long.parseLong(stringTournamentId);
		
		BetUnitData betUnitData=new BetUnitData();
		betUnitData.setId(id);
		betUnitData.setMatchId(matchId);
		betUnitData.setResultId(resultId);
		betUnitData.setTournamentId(tournamentId);
		betUnitData.setUserMail(userMail);
		
		try {
			betUnitDao.modify(id,betUnitData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (seeAll(request,response));
		
	 }

	public ModelAndView forTournament(HttpServletRequest request,HttpServletResponse response){
		System.out.println("for tournament");
		
		String tIdString=request.getParameter("tournamentId");
		
		String userMail=request.getParameter("userMail");
		Long tournamentId=Long.parseLong(tIdString);
		
		User user=new User();
		user.setMail(userMail);
		
		Tournament tournament=new Tournament();
		tournament.setId(tournamentId);
		
		try {
			List<BetUnitData> betUnitDataList=betUnitDao.forUserInTournament(user, tournament);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int state=33;
		return null;
		
	 }

	public IBetUnitDataDao getBetUnitDao() {
		return betUnitDao;
	}

	public void setBetUnitDao(IBetUnitDataDao betUnitDao) {
		this.betUnitDao = betUnitDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	
	
	
}
