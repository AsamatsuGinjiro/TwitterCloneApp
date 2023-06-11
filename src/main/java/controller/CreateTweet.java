package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CreateTweetBL;
import model.UserDto;

public class CreateTweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateTweet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定
		
		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserDto userInfoOnSession = (UserDto)session.getAttribute("LOGIN_INFO");
		
		//ログイン状態によって表示画面を振り分ける
		if (userInfoOnSession != null) {
			//ログイン済：登録処理＆結果画面への遷移を実施
			boolean succesFlg = true;  //成功フラグ（true:成功/false:失敗）
			
			//パラメータのバリデーションチェック
			if( !(validatePrmContent(request.getParameter("content")))) {
				//バリデーションNGの場合
				succesFlg = false ;
			}else {
				//バリデーションOKの場合
				//リクエストパラメータを取得
				String content     = request.getParameter("content");  //リクエストパラメータ（content）
				
				//アンケートデータをDBに登録
				CreateTweetBL logic = new CreateTweetBL();
				succesFlg          = logic.createTweet(userInfoOnSession.getId(), content);  //成功フラグ（true:成功/false:失敗）
			}
			//成功/失敗に応じて表示させる画面を振り分ける
			if (succesFlg) {
				response.sendRedirect("Home");
			} else {
				//Viewにフォワード（フォワード先：show_survey_by_satisfaction_level.jsp）
				RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/create_tweet.jsp");
				dispatch.forward(request, response);
			}
		}	
		else {
			//未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
		}
	}
	
	/**----------------------------------------------------------------------*
	 *■■■validatePrmContentクラス■■■
	 *概要：バリデーションチェック
	 *詳細：入力値（ツイート）の検証を行う
	 *----------------------------------------------------------------------**/
	private boolean validatePrmContent(String pr ) {

		boolean validateResult = true ;

		//入力値がnullまたは空白の場合はエラーとする
		if( pr == null || pr.equals("") ) {
			validateResult = false ;
		}
		return validateResult ;
	}
}

