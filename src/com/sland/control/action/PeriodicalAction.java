package com.sland.control.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import util.JavaTools;
import util.Thumbnail;
import util.TimeTools;

import com.sland.model.pojo.Comment;
import com.sland.model.pojo.Periodical;
import com.sland.model.pojo.Magazine;
import com.sland.model.pojo.Topic;
import com.sland.model.pojo.Year;

import com.sland.model.dao.CommentDao;
import com.sland.model.dao.CreateXML;
import com.sland.model.dao.MagazineDao;
import com.sland.model.dao.PeriodicalDao;
import com.sland.model.dao.UserDao;
import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.entity.PeriodicalPageEntity;

public class PeriodicalAction extends BaseAction implements RequestAware {
	private Magazine magazine;
	String separator = System.getProperty("file.separator"); // 文件分割符号

	// 封面图片上传
	private static final int BUFFER_SIZE = 16 * 1024;
	private File file1; // 具体上传文件的 引用 , 指向临时目录中的临时文件
	private String file1FileName; // 上传文件的名字 ,FileName 固定的写法
	private String file1ContentType; // 上传文件的类型， ContentType 固定的写法

	private String cm1; // 所希望查看的期刊月份
	private String pmonth; // 单个选中的某个期刊的 月份，里面可能有多个期刊
	private String pernum; // 所选期刊，在当月的编号

	private String topicThunbname; // 点赞时，传递的主题缩略图名称
	private String periodicalcoverthumb; // 点赞时，得到当期封面缩略图的名称，以查询当期 id
	private String editcommentid;    //2014-05-07 增加评论内容修改功能

	// 第二版，把评论模块修改为 图片明细
	private Comment comm; // 封装 comment_add.jsp 页面传递的字段

	// 第二版增加的期刊页面 periodical_add.jsp 页面，所选择的日期字段
	private String yearmonth;

	// ////////////多图上传///////////////////////////////////
	// private String imgs; // 每个期刊中，选中上传的多个 主题图片
	private List<File> images = new ArrayList<File>();
	private List<String> contentType = new ArrayList<String>();
	private List<String> fileName = new ArrayList<String>(); // 文件名
	private List<String> imageFileName = new ArrayList<String>();

	private Map<String, Object> request;

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	// /////////////////////////////////////////////////////////////////
	// 期刊模块，仅仅跳转到增加期刊页面
	public String toadd() throws Exception {
		return "toadd";
	}

	// 第二版，窗帘，输入日期，获取所有 窗帘一级分类(年份)列表
	public String selectYear() throws Exception {
		//System.out.println("页面传递的月份： yearmonth =" + yearmonth);
		// 截取年份
		String year = yearmonth.substring(0, 4);
		//System.out.println("year =" + year);
		/** 获取response对象 */
		HttpServletResponse response = ServletActionContext.getResponse();
		/** 获取输出out对象 */
		PrintWriter out = response.getWriter();

		MagazineDao madao = new MagazineDao();
		List allyears = madao.findByYear(year);
		if (allyears.size() == 0) {
			out.print("选择的年份有误，请检查一级分类");
		} else {
			Year y = (Year) allyears.get(0);
			out.print(y.getTitle());
		}

		return null;
	}

	// 期刊模块，增加期刊
	public String add() throws Exception {
		/*
		 * System.out.println("------------------------------------");
		 * System.out.println("Month :" + magazine.getMonth());
		 * System.out.println("Title :" + magazine.getTitle());
		 * System.out.println("描述 :" + magazine.getSynopsis());
		 * 
		 * 
		 * System.out.println("File :" + this.getFile1());
		 * System.out.println("FileName :" + this.getFile1FileName());
		 */

		/*
		 * ===============realPath
		 * =/Users/SDJG/IDE/apache-tomcat-6.0.37/webapps/
		 * nailall/upload/2013/201304/201304_1/frontcover/ 2013.04 dir1 = 201304
		 * ===============大图路径为
		 * :/Users/SDJG/IDE/apache-tomcat-6.0.37/webapps/nailall
		 * /upload/2013/201304/201304_1/frontcover/ 2013.04 dir1 = 201304
		 * ===============缩略图路径为
		 * :/Users/SDJG/IDE/apache-tomcat-6.0.37/webapps/nailall
		 * /upload/2013/201304/201304_1/ThumbPackage/ 文件名 :bg 文件后缀 :.png
		 * ===============图片文件名称 为 :sfrontcover_1383749005.png before =
		 * /Users/SDJG
		 * /IDE/apache-tomcat-6.0.37/webapps/nailall/upload/2013/201304
		 * /201304_1/frontcover/sfrontcover_1383749005.png , after
		 * :/Users/SDJG/IDE
		 * /apache-tomcat-6.0.37/webapps/nailall/upload/2013/201304
		 * /201304_1/ThumbPackage/sfrontcover_1383749005.png
		 */
		MagazineDao dao = new MagazineDao();

		String month = this.magazine.getMonth();
		/* */
		String m = month;
		// 判断年份是否存在
		// List<MyEntity> list = dao.findByYear(month.substring(0, 4));
		// if (list == null || list.size() == 0) {
		// request.put("addYearError", "所选窗帘一级分类没有设置，请设置");
		// return INPUT;
		// }

		Year y = dao.findYear(m.substring(0, 4));
		int perNum = dao.getMonthPeriodical(this.magazine.getMonth()) + 1;
		this.magazine.setYear(y);
		this.magazine.setPeriodicalnum(perNum); // 本月期刊数 OK
		this.magazine.setWholeperiodicanum("" + dao.getWholePeriodical()); // 总期刊数

		// //////////////////////////////////////////////////////////
		String pPath = "";
		if ("\\".equals(separator)) {
			System.out.println("Window 系统");
			pPath = JavaTools.getPeriodicalXMLPath2(this.magazine.getMonth(),
					perNum);
		} else if ("/".equals(separator)) {
			System.out.println("Mac OS 系统");
			pPath = JavaTools.getPeriodicalXMLPath(this.magazine.getMonth(),
					perNum);
		}
		// //////////////////////////////////////////////////////////

		this.magazine.setPpath(pPath); // 本期刊描述文件路径 OK

		//
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"upload")
				+ separator
				+ JavaTools.getCoverPath(magazine.getMonth(), perNum);

