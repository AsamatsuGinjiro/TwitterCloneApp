package model;

/**----------------------------------------------------------------------*
 *■■■UserDtoクラス■■■
 *概要：DTO（「users」テーブル）
 *----------------------------------------------------------------------**/
public class UserDto {
	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
    private String id;             //ユーザーID(@から始まるやつ)
    private String email;          //メールアドレス
    private String password;       //パスワード
    private String username;       //ユーザー名(表示名)
    private String profile_image;  //プロフィールイメージ画像
	private String bio;            //自己紹介文
    
   
	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------
    //ユーザーIDのgetter/setter
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
    //メールアドレスのgetter/setter
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
    //パスワードのgetter/setter
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	//ユーザー名(表示名)のgetter/setter
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	
	//プロフィールイメージ画像のgetter/setter
    public String getProfile_image() {return profile_image;}
    public void setProfile_image(String profile_image) {this.profile_image = profile_image;}
	
	//自己紹介文のgetter/setter
	public String getBio() {return bio;}
	public void setBio(String bio) {this.bio = bio;}

}
