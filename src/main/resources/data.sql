INSERT INTO category (name, type, created_by, created_at, updated_by, updated_at) VALUES ('질문', 'question', 'admin', current_time, 'admin', current_time);
INSERT INTO category (name, type, created_by, created_at, updated_by, updated_at) VALUES ('자유주제', 'chat', 'admin', current_time, 'admin', current_time);
INSERT INTO category (name, type, created_by, created_at, updated_by, updated_at) VALUES ('스터디', 'study', 'admin', current_time, 'admin', current_time);

INSERT INTO roles (name) VALUES('ROLE_ADMIN');
INSERT INTO roles (name) VALUES('ROLE_MANAGER');
INSERT INTO roles (name) VALUES('ROLE_USER');
