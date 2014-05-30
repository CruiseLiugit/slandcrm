package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeTools {

	// 得到系统当前时间
	public static Timestamp getCurrentTime() {
		Timestamp d = new Timestamp(System.currentTimeMillis());
		return d;
	}

	// Timestamp 与String类型相互转换
	// Time -> String
	public static String getString2Time(Timestamp time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 定义格式
		String str = df.format(time);
		return str;
	}

	// String -> Time
	public static Timestamp getTime2String(String str) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Timestamp ts = null;
		try {
			java.util.Date dt = df.parse(str);
			ts = new Timestamp(dt.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ts;
	}

	// java.sql.Date与java.sql.Timestamp相互转换
	// java.sql.Date--->java.sql.Timestamp
	public static Timestamp sqlDate2Time(java.sql.Date date) {
		return new java.sql.Timestamp(date.getTime());
	}

	// java.sql.Timestamp-->java.sql.Date
	public static java.sql.Date time2SqlDate(Timestamp time) {
		return new java.sql.Date(time.getTime());
	}

	// ////////////////////////////////////////////////
	/**
	 * 检查日期格式
	 * 
	 * @param date
	 * @return
	 * 
	 *         public static boolean checkDate(String date) { String eL =
	 *         "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$"
	 *         ; Pattern p = Pattern.compile(eL); Matcher m = p.matcher(date);
	 *         boolean b = m.matches(); return b; }
	 */

	/**
	 * 检查整数
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负整数 "+":正整数 "-0":非正整数 "-":负整数 "":整数
	 * @return
	 * 
	 *         public static boolean checkNumber(String num,String type){ String
	 *         eL = ""; if(type.equals("0+"))eL = "^\\d+$";//非负整数 else
	 *         if(type.equals("+"))eL = "^\\d*[1-9]\\d*$";//正整数 else
	 *         if(type.equals("-0"))eL = "^((-\\d+)|(0+))$";//非正整数 else
	 *         if(type.equals("-"))eL = "^-\\d*[1-9]\\d*$";//负整数 else eL =
	 *         "^-?\\d+$";//整数 Pattern p = Pattern.compile(eL); Matcher m =
	 *         p.matcher(num); boolean b = m.matches(); return b; }
	 */

	/**
	 * 检查浮点数
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负浮点数 "+":正浮点数 "-0":非正浮点数 "-":负浮点数 "":浮点数
	 * @return
	 * 
	 *         public static boolean checkFloat(String num,String type){ String
	 *         eL = ""; if(type.equals("0+"))eL = "^\\d+(\\.\\d+)?$";//非负浮点数
	 *         else if(type.equals("+"))eL =
	 *         "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$"
	 *         ;//正浮点数 else if(type.equals("-0"))eL =
	 *         "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";//非正浮点数 else
	 *         if(type.equals("-"))eL =
	 *         "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$"
	 *         ;//负浮点数 else eL = "^(-?\\d+)(\\.\\d+)?$";//浮点数 Pattern p =
	 *         Pattern.compile(eL); Matcher m = p.matcher(num); boolean b =
	 *         m.matches(); return b; }
	 */
	
	/*
	 一、String与Date（java.util.Date）互转
 
     1.1 String -> Date
 
Java代码  收藏代码
String dateStr = "2010/05/04 12:34:23";  
        Date date = new Date();  
        //注意format的格式要与日期String的格式相匹配  
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        try {  
            date = sdf.parse(dateStr);  
            System.out.println(date.toString());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
 
 
  1.2 Date -> String
 
   日期向字符串转换，可以设置任意的转换格式format
Java代码  收藏代码
String dateStr = "";  
        Date date = new Date();  
        //format的格式可以任意  
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH/mm/ss");  
        try {  
            dateStr = sdf.format(date);  
            System.out.println(dateStr);  
            dateStr = sdf2.format(date);  
            System.out.println(dateStr);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
 二、String与Timestamp互转
 
 
   2.1 String ->Timestamp
 
 
   使用Timestamp的valueOf()方法
Java代码  收藏代码
Timestamp ts = new Timestamp(System.currentTimeMillis());  
        String tsStr = "2011-05-09 11:49:45";  
        try {  
            ts = Timestamp.valueOf(tsStr);  
            System.out.println(ts);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
   注：String的类型必须形如： yyyy-mm-dd hh:mm:ss[.f...] 这样的格式，中括号表示可选，否则报错！！！ 
    如果String为其他格式，可考虑重新解析下字符串，再重组~~
 
    2.2 Timestamp -> String
 
  使用Timestamp的toString()方法或者借用DateFormat
 
Java代码  收藏代码
Timestamp ts = new Timestamp(System.currentTimeMillis());  
        String tsStr = "";  
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        try {  
            //方法一  
            tsStr = sdf.format(ts);  
            System.out.println(tsStr);  
            //方法二  
            tsStr = ts.toString();  
            System.out.println(tsStr);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
 很容易能够看出来，方法一的优势在于可以灵活的设置字符串的形式。
三、Date（ java.util.Date ）和Timestamp互转
 
  声明：查API可知，Date和Timesta是父子类关系
 
 
  3.1 Timestamp -> Date
 
 
Java代码  收藏代码
Timestamp ts = new Timestamp(System.currentTimeMillis());  
        Date date = new Date();  
        try {  
            date = ts;  
            System.out.println(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
 很简单，但是此刻date对象指向的实体却是一个Timestamp，即date拥有Date类的方法，但被覆盖的方法的执行实体在Timestamp中。
 
 
   3.2 Date -> Timestamp
 
   父类不能直接向子类转化，可借助中间的String~~~~
   注：使用以下方式更简洁
   Timestamp ts = new Timestamp(date.getTime());
 
	 * */
	
	
}
