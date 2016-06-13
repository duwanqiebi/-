package com.zhangqiang.sqgl.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;

public class DateUtil {
	
	private static Logger logger = Logger.getLogger(DateUtil.class);  
    private static String defaultDatePattern = null;  
    private static String timePattern = "HH:mm";  
    private static Calendar cale = Calendar.getInstance();  
   // public static final String TS_FORMAT = DateUtil.getDatePattern() + " HH:mm:ss.S";  
    /** ���ڸ�ʽyyyy-MM�ַ������� */  
    private static final String MONTH_FORMAT = "yyyy-MM";  
    /** ���ڸ�ʽyyyy-MM-dd�ַ������� */  
    private static final String DATE_FORMAT = "yyyy-MM-dd";  
    /** ���ڸ�ʽHH:mm:ss�ַ������� */  
    private static final String HOUR_FORMAT = "HH:mm:ss";  
    /** ���ڸ�ʽyyyy-MM-dd HH:mm:ss�ַ������� */  
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";  
    /** ĳ�쿪ʼʱ�����ַ�������  00:00:00 */  
    private static final String DAY_BEGIN_STRING_HHMMSS = " 00:00:00";  
    /**  ĳ�����ʱ�����ַ�������  23:59:59  */  
    public static final String DAY_END_STRING_HHMMSS = " 23:59:59";  
    private static SimpleDateFormat sdf_date_format = new SimpleDateFormat(DATE_FORMAT);  
    private static SimpleDateFormat sdf_hour_format = new SimpleDateFormat(HOUR_FORMAT);  
    private static SimpleDateFormat sdf_datetime_format = new SimpleDateFormat(DATETIME_FORMAT);  
    
