-- users 테이블의 id는 사용자의 고유 식별자
CREATE TABLE users (
      id BIGINT PRIMARY KEY AUTO_INCREMENT,-- 사용자의 고유 ID
      username VARCHAR(50) UNIQUE NOT NULL,
      password VARCHAR(255) NOT NULL,
      email VARCHAR(100),
      name VARCHAR(50),
      created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- board 테이블의 id는 게시글의 고유 식별자
CREATE TABLE board (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,-- 게시글의 고유 ID
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    user_id BIGINT NOT NULL, -- 사용자의 ID를 참조하는 외래 키
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)-- users 테이블의 id 참조
);

