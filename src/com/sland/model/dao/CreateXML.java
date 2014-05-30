package com.sland.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.struts2.ServletActionContext;

import util.JavaTools;

import com.jamesmurty.utils.XMLBuilder;
import com.sland.model.pojo.Magazine;
import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.Periodical;
import com.sland.model.pojo.Topic;

public class CreateXML {
	String ss = File.separator;
	String separator = System.getProperty("file.separator"); // 文件分割符号

	/**
	 * 创建总的杂志XML
	 */
	public void createListXML() {
		MagazineDao dao = new MagazineDao();
		try {
			Map<String, List> map = dao.getListXml();
			XMLBuilder builder = null;
			if (map == null) {
				builder = XMLBuilder.create("magazine").a("name", "NailAll")
						.a("version", "1.0.0").up();
			} else {
				/* Build XML document in-place */
				builder = XMLBuilder.create("magazine").a("name", "NailAll")
						.a("version", "1.0.0");
				Set<String> keys = map.keySet();
				for (Object keyStr : keys) {
					String key = (String) keyStr;
					
					//builder.e("year").a("value", key);
					XMLBuilder e1 = builder.element("year");
					e1.attr("value", key);

					//System.out.println("###################### key ="+key);
					List monthsList = map.get(key);
					for (Object object : monthsList) {
						
						Magazine mag = (Magazine) object;
						//System.out.println("#-----------------------# mag ="+mag);

						e1.e("Month").a("value", mag.getMonth())
								.e("Periodical").t("" + mag.getPeriodicalnum())
								.up().e("WholePeriodical")
								.t("" + mag.getWholeperiodicanum()).up()
								.e("Title").t(mag.getTitle()).up()
								.e("Synopsis").t(mag.getSynopsis()).up()
								.e("FrontCover").t(mag.getFrontcover()).up()
								.e("CatalogCover").t("").up().e("Ppath")
								.t(mag.getPpath()).up();
					}

				}
			}

			/* Set output properties */
			Properties outputProperties = new Properties();
			outputProperties.put(javax.xml.transform.OutputKeys.VERSION, "1.0");
			outputProperties.put(javax.xml.transform.OutputKeys.METHOD, "xml");
			outputProperties.put(javax.xml.transform.OutputKeys.ENCODING,
					"UTF-8");

			// Use Document@setXmlStandalone(true) to ensure
			// OutputKeys.STANDALONE is respected.
			builder.getDocument().setXmlStandalone(true);
			// outputProperties.put(javax.xml.transform.OutputKeys.STANDALONE,"yes");

			// Get 2-space indenting when using the Apache transformer
			// outputProperties.put("{http://xml.apache.org/xslt}indent-amount",
			// "2");

			/* Serialize builder document */
			String path = ServletActionContext.getServletContext().getRealPath(
					"upload")
					+ separator;
			//PrintWriter writer = new PrintWriter(new FileOutputStream(path+"List.xml"));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path+"List.xml"),"UTF-8"));
			builder.toWriter(writer, outputProperties);

			// System.out.println(builder.asString());
			// assertEquals(EXAMPLE_XML_DOC, writer.toString());

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("写文件时 乱码问题");
			e.printStackTrace();
		}
	}

