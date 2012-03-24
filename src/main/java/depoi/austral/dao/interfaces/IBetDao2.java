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
import java.util.Set;

import depoi.austral.model.Tournament;
import depoi.austral.model.User;
import depoi.austral.model.other.BetUnit;

public interface IBetDao2 {

	public void modify(Long id,BetUnit newParams)throws Exception;
	
	public void add(BetUnit betUnit)throws Exception;
	
	List<BetUnit> forUserInTournament(User user,Tournament tournament)throws Exception;

	public Set<User> getUsers()throws Exception;
	
	public BetUnit getById(Long id)throws Exception;
	
}
