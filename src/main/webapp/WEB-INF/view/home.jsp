<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"      %>
<%@ page import="model.TweetDto"      %>
<%@ page import="model.followDto"      %>


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
    <!-- テスト用 -->
    <!-- リストが空になっており、データ抽出がうまくいっていない -->
    <div>
      <p>
        <%=follow_list %>
      </p>
      <p>
        <%=tweet_list %>
      </p>
      <p>
        <%=current_user %>
      </p>
    </div>
    <!-- テスト用 -->
<%
    for (int i = 0; i < tweet_list.size(); i++) {
    	TweetDto dto = tweet_list.get(i);
%>    <div>
        <div>
          <span><%= dto.getUser().getUsername() %></span>
          <span><%= dto.getUser().getId() %></span>
          <span><%= dto.getTime() %></span>
        </div>
        <br>
        <div>
          <td><%= dto.getContent() %></td>
        </div>
      </div>
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