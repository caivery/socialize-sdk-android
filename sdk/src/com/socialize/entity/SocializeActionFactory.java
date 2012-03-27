/*
 * Copyright (c) 2011 Socialize Inc. 
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.socialize.entity;

import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.socialize.log.SocializeLogger;
import com.socialize.util.StringUtils;

/**
 * @author Jason Polites
 *
 * @param <T>
 */
public abstract class SocializeActionFactory<T extends SocializeAction> extends SocializeObjectFactory<T> {
	
	// Injected
	protected SocializeLogger logger;
	private ApplicationFactory applicationFactory;
	private UserFactory userFactory;
	private EntityFactory entityFactory;
	private PropagationFactory propagationFactory;
	
	@Override
	protected void toJSON(T from, JSONObject to) throws JSONException {
		super.toJSON(from, to);
		
		try {
			Entity entityObject = from.getEntity();
			String entityKey = from.getEntityKey();
			Application appObject = from.getApplication();
			Propagation propagation = from.getPropagation();
			
			User userObject = from.getUser();
			Double lat = from.getLat();
			Double lon = from.getLon();
			Long date = from.getDate();
			
			if(entityObject != null && entityFactory != null) {
				if(!StringUtils.isEmpty(entityObject.getName()) && !StringUtils.isEmpty(entityObject.getKey())) {
					JSONObject entity = entityFactory.toJSON(entityObject);
					to.put("entity", entity);
				}
				else if(!StringUtils.isEmpty(entityKey)) {
					to.put("entity_key", entityKey);
				}
			}
			else if(!StringUtils.isEmpty(entityKey)) {
				to.put("entity_key", entityKey);
			}

			if(appObject != null && applicationFactory != null) {
				JSONObject application = applicationFactory.toJSON(appObject);
				to.put("application", application);
			}
			
			if(propagation != null && propagationFactory != null) {
				to.put("propagation", propagationFactory.toJSON(propagation));
			}
			
			if(userObject != null) {
				JSONObject user = userFactory.toJSON(userObject);
				to.put("user", user);
			}
			
			if(lat != null) {
				to.put("lat", lat);
			}
			
			if(lon != null) {
				to.put("lng", lon);
			}
			
			if(date != null) {
				to.put("date", DATE_FORMAT.format(date));
			}
			
			to.put("share_location", from.isLocationShared());
		}
		catch (Exception e) {
			throw new JSONException(e.getMessage());
		}
	}
	
	protected JSONArray newJSONArray() {
		return new JSONArray();
	}

	@Override
	protected void fromJSON(JSONObject from, T to) throws JSONException {
		super.fromJSON(from, to);
		
		try {
			
			if(from.has("application") && !from.isNull("application")) {
				JSONObject application = from.getJSONObject("application");
				if(application != null) {
					to.setApplication(applicationFactory.fromJSON(application));
				}
			}
			
			if(from.has("user") && !from.isNull("user")) {
				JSONObject user = from.getJSONObject("user");
				if(user != null) {
					to.setUser(userFactory.fromJSON(user));
				}
			}
			
			if(from.has("entity") && !from.isNull("entity")) {
				JSONObject entity = from.getJSONObject("entity");
				if(entity != null) {
					to.setEntity(entityFactory.fromJSON(entity));
				}
			}
			
			if(from.has("entity_key") && !from.isNull("entity_key")) {
				to.setEntityKey(from.getString("entity_key"));
			}

			if(from.has("lat") && !from.isNull("lat")) {
				to.setLat(from.getDouble("lat"));
			}
			
			if(from.has("lng") && !from.isNull("lng")) {
				to.setLon(from.getDouble("lng"));
			}
			
			if(from.has("share_location") && !from.isNull("share_location")) {
				to.setLocationShared(from.getBoolean("share_location"));
			}			
			
			if(from.has("date") && !from.isNull("date")) {
				try {
					to.setDate(DATE_FORMAT.parse(from.getString("date")).getTime());
				}
				catch (ParseException e) {
					if(logger != null && logger.isWarnEnabled()) {
						logger.warn("Could not parse date [" +
								from.getString("date") +
								"] using format [" +
								DATE_FORMAT_STRING +
								"]");
					}
				}
			}
		}
		catch (Throwable e) {
			if(e instanceof NullPointerException) {
				throw new JSONException("NullPointerException at fromJSON");
			}
			
			throw new JSONException(e.getMessage());
		}
	}
	
	public SocializeLogger getLogger() {
		return logger;
	}

	public void setLogger(SocializeLogger logger) {
		this.logger = logger;
	}

	public void setApplicationFactory(ApplicationFactory applicationFactory) {
		this.applicationFactory = applicationFactory;
	}

	public void setUserFactory(UserFactory userFactory) {
		this.userFactory = userFactory;
	}

	public void setEntityFactory(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	public void setPropagationFactory(PropagationFactory propagationFactory) {
		this.propagationFactory = propagationFactory;
	}

	protected abstract void postToJSON(T from, JSONObject to) throws JSONException;
	
	protected abstract void postFromJSON(JSONObject from, T to) throws JSONException;
}
