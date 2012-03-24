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

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import depoi.austral.dao.interfaces.TournamentDAO;
import depoi.austral.dao.memory.TournamentRamImplDao;
import depoi.austral.services.RandomService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jose Martin Rozanec;
 */
@PersistenceCapable
public class Tournament implements Persistable,Comparable<Tournament>{
    @PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

    private Map<String, Match> matches;    
    private List<Match> matchList;
    @Persistent private String description;    
    @Persistent private String tournamentName;
    
   public Match getMatchById(Long id)throws Exception{
	   Match answer=null;
	  
	   Match temp=new Match();
	   temp.setId(id);
	   int index=matchList.indexOf(temp);
	   answer=matchList.get(index);
	   return answer;
   }
   
    public Tournament() {
    	RandomService randomService=new RandomService();
    	id=randomService.getRandomLong(2000);
    	tournamentName="noname";
        matches = new HashMap<String, Match>();
        matchList=new ArrayList<Match>();
    }

    public void addMatch(Match match){
    	try {
			addToL(match);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public Map<String, Match> getMatches() {
        if(matches==null){
        	matches=new HashMap<String,Match>();
        }    	
    	return matches;
    }

    public void setMatches(Map<String, Match> matches) {
        this.matches = matches;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

	@Override
	public int compareTo(Tournament o) {
		return (int) (getId()-o.getId());
	}
	
	public int hashCode(){
		Integer hashCode=new Integer(id.intValue());
		return hashCode;		
	}
	
	public boolean equals(Object object){		
		return (this.compareTo((Tournament) object)==0);
	}
	
	public void addToL(Match match)throws Exception{		
		matchList.add(match);
	}
	
	public List<Match> getAll()throws Exception{		
		return (matchList);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Tournament [description=" + description + ", id=" + id
				+ ", matchList=" + matchList + ", matches=" + matches
				+ ", tournamentName=" + tournamentName + "]";
	}	
	
	
}
