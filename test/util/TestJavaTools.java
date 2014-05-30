package util;

import org.junit.Test;

public class TestJavaTools {
	
	@Test
	public void testGetCoverPath()
	{
		System.out.println(JavaTools.getCoverPath("2013.12", 3));
	}
	
	@Test
	public void testGetThumbPackage()
	{
		System.out.println(JavaTools.getThumbPackage("2013.12", 3));
	}
	
	@Test
	public void testGetPXMLPath()
	{
		System.out.println(JavaTools.getPeriodicalXMLPath("2013.12", 3));
	}
	
	
	@Test
	public void testZip()
	{
		//JavaTools.zip("/Users/SDJG/pppp", "/Users/SDJG/pppp.zip");
		JavaTools.zip("/Users/SDJG/IDE/apache-tomcat-6.0.37/webapps/slandcrm/upload/2013/201308/201308_1/frontcover/sfrontcover_1383587595.png", "/Users/SDJG/IDE/apache-tomcat-6.0.37/webapps/slandcrm/upload/2013/201308/201308_1/ThumbPackage/sfrontcover_1383587595.png");
	}
	
	@Test
	public void testDelForder()
	{
		JavaTools.delFolder("/Users/SDJG/pppp2");
	}

	
	@Test
	public void getTopicXMLPath()
	{
		System.out.println(JavaTools.getTopicXMLPath("2013.07",1));
	}
	
	@Test
	public void testgetExtention()
	{
		String ss = JavaTools.getExtentionPrefix("2013/201312/201312_1/frontcover/sfrontcover_1384958722.jpg");
		System.out.println(ss);
	}
	
}
