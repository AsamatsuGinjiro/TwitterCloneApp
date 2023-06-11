package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	//接続先のデータベース
	String JDBC_URL    = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";

	//接続するユーザー名
	String USER_ID     = "test_user";

	//接続するユーザーのパスワード
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
	
    public List<TweetDto> getTimelineTweets(int CurrentUser,int FollowingUser) {
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
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet         rs = null; // ResultSet（SQL抽出結果）格納用変数
		
		// 抽出結果格納用DTOリスト
		List<TweetDto> timelineTweets = new ArrayList<>();

		try {

			// -------------------------------------------
			// 接続の確立（Connectionオブジェクトの取得）
			// -------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			// -------------------------------------------
			// SQL文の送信 ＆ 結果の取得
			// -------------------------------------------

			// 発行するSQL文の生成（SELECT）
			// カレントユーザーのHome画面に表示するツイートを取得
			StringBuffer buf = new StringBuffer();
			buf.append("   SELECT t.id ,t.content ,t.user_id ,t.created_at ,u.email,u.password ,u.name ,u.profile_image ");
			buf.append("     FROM tweets t inner join users u on t.user_id=u.id                                         ");
			buf.append("    WHERE user_id = ?                                                                           ");  //第1パラメータ
			buf.append("       OR user_id = ?                                                                           ");  //第2パラメータ
			buf.append(" ORDER BY created_at;                                                                           ");
			
			ps = con.prepareStatement(buf.toString());
			
			//パラメータをセット
			ps.setInt( 1, CurrentUser   );  //第1パラメータ：CurrentUser（自分）
			ps.setInt( 2, FollowingUser );  //第2パラメータ：FollowingUser（フォローしているユーザー）
			
			rs = ps.executeQuery();

			// ResultSetオブジェクトからDTOリストに格納
			while (rs.next()) {
				TweetDto tweet = new TweetDto();
				tweet.setId(rs.getInt("t.id"));
				tweet.setContent(rs.getString("t.content"));
				tweet.setUser(rs.getInt("t.user_id"));
				tweet.setTime(rs.getTimestamp("t.created_at"));
				tweet.setUser_name(rs.getString("u.name"));
				timelineTweets.add(tweet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// -------------------------------------------
			// 接続の解除
			// -------------------------------------------

			// ResultSetオブジェクトの接続解除
			if (rs != null) { // 接続が確認できている場合のみ実施
				try {
					rs.close(); // 接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// PreparedStatementオブジェクトの接続解除
			if (ps != null) { // 接続が確認できている場合のみ実施
				try {
					ps.close(); // 接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// Connectionオブジェクトの接続解除
			if (con != null) { // 接続が確認できている場合のみ実施
				try {
					con.close(); // 接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 抽出結果を返す
		return timelineTweets;
		
    }
    
    public void createTweet(UserDto user, String content) {
        // 新しい投稿をデータベースに保存する処理を実装
        // クエリを組み立ててデータベースに投稿を保存する
        // ユーザーと投稿内容をデータベースに挿入する
        // データベース処理などを実行
        // INSERT INTO tweets (user_id, content) VALUES (:userId, :content)
    }

    public List<TweetDto> getUserTweets(UserDto user) {
        // ユーザーの投稿をデータベースから取得する処理を実装
        // クエリを組み立ててデータベースから投稿を取得する
        // ユーザーに関連する投稿を返す
        // データベース処理などを実行し、結果を返す
        // SELECT * FROM tweets WHERE user_id = :userId
        // データベースから取得した結果をTweetオブジェクトに変換してリストに詰めて返す
    	List<TweetDto> tweet = new ArrayList<TweetDto>();
    	return tweet; 
    }

    public void deleteTweet(TweetDto tweet) {
        // 投稿を削除する処理を実装
        // クエリを組み立ててデータベースから投稿を削除する
        // 指定された投稿をデータベースから削除する
        // データベース処理などを実行
        // DELETE FROM tweets WHERE tweet_id = :tweetId
    }
}
