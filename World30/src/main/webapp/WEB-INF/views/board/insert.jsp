<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
      <div class="row text-center">
      <h1>글쓰기</h1>
      </div>
      <div class="row">
         
         <form action="/board/insert" method="post">
            <div class="form-group">
               <label for="title">제목</label>
               <input name="title" id="title" class="form-control">
            </div>
            
            <div class="form-group">
               <label for="writer">작성자</label>
               <input name="writer" id="writer" class="form-control">
            </div>
            
            <div class="form-group">
               <label for="content">내용</label>
               <textarea name="content" id="content" rows="5"  class="form-control"></textarea>
            </div>

         </form>
         
         <div class="form-group">
            <label>업로드 할 파일을 drop하세요</label>
            <div class="fileDrop"></div>
            <ul class="uploadedList clearfix">
             <!--  <li class ="col-xs-2">
                  <a href="#" ><img src="/resources/show.png"></a>
                  <p class="orifilename"><a><span class="glyphicon glyphicon-trash"></span>ㅇㅅㅇ</a></p>
             </li>-->
            </ul>
                  
           
         </div>
         
         
         
         <div class ="form-group text-right" >
            <button class="btn btn-danger" id="insertbtn">등록</button>
            <button class="btn btn-info" id="listbtn">목록</button>
         </div>
         
      </div>
      
   </div>
   
   <script type="text/javascript">
   
      $(document).ready(function(){

          $(".uploadedList").on("click",".deletefile",function(event){

                        event.preventDefault();
                        var that =$(this);

                        $.ajax({
                                type: 'post',
                                url: '/deletefile',
                                dataType :'text',
                                data: {
                                       filename : that.attr("href")

                                    },

                                    success : function(result){
                                             if(result){
                                                 that.parent("p").parent("li").remove();
                                                 }
                                        },
                                        error : function(request, status, error){
                                    console.log(error);
                                            }
                                        

                            
                            });
                            

              });
         
         $(".fileDrop").on("dragenter dragover", function(event){
            event.preventDefault();
         });
      
         $(".fileDrop").on("drop", function(event){
            event.preventDefault();
            
            var files = event.originalEvent.dataTransfer.files;
            var file = files[0];
            
            var formData = new FormData();
            formData.append("file", file);
      
            $.ajax({
               type : 'post',
               url : '/uploadajax',
               dataType : 'text',
               data : formData,
               processData : false,
               contentType : false,
               success : function(result){
                /*  var str = "<li><a href='/displayfile?filename="+getImgeLink(result)+ "'><span class='glyphicon glyphicon-search' ></span>";
                  if(checkImage(result)){
                     str += "<img src = '/displayfile?filename="+result+"'/>"
                  } else {                     
                     str += "<img src = '/resources/show.png'/>"
                  }
                  str += getOriginalName(result);
                  str += "</a><a class='deletefile' href='"+result+"'><span class='glyphicon glyphicon-trash'></span></a></li>";*/
                var str = '<li class="col-xs-4">';
                  str += '<a href="/displayfile?filename='+getImageLink(result)+'">';
                  if(checkImage(result)){
                         str += '<img src="/displayfile?filename='+result+'"/>';
                      }else{
                         str += '<img src = "/resources/show.png"/>';
                          }


                str += '</a>';       
                  str += '<p class="orifilename">';
                  str +='<a href="'+result+'" class="deletefile"><span class="glyphicon glyphicon-trash"></span></a>';
                  str += getOriginalName(result);
                  str += '</p>';
                str += '</li>';
              

                  $(".uploadedList").append(str);
               }
            });
         });
         
         $("#insertbtn").click(function(event){
            event.preventDefault();
            var str ='';
              $(".deletefile").each(function(index){ /*each ->반복분.  $(this)는 deletefile을 만드러줌...???????*/
                       str += "<input name='files["+index+"]' value ='"+$(this).attr("href")+"' type='hidden'>";/*동적으러 하기떄문에  '+?+'*/
                
                  });

              
             $("form").append(str);
            $("form").submit();

            
            
            
         });
         
         $("#listbtn").click(function(){
            location.href="list";
         });
         
      });
   </script>
   
</body>
</html>