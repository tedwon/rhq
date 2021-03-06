/*
 * RHQ Management Platform
 * Copyright (C) 2005-2013 Red Hat, Inc.
 * All rights reserved.
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
 * along with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 */

package org.rhq.test.apps.javaee6;

import javax.jws.WebService;

/**
 * @author Thomas Segismont
 */
@WebService(serviceName = "SimpleWebService", portName = "SimpleWebService", name = "SimpleWebService", endpointInterface = "org.rhq.test.apps.javaee6.SimpleWebService", targetNamespace = "http://www.jboss.org/rhq/test-apps/javaee6-test-app/SimpleWebService")
public class SimpleWebServiceImpl implements SimpleWebService {
    @Override
    public String simpleAction() {
        return "ok";
    }
}
