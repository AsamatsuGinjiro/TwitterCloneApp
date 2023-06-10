package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class followDao {
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
	 *■getFollowedUserIdsメソッド
	 *概要　：フォローしているユーザーの情報を抽出
	 *引数　：対象のアンケートデータ（UserDto型）
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/
	public List<followDto> getFollowedUserIds(int CurrentUserId) {
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
		ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数
		
		List<followDto> followingUserIds = new ArrayList<>();	//抽出データ（UserDto型）格納用変数
		
		try {
			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（SELECT）
			//followsテーブルからカレントユーザーのフォローを抽出
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT id,             ");
			buf.append("        follower_id,    ");
			buf.append("        following_id    ");
			buf.append("   FROM follows         ");
			buf.append("  WHERE follower_id =?; ");
			
			//PreparedStatementを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());
			//パラメータをセット
			ps.setInt( 1,  CurrentUserId);  //第1パラメータ：カレントユーザーID（セッション入力）
			//SQL文の送信＆戻り値としてResultSetを取得
			rs = ps.executeQuery();
			
			// ResultSetオブジェクトからDTOリストに格納
			while (rs.next()) {
				followDto dto = new followDto();
				dto.setId(rs.getInt("id"));
				dto.setFollower_id(rs.getInt("follower_id"));
				dto.setFollowing_id(rs.getInt("following_id "));
				followingUserIds.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			//-------------------------------------------
			//接続の解除
			//-------------------------------------------
	
			//ResultSetオブジェクトの接続解除
			if (rs != null) {    //接続が確認できている場合のみ実施
				try {
					rs.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	
			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return followingUserIds;
	}
}

