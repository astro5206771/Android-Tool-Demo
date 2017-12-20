package com.ai.ipu.filetool.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

public class FileTool {
	
	public static boolean saveToFile(Context context,String content,String fileName){
		boolean isSave = false;
		if(!TextUtils.isEmpty(fileName) && !TextUtils.isEmpty(content)){
			try {
				content += "\n";
				FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
				fos.write(content.getBytes());
				fos.flush();
				fos.close();
				isSave = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				isSave = false;
			} catch (IOException e) {
				e.printStackTrace();
				isSave = false;
			}
		}
		return isSave;
	}
	
	public static String readFromFile(Context context,String fileName){
		String content = null;
		if(!TextUtils.isEmpty(fileName)){
			try {
				FileInputStream fis = context.openFileInput(fileName);
				byte[] bytes = new byte[1024];
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				while(fis.read(bytes) != -1){
					bos.write(bytes, 0, bytes.length);
				}
				content = new String(bos.toByteArray());
				fis.close();
				bos.flush();
				bos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				content = "文件不存在";
			}catch (IOException e) {
				e.printStackTrace();
				content = "读取文件报错";
			}
		}
		return content;
	}
	
	public static String readFromFileByReadBuffer(Context context,String fileName){
		String content = null;
		if(!TextUtils.isEmpty(fileName)){
			try {
				FileInputStream fis = context.openFileInput(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
				content = reader.readLine();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				content = "文件不存在";
			}catch (IOException e) {
				e.printStackTrace();
				content = "读取文件报错";
			}
		}
		return content;
	}
	
	public static boolean deleteFile(Context context,String fileName){
		boolean isDelete = false;
		if(!TextUtils.isEmpty(fileName)){
			isDelete =  context.deleteFile(fileName);
		}else {
			isDelete = true;
		}
		return isDelete;
	}
	
	public static boolean saveToFile(String content,String filePath,String fileName){
		boolean isSave = false;
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			if(!TextUtils.isEmpty(fileName)){
				try {
					content += "\n";
					File dir = new File(filePath);
					if(!dir.exists()){
						dir.mkdirs();
					}
					File file = new File(filePath, fileName);
					PrintStream ps = new PrintStream(new FileOutputStream(file, true));
					ps.append(content);
					isSave = true;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isSave;
	}
	
	public static String readFromFile(String filePath,String fileName){
		String content = null;
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			if(!TextUtils.isEmpty(fileName)){
				try {
					File file = new File(filePath,fileName);
					if(!file.exists()){
						content = "文件不存在";
						return content;
					}
					byte[] bytes = new byte[1024];
					FileInputStream fis = new FileInputStream(file);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					while(fis.read(bytes) != -1){
						bos.write(bytes);
					}
					content = new String(bos.toByteArray());
					fis.close();
					bos.flush();
					bos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					content = "文件不存在";
				} catch(IOException e){
					e.printStackTrace();
					content = "读取文件报错";
				}
			}
		}
		return content;
	}
	
	public static boolean deleteExternalFile(String filePath,String fileName){
		boolean isDelete = false;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !TextUtils.isEmpty(fileName)) {
			File file = new File(filePath, fileName);
			if (file.exists()) {
				file.delete();
				return true;
			} else {
				return false;
			}
		} else {
			isDelete = false;
		}
		return isDelete;
	}
	
	public static boolean clearExternalFile(String filePath,String fileName){
		boolean isClear = false;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !TextUtils.isEmpty(fileName)) {
			File file = new File(filePath,fileName);
			if(file.exists()){
				FileWriter writer;
				try {
					writer = new FileWriter(file);
					writer.write("");
					isClear = true;
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			return false;
		}
		return isClear;
	}
	
}
