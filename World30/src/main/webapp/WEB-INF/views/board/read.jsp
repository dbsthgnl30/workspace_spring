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
	<script src="/resources/js/uploadfn.js" type="text/javascript"></script>

<title>Insert title here</title>
<style type="text/css">
   .fileDrop{
      width : 80%;
      height : 200px;
      border : 1px solid skyblue;
      margin : auto;
  
   }

   .uploadedList li{
      list-style : none;
   }

.orifilename {
overflow: hidden;
white-space: nowrap;
text-overflow: ellipsis;

}

.uploadedList{
margin-top: 50px;
}

   
</style>
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
				
				<div class ="form-group">
				<label>첨부파일</label>
				<ul class="uploadedList clearfix">
				
				</ul>
				</div>

			</div><!-- class=row -->

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
		<!-- class -row reply종료테크 -->
		<div class="row" id="replies">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<span>rno: 8</span>, <span>작성자: 김독자</span> <span class="pull-right">2020년07년07일
						!?</span>
				</div>
				<div class="panel-body">
					<p>replycontent</p>
					<button data-name="김독자" data-rno="3"
						class="btn btn-success btn-sm  replymodify pull-right">수정</button>
					<button data-rno="3"
						class="btn btn-info btn-sm replydelete pull-right">삭제</button>
				</div>
			</div>
		</div>
		<!-- class=row 종료데크 댓글보이기 -->
		<div class="row">
			<div data-backdrop="static" id="mymodal" class="modal fade"
				tabindex="-1" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-rno">rno data</h4>
						</div>
						<div class="modal-body">

							<p class="modal-replyer">김독자&hellip;</p>
							<input class="modal-replytext form-control" value="replytext">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-info modal-update-btn"
								data-dismiss="modal">수정이</button>

						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->


		</div>


	</div>
	<!-- container종료테크 -->

	<script type="text/javascript">
   
   var bno = ${vo.bno};

   getList(bno); <!-- 이쪽은무조건 실행대는것들 위에 bno랑-->

   
      $(document).ready(function() {

          $.getJSON("/getAttach/"+bno,function(arr){
				for(var i=0; i<arr.length; i++){
					var str = '<li class="col-xs-4">';
	                str += '<a href="/displayfile?filename='+getImageLink(arr[i])+'">';
	                if(checkImage(arr[i])){
	                       str += '<img src="/displayfile?filename='+arr[i]+'"/>';
	                    }else{
	                       str += '<img src = "/resources/show.png"/>';
	                        }


	              str += '</a>';       
	                str += '<p class="orifilename">';
	                
	                str += getOriginalName(arr[i]);
	                str += '</p>';
	              str += '</li>';
	            

	                $(".uploadedList").append(str);
					}
				
              });
         //여기부터 수정
       $("#replies").on("click",".replymodify", function(){

              var rno =  $(this).attr("data-rno");
               var replyer = $(this).attr("data-name")
                var replytext = $(this).prev().text(); /*경로앞 p테그를 가리킴...?*/

                $(".modal-rno").text(rno);    /*가져오세용*/
                $(".modal-replyer").text(replyer);    /*가져오세용*/
                $(".modal-replytext").val(replytext);    /*가져오세용*/
                
              $("#mymodal").modal("show");
                        
           });
                      $(".modal-update-btn").click(function(){
                              var rno= $(".modal-rno").text();
                               var replytext =$(".modal-replytext").val();

                            $.ajax({
                                   type : 'put',
                                   url : '/replies/'+rno,
                                   headers:{
                                      "Content-Type" : "application/json","X-HTTP-Method-Override" : "PUT"

                                       },
                                       dataType: 'text',
                                       data : JSON.stringify({
                                              replytext : replytext
                                              
                                           }),
                                           success : function(result){
                                                  if(result === "success"){
                                                      getList(bno);
                                                      }
                                               },
                                               error : function(request, status, error){
                                                   console.log(error);
                                                   }
                          });

                          });
     
       //여기부터 삭제
           $("#replies").on("click",".replydelete", function(){
           
                    var rno=  $(this).attr("data-rno");
                    /* alert(rno+"번:수정이 나쁘다") 예네뭔ㄴ대
                    $(this).parent().parent().remove(); */
				$.ajax({
					type:'delete',
					url : '/replies',
					headers:{
						"Content-Type" : "application/json","X-HTTP-Method-Override" : "DELETE"
						},
						dataType : 'text',
						data: JSON.stringify({
							rno : rno,
							
							}),
							success : function(result){
								getList(bno);
								
								}
					});
                    
                     
           });
           //여기부터 댓글

         $("#reply_form").click(function() {
            $("#myCollapse").collapse("toggle");

         });

         $("#replyInsertBtn").click(function() {
            var replyer = $("#replyer").val();

            var replytext = $("#replytext").val();
            $.ajax({
               type: 'post',
               url: '/replies',
               headers: {
                  "Content-Type" : "application/json",
                  "X-HTTP-Method-Override" : "POST"
                  

                  },
                  dataType: 'text',
                  data: JSON.stringify({
                               bno : bno, 
                               replyer : replyer,
                               replytext : replytext
                               
                     }),

                     success : function(result){
                        $("#replyer").val("");<!--값을 넣으면 갑을가져오고 안넣으면 비게댐 뭐래?-->
                        $("#replytext").val("");
                        
                                  getList(bno);

                        
                        },
                        error : function(request, status, error){
                                      console.log(error);
                           }
               
               });
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


   
      
      function getList(bno){ //댓글...리스트가져오기위해 bno
                 var str='';         
         $.getJSON("/replies/all/"+bno, function(data){
         
                  for(var i =0; i < data.length; i++){

                      str += '<div class="panel panel-warning"><div class="panel-heading"><span>rno: '+data[i]["rno"]+'</span>, <span>작성자: '+data[i]["replyer"]+'</span><span class="pull-right">'+data[i]["updatedate"]+'</span></div><div class="panel-body"><p>'+data[i]["replytext"]+'</p><button data-name="'+data[i]["replyer"]+'" data-rno = "'+data[i]["rno"]+'" class="btn btn-success btn-sm replymodify">수정</button><button data-rno = "'+data[i]["rno"]+'" class="btn btn-info btn-sm replydelete">삭제</button></div></div>';
                    
                      }
                  $("#replies").html(str);
                
                  
            });

         }
      
   </script>

</body>
</html>