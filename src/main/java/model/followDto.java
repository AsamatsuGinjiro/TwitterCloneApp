package model;

/**----------------------------------------------------------------------*
 *■■■followDtoクラス■■■
 *概要：DTO（「follows」テーブル）
 *----------------------------------------------------------------------**/
public class followDto {
	
	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private int id;
	private int follower_id;
	private int following_id;
	
 	//----------------------------------------------------------------
 	//getter/setter
 	//----------------------------------------------------------------
	//IDのgetter/setter
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	//フォロワーのgetter/setter
	public int getFollower_id() {return follower_id;}
	public void setFollower_id(int follower_id) {this.follower_id = follower_id;}
	
	//フォローのgetter/setter
	public int getFollowing_id() {return following_id;}
	public void setFollowing_id(int following_id) {this.following_id = following_id;}
}
