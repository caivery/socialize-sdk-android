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
package com.socialize.test;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import com.socialize.SocializeService;
import com.socialize.networks.SocialNetworkSignOutClickListener;
import com.socialize.networks.SocialNetworkSignOutListener;


/**
 * @author Jason Polites
 *
 */
public class PublicSocialNetworkSignOutClickListener extends SocialNetworkSignOutClickListener {

	@Override
	public AlertDialog makeDialog(Context context) {
		return super.makeDialog(context);
	}

	@Override
	public void doSignOut(Context context) {
		super.doSignOut(context);
	}

	@Override
	public AlertDialog getDialog() {
		return super.getDialog();
	}

	@Override
	public SocialNetworkSignOutListener newSocialNetworkSignOutListener(View v) {
		return super.newSocialNetworkSignOutListener(v);
	}

	@Override
	public void exitProfileActivity(View v) {
		super.exitProfileActivity(v);
	}

	@Override
	public void logError(String msg, Exception error) {
		super.logError(msg, error);
	}

	@Override
	public SocializeService getSocialize() {
		return super.getSocialize();
	}
}
