<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>전체 도서 조회</title>
</head>
<body>
    <h1> 전체 도서 조회 </h1>
  
  <table border="1">
  
   <thead>
    <tr>
      <th>행</th>
      <th>도서 번호</th>
      <th>도서 종류</th>
      <th>책 이름</th>
      <th>저자</th>
      <th>출판사</th>


      <th>수정</th>
      <th>삭제</th>
    </tr>
   </thead>
   
   <tbody>
    <c:forEach items="${bookList}" var="bookList" varStatus="vs"> 
      
      <tr>
       <%-- vs.count : 현재 반복 횟수 (1부터 시작) --%>
       <td>${vs.count}</td>
       
       <td>${bookList.bookNo}</td>
       
       <td>${bookList.bookType}</td>
       
       <td>${bookList.bookName}</td>

       <td>${bookList.bookAuthor}</td>

       <td>${bookList.bookPublisher}</td>


        <th>
         <button type="button" class="update-btn">수정</button>
        </th>

        <th>
         <button type="button" class="delete-btn">삭제</button>
        </th>
       
      </tr>
      
    </c:forEach>
   </tbody>
   
  
  </table> 


    <%-- session scope로 전달 받은 message가 있으면 alert() 출력 --%>
  <c:if test="${not empty message}" >
	
		<script>
			const message = "${message}";
      alert(message);
    </script>  

      <c:remove var="message" scope="session" />

  </c:if>


   <script src="/resources/js/selectAll.js"></script>



</body>
</html>