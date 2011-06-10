/*
 * RHQ Management Platform
 * Copyright 2011, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package org.rhq.enterprise.server.drift;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.rhq.core.util.stream.StreamUtil;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/DriftChangesetQueue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class DriftChangesetBean implements MessageListener {

    private final Log log = LogFactory.getLog(DriftChangesetBean.class);

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage msg = (ObjectMessage) message;
            DriftUploadRequest request = (DriftUploadRequest) msg.getObject();

            File tempFile = null;
            OutputStream os = null;
            try {
                tempFile = File.createTempFile("drift-changeset", ".zip");
                os = new BufferedOutputStream(new FileOutputStream(tempFile));

                StreamUtil.copy(DriftUtil.remoteStream(request.getDataStream()), os, false);

                os = null;
                if (log.isDebugEnabled()) {
                    log.debug("Copied [" + request.getDataSize() + "] bytes from agent into [" + tempFile.getPath()
                        + "]");
                }

            } catch (IOException e) {
                log.error(e);
            } finally {
                if (null != tempFile) {
                    tempFile.delete();
                }
                DriftUtil.safeClose(os);
            }
        } catch (JMSException e) {
            log.error(e);
        }
    }

}