	/**
	 * 创建每期的杂志XML
	 * 2013-11-20 晚上补充一个参数 thumbName 遗漏的一个缩略图名称属性
	 */
	public void createMonthXML(String month,String path,int count) {
		PeriodicalDao dao = new PeriodicalDao();
		try {
			Map<String, List> map = dao.findAllMonthPerodical(month);
			XMLBuilder builder = null;
			if (map == null) {
				builder = XMLBuilder.create("periodical").up();
			} else {
				/* Build XML document in-place */
				builder = XMLBuilder.create("periodical");

				Set keys = map.keySet();
				//for (Object keyStr : keys) {
				//	String key = (String) keyStr;

					XMLBuilder e1 = builder.element("contents");
					Periodical temp = null;
					List topicList = null;
					//if (month.equals(key)) {
						temp = (Periodical) map.get(month);
					//}else if ("topiclist".equals(key)) {
						topicList = map.get("topiclist");
						//System.out.println("$$$$$$$$$$$$$$$ topicList ="+topicList.size());
					//}
						/*
						 <contents>
    <CoverPage PageName="封面" ThumbName="sfrontcover_364770974.jpg" CoverPath="2013/201301/201301_1/frontcover/frontcover.html" />
						 */
						e1.e("CoverPage").a("PageName",temp.getCatalogPagename()).a("ThumbName",temp.getCoverpageThunmbname()).a("CoverPath",temp.getCoverPath()).up();
					
						for (Object object : topicList) {
							Topic mag = (Topic) object;
						/*
						 <!-- 主题 -->
    				<Topic TopicName="尖锋产品列表展示" 
    					   ThumbName="s201301_1_jianfengchanpinliebiaozhanshi_325163512.jpg" 
    					   TopicPath="2013/201301/201301_1/201301_1_jianfengchanpinliebiaozhanshi/201301_1_jianfengchanpinliebiaozhanshi.html" 
    					   Intro="尖峰产品列表展示" /> 
						 */	
						e1.e("Topic")
								.a("TopicName", mag.getTopicName())
								.a("TopicPath",mag.getTopicPath())
								.a("ThumbName", mag.getTopicThumbname())
								.a("Intro",mag.getIntro()).up();
					}

				//}
			}

			/* Set output properties */
			Properties outputProperties = new Properties();
			outputProperties.put(javax.xml.transform.OutputKeys.VERSION, "1.0");
			outputProperties.put(javax.xml.transform.OutputKeys.METHOD, "xml");
			outputProperties.put(javax.xml.transform.OutputKeys.ENCODING,
					"UTF-8");

			// Use Document@setXmlStandalone(true) to ensure
			// OutputKeys.STANDALONE is respected.
			builder.getDocument().setXmlStandalone(true);
			// outputProperties.put(javax.xml.transform.OutputKeys.STANDALONE,"yes");

			// Get 2-space indenting when using the Apache transformer
			// outputProperties.put("{http://xml.apache.org/xslt}indent-amount",
			// "2");

			/* Serialize builder document */
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			String fileName = JavaTools.getTopicXMlName(month, count);
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ dir ="+dir);
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ fileName ="+fileName);
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ xml ="+builder.asString());
			
			
			//PrintWriter writer = new PrintWriter(new FileOutputStream(path+fileName));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path+fileName),"UTF-8"));
			
			
			builder.toWriter(writer, outputProperties);

			// System.out.println(builder.asString());
			// assertEquals(EXAMPLE_XML_DOC, writer.toString());

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建每张图片的评论
	 */
	public void createTopicCommentXML() {
		MagazineDao dao = new MagazineDao();
		try {
			Map<String, List> map = dao.getListXml();
			XMLBuilder builder = null;
			if (map == null) {
				builder = XMLBuilder.create("magazine").a("name", "naillAll")
						.a("version", "1.0.0").up();
			} else {
				/* Build XML document in-place */
				builder = XMLBuilder.create("magazine").a("name", "naillAll")
						.a("version", "1.0.0");
				Set keys = map.keySet();
				for (Object keyStr : keys) {
					String key = (String) keyStr;

					// builder.e("year").a("value", key);
					XMLBuilder e1 = builder.element("year");
					e1.attr("value", key);

					List monthsList = map.get(key);
					for (Object object : monthsList) {
						Magazine mag = (Magazine) object;

						e1.e("Month").a("value", mag.getMonth())
								.e("Periodical").t("" + mag.getPeriodicalnum())
								.up().e("WholePeriodical")
								.t("" + mag.getWholeperiodicanum()).up()
								.e("Title").t(mag.getTitle()).up()
								.e("Synopsis").t(mag.getSynopsis()).up()
								.e("FrontCover").t(mag.getFrontcover()).up()
								.e("CatalogCover").t("").up().e("Ppath")
								.t(mag.getPpath()).up();
					}

				}
			}

			/* Set output properties */
			Properties outputProperties = new Properties();
			outputProperties.put(javax.xml.transform.OutputKeys.VERSION, "1.0");
			outputProperties.put(javax.xml.transform.OutputKeys.METHOD, "xml");
			outputProperties.put(javax.xml.transform.OutputKeys.ENCODING,
					"UTF-8");

			// Use Document@setXmlStandalone(true) to ensure
			// OutputKeys.STANDALONE is respected.
			builder.getDocument().setXmlStandalone(true);
			// outputProperties.put(javax.xml.transform.OutputKeys.STANDALONE,"yes");

			// Get 2-space indenting when using the Apache transformer
			// outputProperties.put("{http://xml.apache.org/xslt}indent-amount",
			// "2");

			/* Serialize builder document */
			String path = ServletActionContext.getServletContext().getRealPath(
					"upload")
					+ separator;
			PrintWriter writer = new PrintWriter(new FileOutputStream(
					"List.xml"));
			builder.toWriter(writer, outputProperties);

			// System.out.println(builder.asString());
			// assertEquals(EXAMPLE_XML_DOC, writer.toString());

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
