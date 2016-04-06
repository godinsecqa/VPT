package com.godin.virtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.util.Log;

public class My_UiAutomatorHelper {

	// ���²�����Ҫ���ã�������id������id����׿id
	private static String android_id = "1";
	private static String jar_name = "";
	private static String test_class = "";
	private static String test_name = "";
	private static String android_path="/Users/kgdset/Documents/adt-bundle-mac-x86_64-20140702/sdk/tools/";
	private static String adb_path="/Users/kgdset/Documents/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/";
	// �����ռ䲻��Ҫ���ã��Զ���ȡ�����ռ�Ŀ¼
	private static String workspace_path;

    public static void main(String[] args) {
		
	}
	public My_UiAutomatorHelper() {
		workspace_path = getWorkSpase();
		System.out.println("---�����ռ䣺\t\n" + getWorkSpase());
	}

	/**
	 * ����UI���̵��Թ�����������jar������������������������
	 * @param jarName
	 * @param testClass
	 * @param testName
	 * @param androidId
	 */
	public My_UiAutomatorHelper(String jarName, String testClass, String testName,
			String androidId) {
		System.out.println("-----------start--uiautomator--debug-------------");
		workspace_path = getWorkSpase();
		System.out.println("----�����ռ䣺\t\n" + getWorkSpase());

		jar_name = jarName;
		test_class = testClass;
		test_name = testName;
		android_id = androidId;
		runUiautomator();
		System.out.println("*******************");
		System.out.println("---FINISH DEBUG----");
		System.out.println("*******************");
	}		
	// ���в���
	private void runUiautomator() {
		creatBuildXml();
		modfileBuild();
		buildWithAnt();
		
		if (System.getProperty("os.name").equals("Linux")||System.getProperty("os.name").equals("Mac OS X")) {
			pushTestJar(workspace_path + "/bin/" + jar_name + ".jar");
		}else{
		pushTestJar(workspace_path + "\\bin\\" + jar_name + ".jar");
		}
		
		if (test_name.equals("")) {
			runTest(jar_name, test_class);
			return;
		}
		runTest(jar_name, test_class + "#" + test_name);
	}		


	// 1--�ж��Ƿ���build
	public boolean isBuild() {
		File buildFile = new File("build.xml");
		if (buildFile.exists()) {
			return true;
		}
		// ����build.xml
		execCmd(android_path+"android create uitest-project -n " + jar_name + " -t "
				+ android_id + " -p " + workspace_path);
		return false;
	}

	// ����build.xml
	public void creatBuildXml() {
		execCmd(android_path+"android create uitest-project -n " + jar_name + " -t "
				+ android_id + " -p " + workspace_path);
	}

	// 2---�޸�build
	public void modfileBuild() {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			File file = new File("build.xml");
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.matches(".*help.*")) {
						lineTxt = lineTxt.replaceAll("help", "build");
						// System.out.println("�޸ĺ� " + lineTxt);
					}
					stringBuffer = stringBuffer.append(lineTxt + "\t\n");
				}
				read.close();
			} else {
				System.out.println("�Ҳ���ָ�����ļ�");
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}

		System.out.println("-----------------------");

		// �޸ĺ�д��ȥ
		writerText("build.xml", new String(stringBuffer));
		System.out.println("--------�޸�build���---------");
	}

	

	// 3---ant ִ��build
	public void buildWithAnt() {
		if (System.getProperty("os.name").equals("Linux")||System.getProperty("os.name").equals("Mac OS X")) {
			execCmd("/usr/local/Cellar/ant/1.9.6/bin/ant");
			return;
		}
		execCmd("cmd /c ant");
	}

	// 4---push jar
	public void pushTestJar(String localPath) {
		//localPath="\""+localPath+"\"";
		System.out.println("----jar��·���� "+localPath);
		String pushCmd = adb_path+"adb push " + localPath + " /data/local/tmp/";
		System.out.println("----" + pushCmd);
		execCmd(pushCmd);
	}

	// 5---���в���
	public void runTest(String jarName, String testName) {
		String runCmd = adb_path+"adb shell uiautomator runtest ";
		String testCmd = jarName + ".jar " + "--nohup -c " + testName;
		System.out.println("----runTest:  " + runCmd + testCmd);
		execCmd(runCmd + testCmd);
	}

	public String getWorkSpase() {
		File directory = new File("");
		String abPath = directory.getAbsolutePath();
		return abPath;
	}
	
	/**
	 * ����ִ��cmd����������Ϣ������̨
	 * @param cmd
	 */
	public void execCmd(String cmd) {
		System.out.println("----execCmd:  " + cmd);
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			//��ȷ�����
			InputStream input = p.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
                saveToFile(line, "runlog.log", false);
			}
			//���������
			InputStream errorInput = p.getErrorStream();
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(
					errorInput));
			String eline = "";
			while ((eline = errorReader.readLine()) != null) {
				System.out.println(eline);
                saveToFile(eline, "runlog.log", false);
			}       
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����д�����ݵ�ָ�����ļ���
	 * 
	 * @param path
	 *            �ļ���·��
	 * @param content
	 *            д���ļ�������
	 */
	public void writerText(String path, String content) {

		File dirFile = new File(path);

		if (!dirFile.exists()) {
			dirFile.mkdir();
		}

		try {
			// new FileWriter(path + "t.txt", true) �������true ���Բ�����ԭ��TXT�ļ����� ��д
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(path));
			bw1.write(content);
			bw1.flush();
			bw1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public void saveToFile(String text,String path,boolean isClose) {
    	File file=new File("runlog.log");   	
		BufferedWriter bf=null;
		try {
		    FileOutputStream outputStream=new FileOutputStream(file,true);
		    OutputStreamWriter outWriter=new OutputStreamWriter(outputStream);
		    bf=new BufferedWriter(outWriter);
			bf.append(text);
			bf.newLine();
			bf.flush();
			
			if(isClose){
				bf.close();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	

}