
const deleteBtnList = document.querySelectorAll(".delete-btn");

// deleteBtnList.addEventListener() --> 안됨!!!
// 왜? 이벤트 리스너는 DOM 요소에 추가해야 동작하는데
//deleteBtnList는 DOM 요소가 아니라 배열(유사 배열, NodeList) 
// 요소가 아닌 배열
//-> 이벤트 리스너를 추가할 수 없다!!

// 해결 방법-> 배열 내 각 인덱스 요소 == DOM 요소(버튼)
// -> 배열 내 요소를 하나씩 꺼내서 이벤트 리스너를 추가하면 된다!!!

for(let btn of deleteBtnList){ // 향상된 for문

 btn.addEventListener("click", e=>{

  // console.log("삭제 버튼 클릭됨");

  // 클릭된 삭제 버튼이 존재하는 행의 부서 코드 얻어오기 // 순회 탐색 코드
  //const deptId = e.target.parentElement.parentElement.children[1].innerText;
  
  // 요소.closest("CSS선택자")
  // - 지정된 요소의 상위 요소(부모 방향, 루트 최상위 까지)
  //  중에서 CSS 선택자가 일치하는 요소를 찾을 때 까지 검색해
  //  일치하는 요소가 있으면 해당 요소 반환
  const deptId = e.target.closest("tr").children[1].innerText;
  console.log(bookNo);

  if(confirm(`${bookNo} 도서를 정말 삭제하시겠습니까?`)){
   // 확인 클릭 시

   // 권장하지 않는 방법 
   // 삭제 요청 보내기 1) location.href 이용 -> GET 방식 요청
   // -> 브라우저 주소창에 입력하는 방법도 GET 방식 요청
   // --> 임의의 사용자가 삭제요청을 마음대로 보내는 문제가 
   // 발생할 수 있다!!!
   //location.href = "/department/delete?deptId=" + deptId;
    
   // 삭제 요청 보내기 2) -> POST 방식 (실제로 많이 이용하는 방식)
   //(주소창에 안 보이게 body에 담아서 보내는 방식)
   // form 태글를 만들어서 POST 방식 요청 보내기

   // form 생성
   const form = document.createElement("form");
   form.action = "/booklist/delete";
   form.method = "POST";
   //console.log(form);
   // input type ="hidden" 생성

   const input = document.createElement("input");
   input.type = "hidden";
   input.value = bookNo; // 값으로 부서 코드 대입
   input.name = "bookNo"; //파라미터 키 값 지정
   
 
   // form 자식으로 input 추가
   form.append(input);

   // body 태그 제일 밑에 form 추가
   document.querySelector("body").append(form);

   // 화면에 추가된 form 제출하기
   form.submit();

  }else{// 취소 클릭 시
    alert("삭제 취소");
  }


 });


  // .update-btn 요소 모두 얻어오기
  const updateBtnList = document.querySelectorAll(".update-btn");

  // updateBtnList 배열의 모든 요소의 순차 접근하며 이벤트 리스너 추가
  updateBtnList.forEach( (btn,index) => {

   btn.addEventListener('click', e =>{

   //부모 요소 중 가장 가까운 tr 태그 찾기
   // const tr = e.target.parentElement.parentElement;
    const tr = e.target.closest("tr");

    // 도서 번호 얻어오기
    const bookNo = tr.children[1].innerText;
 
    // JS에서 요청하기 
    
    location.href = "/booklist/update?bookNo=" + bookNo;
    


   });
    
  });

}