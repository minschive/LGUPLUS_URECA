-- 동시성 제어 (쓰기, 쓰기)
-- madang schema
-- Lock은 row 단위로 처리
select * from book where bookid = 1;

select @@autocommit;
set autocommit = 0;

start transaction;

update book set price = 2000 where bookid = 1;

-- 다른 세션에서 commit이 발생한 경우, 자동으로 commit된 데이터가 바로 보이지 않고, 한번 더 commit을 수행해야 보인다.
commit;

-- 데드락 (Dead Lock)
-- 1, 2 book에 대해서 테스트
start transaction;

update book set price = 5000 where bookid = 1; -- 1번 lock

update book set price = 5000 where bookid = 2; -- 2번 lock

commit;