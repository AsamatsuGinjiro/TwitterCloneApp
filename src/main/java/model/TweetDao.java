package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**----------------------------------------------------------------------*
 *■■■TweetDaoクラス■■■
 *概要：DAO（「tweets」テーブル）
 *----------------------------------------------------------------------**/
public class TweetDao {
	//-------------------------------------------
	//データベースへの接続情報
	//-------------------------------------------

	//JDBCドライバの相対パス
	//※バージョンによって変わる可能性があります（MySQL5系の場合は「com.mysql.jdbc.Driver」）
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	//接続先のデータベース
	//※データベース名が「test_db」でない場合は該当の箇所を変更してください
	String JDBC_URL    = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	//接続するユーザー名
	//※ユーザー名が「test_user」でない場合は該当の箇所を変更してください
	String USER_ID     = "test_user";

	//接続するユーザーのパスワード
	//※パスワードが「test_pass」でない場合は該当の箇所を変更してください
	String USER_PASS   = "test_pass";
	
	//----------------------------------------------------------------
	//メソッド
	//----------------------------------------------------------------
	/**----------------------------------------------------------------------*
	 *■getTimelineTweetsメソッド
	 *概要　：userのがフォロー中のuserのtweetsを表示
	 *引数　：対象のアンケートデータ（UserDto型）
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/
	
    public List<TweetDto> getTimelineTweets(UserDto user) {
    	//-------------------------------------------
    	//JDBCドライバのロード
    	//-------------------------------------------
    	try {
    		Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
		//-------------------------------------------
		//SQL発行
		//-------------------------------------------
		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
		
		List<TweetDto> timelineTweets = new ArrayList<>();

		//実行結果（真：成功、偽：例外発生）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		boolean isSuccess = true ;
        // ユーザーとそのフォロー中のユーザーの投稿をデータベースから取得する処理を実装
        // クエリを組み立ててデータベースから投稿を取得する
        // ユーザーの投稿を含めたタイムラインの投稿を返す
        // データベース処理などを実行し、結果を返す
        // 例: SELECT * FROM tweets WHERE user_id IN (SELECT followed_user_id FROM follows WHERE follower_user_id = :userId)
        // データベースから取得した結果をTweetオブジェクトに変換してリストに詰めて返す
		
		return timelineTweets;
    }
    
    public void createTweet(UserDto user, String content) {
        // 新しい投稿をデータベースに保存する処理を実装
        // クエリを組み立ててデータベースに投稿を保存する
        // ユーザーと投稿内容をデータベースに挿入する
        // データベース処理などを実行
        // 例: INSERT INTO tweets (user_id, content) VALUES (:userId, :content)
    }

    public List<TweetDto> getUserTweets(UserDto user) {
        // ユーザーの投稿をデータベースから取得する処理を実装
        // クエリを組み立ててデータベースから投稿を取得する
        // ユーザーに関連する投稿を返す
        // データベース処理などを実行し、結果を返す
        // 例: SELECT * FROM tweets WHERE user_id = :userId
        // データベースから取得した結果をTweetオブジェクトに変換してリストに詰めて返す
    	List<TweetDto> tweet = new ArrayList<TweetDto>();
    	return tweet; 
    }

    public void deleteTweet(TweetDto tweet) {
        // 投稿を削除する処理を実装
        // クエリを組み立ててデータベースから投稿を削除する
        // 指定された投稿をデータベースから削除する
        // データベース処理などを実行
        // 例: DELETE FROM tweets WHERE tweet_id = :tweetId
    }
}
