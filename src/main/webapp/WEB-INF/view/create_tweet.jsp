<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta charset="UTF-8">
  <title>tweet</title>
</head>
  <body>
    <form action="CreateTweet" method="post">
        <textarea name="content" rows="4" cols="50" placeholder="いまどうしてる？"></textarea><br>
        <input type="submit" value="ツイートする" id="ID_SUBMIT">
    </form>
  </body>
</html>