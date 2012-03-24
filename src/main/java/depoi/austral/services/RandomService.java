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

import java.util.Random;
import java.util.UUID;

public class RandomService {

	private Random random;

	public RandomService() {
		random = new Random();

	}

	public int getRandomInt(int max) {
		Integer integer = random.nextInt(max);
		integer = Math.abs(integer);
		return integer;

	}

	public Long getRandomLong(int max) {
		Integer integer = getRandomInt(max);
		return new Long(integer.longValue());
	}

	public Long getRandomLongNotZero(int max) {
		Long answer = getRandomLong(max);
		if (answer.equals(new Long(0))) {
			answer++;
		}
		return answer;
	}

	public String getRandomString(int charLenght) {
		UUID uuid = UUID.randomUUID();
		String string = uuid.toString();

		if (charLenght < string.length()) {
			string = string.substring(0, charLenght);
		}
		return string;
	}

}
