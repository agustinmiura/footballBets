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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import depoi.austral.dao.interfaces.TeamDAO;
import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.model.Match;
import depoi.austral.model.Team;
import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.other.BetUnit;
import depoi.austral.services.IUserService;
import depoi.austral.services.RandomService;
import depoi.austral.services.TournamentService;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TournamentController extends MultiActionController {
	private TournamentService tournamentService;
	private IUserService userService;
	private RandomService randomService;

	public ModelAndView createTournament(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("private/user/index");
		try {
			String tournamentName = request.getParameter("tournamentName");
			Long id = randomService.getRandomLong(300);

			Tournament tournament = new Tournament();
			tournament.setTournamentName(tournamentName);
			tournament.setId(id);
			tournamentService.makePersistant(tournament);
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("private/user/result");
			mav.addObject("result", "fail creation of the team");
		} finally {
			
			return mav;
		}		
	}
	
	public ModelAndView createRandom(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("private/user/index");
		try {
			Long id = randomService.getRandomLong(300);
			String tournamentName = "RandomTournament"+id;

			Tournament tournament = new Tournament();
			tournament.setTournamentName(tournamentName);
			tournament.setId(id);
			tournamentService.makePersistant(tournament);
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("private/user/result");
			mav.addObject("result", "fail creation of the team");
		} finally {			
			return mav;
		}	
	}

	public ModelAndView deleteTournament(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("here to delete");
		ModelAndView mav = new ModelAndView("private/user/index");
		try {
			String idString=request.getParameter("param");
			Long id=new Long(idString);
			Tournament tournament=new Tournament();
			tournament.setId(id);
			tournamentService.remove(tournament);
			
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("private/user/result");
			mav.addObject("result", "fail creation of the team");
		}finally{
			return mav;
			
		}
	}
	
	public ModelAndView modifyTournament(HttpServletRequest request,
			HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView("private/admin/tournament/modify");
		System.out.println("Entrando en modify tournament!");
		ModelAndView mav = new ModelAndView("private/user/fragments/successMatchFragment");
		try {
			String idString=request.getParameter("matchId");
			Long matchId=new Long(idString);
			int localScore;
			int guestScore;
			try{				
				localScore =Integer.parseInt(request.getParameter("localScore"));
				System.out.println(""+localScore);
			}catch(Exception e){
				localScore = 0;
			}
			try{
				guestScore = Integer.parseInt(request.getParameter("guestScore"));
				System.out.println(""+guestScore);
			}catch(Exception e){
				guestScore = 0;
			} 
			
			Tournament tournament=new Tournament();
			tournament.setId(new Long(request.getParameter("tournamentId")));
			tournament=tournamentService.loadTournament(tournament);
			tournament.getMatchById(matchId).setLocalScore(localScore);
			tournament.getMatchById(matchId).setGuestScore(guestScore);
			tournamentService.modifyTournament(tournament);
			
			mav.addObject("status", ""+localScore+" - "+guestScore);
			System.out.println(""+localScore+" - "+guestScore);
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("private/user/fragments/notifyErrorFragment");
			mav.addObject("status", "");
		}finally{
			System.out.println("Voy a devolver el MAV"+mav.getViewName());
			return mav;
		}
	}

	public ModelAndView seeAllTournament(HttpServletRequest request,
			HttpServletResponse response) {
		List<Tournament> tournamentList=null;	
		Map parameter=new HashMap<String,Object>(13);	
		ModelAndView mav=new ModelAndView("private/tournament/choose","parameter",parameter);
		String logoutUrl=userService.createLogoutURL("/hello.htm");
  		parameter.put("logoutUrl",logoutUrl);
		List<String> links=new ArrayList<String>();
		try {
			tournamentList=tournamentService.listCurrentTournaments();
			String link="/tournament/show.htm?id=";
			for(int i=0;i<tournamentList.size();i++){
				Tournament tournament=tournamentList.get(i);
				Long id=tournament.getId();
				links.add(link+id.toString());
			}
			
			mav.addObject("links", links);
			mav.addObject("tournamentList",tournamentList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return mav;
		}		
			
//		Map parameter=new HashMap<String,Object>(13);	
//		ModelAndView mav=new ModelAndView("private/tournament/choose","parameter",parameter);
//		String logoutUrl=userService.createLogoutURL("/hello.htm");
//  		parameter.put("logoutUrl",logoutUrl);
//		List<Tournament> tournamentList = new ArrayList<Tournament>();
//		mav.addObject("result", "fail creation of the team");
//		try {
//			tournamentList = tournamentService.listCurrentTournaments();
//			List<String> delList=new ArrayList<String>();
//			List<String> modList=new ArrayList<String>();
//			
//			this.prepareLinkList(tournamentList,modList,delList);
//			 
//			mav.addObject("tournamentList",tournamentList);		
//			mav.addObject("delList",delList);
//			mav.addObject("modList",modList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mav;
	}
	
	public ModelAndView showMatches(HttpServletRequest request,HttpServletResponse response){
		System.out.println("Hasta aca llegamos!");
		ModelAndView mav=new ModelAndView("private/tournament/matchGrid");
		String stringId=request.getParameter("id");
		User user=userService.getCurrentUser();
		Tournament tournament=new Tournament();
		List<Match> matchList=new ArrayList<Match>();

		try {
			tournament=new Tournament();
			Long id=Long.parseLong(stringId);
			tournament.setId(id);
			boolean check=tournamentService.exist(id);
			
			if(check){
				tournament = tournamentService.getTournament(stringId);
				matchList=tournament.getAll();
				
				System.out.println("Printing matches");
				for(int j=0;j<matchList.size();j++){
					System.out.println(matchList.get(j));
				}
				System.out.println("End of printing matches");
				
			}else{
				mav.setViewName("private/user/fragments/notifyErrorFragment");
			}
				
			mav.addObject("matchList",matchList);
			mav.addObject("tournament",tournament);
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("private/user/fragments/notifyErrorFragment");
		}finally{
			return mav;
		}
	}

	public void prepareLinkList(List<Tournament> tournamentList,List<String> modifyList,List<String> deleteList){
		Long id=null;
		Tournament tournament=new Tournament();
		String modLink;
		String delLink;
		for(int i=0;i<tournamentList.size();i++){
			tournament=tournamentList.get(i);
			id=tournament.getId();
			modLink="/tournament/modify/action.htm?param="+id;
			delLink="/tournament/delete/action.htm?param="+id;
			modifyList.add(modLink);
			deleteList.add(delLink);
		}		
	}
	
	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	public RandomService getRandomService() {
		return randomService;
	}

	public void setRandomService(RandomService randomService) {
		this.randomService = randomService;
	}
	
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	/*
	 * public void setTournamentDao(TournamentDAO tournamentDAO) {
	 * this.tournamentDAO = tournamentDAO; }
	 * 
	 * protected void initBinder(HttpServletRequest request,
	 * ServletRequestDataBinder binder) throws Exception {
	 * binder.registerCustomEditor(String.class, new
	 * StringTrimmerEditor(false)); }
	 * 
	 * 
	 * public TournamentController() { setSessionForm(true);//todo que hace?? }
	 * 
	 * //todo debe hacer retrieve de los objetosa ser usados...
	 * 
	 * protected Object formBackingObject(HttpServletRequest request) throws
	 * Exception { return tournamentDAO.retrieveCurrentTournaments(); }
	 * 
	 * 
	 * protected ModelAndView onSubmit(Object command) throws ServletException {
	 * //El ModelAndView funciona como un Map al cual le voy agregando objetos
	 * con un String asociado. //La idea es que en el jsp de destino ya aparece
	 * "declarado" magicamente, de forma que puedo // usarlo directamente con el
	 * nombre que lo puse en el ModelAndView ModelAndView mav=null;
	 * mav.addObject(new Object()); return new ModelAndView(getSuccessView(),
	 * null); }
	 */
}
