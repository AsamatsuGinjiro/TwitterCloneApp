package model;

import java.util.List;

public class HomeBL {
	/**----------------------------------------------------------------------*
	 *■getTimelineTweetsメソッド
	 *概要　：タイムラインを抽出する
	 *引数　：CurrentUser,FollowingUser
	 *戻り値：抽出結果（TweetDtoリスト）
	 *----------------------------------------------------------------------**/
	public List<TweetDto> getTimelineTweets(int CurrentUser,int FollowingUser) {	
		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------
		//TweetDaoクラスをインスタンス化＆ツイートを取得するよう依頼
		TweetDao tweet_dao = new TweetDao();
		List<TweetDto> dtoList= tweet_dao.getTimelineTweets(CurrentUser,FollowingUser);
		
		return dtoList;
	}
	
	/**----------------------------------------------------------------------*
	 *■getfollowListメソッド
	 *概要　：カレントユーザーのフォローリストを抽出する
	 *引数　：CurrentUser
	 *戻り値：抽出結果（followDtoリスト）
	 *----------------------------------------------------------------------**/
	public List<followDto> getfollowingList(int CurrentUser){
		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------
		//followDaoクラスをインスタンス化&フォロー状況を取得するよう依頼
		followDao follow_dao = new followDao();
		List<followDto> dtoList = follow_dao.getFollowedUserIds(CurrentUser);
		
		return dtoList;
	}
	

}
