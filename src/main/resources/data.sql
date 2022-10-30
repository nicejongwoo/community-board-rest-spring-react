INSERT INTO roles (role, code, name) VALUES('ROLE_ADMIN', 'regaXL', '관리자');
INSERT INTO roles (role, code, name) VALUES('ROLE_MANAGER', 'rGrAql', '매니저');
INSERT INTO roles (role, code, name) VALUES('ROLE_USER', 'rwCtSm', '사용자');

INSERT INTO category (name, type, created_by, created_at, updated_by, updated_at) VALUES ('질문', 'question', 'admin', current_time, 'admin', current_time);
INSERT INTO category (name, type, created_by, created_at, updated_by, updated_at) VALUES ('자유주제', 'chat', 'admin', current_time, 'admin', current_time);
INSERT INTO category (name, type, created_by, created_at, updated_by, updated_at) VALUES ('스터디', 'study', 'admin', current_time, 'admin', current_time);

INSERT INTO account (account_token, email, name, password, phone, joined_at) VALUES ('vcblpDDaEdvcblpDDaEd', 'user@email.com', '사용자', '{noop}password', '010-1234-1111', CURRENT_TIMESTAMP);
INSERT INTO account (account_token, email, name, password, phone, joined_at) VALUES ('ADFAEdfodsADFAEdfods', 'manager@email.com', '매니저', '{noop}password', '010-1234-9999', CURRENT_TIMESTAMP);
INSERT INTO account (account_token, email, name, password, phone, joined_at) VALUES ('erewADFffferewADFfff', 'admin@email.com', '관리자', '{noop}password', '010-1234-0000', CURRENT_TIMESTAMP);

INSERT INTO account_roles (account_id, role_id) VALUES (1, 3);
INSERT INTO account_roles (account_id, role_id) VALUES (2, 2);
INSERT INTO account_roles (account_id, role_id) VALUES (2, 3);
INSERT INTO account_roles (account_id, role_id) VALUES (3, 1);
INSERT INTO account_roles (account_id, role_id) VALUES (3, 2);
INSERT INTO account_roles (account_id, role_id) VALUES (3, 3);

INSERT INTO community (created_by, created_at, updated_by, updated_at, title, category_id, content) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, '영상에 관한 질문입니다.', 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac.');

INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content1', false, 'N', 'title1');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content2', false, 'N', 'title2');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content3', false, 'N', 'title3');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content4', false, 'N', 'title4');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content5', false, 'N', 'title5');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content6', false, 'N', 'title6');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content7', false, 'N', 'title7');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content8', false, 'N', 'title8');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content9', false, 'N', 'title9');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content10', false, 'N', 'title10');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content11', false, 'N', 'title11');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content12', false, 'N', 'title12');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content13', false, 'N', 'title13');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content14', false, 'N', 'title14');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content15', false, 'N', 'title15');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content16', false, 'N', 'title16');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content17', false, 'N', 'title17');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content18', false, 'N', 'title18');
INSERT INTO test (created_by, created_at, updated_by, updated_at, content, use_enabled, notice, title) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, 'content19', false, 'N', 'title19');