<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.domain.MemberDTO"%>
<%@page import="java.util.List"%>
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
	<button>at4 test</button>

	<%
		List<MemberDTO> list = new ArrayList<MemberDTO>();
	list.add(new MemberDTO("m001", "kim", 22));
	list.add(new MemberDTO("m002", "lee", 23));
	list.add(new MemberDTO("m003", "park", 24));
	ObjectMapper mapper = new ObjectMapper();
	String listStr = mapper.writeValueAsString(list);
	pageContext.setAttribute("listStr", listStr);
	%>





	<script type="text/javascript">
	$(document).ready(function(){
		$("button").click(function(){
			$.ajax({
				type : 'post',
				url :'at4',
				dataType : 'text',
				data :{
					listStr : JSON.stringify(${listStr})
					
					},
					success :function(result){
						console.log(result);
						var obj =JSON.parse(result);
						alert(obj[0]['id']);
						$("p").text(obj[0]['id']);

						for(var i=0;  i<obj.length; i++){

							for(x in obj[i]){
								console.log(x);/* 속성 */
								console.log(obj[i][x]);/*값 */
								console.log(":::::::::::::");
								}
							}
						

						
							
						}
				});
			
			});
		});

</script>
</body>
</html>