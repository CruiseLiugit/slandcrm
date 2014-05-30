package com.sland.control.action.fileupload;

import java.io.File;
import java.util.Date;

import util.JavaTools;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	private String month;
	private int count;
	
	public MyFileRenamePolicy()
	{
		
	}
	
	public MyFileRenamePolicy(String month,int count)
	{
		this.month = month;
		this.count = count;
	}
	
	public File rename(File f) {
		//获取父目录路径，
		String parentDir = f.getParent();
		//获取不带路径的文件名
		String fname = f.getName();
		
		//获取文件扩展名
		String fileExt = "";
		int i=-1;
		if ((i = fname.indexOf("."))!= -1) {
			fileExt  = fname.substring(i);
			fname = fname.substring(0, i);
		}

		System.out.println("文件路径  :"+parentDir);
		System.out.println("文件名  :"+fname);
		System.out.println("文件后缀  :"+fileExt);
		
		//添加时间戳
		fname = "sfrontcover_"+(""+(new Date().getTime()/1000));
		String separator =System.getProperty("file.separator");
		//组合文件名
		fname = parentDir+separator+JavaTools.getCoverPath(this.month, this.count)+fname+fileExt;
		System.out.println("文件全名  :"+fname);
		//创建文件
		File temp = new File(fname);
		
		return temp;
	}

}
