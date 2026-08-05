-- 맛집리스트 서비스 실습용 테이블
CREATE TABLE restaurant (
    id              INTEGER         AUTO_INCREMENT PRIMARY KEY,                    -- PK
    name            VARCHAR(100)    NOT NULL,                                      -- 맛집 이름
    category        VARCHAR(50)     NOT NULL,                                      -- 음식 종류 (한식/중식/일식 등)
    address         VARCHAR(255)    NOT NULL,                                      -- 주소
    phone           VARCHAR(20),                                                   -- 전화번호
    rating          INT             NOT NULL DEFAULT 5,                            -- 별점 (1~5)
    description     VARCHAR(500),                                                  -- 한 줄 소개
    reg_date        TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,                     -- 등록일
    mod_date        TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 수정일
);

-- 샘플 데이터
INSERT INTO restaurant (name, category, address, phone, rating, description) VALUES
('할매순대국', '한식', '부산 동래구 온천동 123-4', '051-123-4567', 5, '3대째 이어온 순대국 맛집'),
('진짜중화반점', '중식', '부산 해운대구 우동 45-6', '051-234-5678', 4, '탱탱한 짜장면이 일품'),
('스시요코초', '일식', '부산 부산진구 부전동 78-9', '051-345-6789', 5, '오마카세 전문점'),
('브릭오븐', '양식', '부산 수영구 광안동 12-3', '051-456-7890', 4, '화덕피자와 파스타'),
('카페드롭탑', '카페/디저트', '부산 남구 대연동 34-5', '051-567-8901', 3, '조용한 분위기의 브런치 카페');

commit;