		System.out.println("===============realPath =" + realPath);

		if (file1 == null) {
			return INPUT;
		}

		// 获取文件存储路径,大图路径
		String path = ServletActionContext.getServletContext().getRealPath(
				"upload")
				+ separator
				+ JavaTools.getCoverPath(this.magazine.getMonth(), perNum);
		// 大图路径里面，html 文件同目录下新建一个 resource
		String pathImg = ServletActionContext.getServletContext().getRealPath(
				"upload")
				+ separator
				+ JavaTools.getCoverImagePath(this.magazine.getMonth(), perNum);

		System.out.println("===============大图路径为 :" + path);
		// 缩略图路径
		String thumnPackage = ServletActionContext.getServletContext()
				.getRealPath("upload")
				+ separator
				+ JavaTools.getThumbPackage(this.magazine.getMonth(), perNum);
		System.out.println("===============缩略图路径为  :" + thumnPackage);
		// 判断目录是否存在，不存在先创建
		File dir = new File(path);
		File thumn = new File(thumnPackage);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (!thumn.exists()) {
			thumn.mkdirs();
		}
		// /////////////////////////////////////////////////////////////
		// 2013年11月22日 为 frontcover 封面图片，创建描述的 html 文件
		File frontimgdir = new File(pathImg);
		if (!frontimgdir.exists()) {
			frontimgdir.mkdirs();
		}

		// /////////////////////////////////////////////////////////////

		// 获取不带路径的文件名
		String fname = file1FileName;

		// 获取文件扩展名
		String fileExt = "";
		int i = -1;
		if ((i = fname.indexOf(".")) != -1) {
			fileExt = fname.substring(i);
			fname = fname.substring(0, i);
		}

		System.out.println("文件名  :" + fname);
		System.out.println("文件后缀  :" + fileExt);

		// 添加时间戳
		fname = "sfrontcover_" + ("" + (new Date().getTime() / 1000));

		file1FileName = fname + fileExt;
		System.out.println("===============图片文件名称 为 :" + file1FileName);

		// 保存路径和文件名
		// path =
		// /Users/SDJG/IDE/apache-tomcat-6.0.37/webapps/slandcrm/upload/2013/201309/201309_2/frontcover/
		// fileName = sfrontcover_1383581895.jpg
		this.magazine.setFrontcover(file1FileName); // 封面图路径 OK

		// //////////////////////////////////////////////////////////
		// 大图，缩略图完整路径
		String foverurl = "";
		String thumurl = "";
		if ("\\".equals(separator)) {
			System.out.println("Window 系统");
			foverurl = "upload" + "/"
					+ JavaTools.getCoverPath2(this.magazine.getMonth(), perNum)
					+ file1FileName;
			thumurl = "upload"
					+ "/"
					+ JavaTools.getThumbPackage2(this.magazine.getMonth(),
							perNum) + file1FileName;
		} else if ("/".equals(separator)) {
			System.out.println("Mac OS 系统");
			foverurl = "upload" + separator
					+ JavaTools.getCoverPath(this.magazine.getMonth(), perNum)
					+ file1FileName;
			thumurl = "upload"
					+ separator
					+ JavaTools.getThumbPackage(this.magazine.getMonth(),
							perNum) + file1FileName;
		}
		// //////////////////////////////////////////////////////////

		this.magazine.setColumn1(foverurl); // 保存大图路径
		this.magazine.setColumn2(thumurl); // 保存小图路径