    /** 
     * ��÷�������ǰ���ڼ�ʱ�䣬�Ը�ʽΪ��yyyy-MM-dd HH:mm:ss�������ַ�����ʽ���� 
     */  
    public static String getDateTime() {  
        try {  
            return sdf_datetime_format.format(cale.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDateTime():" + e.getMessage());  
            return "";  
        }  
    }  
    
    /** 
     * ��÷�������ǰ���ڣ��Ը�ʽΪ��yyyy-MM-dd�������ַ�����ʽ���� 
     */  
    public static String getDate() {  
        try {  
            return sdf_date_format.format(cale.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDate():" + e.getMessage());  
            return "";  
        }  
    } 
    public static String getDate(Date date){
    	try {  
            return sdf_date_format.format(date);  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDate():" + e.getMessage());  
            return "";  
        } 
    }
    
    /** 
     * ��÷�������ǰʱ�䣬�Ը�ʽΪ��HH:mm:ss�������ַ�����ʽ���� 
     */  
    public static String getTime() {  
        String temp = " ";  
        try {  
            temp += sdf_hour_format.format(cale.getTime());  
            return temp;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getTime():" + e.getMessage());  
            return "";  
        }  
    }  
    
    /** 
     * ͳ��ʱ��ʼ���ڵ�Ĭ��ֵ  
     */  
    public static String getStartDate() {  
        try {  
            return getYear() + "-01-01";  
        } catch (Exception e) {  
            logger.debug("DateUtil.getStartDate():" + e.getMessage());  
            return "";  
        }  
    } 
    
    /** 
     * ͳ��ʱ�������ڵ�Ĭ��ֵ  
     */  
    public static String getEndDate() {  
        try {  
            return getDate();  
        } catch (Exception e) {  
            logger.debug("DateUtil.getEndDate():" + e.getMessage());  
            return "";  
        }  
    }  
    
    /** 
     * ��÷�������ǰ���ڵ���� 
     */  
    public static String getYear() {  
        try {  
            return String.valueOf(cale.get(Calendar.YEAR));  
        } catch (Exception e) {  
            logger.debug("DateUtil.getYear():" + e.getMessage());  
            return "";  
        }  
    } 
    
    /** 
     * ��÷�������ǰ���ڵ��·� 
     */  
    public static String getMonth() {  
        try {  
            java.text.DecimalFormat df = new java.text.DecimalFormat();  
            df.applyPattern("00;00");  
            return df.format((cale.get(Calendar.MONTH) + 1));  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMonth():" + e.getMessage());  
            return "";  
        }  
    } 
    
    /** 
     * ��÷������ڵ�ǰ�������� 
     */  
    public static String getDay() {  
        try {  
            return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDay():" + e.getMessage());  
            return "";  
        }  
    }  
    
    /** 
     * �Ƚ�����������������  
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int getMargin(String date1, String date2) {  
        int margin;  
        try {  
            ParsePosition pos = new ParsePosition(0);  
            ParsePosition pos1 = new ParsePosition(0);  
            Date dt1 = sdf_date_format.parse(date1, pos);  
            Date dt2 = sdf_date_format.parse(date2, pos1);  
            long l = dt1.getTime() - dt2.getTime();  
            margin = (int) (l / (24 * 60 * 60 * 1000));  
            return margin;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMargin():" + e.toString());  
            return 0;  
        }  
    }  
    
    /** 
     * �Ƚ�����������������  
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static double getDoubleMargin(String date1, String date2) {  
        double margin;  
        try {  
            ParsePosition pos = new ParsePosition(0);  
            ParsePosition pos1 = new ParsePosition(0);  
            Date dt1 = sdf_datetime_format.parse(date1, pos);  
            Date dt2 = sdf_datetime_format.parse(date2, pos1);  
            long l = dt1.getTime() - dt2.getTime();  
            margin = (l / (24 * 60 * 60 * 1000.00));  
            return margin;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMargin():" + e.toString());  
            return 0;  
        }  
    }  
    
    /** 
     * �Ƚ�����������������  
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int getMonthMargin(String date1, String date2) {  
        int margin;  
        try {  
            margin = (Integer.parseInt(date2.substring(0, 4)) - Integer.parseInt(date1.substring(0, 4))) * 12;  
            margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0",  
                    "-")) - Integer.parseInt(date1.substring(4, 7).replaceAll("-0", "-")));  
            return margin;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMargin():" + e.toString());  
            return 0;  
        }  
    }  
    
    /** 
     * �������ڼ�X�������� 
     * @param date 
     * @param i 
     * @return 
     */  
    public static String addDay(String date, int i) {  
        try {  
            GregorianCalendar gCal = new GregorianCalendar(  
                    Integer.parseInt(date.substring(0, 4)),   
                    Integer.parseInt(date.substring(5, 7)) - 1,   
                    Integer.parseInt(date.substring(8, 10)));  
            gCal.add(Calendar.DATE, i);  
            return sdf_date_format.format(gCal.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.addDay():" + e.toString());  
            return getDate();  
        }  
    } 
    /** 
     * �������ڼ�X�º������ 
     * @param date 
     * @param i 
     * @return 
     */  
    public static String addMonth(String date, int i) {  
        try {  
            GregorianCalendar gCal = new GregorianCalendar(  
                    Integer.parseInt(date.substring(0, 4)),   
                    Integer.parseInt(date.substring(5, 7)) - 1,   
                    Integer.parseInt(date.substring(8, 10)));  
            gCal.add(Calendar.MONTH, i);  
            return sdf_date_format.format(gCal.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.addMonth():" + e.toString());  
            return getDate();  
        }  
    }  
    
    /** 
     * �������ڼ�X�������� 
     * @param date 
     * @param i 
     * @return 
     */  
    public static String addYear(String date, int i) {  
        try {  
            GregorianCalendar gCal = new GregorianCalendar(  
                    Integer.parseInt(date.substring(0, 4)),   
                    Integer.parseInt(date.substring(5, 7)) - 1,   
                    Integer.parseInt(date.substring(8, 10)));  
            gCal.add(Calendar.YEAR, i);  
            return sdf_date_format.format(gCal.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.addYear():" + e.toString());  
            return "";  
        }  
    }  
    
    /** 
     * �������ַ�����ָ����ʽת������������ 
     * @param aMask ָ�������ڸ�ʽ����:yyyy-MM-dd 
     * @param strDate ��ת���������ַ��� 
     * @return 
     * @throws ParseException 
     */  
    public static final Date convertStringToDate(String aMask, String strDate)  throws ParseException {  
        SimpleDateFormat df = null;  
        Date date = null;  
        df = new SimpleDateFormat(aMask);  
  
        if (logger.isDebugEnabled()) {  
            logger.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");  
        }  
        try {  
            date = df.parse(strDate);  
        } catch (ParseException pe) {  
            logger.error("ParseException: " + pe);  
            throw pe;  
        }  
        return (date);  
    }  
    
    /**
     * �õ���ǰʱ���Date����
     * @throws ParseException 
     * */
    public static Date getNowDate() throws ParseException{
    	Date date =  convertStringToDate("yyyy-MM-dd",getDateTime());
    	return date;
    }
  

  
    /** 
     * ����һ��JAVA�����͵������ַ��� 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getSimpleDateFormat() {  
        SimpleDateFormat formatter = new SimpleDateFormat();  
        String NDateTime = formatter.format(new Date());  
        return NDateTime;  
    }  
    
    public static Date refFormatNowDate(long addDate) {
    	  Date date = new Date(addDate);
    	  //SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
    	  //String retStrFormatNowDate = sdFormatter.format(nowTime);
    	  return date;
    	}
    
    
	public static void main(String[] args) throws ParseException {
		//DateUtil.getNowDateTime();
		//System.out.println(DateUtil.refFormatNowDate());
		
		System.out.println(System.currentTimeMillis());
	}

}
