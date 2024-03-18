// id가 addbtn인 버튼 요소 얻어오기
const addBtn = document.querySelector("#addBtn");


// id tbody인 요소 얻어오기
const tbody = document.querySelector("#tbody");



// addBtn이 클릭 되었을 때(이벤트 리스너) 추가
addBtn.addEventListener("click", () => {

 //한 행을 나타내는 tr 요소 생성
 const tr = document.createElement("tr");

 //name 속성 값이 저장된 배열 생성
 const names = ['deptId', 'deptTitle', 'locationId'];

 // 배열 요소 순차 접근( 향상된 for문 )
 for(let name of names){
   
    const td = document.createElement("td"); // td 요소 생성
   
    const input = document.createElement("input"); // input 요소 생성

  input.type = "text"; // type = "text" 설정
  input.name = name; // 배열 요소를 name 속성 값으로 설정

  td.append(input);
  tr.append(td);


 }
});