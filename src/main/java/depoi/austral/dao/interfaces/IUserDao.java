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
package depoi.austral.dao.interfaces;

import java.util.List;

import depoi.austral.model.User;

public interface IUserDao {
	
	public void addUser(User user)throws Exception;
	
	public User getByMail(String mail)throws Exception;
	
	public List<User> getAll()throws Exception;
	
	public boolean exist(User user)throws Exception;

}
