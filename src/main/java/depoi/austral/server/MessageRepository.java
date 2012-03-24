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
package depoi.austral.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import depoi.austral.model.Message;

public class MessageRepository {

	PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	public Collection<Message> getAll() {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		try {
			List<Message> messages = new ArrayList<Message>();
			Extent<Message> extent = pm.getExtent(Message.class, false);
			for (Message message : extent) {
				messages.add(message);
			}
			extent.closeAll();

			return messages;
		} finally {
			pm.close();
		}
	}

	public void create(Message message) {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		try {
			pm.makePersistent(message);
		} finally {
			pm.close();
		}
	}

	public void deleteById(Long id) {
		PersistenceManager pm = pmfInstance.getPersistenceManager();
		try {
			pm.deletePersistent(pm.getObjectById(Message.class, id));
		} finally {
			pm.close();
		}
	}
}
