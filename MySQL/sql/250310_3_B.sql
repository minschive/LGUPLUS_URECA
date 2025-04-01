-- 동시성 제어 (쓰기, 쓰기)
-- madang schema
-- Lock은 row 단위로 처리
select * from book where bookid = 1;

select @@autocommit;
set autocommit = false;

start transaction;

update book set price = 10000 where bookid = 5;

commit;

-- 데드락 (Dead Lock)
-- 1, 2 book에 대해서 테스트
start transaction;

update book set price = 4000 where bookid = 2; -- 2번 lock

update book set price = 4000 where bookid = 1; -- 1번 lock

commit;