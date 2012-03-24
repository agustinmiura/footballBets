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

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import depoi.austral.model.Message;

public class MenuController extends MultiActionController {
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	 public ModelAndView goToCreateMessage(HttpServletRequest request,HttpServletResponse response){
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
	 }

	 /*google app engine */
	 public ModelAndView goToCreateTournament(HttpServletRequest request,HttpServletResponse response){
		 return new ModelAndView("private/admin/tournament/create");
	 
	 }
	 
	 public ModelAndView goToDeleteTournament(HttpServletRequest request,HttpServletResponse response){
		 return new ModelAndView("private/admin/tournament/delete");
	 }
	 
	}