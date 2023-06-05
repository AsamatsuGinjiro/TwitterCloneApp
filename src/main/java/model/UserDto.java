package model;

/**----------------------------------------------------------------------*
 *■■■UserDtoクラス■■■
 *概要：DTO（「users」テーブル）
 *----------------------------------------------------------------------**/
public class UserDto {
	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
    private int    id;        //ユーザーID(@から始まるやつ)
    private String email;     //メールアドレス
    private String password;  //パスワード
    private String username;  //ユーザー名(表示名)
    private String bio;       //自己紹介文
    
   
	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------
    //ユーザーIDのgetter/setter
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
    //メールアドレスのgetter/setter
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
    //パスワードのgetter/setter
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	//ユーザー名(表示名)のgetter/setter
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	//自己紹介文のgetter/setter
	public String getBio() {return bio;}
	public void setBio(String bio) {this.bio = bio;}

}
