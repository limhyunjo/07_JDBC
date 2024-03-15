//목록으로 버튼 동작
const goToList = document.querySelector("#goToList");

// 버튼 클릭 시 메인 페이지로 날림
goToList.addEventListener("click", () => {
    location.href = "/"; // 메인 페이지 요청
  }); // JS 자바스크립트 안되면 콘솔 보기


//SQL 먼저 생각해놓고 화면 만들기

// 완료 여부 변경 버튼 클릭 시
const completeBtn = document.querySelector(".complete-btn");

completeBtn.addEventListener("click", e => {

  // 요소.dataset : data-* 속성에 저장된 값 반환(객체)
  
  // data-todo-set 속성의 속성 값 얻어오기 (버튼의 data속성 값을 얻어옴)
  const todoNo = e.target.dataset.todoNo; // 요즘 많이 씀

  //console.log(todoNo);

  let complete = e.target.innerText; //기존 완료 여부 값 얻어오기
  // 기존 값을 얻어와 반대로 바꿔서 저장 
  // 삼항 연산자 사용해보자(if문 써도 됨)
  // Y <-> N
  complete = (complete === 'Y') ? 'N' : 'Y';

  // 완료 여부 수정 요청하기 // 파라미터 2개를 보냄
  location.href = `/todo/changeComplete?todoNo=${todoNo}&complete=${complete}`;
  ///todo/changeComplete?todo=1&complete=Y 주소로 확인 가능

});



//----------------------------------------------------------

// 수정 버튼 클릭 시
const updateBtn = document.querySelector("#updateBtn");

updateBtn.addEventListener("click", e => {

 // data-todo-no="${todo.todoNo}" 얻어오기
 const todoNo = e.target.dataset.todoNo;

 location.href = `/todo/update?todoNo=${todoNo}`;
 // GET방식 요청 보내면서 파라미터로 todoNo가 넘어감
});


//----------------------------------------------------
// 삭제는 수정과 비슷
// 읽어와서 수정이 아닌 삭제

// 삭제 버튼 클릭 시 
const deleteBtn = document.querySelector("#deleteBtn");

deleteBtn.addEventListener("click", e => {

if(confirm("삭제 하시겠습니까?")){
    location.href = `/todo/delete?todoNo=${e.target.dataset.todoNo}`;
} //파라미터로 전달
    


});