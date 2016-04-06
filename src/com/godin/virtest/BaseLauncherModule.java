package com.godin.virtest;

import junit.framework.Assert;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/** 
* @author wubin
* @date ����ʱ�䣺2016��4��01�� ����5:16:13 
* @version 1.0 
*/
public class BaseLauncherModule extends UiAutomatorTestCase{
	public static void main(String[] args)
	{
		new My_UiAutomatorHelper("BaseLauncherModule","com.godin.virtest.BaseLauncherModule","testStartVirtualPhone","1");
	}
	/**
	 * Id:1
	 * Title:��������ֻ�ͼ�꣬���������ֻ�
	 * Checkpoint:�ж��������������ֻ�����app
	 * @throws RemoteException 
	 * @throws UiObjectNotFoundException 
	 */
	public void testStartVirtualPhone() throws RemoteException, UiObjectNotFoundException{
       UiDevice device=UiDevice.getInstance();
       device.wakeUp();
       device.pressHome();
       UiObject obj=new UiObject(new UiSelector().resourceId("com.android.launcher3:id/hotseat"));
       UiObject appbtn=obj.getChild(new UiSelector().className("android.widget.TextView").index(2));
       appbtn.clickAndWaitForNewWindow();
       UiScrollable collectionObject = new UiScrollable(new UiSelector().scrollable(true));
      
      
if(collectionObject.exists()) {
    	  // collectionObject.scrollForward();
    	   collectionObject.setAsHorizontalList();
    	   UiObject scrollableObject=new UiObject(new UiSelector().description("GLauncher"));
    	   collectionObject.scrollIntoView(scrollableObject);
    	   if(scrollableObject.exists())
    	   {
           scrollableObject.clickAndWaitForNewWindow();
           UiScrollable Gscr=new UiScrollable(new UiSelector().scrollable(true));
           if(Gscr.exists())
           {
        	   UiObject Wdjapp=new UiObject(new UiSelector().resourceId("com.godinsec.glauncher:id/item_image"));
        	   Gscr.scrollIntoView(Wdjapp);
        	   if(!Wdjapp.exists())
        	   {
        		Assert.fail();  
        	   }
           }
           
    	   }
    	   System.out.println("ok");
       } else {
    	   Assert.fail();  
    	   System.out.println("no");
       }
        
	}
	
	/**
	 * Id:2
	 * Title:�������Ӧ��ͼ�꣬������ӦӦ��
	 * Checkpoint:�ж�Ӧ������
	 * @throws RemoteException 
	 * @throws UiObjectNotFoundException 
	 */
	public void testOpenStartApp() throws RemoteException, UiObjectNotFoundException{
		 UiDevice device=UiDevice.getInstance();
	       device.wakeUp();
	       device.pressHome();
	       UiObject obj=new UiObject(new UiSelector().resourceId("com.android.launcher3:id/hotseat"));
	       UiObject appbtn=obj.getChild(new UiSelector().className("android.widget.TextView").index(2));
	       appbtn.clickAndWaitForNewWindow();
	       UiScrollable collectionObject = new UiScrollable(new UiSelector().scrollable(true));
	      
	      
	if(collectionObject.exists()) {
	    	  // collectionObject.scrollForward();
	    	   collectionObject.setAsHorizontalList();
	    	   UiObject scrollableObject=new UiObject(new UiSelector().description("GLauncher"));
	    	   collectionObject.scrollIntoView(scrollableObject);
	    	   if(scrollableObject.exists())
	    	   {
	           scrollableObject.clickAndWaitForNewWindow();
	           UiScrollable Gscr=new UiScrollable(new UiSelector().scrollable(true));
	           if(Gscr.exists())
	           {
	        	   UiObject Wdjapp=new UiObject(new UiSelector().resourceId("com.godinsec.glauncher:id/item_image"));
	        	   Gscr.scrollIntoView(Wdjapp);
	        	   if(!Wdjapp.exists())
	        	   {
	        		Assert.fail();  
	        	   }
	        	   else
	        	   {
	        		   UiObject text=new UiObject(new UiSelector().text("����"));
	        	   }
	           }
	           
	    	   }
	    	   System.out.println("ok");
	       } else {
	    	   Assert.fail();  
	    	   System.out.println("no");
	       }
	}
	
	/**
	 * Id:3
	 * Title:��������Ӧ��ͼ�꣬�����϶�
	 * Checkpoint:�ж�Ӧ��ͼ�걻�ɹ��϶�������λ��
	 */
	public void testDragAppIcon(){
		
	}
	
	/**
	 * Id:4
	 * Title:��������Ӧ��ͼ�꣬�϶�����һ��Ӧ��ͼ���ϣ����Ժϲ����ļ���
	 * Checkpoint:�ж��ļ��кϲ��ɹ�
	 */
	public void testDragMergeFolder(){
		
	}
	
	/**
	 * Id:5
	 * Title:��������ͼ�꣬�϶���ɾ�����򣬿���ɾ��Ӧ��
	 * Checkpoint:�ж�Ӧ��ͼ���������ϲ�����
	 */
	public void testDellApp(){
		
	}
	
	/**
	 * Id:6
	 * Title:���������ɣ���ʼ��װ
	 * Checkpoint:�жϡ���װ������
	 */
	public void testStartInstallation(){
		
	}
	
	
}
