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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import depoi.austral.dao.interfaces.IUserDao;
import depoi.austral.model.User;
import depoi.austral.services.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import depoi.austral.model.Message;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.Query;


public class HelloController implements Controller{
    private IUserService userService;
    private IUserDao userDao;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {		
        //Aca obtengo el servicio de usuario, accedo al usuario.
  	  	User user=userService.getCurrentUser();
    	boolean userLoggedIn=(user!=null);
  	  	String viewToRedirect="";
  	  
  	  	Map parameter=new HashMap<String,Object>(13);	
  	  	ModelAndView modelAndView=new ModelAndView(viewToRedirect,"parameter",parameter);
  	  	String userName=null;
  	  	boolean userIsAdmin=false;
  	  if(userLoggedIn){
	  		userIsAdmin=userService.isUserAdmin();
	  		userName=user.getMail();
	  		viewToRedirect="private/user/index";
	  		String logoutUrl=userService.createLogoutURL("/hello.htm");
	  		parameter.put("logoutUrl",logoutUrl);
	  		try {
				verifyUserInDb(user);
			} catch (Exception e) {
				e.printStackTrace();
			}  	  		
  	  		}else{
	  		
	  		userName="guestUser";
	  		viewToRedirect="public/index";
	  		String loginUrl=userService.createLoginURL("/hello.htm");
	  		parameter.put("loginUrl",loginUrl); 
	  		
	  		
	  		

	  	}
		if(userIsAdmin){
			modelAndView.addObject("tournamentLink","/tournament/see.htm");
		}
		modelAndView.addObject("isUserAdmin",userIsAdmin);
  	  	modelAndView.setViewName(viewToRedirect);
  		return modelAndView;
    }


	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private void verifyUserInDb(User user)throws Exception{
		System.out.println("im user dao checking for user "+user);
		boolean check=userDao.exist(user);		
		if(!check){
			System.out.println("im user dao persisting the user "+user);
			userDao.addUser(user);
		}		
	}


	public IUserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
}
