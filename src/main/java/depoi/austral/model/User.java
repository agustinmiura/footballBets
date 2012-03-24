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

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User implements Comparable<User>,Serializable{
	
	public User(){}
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
    private String userId;

    private String mail;

    private Integer points;
    
    public User(String mail, String userId){
    	
        this.mail=mail;
        this.userId=userId;
        points=0;
        id=Long.parseLong(userId);
    }

    public String getMail() {
        return mail;
    }

    public String getUserId() {
        return userId;
    }

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int hashCode(){
		
		return mail.hashCode();
	}

	@Override
	public int compareTo(User o) {
		return mail.hashCode()-o.hashCode();
	}
	
	public boolean equals(Object o){
		int myCode=hashCode();
		int otherHashCode=o.hashCode();
		
		return (myCode==otherHashCode);
		
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", mail=" + mail + ", points=" + points
				+ ", userId=" + userId + "]";
	}
}