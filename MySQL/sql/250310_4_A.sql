-- 고립수준
-- 한 트랜잭션은 읽기, 다른 트랜잭션을 쓰기를 진행
-- 읽는 트랜잭션이 쓰는 트랜잭션의 변화를 어떻게 대응할 것인가 하는 정책에 따라 다른 결과를 보여준다.

-- set transaction isolation level ___ ;
-- ___ 에 올 수 있는 경우
-- read uncommited : 쓰기 트랜잭션의 변화가 commit 되지 않아도 읽는다. 
-- <= 읽기 트랜잭션에서 commit 되지 않은 데이터를 읽은 후 쓰기 트랜잭션에서 rollback 하면 
--    잘못된 데이터를 읽게 된다. ( dirty read )
-- read commited : 쓰기 트랜잭션의 변화가 commit 되어야만 읽는다.
-- <= 읽기 트랜잭션에서 이전에 commit 된 데이터를 읽은 후 쓰기 트랜잭션에서 변경 commit 하면 
--    이전에 읽은 데이터와 달라진다. ( non - repeatable read )
-- <= 읽기 트랜잭션에서 이전에 commit 된 데이터들을 읽은 후 ( 복수개가 될 수 있는) 쓰기 트랜잭션에서 등록 commit 하면 
--    이전에 읽은 데이터들과 달라진다. ( phantom read )
-- repeatable read
-- <= 읽기 트랜잭션에서 이전에 commit 된 데이터를 읽은 후 쓰기 트랜잭션에서 변경 commit 해도
--    이전에 읽은 데이터는 동일하게 읽는다 ( X )

create table `users` (
  `id` int not null,
  `name` varchar(20) default null,
  `age` int default null,
  primary key (`id`)
);

insert into users values (1, 'hong gildong', 30);

select * from users;

-- #1 read uncommited
set transaction isolation level read uncommitted;
start transaction;
select * from users where id = 1; -- 최초 30

                                                            -- 쓰기 트랜잭션
                                                            start transaction;
                                                            update users set age = 21 where id = 1; -- uncommitted  상태
                                                            
select * from users where id = 1; -- 쓰기 트랜잭션 uncommitted 된 21 ( dirty read )

                                                            -- 쓰기 트랜잭션
                                                            rollback;
                                                            
select * from users where id = 1; -- 최초 30

commit;

-- 데이터 초기화
update users set age = 30 where id = 1; commit;

-- #2 read committed
set transaction isolation level read committed;
start transaction;
select * from users where id = 1; -- 최초 30

                                                            -- 쓰기 트랜잭션
                                                            start transaction;
                                                            update users set age = 21 where id = 1;
                                                            commit; -- committed  상태
                                                            
select * from users where id = 1; -- 쓰기 트랜잭션 committed 된 21 ( non-repeatable read )( dirty-read 는 X)

commit;

-- 데이터 초기화
update users set age = 30 where id = 1; commit;

-- #3 read committed
set transaction isolation level read committed;
start transaction;
select * from users where age between 10 and 30;  -- 최초 30

                                                            -- 쓰기 트랜잭션
                                                            start transaction;
                                                            insert into users values (2, 'LEE GILDONG', 21);
                                                            commit;
                                                            
select * from users where age between 10 and 30;  -- 최초 30 과 쓰기 트랜잭션에서 추가된 21 이 함께 보인다. (phamtom read)

commit;

-- 데이터 초기화
delete from users where id = 2; commit;

-- #4 repeatable read
set transaction isolation level repeatable read;
start transaction;
select * from users where age between 10 and 30;  -- 최초 30

                                                            -- 쓰기 트랜잭션
                                                            start transaction;
                                                            insert into users values (2, 'LEE GILDONG', 21);
                                                            commit;
                                                            
select * from users where age between 10 and 30;  -- 최초 30 만 보인다. ( phamtom read X )

commit;