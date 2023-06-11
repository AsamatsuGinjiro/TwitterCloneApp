package model;

public class CreateTweetBL {
	/**----------------------------------------------------------------------*
	 *■createTweetメソッド
	 *概要　：TweetDaoに値を渡し投稿情報をデータベースに登録する
	 *引数　：user,content
	 *戻り値：真/偽（ツイート作成が成功したかどうかをbooleanで表現）
	 *----------------------------------------------------------------------**/
	public boolean createTweet(int CurrentUser, String content){
		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------
		//TweetDaoクラスをインスタンス化&フォロー状況を取得するよう依頼
		TweetDao tweet_dao   = new TweetDao();
		boolean succesInsert = tweet_dao.createTweet(CurrentUser, content);
		
		return succesInsert;
	}
}
