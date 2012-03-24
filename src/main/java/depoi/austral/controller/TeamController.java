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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import depoi.austral.model.Team;
import depoi.austral.services.RandomService;
import depoi.austral.services.TeamService;

public class TeamController extends MultiActionController{

	private TeamService teamService;

	private RandomService randomService;
	
	public ModelAndView goToViewAllTeams(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView("private/admin/team/see"); 
		
		try {
			List<Team> teamList=teamService.listAll();
			List<String> delList=new ArrayList<String>();
			prepareLinkList(teamList,delList);
			mav.addObject("delList",delList);
			mav.addObject("teamList",teamList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mav=new ModelAndView("private/user/result");
			mav.addObject("result","failed retrieving all the teams");
			e.printStackTrace();
		}finally{
			return mav;
				}
		
	 }
	
	public ModelAndView deleteTeam(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView("private/admin/team/see"); 
		
		try {
			String teamId=request.getParameter("param");
			Long idToDelete=new Long(teamId);
			Team team=new Team();
			team.setId(idToDelete);
			team=teamService.loadTeam(team);
			teamService.remove(team);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mav=new ModelAndView("private/user/result");
			mav.addObject("result","failed retrieving all the teams");
			e.printStackTrace();
		}finally{
			return mav;
				}
	 }

	public ModelAndView createTeam(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView("private/admin/team/see"); 
		
		try {
			String teamName=request.getParameter("teamName");
			Long id=new Long(randomService.getRandomInt(400));
			Team team=new Team();
			team.setId(id);
			team.setName(teamName);
			teamService.makePersistant(team);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mav=new ModelAndView("private/user/result");
			mav.addObject("result","failed retrieving all the teams");
			e.printStackTrace();
		}finally{
			return mav;
				}
	 }
	
	
	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
	
	
	
	public RandomService getRandomService() {
		return randomService;
	}

	public void setRandomService(RandomService randomService) {
		this.randomService = randomService;
	}

	public void prepareLinkList(List<Team> teamList,List<String> delList){
		Long id;
		Team team;
		for(int i=0;i<teamList.size();i++){
		   team=teamList.get(i);
		   id=team.getId();
		   String link="/team/delete/action.htm?param="+id;
		   delList.add(link);
	   }
	}
	
}
	

