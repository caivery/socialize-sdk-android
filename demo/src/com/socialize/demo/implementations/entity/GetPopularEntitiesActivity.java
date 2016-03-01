/*
 * Copyright (c) 2012 Socialize Inc.
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
package com.socialize.demo.implementations.entity;

import com.socialize.EntityUtils;
import com.socialize.EntityUtils.SortOrder;
import com.socialize.demo.SDKDemoActivity;
import com.socialize.entity.Entity;
import com.socialize.entity.ListResult;
import com.socialize.error.SocializeException;
import com.socialize.listener.entity.EntityListListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author Jason Polites
 *
 */
public class GetPopularEntitiesActivity extends SDKDemoActivity {

	/* (non-Javadoc)
	 * @see com.socialize.demo.DemoActivity#executeDemo()
	 */
	@Override
	public void executeDemo(String text) {
		
		EntityUtils.getEntities(this, 0, PAGE_SIZE, SortOrder.TOTAL_ACTIVITY, new EntityListListener() {
			
			@Override
			public void onList(ListResult<Entity> entities) {
				
				List<Entity> items = entities.getItems();
				Collection<String> strResults = new ArrayList<String>();
				for (Entity socializeAction : items) {
					strResults.add(socializeAction.toString() + " (Total Activity " + socializeAction.getEntityStats().getTotalActivityCount() + ")");
				}
				
				handleResults(strResults);
			}
			
			@Override
			public void onError(SocializeException error) {
				handleError(GetPopularEntitiesActivity.this, error);
			}
		});
	}
	
	@Override
	public boolean isTextEntryRequired() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.socialize.demo.DemoActivity#getButtonText()
	 */
	@Override
	public String getButtonText() {
		return "List top " + PAGE_SIZE + " most popular entities";
	}
}