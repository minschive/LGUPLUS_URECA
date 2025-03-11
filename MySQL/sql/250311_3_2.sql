-- 프론트에서 회원 구분 목록 요청 (회원가입 시에 라디오 버튼과 함께 선택할 수 있도록 하기 위해서)
-- 공통코드가 없다면 하드코딩 (업무적으로 합의)
-- 공통코드를 활용하면
select * from group_code; -- 001 확인

select * from code where group_code = '001';

select code, code_name from code where group_code = '001' and use_yn = 'Y';
-- 위 결과를 백엔드에서는 프론트에게 내려주면(json) 프론트는 라디오버튼 등 화면 구성 보여준다.
-- 회원은 선택하고 프론트는 선택된 값(이름, 이메일 등 code = 010과 함께)을 전송하게 되고
-- 백엔드는 users 테이블에 insert 한다.
insert into users (user_id, user_name, email, user_clsf) values (100, '백길동', 'back@gildong.com', '010');

-- 프론트에서 전체 회원 목록을 필요로 한다. 이때 회원구분코드외에 회원구분코드명까지 함께 전달한다.
select user_id, user_name, email, user_clsf,
	   (select code_name from code where group_code = '001' and code = user_clsf) user_clsf_name from users;
       
-- 회원 테이블에 회원 상태를 추가로 관리 지시 공통코드에는 이미 반영되어 있다. '002' 그룹코드 이용, 기본값은 'SILVER'
select * from group_code where group_code = '002';
select * from code where group_code = '002';

-- user 테이블에 user_state 컬럼을 추가하되, 기본값을 '030'으로

-- 프론트에서 전체 회원 목록을 필요로 한다.
-- 이때 회원구분코드외에 회원구분코드명, 회원상태코드외에 회원상태코드명까지 함께 전달한다.
select user_id, user_name, email, user_clsf, user_state,
	   (select code_name from code where group_code = '001' and code = user_clsf) user_clsf_name,
       (select code_name from code where group_code = '002' and code = user_state) user_state_name
from users;

-- 위 공통코드 구성이 불편한 점 ...

-- group_code = '001' <= 가독성이 떨어진다.
-- => '001' 대신 'user_clsf' 변경 가능. group_code 컬럼 varchar로 변경 필요
--    (select code_name from code where group_code = 'user_clsf' and code = user_clsf)

-- scalar 서브쿼리를 이용하는 방법은 다소 귀찮다.
-- => function으로 대체

-- 공통코드용 function 추가
-- SET GLOBAL log_bin_trust_function_creators = 1; -- 실행
SET GLOBAL log_bin_trust_function_creators = 1;

/*
CREATE DEFINER=`root`@`localhost` FUNCTION `fun_code`(
  p_group_code char(3),
  p_code char(3)
) RETURNS varchar(45) CHARSET utf8mb4
BEGIN
  declare r_code_name varchar(45);
  
  select code_name
   into r_code_name
   from code
  where group_code = p_group_code
   and code = p_code;
   
RETURN r_code_name;
END
*/

select user_id, user_name, email, user_clsf, user_state,
	   fun_code('001', user_clsf) user_clsf_name,
       fun_code('002', user_state) user_state_name
from users;
-- scalar sub query는 subquery returns more than 1 row 오류가 빈번
-- 사용자가 복수개의 취미를 가지고 그 테이블이 (user_hobby)일 떄, 사용자 1명 당 user_hobby 테이블에 값이 없을 수도, 1개일 수도, 복수개일 수도 있다.
-- 만약 사용자 목록을 추출하면서 사용자의 취미도 같이 추출할 경우 subquery 대신 function으로 대체하고
--   function 안에서 없거나, 1개이거나, 여러개 데이터에 대한 대응 코드를 1개로 return하는 형태로 많이 만든다.