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
package depoi.austral.services.impl;

import depoi.austral.model.User;
import depoi.austral.services.IUserService;
import depoi.austral.services.RandomService;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GoogleUserService implements IUserService {
	private com.google.appengine.api.users.UserService userService;

	public GoogleUserService() {
		checkAndInitize();
		System.out
				.println("the user service started here " + userService == null
						+ "  user service is null");
	}

	public String createLoginURL(String s) {
		checkAndInitize();
		return userService.createLoginURL(s);
	}

	public String createLogoutURL(String s) {
		checkAndInitize();
		return userService.createLogoutURL(s);
	}

	public User getCurrentUser() {
		// pasando los valores al constructor gano mas flexibilidad en el
		// diseno,
		// ya que no necesito formar un wrapper por cada user que no sea el de
		// mi implementacion
		// simplemente le pido los datos y sigo con la implementacion User
		// propia
		// podria centralizarlo en un metodo estatico para aumentar la cohesion.
		checkAndInitize();
		com.google.appengine.api.users.User googleUser = userService
				.getCurrentUser();

		User answer = null;
		if (googleUser != null) {
			System.out.println("GoogleUserAuth: " + googleUser);
			answer = new User();
			String eMail = googleUser.getEmail();
			String userId = googleUser.getUserId();

			answer.setMail(eMail);
			answer.setUserId(userId);

			Long id = null;

			try {
				id = Long.parseLong(userId);
			} catch (NumberFormatException e) {
				RandomService randomService = new RandomService();
				id = randomService.getRandomLong(1000);
			}

			answer.setId(id);

			answer.setPoints(new Integer(100));
		}
		return answer;
	}

	public void setUserService(
			com.google.appengine.api.users.UserService userService) {
		checkAndInitize();
		this.userService = userService;
	}

	public com.google.appengine.api.users.UserService getUserService() {
		checkAndInitize();
		return userService;
	}

	private void checkAndInitize() {
		if (userService == null) {
			userService = UserServiceFactory.getUserService();
		}

	}

	@Override
	public boolean isUserAdmin() {
		// TODO Auto-generated method stub
		return userService.isUserAdmin();
	}
}
