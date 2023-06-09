package model;

import java.time.LocalDateTime;

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
     private UserDao user;             //投稿ユーザー
     private LocalDateTime timestamp;  //投稿時間
     
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
 	 public UserDao getUser() {return user;}
 	 public void setUser(UserDao user) {this.user = user;}
 	 
 	 //投稿時間のgetter/setter
 	 public LocalDateTime getTimestamp() {return timestamp;}
 	 public void setTimestamp(LocalDateTime timestamp) {this.timestamp = timestamp;}
}