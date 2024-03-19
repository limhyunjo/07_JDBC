const deleteBtnList = document.querySelectorAll(".delete-btn");



for(let btn of deleteBtnList){ // 향상된 for문

 btn.addEventListener("click", e=>{

 
  const bookNo = e.target.closest("tr").children[1].innerText;
  console.log(bookNo);

  if(confirm(`도서를 정말 삭제하시겠습니까?`)){
  
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



  // ---------------------------------------------------------

  // .update-btn 요소 모두 얻어오기
  const updateBtnList = document.querySelectorAll(".update-btn");

  // updateBtnList 배열의 모든 요소의 순차 접근하며 이벤트 리스너 추가
  updateBtnList.forEach( (btn,index) => {

   btn.addEventListener('click', e =>{

   //부모 요소 중 가장 가까운 tr 태그 찾기
   // const tr = e.target.parentElement.parentElement;
    const tr = e.target.closest("tr");

    // 부서 코드 얻어오기
    const bookNo = tr.children[1].innerText;
 
    // JS에서 요청하기 
    location.href = "/booklist/update?bookNo=" + bookNo;
    

   });
    
  });


}