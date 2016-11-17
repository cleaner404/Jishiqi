package com.example.jishiqi;
/*
 *	防止切换时，暂停计时，存储数据
 */
public class SaveRun {
static boolean isjishi=false;
static boolean isdaojishi=false;
public static boolean getisjishi(){
	return isjishi;
}
public static boolean getisdaojishi(){
	return isdaojishi;
}
public  static void setisdaojishi(boolean a){
	isdaojishi=a;
}
public  static void setisjishi(boolean a){
	isjishi=a;
}
}