		// /////////////////////////////// 同时插入 期刊表
		Periodical p = new Periodical();
		p.setCoverpageName(magazine.getTitle());
		p.setCoverpageThunmbname(file1FileName);

		// //////////////////////////////////////////////////////////
		String coverPath = "";
		if ("\\".equals(separator)) {
			System.out.println("Window 系统");
			pPath = JavaTools.getCoverPath2(magazine.getMonth(), perNum);
		} else if ("/".equals(separator)) {
			System.out.println("Mac OS 系统");
			pPath = JavaTools.getCoverPath(magazine.getMonth(), perNum);
		}
		// //////////////////////////////////////////////////////////

		p.setCoverPath(pPath + file1FileName);
		// 把期刊的数目和总数添加到备用字段中
		p.setColumn1("" + perNum); // 当前期刊数
		p.setColumn2("" + dao.getWholePeriodical()); // 期刊总数

		p.setMagazine(this.magazine);

		Set<Periodical> pset = new HashSet<Periodical>();
		pset.add(p);

		this.magazine.setPeriodicallist(pset);

		// /////////////////////////////存入数据库
		dao.saveMonth(magazine);
		// 得到工作台上的数据信息
		UserDao udao = new UserDao();
		List allMagazines = udao.findAllMagziones();
		super.getSession().put("magazinesnum", allMagazines.size());

		// //////////////////////////////////////////////////////////
		// 2013-11-22 晚修改，把插入数据库的 topicPath 中的最后图片名，改为 html
		String filePrefixName = JavaTools.getExtentionPrefix(file1FileName);

		// 2013-11-22 晚补充，生成的每期xml 中遗漏了一个 <Topic > 属性ThumbName
		// 2014-04-19 晚补充，生成三种 html ，里面的图片尺寸设定死 4和4s 5和5s 还有iPad 320*480 320*568
		// 768*2014
		String frontcoverHtmlName = filePrefixName + ".html";
		// 得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
		File htmlFile = new File(path, frontcoverHtmlName);
		copyImageHtmlForiPad(htmlFile, file1FileName);

		/*
		 * String frontcoverHtmlName = filePrefixName + ".html"; //
		 * 得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里) File htmlFile = new
		 * File(path, frontcoverHtmlName); copyImageHtml(htmlFile,
		 * file1FileName);
		 * 
		 * String frontcoverHtmlName = filePrefixName + ".html"; //
		 * 得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里) File htmlFile = new
		 * File(path, frontcoverHtmlName); copyImageHtml(htmlFile,
		 * file1FileName);
		 */

		// ////////////////////////////////////////////////////////////

		// /////////////////////////////上传图片
		// 输出流
		OutputStream os = new FileOutputStream(new File(pathImg, file1FileName)); // 大图

		// 输入流
		InputStream is = new FileInputStream(file1);

		byte[] buf = new byte[BUFFER_SIZE];
		int length = 0;

		while (-1 != (length = is.read(buf))) {
			os.write(buf, 0, length);
		}
		is.close();
		os.close();

		// /////////////////////////////
		// 根据大图、小图路径 生成缩略图
		String before = pathImg + file1FileName;
		String after = thumnPackage + file1FileName;
		Thumbnail ccc = new Thumbnail(before, after);
		ccc.resizeFix(240, 320);

		// /////////////////////////////压缩内容
		String todir = JavaTools.changeTimeToPath(this.magazine.getMonth(),
				perNum) + "frontcover.zip";
		System.out.println("压缩文件  todir =" + todir);
		JavaTools.zip(path, ServletActionContext.getServletContext()
				.getRealPath("upload") + separator + todir);

		// ///////////////////////////并删除指定目录
		JavaTools.delFolder(path);

		// ////////////////////////////跳转获得下一页数据
		// 取出所有状态为 1 的年份列表
		List<MyEntity> list1 = dao.findAllStatuYears();

		// 取出所有 magazine 表中的数据
		request.put("yearlist", list1);
		request.put("yearSize", list1.size());

		// 得到所有页面需要 的杂志对象
		List<PeriodicalPageEntity> list2 = dao.findAllPeriodicals();
		request.put("periodicallist", list2);

		// ///////////////生成 XML/////////////////
		CreateXML cxml = new CreateXML();
		cxml.createListXML();

