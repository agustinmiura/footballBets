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
package depoi.austral.controller.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class MenuController extends MultiActionController {
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
/*	 public ModelAndView goToCreateMessage(HttpServletRequest request,HttpServletResponse response){
		 return new ModelAndView("private/user/createMessage");
	 }
	 
	 
	 public ModelAndView goToCreatePerson(HttpServletRequest request,HttpServletResponse response){
		 return new ModelAndView("private/user/personForm");
	 
	 }
	 
	 public ModelAndView goToModifyPerson(HttpServletRequest request,HttpServletResponse response){
		 return new ModelAndView("private/user/modifyPerson");
	 }
	 
	 public ModelAndView goToAjaxExample(HttpServletRequest request,HttpServletResponse response){
		 return new ModelAndView("private/user/mock/index");
	 }*/
	 
	/*
	public ModelAndView goToUserPage(HttpServletRequest request,HttpServletResponse response){
	System.out.println("executed method");
	 return new ModelAndView("private/index");
	 }
	 
	 public ModelAndView goToViewBet(HttpServletRequest request,HttpServletResponse response){
			System.out.println("go to view bet");

		 return new ModelAndView("private/viewBet");
	 }
	 
	 public ModelAndView goToViewRanking(HttpServletRequest request,HttpServletResponse response){
	 return new ModelAndView("private/viewRanking");
	 }

	 public ModelAndView goToAdminPage(HttpServletRequest request,HttpServletResponse response){
	return new ModelAndView("admin/index");
	 }

	 public ModelAndView processComment(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView=new ModelAndView("public/result");	
		boolean state=true;
		
		Message message=new Message();
		String messageContent=request.getParameter("comment");
		Date date=new Date();
		message.setContent("content "+date);
		
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(message);

		}catch(Exception e){
			
		}finally{
			pm.close();
		}
		
		modelAndView.addObject("state",state);
		modelAndView.addObject("message",message);
		
		return modelAndView;
	 }

	 public ModelAndView seeComments(HttpServletRequest request,HttpServletResponse response){
			PersistenceManager pm=PMF.get().getPersistenceManager();
		    Query query = pm.newQuery(Message.class);
		    
		    try {
		        List<Message> results = (List<Message>) query.execute();
		        if (results.iterator().hasNext()) {
		            for (Message m : results) {
		                System.out.println(m);
		            }
		        } else {
		            // ... no results ...
		        }
		    } finally {
		        query.closeAll();
		    }
		 return new ModelAndView("public/viewComments");
	 }
	 */
	}