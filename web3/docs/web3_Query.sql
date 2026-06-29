-- web3 SQL

-- Database 생성
CREATE DATABASE busan14;

-- 확인
SHOW DATABASES;

-- 선택
USE busan14;

-- 현재 선택된 Database 확인
SELECT database();

-- 1. 기존 테이블이 있다면 삭제
DROP TABLE IF EXISTS person;

-- 2. 테이블 생성
CREATE TABLE person (
                        id      VARCHAR(30)     NOT NULL    PRIMARY KEY,
                        name    VARCHAR(50),
                        age     INTEGER
);

-- 3. 데이터 삽입
INSERT INTO person (id, name, age) VALUES ('aaa', '홍길동', 10);
INSERT INTO person (id, name, age) VALUES ('bbb', '김철수', 20);

COMMIT;