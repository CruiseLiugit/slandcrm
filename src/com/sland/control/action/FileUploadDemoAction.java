package com.sland.control.action;

import org.apache.struts2.interceptor.RequestAware;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

public class FileUploadDemoAction extends BaseAction implements RequestAware {
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;
	private List<File> myFile = new ArrayList<File>();
	private List<String> contentType = new ArrayList<String>();
	private List<String> fileName = new ArrayList<String>(); // 文件名
	private List<String> imageFileName = new ArrayList<String>();

	private String caption;

	private Map<String, Object> request;

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	// /////////////////////////////////////////////////////////////////
	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		System.out.println("getExtention pos =" + pos);
		return fileName.substring(pos);
	}

	public String upload() {

		System.out.println("FileName :" + this.getMyFileFileName());

		if (myFile == null) {
			return INPUT;
		}
		for (int i = 0; i < myFile.size(); i++) {
			String name = new Date().getTime()
					+ getExtention(this.getMyFileFileName().get(i));
			System.out.println("........................fileName =" + name);

			imageFileName.add(name);

			// 得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
			File imageFile = new File(ServletActionContext.getServletContext()
					.getRealPath("upload") + "/" + name);
			// 把图片写入到上面设置的路径里
			copy(myFile.get(i), imageFile);
		}
		return SUCCESS;
	}

	// /////////////////////////////////////////////////
	// 使用 html5 标签实现多图上传
	public String uploadmore() {

		System.out.println("多图上传 ...FileName :" + this.getMyFileFileName());

		if (myFile == null) {
			return INPUT;
		}
		for (int i = 0; i < myFile.size(); i++) {
			String name = new Date().getTime()
					+ getExtention(this.getMyFileFileName().get(i));
			System.out.println("........................fileName =" + name);

			imageFileName.add(name);

			// 得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
			File imageFile = new File(ServletActionContext.getServletContext()
					.getRealPath("upload") + "/" + name);
			// 把图片写入到上面设置的路径里
			copy(myFile.get(i), imageFile);
		}
		return SUCCESS;
	}

	// ///////////////////////////////////////////////
	public List<File> getMyFile() {
		return myFile;
	}

	public void setMyFile(List<File> myFile) {
		this.myFile = myFile;
	}

	public List<String> getContentType() {
		return contentType;
	}

	public void setContentType(List<String> contentType) {
		this.contentType = contentType;
	}

	public List<String> getMyFileFileName() {
		return fileName;
	}

	public void setMyFileFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	public List<String> getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(List<String> imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}
}
