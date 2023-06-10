package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HomeBL;
import model.TweetDto;
import model.UserDto;
import model.followDto;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Home() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");

		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserDto     userInfoOnSession = (UserDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態によって表示画面を振り分ける
		if (userInfoOnSession != null) {
			//ログイン済：ホームを出力
			
			HomeBL logic                = new HomeBL();
			
			//「follows」テーブルの情報を抽出
			List<followDto> follow_list = new ArrayList<followDto>();
			follow_list                 = logic.getfollowingList(userInfoOnSession.getId());
			
			//「tweets」テーブルのデータを抽出
			List<TweetDto> tweet_list   = new ArrayList<TweetDto>();
			for(int i=0; i<follow_list.size(); i++) {
				tweet_list              = logic.getTimelineTweets(follow_list.get(i).getFollower_id(),follow_list.get(i).getFollowing_id());
			}

			//tweetsの抽出結果をリクエストスコープに保存
			request.setAttribute( "TWEET_LIST" , tweet_list );
			request.setAttribute( "FOLLOW_LIST" , follow_list );
			request.setAttribute( "USER_NUMBER" , userInfoOnSession.getId() );
			


			//Viewにフォワード（フォワード先：home.jsp）
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/home.jsp");
			dispatch.forward(request, response);

		} else {
			//未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
