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
package depoi.austral.model.other;

import java.util.Comparator;

import depoi.austral.model.*;

public class TournamentComparator implements Comparator<Tournament> {

	@Override
	public int compare(Tournament o1, Tournament o2) {
		// TODO Auto-generated method stub
		Long id0 = o1.getId();
		Long id1 = o2.getId();

		return (id0.compareTo(id1));
	}

}
