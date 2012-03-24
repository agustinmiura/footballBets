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
package depoi.austral.model;

import java.util.ArrayList;
import java.util.List;

import depoi.austral.model.jdo.BetUnitData;
import depoi.austral.model.other.BetUnit;
import depoi.austral.services.RandomService;

public class BetUnitDataFactory {

	public BetUnitData fromBetUnit(BetUnit betUnit) throws Exception {

		BetUnitData answer = new BetUnitData();
		answer.setId(betUnit.getId());
		answer.setMatchId(betUnit.getMatch().getId());
		answer.setResultId(betUnit.getResult().getId());
		answer.setTournamentId(betUnit.getTournament().getId());

		answer.setUserMail(betUnit.getUser().getMail());

		return answer;
	}

	public List<BetUnitData> createRandom(int qty) throws Exception {
		List<BetUnitData> answer = new ArrayList<BetUnitData>();

		RandomService randomService = new RandomService();

		for (int i = 0; i < qty; i++) {

			BetUnitData betUnitData = new BetUnitData();

			betUnitData.setId((randomService.getRandomLong(300) + 233));
			betUnitData.setMatchId(randomService.getRandomLong(300));
			betUnitData.setResultId(new Integer(0));
			betUnitData.setTournamentId(new Long(1));
			betUnitData.setUserMail("test@gmail.com");
			answer.add(betUnitData);
		}

		return answer;
	}

}
