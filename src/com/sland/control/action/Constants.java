package com.sland.control.action;

//所有跳转路径在这里定义
public final class Constants {
	// 菜单项有关的跳转路径
	public static final String HOMEPAGE = "index_page"; // 首页
	public static final String MAGAZINE = "magazine_page"; // 杂志管理页面
	public static final String PERIODICAL = "periodical_page"; // 期刊管理页面
	public static final String TOPICPAGE = "topic_page"; // 主题管理页面

	public static final String REGISTER_USER = "register_page"; // 注册用户页面
	public static final String MODIFY_PASSWORD = "modify_pwd"; // 修改密码

	public static String separator = System.getProperty("file.separator"); // 文件分割符号
	/**
	 * 获取当前项目中的  webapps/resources 目录
	 * @return
	 */
	public static String getResourcesPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir; // 当前tomcat中 webapps/resources 目录

		nowpath = System.getProperty("user.dir");     // 默认获取 当前tomcat的bin目录的路径

		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面

		if ("\\".equals(separator)) {
			System.out.println("Window 系统");
			tempdir += "\\resources"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		} else if ("/".equals(separator)) {
			System.out.println("Mac OS 系统");
			tempdir += "/resources"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		}

		return tempdir;
	}

	// --- 登录相关路径 ---
	public static final String LOGIN_OK = "login_ok";

	/**
	 * <p>
	 * 用户名或密码错误
	 * <p>
	 */
	public static final String PASSWORD_MISMATCH_FIELD = "login_error";

	// --- Tokens ----

	/**
	 * <p>
	 * The token representing a "cancel" request.
	 * </p>
	 */
	public static final String CANCEL = "cancel";

	/**
	 * <p>
	 * The token representing a "create" task.
	 * </p>
	 */
	public static final String CREATE = "Create";

	/**
	 * <p>
	 * The application scope attribute under which our user database is stored.
	 * </p>
	 */
	public static final String DATABASE_KEY = "database";

	/**
	 * <p>
	 * The token representing a "edit" task.
	 * </p>
	 */
	public static final String DELETE = "Delete";

	/**
	 * <p>
	 * The token representing a "edit" task.
	 * </p>
	 */
	public static final String EDIT = "Edit";

	/**
	 * <p>
	 * The package name for this application.
	 * </p>
	 */
	public static final String PACKAGE = "com.demo.control.action";

	/**
	 * <p>
	 * The session scope attribute under which the Subscription object currently
	 * selected by our logged-in User is stored.
	 * </p>
	 */
	public static final String SUBSCRIPTION_KEY = "subscription";

	/**
	 * <p>
	 * The session scope attribute under which the User object for the currently
	 * logged in user is stored.
	 * </p>
	 */
	public static final String USER_KEY = "user";

	/**
	 * <p>
	 * The token representing the "Host" property.
	 */
	public static final String HOST = "host";

	// ---- Error Messages ----

	/**
	 * <p>
	 * A static message in case message resource is not loaded.
	 * </p>
	 */
	public static final String ERROR_MESSAGES_NOT_LOADED = "ERROR:  没有登录 -- 查看后台错误日志信息.";

	/**
	 * <p>
	 * A static message in case database resource is not loaded.
	 * <p>
	 */
	public static final String ERROR_DATABASE_NOT_LOADED = "ERROR:  数据库没有连接成功 -- 查看后台错误日志信息.";

	/**
	 * <p>
	 * A standard key from the message resources file, to test if it is
	 * available.
	 * <p>
	 */
	public static final String ERROR_DATABASE_MISSING = "error.database.missing";

	/**
	 * <P>
	 * A "magic" username to trigger an ExpiredPasswordException for testing.
	 * </p>
	 */
	public static final String EXPIRED_PASSWORD_EXCEPTION = "ExpiredPasswordException";

	// ---- Log Messages ----

	/**
	 * <p>
	 * Message to log if saving a user fails.
	 * </p>
	 */
	public static final String LOG_DATABASE_SAVE_ERROR = " Unexpected error when saving User: ";

}
