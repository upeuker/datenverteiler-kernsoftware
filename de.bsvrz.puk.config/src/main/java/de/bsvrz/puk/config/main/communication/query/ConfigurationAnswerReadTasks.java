/*
 * Copyright 2006 by Kappich Systemberatung Aachen 
 * 
 * This file is part of de.bsvrz.puk.config.
 * 
 * de.bsvrz.puk.config is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * de.bsvrz.puk.config is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with de.bsvrz.puk.config.  If not, see <http://www.gnu.org/licenses/>.

 * Contact Information:
 * Kappich Systemberatung
 * Martin-Luther-Straße 14
 * 52062 Aachen, Germany
 * phone: +49 241 4090 436 
 * mail: <info@kappich.de>
 */
package de.bsvrz.puk.config.main.communication.query;

import de.bsvrz.dav.daf.main.ClientDavInterface;
import de.bsvrz.dav.daf.main.OneSubscriptionPerSendData;
import de.bsvrz.dav.daf.main.impl.config.request.telegramManager.SenderReceiverCommunication;
import de.bsvrz.dav.daf.main.impl.config.request.telegramManager.AbstractSenderReceiverCommunication;
import de.bsvrz.dav.daf.main.config.Aspect;
import de.bsvrz.dav.daf.main.config.DataModel;
import de.bsvrz.dav.daf.main.config.SystemObject;
import de.bsvrz.dav.daf.main.config.AttributeGroup;

/**
 * Beantwortet Konfigurationsanfragen, es wird kein Empfänger angemeldet nur ein Sender.
 *
 * @author Achim Wullenkord (AW), Kappich Systemberatung
 * @version $Revision$ / $Date$ / ($Author$)
 */
public class ConfigurationAnswerReadTasks extends AbstractSenderReceiverCommunication implements SenderReceiverCommunication {
	private final ClientDavInterface _connection;
	private final Aspect _requestAspect;
	private final AttributeGroup _requestAtg;

	/**
	 *
	 * @param connection
	 * @param senderObject Objekt, zu dem die Daten verschickt werden sollen (wird zum anmelden des Senders gebraucht)
	 * @param ordererObject Objekt von dem die Daten geschickt werden (wird als Absender eingetragen)
	 * @throws de.bsvrz.dav.daf.main.OneSubscriptionPerSendData
	 */
	public ConfigurationAnswerReadTasks(ClientDavInterface connection, SystemObject senderObject, SystemObject ordererObject, short simulationVariant) throws OneSubscriptionPerSendData {
		super(connection, senderObject, ordererObject);
		_connection = connection;
		final DataModel dataModel = connection.getDataModel();

		_requestAspect = dataModel.getAspect("asp.antwort");
		_requestAtg = dataModel.getAttributeGroup("atg.konfigurationsAnfrageSchnittstelleLesend");

		// Sender und Empfänger anmelden
		init(_requestAtg, _requestAspect, null, null, simulationVariant,null);
	}
}