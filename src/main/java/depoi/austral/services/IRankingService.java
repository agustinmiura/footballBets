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
package depoi.austral.services;

import java.util.List;
import java.util.Map;

import depoi.austral.dao.interfaces.IRankingDao;
import depoi.austral.model.*;

public interface IRankingService {

	public Map<User, Integer> getFor(Tournament tournament) throws Exception;

	public void setRankingDao(IRankingDao rankingDao);

}
