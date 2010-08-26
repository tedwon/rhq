/*
 * RHQ Management Platform
 * Copyright (C) 2010 Red Hat, Inc.
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
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package org.rhq.enterprise.gui.coregui.client.inventory.resource.detail.monitoring.traits;

import com.smartgwt.client.data.Criteria;

import org.rhq.core.domain.criteria.MeasurementDataTraitCriteria;
import org.rhq.enterprise.gui.coregui.client.inventory.common.AbstractMeasurementDataTraitListView;

/**
 * The Resource Monitoring>Traits subtab.
 *
 * @author Ian Springer
 */
public class TraitsView extends AbstractMeasurementDataTraitListView {

    private static final String TITLE = "Traits";

    private static final String[] EXCLUDED_FIELD_NAMES = new String[] { MeasurementDataTraitCriteria.FILTER_FIELD_RESOURCE_ID };

    public TraitsView(String locatorId, int resourceId) {
        super(locatorId, TITLE, new TraitsDataSource(resourceId), createCriteria(resourceId), EXCLUDED_FIELD_NAMES);
    }

    private static Criteria createCriteria(int resourceId) {
        Criteria criteria = new Criteria();
        criteria.addCriteria(MeasurementDataTraitCriteria.FILTER_FIELD_RESOURCE_ID, resourceId);
        return criteria;
    }
}
