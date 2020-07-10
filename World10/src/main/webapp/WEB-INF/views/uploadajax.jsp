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
<style type="text/css">
	.fileDrop{
		width: 80%;
		height: 200px;
		border: 1px dotted red;
	
	}

</style>
</head>
<body>
	<div class="fileDrop"></div>
	<div class="uploadedList"></div>
	<script type="text/javascript">
		$(document).ready(function(){
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
					dataType: 'text', 
					data : formData,
					processData : false,
					contentType: false,
					success : function(result){
						var str = "<div><a href = '#'>";
						

						
						str += getOriginalName(result);
						str += "</a></div>";
						$(".uploadedList").append(str);
					}

				});

			});

		});

		function getOriginalName(filename){
			if(checkImage(filename)){
				var idx = filename.indexOf("_");
				idx = filename.indexOf("_", idx+1);
				return filename.substring(idx+1);
			}else{
				var idx = filename.indexOf("_");
				return filename.substring(idx+1);
			}
		}

		function checkImage(filename){
			var idx = filename.lastIndexOf(".");

			var format = filename.substring(idx+1).toUpperCase();
			if(format == 'PNG' || format == 'JPG' || 
					format == 'JPEG' || format == 'GIF'){
				return true;
			}else{
				return false;
			}
		}

		
	</script>

</body>

</html>

 