-- 공통코드 테이블을 2개로 분리, 그룹(대분류)을 표현하는 테이블과 코드 및 코드값을 표현하는 테이블 2개로 나눈 방식
create table group_code( -- 회원구분, 회원상태...
    group_code char(3) not null,
    group_code_name varchar(50) not null,
    primary key(group_code)
);
create table code( -- 회원구분의 일반회원, 준회원...
    group_code char(3) not null,
    code char(3) not null,
    code_name varchar(50) not null,
    use_yn char(1) null,
    primary key (group_code, code)
);

insert into group_code values ('001', '회원구분');
insert into group_code values ('002', '회원상태');
insert into group_code values ('003', '주문상태');

-- 회원구분 공통코드
insert into code values ('001', '010', '일반회원', 'Y');
insert into code values ('001', '020', '준회원', 'Y');
-- insert into code values ('001', '003', '일반회원');

-- 회원상태 공통코드
insert into code values ('002', '010', 'VIP', 'Y');
insert into code values ('002', '020', 'GOLD', 'Y');
insert into code values ('002', '030', 'SILVER', 'Y');