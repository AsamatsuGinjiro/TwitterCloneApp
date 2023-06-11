<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"      %>
<%@ page import="model.TweetDto"      %>
<%@ page import="model.followDto"     %>
<%@ page import="model.UserDto"       %>



<%--
-------------------------------------------------------------------------------------------------
■■■ファイル名：home.jsp■■■
概要：JSP
詳細：HTML文書（タイムライン）を出力する。
-------------------------------------------------------------------------------------------------
--%>

<%
//「tweets」テーブルから抽出したデータを受け取る
List<TweetDto> tweet_list = (List<TweetDto>)request.getAttribute("TWEET_LIST");
List<followDto> follow_list = (List<followDto>)request.getAttribute("FOLLOW_LIST");
int current_user = (int)request.getAttribute("USER_NUMBER");
%>


<html>
  <head>
    <title>タイムライン</title>
  </head>
  <body>
    <textarea name="TWEET" rows="4" cols="50" maxlength = "250" id="ID_TWEET"></textarea>
    <input type="submit" value="ツイートする" id="ID_SUBMIT">
    <br>
<%
    for (int i = 0; i < tweet_list.size(); i++) {
    	TweetDto tweet_dto = tweet_list.get(i);
%>    <article>
        <div>
          <span><%= tweet_dto.getUser_name() %></span>
          <span>@<%= tweet_dto.getUser() %></span>
          <span><%= tweet_dto.getTime() %></span>
        </div>
        <br>
        <div>
          <p><%= tweet_dto.getContent() %> <p>
        </div>
        <p>---------------------------------</p>
      </article>
      <br>
<%
    }
%>
    <br>
    <a href="<%=request.getContextPath()%>/DirectMessage">DM</a>
    <br>
    <a href="<%=request.getContextPath()%>/Logout">ログアウト</a>
  </body>
</html>

<%!
/**----------------------------------------------------------------------*
 *■■■replaceEscapeCharクラス■■■
 *概要：文字列データのエスケープを行う
 *----------------------------------------------------------------------**/
String replaceEscapeChar(String inputText) {

	String charAfterEscape = inputText ; //エスケープ後の文字列データ

	// 「&」を変換
	charAfterEscape = charAfterEscape.replace("&", "&amp;");
	// 「<」を変換
	charAfterEscape = charAfterEscape.replace("<", "&lt;");
	// 「>」を変換
	charAfterEscape = charAfterEscape.replace(">", "&gt;");
	// 「"」を変換
	charAfterEscape = charAfterEscape.replace("\"", "&quot;");
	// 「'」を変換
	charAfterEscape = charAfterEscape.replace("'", "&#039;");

	return charAfterEscape;
}
%>