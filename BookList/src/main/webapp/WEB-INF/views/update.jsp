<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>도서 수정 페이지 </title>
</head>
<body>
    
  <h1>도서 수정 페이지</h1>


  <form action="update" method="POST">
    <div>
      도서 번호 : 
      <input type="number" name="bookNo"
      value="${book.bookNo}" placeholder="수정할 도서 번호">
     
    </div>

    <div>
      도서 종류 : 
      <input type="text" name="bookType" 
       value="${book.bookType}" placeholder="수정할 도서 종류">
    </div>
    <div>
      책 이름 : 
      <input type="text" name="bookName" 
       value="${book.bookName}" placeholder="수정할 책 이름">
    </div>
    <div>
      저자 : 
      <input type="text" name="bookAuthor" 
       value="${book.bookAuthor}" placeholder="수정할 저자">
    </div>

    <div>
      출판사 : 
      <input type="text" name="bookPublisher"
       value="${book.bookPublisher}" placeholder="수정할 출판사">
    </div>

    <button>수정</button>
  </form>

    
      <c:if test="${not empty message}" >
       <script>
         alert("${message}");
       </script>
       
       <c:remove var="message" />
      </c:if>  
    

</body>
</html>