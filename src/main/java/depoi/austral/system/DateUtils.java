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
package depoi.austral.system;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import depoi.austral.model.Match;
import depoi.austral.model.other.BetUnit;
import depoi.austral.model.other.Result;

public class DateUtils {
	public static Date create(Integer day, Integer month, Integer year)
			throws ParseException {
		Date date = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = day.toString() + "/" + month.toString() + "/"
				+ year.toString();
		date = df.parse(dateString);
		return date;
	}

	public static String getTimestamp(Date date) {
		return "" + date.getDay() + "/" + date.getMonth() + "/"
				+ date.getYear();
	}

	public static Date getDate(String timestamp) throws ParseException {
		String[] array = timestamp.split("/");
		int day = Integer.parseInt(array[0]);
		int month = Integer.parseInt(array[1]);
		int year = Integer.parseInt(array[2]);
		return create(day, month, year);
	}

	public static Date getDayBefore(Date date) {
		return date;

	}

	/**
	 * compare that date vs current date
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static boolean lessThanNow(Date date) throws Exception {
		Date now = new Date();
		boolean answer = (date.compareTo(now) < 0);
		return answer;
	}

	public static boolean hasPlayedMatch(BetUnit betUnit) throws Exception {
		Match match = betUnit.getMatch();
		return match.getDate().before(new Date());
	}

	/**
	 * check if the user guessed;
	 * 
	 * @param match
	 * @param bet
	 * @return
	 */
	public static boolean hasUserGuess(Match match, BetUnit betUnit) {
		Result result = match.getResult();
		return (result == betUnit.getResult());
	}

}
