package com.godin.virtest;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class TestAct extends UiAutomatorTestCase{
	/*
	 * ���ǵ�1������
	 * 
	 * 
	 */
	public void testWakeUp() throws android.os.RemoteException{
		UiDevice.getInstance().wakeUp();
	}
	
	/*
	 * ���ǵ�2������
	 */
	
	public void testHome() throws android.os.RemoteException{
		UiDevice.getInstance().pressHome();
	}	
	
	/**
	 * 
	 */
	
}





