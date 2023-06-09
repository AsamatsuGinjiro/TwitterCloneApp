<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="model.UserDto" %>

<%--
-------------------------------------------------------------------------------------------------
■■■ファイル名：creat_tweet.jsp■■■
概要：JSP
詳細：HTML文書（ツイート作成画面）を出力する。
-------------------------------------------------------------------------------------------------
--%>

<%
//セッションからユーザーデータを取得
UserDto userInfoOnSession = (UserDto)session.getAttribute("LOGIN_INFO");
%>

<!DOCTYPE html>
<html>
  <head>
    <title>tweet</title>
  </head>
  <body>
    <h2>投稿内容</h2>
    <form action="SaveSurvey" method="post">
      <p>tweet：<br>
        <textarea name="TWEET" rows="4" cols="50" maxlength = "250" id="ID_TWEET"></textarea>
      </p>
      <input type="submit" value="ツイートする" id="ID_SUBMIT">
    </form>
    <script type="text/javascript" src="js/input_survey.js"></script>
    <br>
    <a href="ShowAllSurvey">回答一覧画面へ</a>
    <br>
    <a href="ExecuteLogout">ログアウトする</a>
  </body>
</html>
