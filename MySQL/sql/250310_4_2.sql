-- 고립수준
start transaction;
update users set age = 21 where id = 1; -- uncommitted 상태
rollback;

start transaction;
update users set age = 21 where id = 1; -- committed 상태
commit;

start transaction;
insert into users values(2, 'LEE GILDONG', 21); -- committed 상태
commit;

start transaction;
insert into users values(2, 'LEE GILDONG', 21); -- repeatable read
commit;