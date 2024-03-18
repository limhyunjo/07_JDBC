<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>도서 추가 페이지</title>
</head>
<body>

  <form action="insert" method="POST"> <%-- 상대 경로 방식 --%>
   
     <div>
       도서 번호 : <input type="number" name="bookNo">
     </div>
     
     <div>
       도서 종류 : <input type="text" name="bookType">
     </div>
    
      <div>
      책 이름 : <input type="text" name="bookName">
      </div>
      
      <div>
      저자 : <input type="text" name="bookAuthor">
      </div>
     
      <div>
      출판사 : <input type="text" name="bookPublisher">
      </div>
     
     <%-- button의 type 기본값은 submit --%>
      <button type="submit">도서 추가</button>
   </form>

  <script src="/resources/js/insert.js"></script> 
</body>
</html>