select * from customer;

insert into customer values(1, '홍길동');
insert into customer values(2, '이길동');
insert into customer values(3, '삼길동');
-- 위 3개의 insert는 모두 종류 후 자동 commit 됨. (현재 설정)
select @@autocommit; -- 1 : on, 0 : off

-- autocommit을 off
insert into customer values(4, '사길동');
commit;

insert into customer values(5, '오길동');
commit;

update customer set name = '육길동' where id = 5;
commit;

delete from customer where id = 5;
commit;

set autocommit = 1;

delete from customer where id = 4;