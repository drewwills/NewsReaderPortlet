/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.portlet.newsreader.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.web.portlet.mvc.AbstractAjaxController;

public class SaveDisplayPreferenceController extends AbstractAjaxController {
	
	private Log log = LogFactory.getLog(SaveDisplayPreferenceController.class);
	
	@Override
	protected Map<Object, Object> handleAjaxRequestInternal(ActionRequest request,
			ActionResponse response) throws Exception {

	    try {
			String prefName = request.getParameter("prefName");
			String prefValue = request.getParameter("prefValue");
			
			PortletPreferences prefs = request.getPreferences();
			prefs.setValue(prefName, prefValue);
			prefs.store();

			JSONObject json = new JSONObject();
			json.put("status", "success");

			Map<Object, Object> model = new HashMap<Object, Object>();
			model.put("json", json);
			return model;
		} catch (Exception e) {
			log.error("There was an error saving the preferences.", e);
			throw e;
		}

	}

}