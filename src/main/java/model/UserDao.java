package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**----------------------------------------------------------------------*
 *■■■UserDaoクラス■■■
 *概要：DAO（「users」テーブル）
 *----------------------------------------------------------------------**/
public class UserDao {
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
	 *■doLoginメソッド
	 *概要　：引数のユーザー情報に紐づくユーザーデータを「users」テーブルから抽出する
	 *引数①：メールアドレス（ユーザー入力）
	 *引数②：ユーザーパスワード（ユーザー入力）
	 *戻り値：「users」テーブルから抽出したユーザーデータ（UserDto型）
	 *----------------------------------------------------------------------**/
	public UserDto doLogin(String inputEmail, String inputPassWord) {
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
		ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数

		//抽出データ（UserDto型）格納用変数
		UserDto dto = new UserDto();

		try {
			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（SELECT）
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append("   id,              ");
			buf.append("   email,           ");
			buf.append("   password,        ");
			buf.append("   name,            ");
			buf.append("   profile_image    ");
			buf.append(" FROM               ");
			buf.append("   USERS            ");
			buf.append(" WHERE              ");
			buf.append("   email  = ? AND   ");  //第1パラメータ
			buf.append("   password = ?     ");  //第2パラメータ

			//PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString( 1, inputEmail    );  //第1パラメータ：ユーザーID（ユーザー入力）
			ps.setString( 2, inputPassWord );  //第2パラメータ：ユーザーパスワード（ユーザー入力）

			//SQL文の送信＆戻り値としてResultSet（SQL抽出結果）を取得
			rs = ps.executeQuery();

			//--------------------------------------------------------------------------------
			//ResultSetオブジェクトからユーザーデータを抽出
			//--------------------------------------------------------------------------------
			if (rs.next()) {
				//ResultSetから1行分のレコード情報をDTOへ登録
				dto.setId(            rs.getInt(    "id"            ));    //ユーザーID
				dto.setEmail(         rs.getString(    "email"         ));    //メールアドレス
				dto.setPassword(      rs.getString(    "password"      ));    //パスワード
				dto.setUsername(      rs.getString(    "name"          ));    //ユーザー名
				dto.setProfile_image( rs.getString(    "profile_image" ));    //プロフィールイメージ
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
		return dto;
	}
}
