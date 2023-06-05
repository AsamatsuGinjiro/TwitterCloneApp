package model;

import java.util.Date;

/**----------------------------------------------------------------------*
 *■■■DirectMessageDtoクラス■■■
 *概要：DTO（「direct_messages」テーブル）
 *----------------------------------------------------------------------**/
public class DirectMessageDto {
	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
    private int     messageId;  //メッセージID（AUTO_INCREMENT）
    private UserDto sender;     //送信者User
    private UserDto recipient;  //受信者User
    private String  message;    //メッセージ本文
    private Date    timestamp;  //送信日時
    
	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------
    //メッセージIDのgetter/setter
	public int getMessageId() {return messageId;}
	public void setMessageId(int messageId) {this.messageId = messageId;}
	
    //送信者Userのgetter/setter
	public UserDto getSender() {return sender;}
	public void setSender(UserDto sender) {this.sender = sender;}

    //受信者Userのgetter/setter
	public UserDto getRecipient() {return recipient;}
	public void setRecipient(UserDto recipient) {this.recipient = recipient;}

    //メッセージ本文のgetter/setter
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}

    //送信日時のgetter/setter
	public Date getTimestamp() {return timestamp;}
	public void setTimestamp(Date timestamp) {this.timestamp = timestamp;}

}
