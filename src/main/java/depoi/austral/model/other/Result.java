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

import java.util.Random;

public enum Result {

	WIN(1), DRAW(0), LOSS(-1), NOT_BET(-2);

	private Integer id;

	private Result(Integer id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		switch (id) {
		case 0:
			return "Draw";
		case 1:
			return "Win";
		case -1:
			return "Lose";
		default:
			return "Not bet";
		}
	}

	public static Result getRandom() {
		Random random = new Random();
		Integer number = random.nextInt(2);
		number = Math.abs(number);
		Integer answer = BetFactory.indexArr[number];
		return Result.getResult(answer);
	}

	public static Result getResult(int type) {
		switch (type) {
		case 0:
			return Result.DRAW;
		case 1:
			return Result.WIN;
		case -1:
			return Result.LOSS;
		case -2:
			return Result.NOT_BET;
		default:
			throw new IllegalArgumentException(
					"Invalid number to create the enum");
		}
	}
}
