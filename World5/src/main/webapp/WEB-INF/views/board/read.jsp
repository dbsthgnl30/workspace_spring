<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>

   <div class="container">
      <div class="row">
         <h1>자세히보기</h1>
         <div class="row">
            <div class="form-group">
               <label for="title">제목</label> <input readonly value="${vo.title}"
                  class="form-control">
            </div>
            <div class="form-group">
               <label for="writer">작성자</label> <input readonly
                  value="${vo.writer}" class="form-control">
            </div>

            <div class="form-group">
               <label for="content">내용</label>
               <textarea readonly rows="5" class="form-control">${vo.content}</textarea>

            </div>

         </div>

         <div class="row">

            <div class="form-group">
               <button id="reply_form" class="btn btn-danger">댓글</button>
               <button id="list" class="btn btn-warning">목록</button>
               <button id="update" class="btn btn-primary">수정</button>
               <button id="delete" class="btn btn-info">삭제</button>


            </div>

         </div>
      </div>
      <!-- class=row -->
      <div class="row">
         <div class="collapse" id="myCollapse">
            <div class="form-group">
               <label for="replyer">작성자</label> <input class="form-control"
                  id="replyer">

            </div>

            <div class="form-group">
               <label for="replytext">내용</label> <input class="form-control"
                  id="replytext">

            </div>
            <div class="form-group">
               <button class="btn btn-warning" id="replyInsertBtn">등록</button>
            </div>

         </div>

      </div>
      <!-- reply종료테크 -->

   </div>
   <!-- container종료테크 -->
   <script type="text/javascript">
   var bno = ${vo.bno};

   
      $(document).ready(function() {
         $("#reply_form").click(function() {
            $("#myCollapse").collapse("toggle");

         });

         $("#replyInsertBtn").click(function() {
            var replyer = $("#replyer").val();

            var replytext = $("#replytext").val();
            console.log(replyer);
            console.log(replytext);
         });

         $("#update").click(function() {
            location.assign("/board/update/${vo.bno}");

         });
         $("#delete").click(function() {
            location.assign("/board/delete/${vo.bno}")
         });
         $("#list").click(function() {
            location.assign("/board/list")
         });

      });
   </script>

</body>
</html>