		return SUCCESS;
	}

	// 期刊模块，查询所有期刊
	public String findAll() throws Exception {
		System.out.println(cm1);
		// 取出所有状态为 1 的年份列表
		MagazineDao dao = new MagazineDao();
		List<MyEntity> list = dao.findAllStatuYears();

		// 取出所有 magazine 表中的数据
		request.put("yearlist", list);
		request.put("yearSize", list.size());

		// 得到所有页面需要 的杂志对象
		List<PeriodicalPageEntity> list2 = dao.findAllPeriodicals();
		request.put("periodicallist", list2);

		// 取出所选择的月份的期刊明细
		List<PeriodicalPageEntity> list3 = dao.fincAllDetailPeriodicals(cm1);
		request.put("detaillist", list3);
		request.put("detaillistsize", list3.size());
		request.put("detailmonth", cm1);

		return "getmonth";
	}

	// 期刊模块，查看选中的一起的期刊，跳转到 topic_list.jsp
	public String findByPid() throws Exception {
		//System.out.println(".....跳转到 topic_list.jsp   pmonth =" + pmonth);
		PeriodicalDao pdao = new PeriodicalDao();
		List<Periodical> list = pdao.findAllPeriodicals(pmonth);
		// System.out.println(list.size());

		request.put("periodical_list", list);
		request.put("month", pmonth);

		return "gettopiclist";
	}

	// 选中某个期刊，准备增加这个期刊里面所有的 图片
	public String readytoAddTopic() throws Exception {
		// System.out.println("准备增加 pid = " + this.cm1 + "  ,pmonth = " +
		// pmonth);
		request.put("cm1", cm1);
		request.put("pmonth", pmonth);

		return "readyadd";
	}

	// //////////////////////////////////////////////////////////////
	// 增加主题图片，多图上传
	public String addTopic() throws Exception {
		// System.out.println("==========多图上传  pid = " + this.cm1);
		/*
		 * System.out .println("月份为 :" + this.pmonth + " , pernum = " +
		 * this.pernum);
		 * 
		 * System.out.println("添加的图片名称 imgs = " + this.images + " , 月份为 :" +
		 * this.pmonth + " , pernum = " + this.pernum);
		 */

		if (this.images == null || this.images.size() == 0) {
			request.put("addTopicError", "没有选中图片，请重新选择.");
			return "addtopicinput";
		}

		PeriodicalDao dao = new PeriodicalDao();
		// 2013-11-20 晚补充，生成的每期xml 中遗漏了一个 <Topic > 属性ThumbName
		String topicThumbName = "";
		// ////////////////////////////////////////////////////////////
		for (int i = 0; i < this.images.size(); i++) {
			File src = images.get(i);
			String fileName = "";

			if (src.isFile()) {
				fileName = new Date().getTime() + getExtention(src.getName());
			}
			// System.out.println("........................fileName =" +
			// fileName);

			imageFileName.add(fileName);

			// ///////////////////////////////////////////////////////////////
			// 获取文件存储路径,大图路径
			// 大图路径里面存放 与图片同名的 html
			String path = ServletActionContext.getServletContext().getRealPath(
					"upload")
					+ separator
					+ JavaTools.getTopicPath(this.pmonth,
							new Integer(pernum.trim()), fileName);
			// 大图路径里面，html 文件同目录下新建一个 resource
			String pathImg = ServletActionContext.getServletContext()
					.getRealPath("upload")
					+ separator
					+ JavaTools.getTopicPathImg(this.pmonth,
							new Integer(pernum.trim()), fileName);
			System.out.println("===============大图路径为 :" + pathImg);

			// 缩略图路径
			String thumnPackage = ServletActionContext.getServletContext()
					.getRealPath("upload")
					+ separator
					+ JavaTools.getThumbPackage(this.pmonth,
							new Integer(pernum.trim()));
			System.out.println("===============缩略图路径为  :" + thumnPackage);
			// 判断目录是否存在，不存在先创建
			File dir = new File(path); // 大图中存放链接图片的 html 文件的路径
			File dirImg = new File(pathImg); // 大图中存放 图片的路径
			File thumn = new File(thumnPackage);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (!dirImg.exists()) {
				dirImg.mkdirs();
			}
			if (!thumn.exists()) {
				thumn.mkdirs();
			}

			// 大图，缩略图完整路径
			// //////////////////////////////////////////////////////////
			String foverurl = "";
			String thumurl = "";
			String coverPath = "";
			if ("\\".equals(separator)) {
				System.out.println("Window 系统");
				foverurl = "upload"
						+ "/"
						+ JavaTools.getTopicPath2(this.pmonth, new Integer(
								pernum.trim()), fileName) + fileName;
				thumurl = "upload"
						+ "/"
						+ JavaTools.getThumbPackage2(this.pmonth, new Integer(
								pernum.trim())) + fileName;
			} else if ("/".equals(separator)) {
				System.out.println("Mac OS 系统");
				foverurl = "upload"
						+ separator
						+ JavaTools.getTopicPath(this.pmonth, new Integer(
								pernum.trim()), fileName) + fileName;
				thumurl = "upload"
						+ separator
						+ JavaTools.getThumbPackage(this.pmonth, new Integer(
								pernum.trim())) + fileName;
			}
			// //////////////////////////////////////////////////////////
			System.out.println("大图完整路径  :" + foverurl);
			System.out.println("小图完整路径  :" + thumurl);

			// //////////////////////////////////////////////////////////
			// 2013-11-20 晚修改，把插入数据库的 topicPath 中的最后图片名，改为 html
			String filePrefixName = JavaTools.getExtentionPrefix(fileName);

			// 2013-11-20 晚补充，生成的每期xml 中遗漏了一个 <Topic > 属性ThumbName
			// topicThumbName = filePrefixName + ".html";
			// 11-24 晚修改，
			topicThumbName = JavaTools.getHtmlFileName(this.pmonth,
					new Integer(pernum.trim()), filePrefixName);

			// /////////////////////////////上传图片
			// 得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
			// 2014-04-19 晚上增加方法，为 iphone 4 iphone5 ipad 增加不同的 html 描述 320*480
			// 320*568 768*2014
			File htmlFile = new File(path, topicThumbName);
			copyImageHtmlForiPad(htmlFile, fileName);

			File imageFile = new File(pathImg, fileName);
			// 把图片写入到上面设置的路径里
			copy(src, imageFile);

			// /////////////////////////////存入数据库
			Topic topic = new Topic();
			topic.setTopicName("");
			topic.setTopicThumbname(fileName);

			// //////////////////////////////////////////////////////////
			String topicPath = "";
			if ("\\".equals(separator)) {
				System.out.println("Window 系统");
				topicPath = JavaTools.getTopicPath2(this.pmonth, new Integer(
						pernum.trim()), fileName, topicThumbName);
			} else if ("/".equals(separator)) {
				System.out.println("Mac OS 系统");
				topicPath = JavaTools.getTopicPath(this.pmonth, new Integer(
						pernum.trim()), fileName, topicThumbName);
			}

			// 存入数据库的名称也变为 xxx.html
			// topic.setTopicPath(topicPath + fileName);
			// 11-23晚修改，生成的 topicPath 后缀名为 xxx.html
			// topic.setTopicPath(topicPath + topicThumbName);
			// 11-24 晚修改，生成的 xml 文件里面
			// TopicPath="2013/201302/201302_1/2013_02_1385223267581/2013_02_1385223267581.html
			topic.setTopicPath(topicPath);
			topic.setColumn1(foverurl); // 大图路径
			topic.setColumn2(thumurl); // 小图路径
			topic.setColumn4(0); // 赞的数量
			Periodical perio = new Periodical();
			perio.setId(new Integer(cm1.trim()));
			topic.setPeriodical(perio);

			dao.saveTopic(topic);

			// 更新数据
			UserDao udao = new UserDao();
			List allTopics = udao.findAllTopics();
			super.getSession().put("topicsnum", allTopics.size());

			// /////////////////////////////
			// 根据大图、小图路径 生成缩略图
			String before = pathImg + fileName;
			String after = thumnPackage + fileName;
			// System.out.println("zip   befor :" + before + "  ,after :" +
			// after);

			Thumbnail ccc = new Thumbnail(before, after);
			ccc.resizeFix(240, 320);

			// /////////////////////////////压缩内容
			// 获取不带路径的文件名
			String fname = fileName;

			// 获取文件扩展名前面的 名称
			int ii = -1;
			if ((ii = fname.indexOf(".")) != -1) {
				fname = fname.substring(0, ii);
			}

			// System.out.println("扩展名前的 文件名  :" + fname);

			String todir = JavaTools.getZipPath(this.pmonth,
					new Integer(pernum.trim()), fname);
			// System.out.println("........压缩文件  todir =" + todir);
			JavaTools.zip(path, ServletActionContext.getServletContext()
					.getRealPath("upload") + separator + todir);

			// ///////////////////////////并删除指定目录
			JavaTools.delFolder(path);
		}

		// ////////////////////////////跳转获得 tipic.jsp 数据
		// 取出所有指定 月份 期刊的 periodicalID 缩略图
		List list = dao.findAllTopicByPid(new Integer(cm1));
		request.put("alltopics", list);
		request.put("cm1", cm1);
		request.put("pmonth", pmonth);
		request.put("pernum", pernum);

		// 11-30 晚上增加
		// 根据 id 查询得到一个 期刊对象，前端无法得到 pid，通过 coverpagethunbname 间接得到
		PeriodicalDao pdao = new PeriodicalDao();
		Periodical peri = pdao.findPeriodicalByPid(new Integer(cm1));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@根据期刊 id  查询得到期刊对象 ="
				+ peri.getCoverpageThunmbname());
		request.put("periodicalcoverthumb", peri.getCoverpageThunmbname());

		// ////////////生成 XML /////////////////////
		CreateXML xml = new CreateXML();
		String xmlpath = ServletActionContext.getServletContext().getRealPath(
				"upload")
				+ separator
				+ JavaTools.getTopicXMLPath(pmonth, new Integer(pernum.trim()));
		// 2013-11-20 晚补充，生成的每期xml 中遗漏了一个 <Topic > 属性ThumbName，加一个参数
		xml.createMonthXML(this.pmonth, xmlpath, new Integer(pernum.trim()));

		return "add_ok";
	}

	// 选中某个期刊，准备增加这个期刊里面所有的 图片
	public String readytoDeletTopic() throws Exception {
		System.out.println("准备删除 pid = " + this.cm1);

		return "readydelete";
	}

	// ////////////////////////////////////////////////////////////
	// 主题模块，列表显示
	public String seelist() throws Exception {
		System.out.println("主题模块，列表显示 month = " + this.cm1);
		PeriodicalDao pdao = new PeriodicalDao();
		List<Periodical> list = pdao.findAllPeriodicals(cm1);

		request.put("periodical_list", list);
		request.put("month", cm1);

		return "gettopiclist";
	}

	// 主题模块，图片预览显示
	public String seeimages() throws Exception {
		System.out.println("主题模块，图片预览显示 month = " + this.cm1);
		PeriodicalDao pdao = new PeriodicalDao();
		// 取出所有指定 月份 期刊的 periodicalID 缩略图
		List list = pdao.findAllTopicByPid(new Integer(cm1));
		request.put("alltopics", list);
		request.put("cm1", cm1);

		// 11-30 晚上增加
		// 根据 id 查询得到一个 期刊对象，前端无法得到 pid，通过 coverpagethunbname 间接得到
		Periodical peri = pdao.findPeriodicalByPid(new Integer(cm1));
		// System.out.println("@@@@@@@@@@@@@@@@@@@@@@根据期刊 id  查询得到期刊对象 ="+peri.getCoverpageThunmbname());
		request.put("periodicalcoverthumb", peri.getCoverpageThunmbname());

		return "add_ok";
	}

	// 2013-11-30 晚上做赞
	// 主题模块，图片预览中，每个图片点击增加 赞
	// 第二版目前该功能暂时不用，替换成 增加评论 commentTopic()
	public String approveTopic() throws Exception {
		System.out.println("主题模块，图片预览 点赞 的图片 id = " + this.topicThunbname
				+ "， 期刊 缩略图名 =" + this.periodicalcoverthumb);

		PeriodicalDao pdao = new PeriodicalDao();

		// 得到期刊封面的缩略图名称
		// 根据封面的缩略图名称，查询得到期刊 id
		Periodical peri = pdao
				.findPeriodicalByCoverThunbname(periodicalcoverthumb);
		System.out.println(peri.getId());
		int pid = peri.getId();
		System.out.println("pic = " + pid);

		// 按照id 给 图片 topic 表中 column3 列 +1
		pdao.getTopicByThunbname(topicThunbname);

		// ///////////供点赞后返回页面，显示更新结果使用/////////////////
		// 根据期刊 id ，查询得到这一期所有的 外键为 periodicalID 的主题缩略图
		List list = pdao.findAllTopicByPid(pid);
		// System.out.println("----------------------- list ="+list);
		request.put("alltopics", list);
		request.put("cm1", pid);

		// 11-30 晚上增加
		// 根据 id 查询得到一个 期刊对象，前端无法得到 pid，通过 coverpagethunbname 间接得到
		request.put("periodicalcoverthumb", periodicalcoverthumb);

		return "add_ok";
	}

	// 2014-04-06 跳转到评论页面
	public String commentTopic() throws Exception {
		// System.out.println("主题模块，图片预览 加评论 的图片 name = " +
		// this.topicThunbname+"， 所属期刊 缩略图名 ="+this.periodicalcoverthumb);

		// 2014-04-07 修改，跳转到评论页面的时候，把 所评论的图片的路径发送过去，看着图填写评论
		CommentDao cdao = new CommentDao();
		Topic topic = cdao.findTopicByThumbname(topicThunbname.trim());
		request.put("topicimgPath", topic.getColumn2());
		// 2014-05-07 修改，根据期刊缩略图名称，查询期刊对象
		PeriodicalDao pdao = new PeriodicalDao();
		Periodical period = pdao
				.findPeriodicalByCoverThunbname(this.periodicalcoverthumb
						.trim());

		// 直接把得到的 两个值传递给下一个页面 comment_add.jsp。
		// 因为增加一个 comment ，需要Topic、Periodical id
		request.put("topicThunbname", this.topicThunbname);
		request.put("periodicalcoverthumb", this.periodicalcoverthumb);

		// 2014-05-07
		// 如果该杂志评论已经填写过，那么查询杂志表，并把查询到的结果传递到到一个页面。
		// 下一个页面显示修改
		// 在 comment 数据库表中，根据 topicID peridicalID 两个字段查询出唯一的一个图片评论对象
		CommentDao cmmdao = new CommentDao();
		Comment comment = cmmdao.findCommentByTidAndPid(period, topic);
		System.out.println("^^^^^^^根据 topicThum 和  PeriodicalThum 查询出来的 评论对象为 :"
				+ comment);
		if (comment != null) {
			request.put("commentEntity", comment);
		}

		return "to_comment";
	}

	// 2014-04-06 从 comment_add.jsp 页面，完成增加评论功能
	public String addComment() throws Exception {
		// System.out.println("主题模块，图片评论页面 添加评论 的图片 id = " +
		// this.topicThunbname+"， 期刊 缩略图名 ="+this.periodicalcoverthumb);
		// System.out.println("评论内容： 窗帘名称 ："+comm.getColumn1()+" , 淘宝链接 :"+comm.getColumn2()+" ,评论内容 :"+comm.getComment());

		PeriodicalDao pdao = new PeriodicalDao();

		// 得到期刊封面的缩略图名称
		// 根据封面的缩略图名称，查询得到期刊 id
		Periodical peri = pdao
				.findPeriodicalByCoverThunbname(periodicalcoverthumb);
		// System.out.println(peri.getId());
		int pid = peri.getId();
		// System.out.println("主题模块，图片评论页面 添加评论 对应的期刊 picid = "+pid);

		// 201404 第二版
		// 把数据插入到 comment 数据库表中，
		CommentDao cdao = new CommentDao();
		comm.setStatu(new Integer(1)); // 状态，默认 1
		// 窗帘名称 :+comm.getColumn1()
		// 淘宝链接 :+comm.getColumn2()
		// 评论内容 :+comm.getComment()
		comm.setTime(TimeTools.getString2Time(TimeTools.getCurrentTime())); // 时间
		comm.setPeriodical(peri); // 当前图片属于哪个期刊
		comm.setTopic(cdao.findTopicByThumbname(topicThunbname)); // 当前图片属于哪张图片
		cdao.saveComment(comm);

		// 201404 第二版
		// 返回 topic.jsp 页面，取出这个页面需要的数据

		// 201404 第二版
		// 返回特殊标记，表明哪些 图片已经修改过(用赞的数字表示)
		// 按照id 给 图片 topic 表中 column3 列 +1
		pdao.getTopicByThunbname(topicThunbname);

		// ///////////供点赞后返回页面，显示更新结果使用/////////////////
		// 根据期刊 id ，查询得到这一期所有的 外键为 periodicalID 的主题缩略图
		List list = pdao.findAllTopicByPid(pid);
		request.put("alltopics", list);
		request.put("cm1", pid);

		// 11-30 晚上增加
		// 根据 id 查询得到一个 期刊对象，前端无法得到 pid，通过 coverpagethunbname 间接得到
		request.put("periodicalcoverthumb", periodicalcoverthumb);

		return "add_ok";
	}

	// 2014-05-07 从 comment_add.jsp 页面，完成 修改 评论功能
	public String editComment() throws Exception {
		 System.out.println("主题模块，图片评论页面 添加评论 的图片 id = " +
		 this.topicThunbname+"， 期刊 缩略图名 ="+this.periodicalcoverthumb+",id ="+this.getEditcommentid());
		 System.out.println("评论内容： 窗帘名称 ："+comm.getColumn1()+" , 淘宝链接 :"+comm.getColumn2()+" ,评论内容 :"+comm.getComment());
		 
		PeriodicalDao pdao = new PeriodicalDao();

		// 得到期刊封面的缩略图名称
		// 根据封面的缩略图名称，查询得到期刊 id
		Periodical peri = pdao
				.findPeriodicalByCoverThunbname(periodicalcoverthumb);
		// System.out.println(peri.getId());
		int pid = peri.getId();
		// System.out.println("主题模块，图片评论页面 添加评论 对应的期刊 picid = "+pid);

		// 201405 第二版
		// 把数据插入到 comment 数据库表中，
		CommentDao dao = new CommentDao();
		Comment entity = dao.findCommentById(this.getEditcommentid().trim());
		entity.setColumn1(comm.getColumn1());
		entity.setColumn2(comm.getColumn2());
		entity.setComment(comm.getComment());
		entity.setTime(TimeTools.getString2Time(TimeTools.getCurrentTime()));
		dao.update(entity);
		
		// 201404 第二版
		// 返回 topic.jsp 页面，取出这个页面需要的数据

		// 201404 第二版
		// 返回特殊标记，表明哪些 图片已经修改过(用赞的数字表示)
		// 按照id 给 图片 topic 表中 column3 列 +1
		//pdao.getTopicByThunbname(topicThunbname);

		// ///////////供点赞后返回页面，显示更新结果使用/////////////////
		// 根据期刊 id ，查询得到这一期所有的 外键为 periodicalID 的主题缩略图
		List list = pdao.findAllTopicByPid(pid);
		request.put("alltopics", list);
		request.put("cm1", pid);

		// 11-30 晚上增加
		// 根据 id 查询得到一个 期刊对象，前端无法得到 pid，通过 coverpagethunbname 间接得到
		request.put("periodicalcoverthumb", periodicalcoverthumb);

		return "add_ok";
	}

	// /////////////////////////////////////////////////////////////////////
	// 存储图片
	private void copy(File src, File dst) {
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

	/**
	 * 为 ipad 生成 html
	 * 
	 * @param txt
	 * @param imageFileName
	 */
	private void copyImageHtmlForiPad(File txt, String imageFileName) {
		try {
			FileOutputStream fos = null;
			try {
				String str = "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div type=\"image\" style=\"z-index:1;position:absolute;top:0px;left:0px\"><img class=\"img\" width=\"768\" height=\"1024\" src=\"resource\\"
						+ imageFileName + "\" /></div></body></html>";
				if (!txt.exists()) {
					txt.createNewFile();
				}
				byte bytes[] = new byte[1000];
				bytes = str.getBytes(); // 新加的
				int b = str.length(); // 改
				fos = new FileOutputStream(txt);
				fos.write(bytes, 0, b);
				fos.close();
			} finally {
				if (null != fos) {
					fos.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 为 iphone 4 4s
	 * 
	 * @param txt
	 * @param imageFileName
	 */
	private void copyImageHtmlFor4s(File txt, String imageFileName) {
		try {
			FileOutputStream fos = null;
			try {
				String str = "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div type=\"image\" style=\"z-index:1;position:absolute;top:0px;left:0px\"><img class=\"img\" width=\"320\" height=\"480\" src=\"resource\\"
						+ imageFileName + "\" /></div></body></html>";
				if (!txt.exists()) {
					txt.createNewFile();
				}
				byte bytes[] = new byte[1000];
				bytes = str.getBytes(); // 新加的
				int b = str.length(); // 改
				fos = new FileOutputStream(txt);
				fos.write(bytes, 0, b);
				fos.close();
			} finally {
				if (null != fos) {
					fos.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 为 iphone 5 或者 5s 创建 html
	 * 
	 * @param txt
	 * @param imageFileName
	 */
	private void copyImageHtmlFor5s(File txt, String imageFileName) {
		try {
			FileOutputStream fos = null;
			try {
				String str = "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><div type=\"image\" style=\"z-index:1;position:absolute;top:0px;left:0px\"><img class=\"img\" width=\"320\" height=\"568\" src=\"resource\\"
						+ imageFileName + "\" /></div></body></html>";
				if (!txt.exists()) {
					txt.createNewFile();
				}
				byte bytes[] = new byte[1000];
				bytes = str.getBytes(); // 新加的
				int b = str.length(); // 改
				fos = new FileOutputStream(txt);
				fos.write(bytes, 0, b);
				fos.close();
			} finally {
				if (null != fos) {
					fos.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		String name = fileName.substring(pos);
		System.out.println("getExtention pos =" + pos + ", name = " + name);
		return ".jpg";
	}

	// //////////////////////////////////////////////////////////

	public Magazine getMagazine() {
		return magazine;
	}

	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public String getFile1FileName() {
		return file1FileName;
	}

	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}

	public String getFile1ContentType() {
		return file1ContentType;
	}

	public void setFile1ContentType(String file1ContentType) {
		this.file1ContentType = file1ContentType;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public String getCm1() {
		return cm1;
	}

	public void setCm1(String cm1) {
		this.cm1 = cm1;
	}

	public String getPmonth() {
		return pmonth;
	}

	public void setPmonth(String pmonth) {
		this.pmonth = pmonth;
	}

	public List<String> getContentType() {
		return contentType;
	}

	public void setContentType(List<String> contentType) {
		this.contentType = contentType;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	public List<String> getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(List<String> imageFileName) {
		this.imageFileName = imageFileName;
	}

	public List<File> getImages() {
		return images;
	}

	public void setImages(List<File> images) {
		this.images = images;
	}

	public String getPernum() {
		return pernum;
	}

	public void setPernum(String pernum) {
		this.pernum = pernum;
	}

	public String getTopicThunbname() {
		return topicThunbname;
	}

	public void setTopicThunbname(String topicThunbname) {
		this.topicThunbname = topicThunbname;
	}

	public String getPeriodicalcoverthumb() {
		return periodicalcoverthumb;
	}

	public void setPeriodicalcoverthumb(String periodicalcoverthumb) {
		this.periodicalcoverthumb = periodicalcoverthumb;
	}

	public String getYearmonth() {
		return yearmonth;
	}

	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}

	public Comment getComm() {
		return comm;
	}

	public void setComm(Comment comm) {
		this.comm = comm;
	}

	public String getEditcommentid() {
		return editcommentid;
	}

	public void setEditcommentid(String editcommentid) {
		this.editcommentid = editcommentid;
	}

	
	
}
