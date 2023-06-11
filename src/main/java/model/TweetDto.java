package model;

import java.sql.Timestamp;

/**----------------------------------------------------------------------*
 *■■■TweetDtoクラス■■■
 *概要：DTO（「tweets」テーブル）
 *----------------------------------------------------------------------**/
public class TweetDto {
	
	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	 private int id;                   //投稿ID（AUTO_INCREMENT）
     private String content;           //投稿本文
     private int user;                 //投稿ユーザー
     private Timestamp time;           //投稿時間
     private String user_name;         //ユーザー名
     

	//----------------------------------------------------------------
 	//getter/setter
 	//----------------------------------------------------------------
     //投稿IDのgetter/setter
 	 public int getId() {return id;}
 	 public void setId(int id) {this.id = id;}
 	 
 	 //投稿本文のgetter/setter
 	 public String getContent() {return content;}
 	 public void setContent(String content) {this.content = content;}
 	 
 	 //投稿ユーザーのgetter/setter
 	 public int getUser() {return user;}
 	 public void setUser(int user) {this.user = user;}
 	 
 	 //投稿時間のgetter/setter
 	 public Timestamp getTime() {return time;}
 	 public void setTime(Timestamp timestamp) {this.time = timestamp;}
 	 
 	 //ユーザー名のgetter/setter
 	 public String getUser_name() {return user_name;}
 	 public void setUser_name(String user_name) {this.user_name = user_name;}
}