create table Student (
	sid			int				primary key 	auto_increment,
    name		varchar(20)		not null,
    major		varchar(50),
    java		varchar(10)		CHECK (java IN ('상', '중', '하')),
    db			varchar(10)		CHECK (db IN ('상', '중', '하')),
    web			varchar(10)		CHECK (web IN ('상', '중', '하'))
);
ALTER TABLE student AUTO_INCREMENT = 1000;
select * from student;

commit;