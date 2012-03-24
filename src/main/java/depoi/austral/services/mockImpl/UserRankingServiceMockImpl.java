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
package depoi.austral.services.mockImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import depoi.austral.model.User;
import depoi.austral.services.IRankingUserService;

public class UserRankingServiceMockImpl implements IRankingUserService{

	
	private List<User> userList;
	private List<Integer> pointList;
	
	public UserRankingServiceMockImpl(){
		subConstructor();
	}
	
	public List<User> getUserRanking(){
	return userList;
		
	}
	
	public List<Integer> getUserPoints(){
	return pointList;
	}
	
	private void subConstructor(){
		
		userList=new ArrayList<User>();
		pointList=new ArrayList<Integer>();
		
		int qtyOfUsersToShow=25;
		Random random=new Random();
		Integer points=null;
		User user=new User();
		for(int i=0;i<qtyOfUsersToShow;i++){
			user=new User();
			points=random.nextInt();
			points=(points%1000);
			points=Math.abs(points);
			user.setMail("user"+points+"@gmail.com");
			user.setUserId("points "+points);
		
			userList.add(user);
			pointList.add(points);
		
		}
	}
}
