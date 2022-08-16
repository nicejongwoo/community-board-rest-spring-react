INSERT INTO category (name, type, created_by, created_at, updated_by, updated_at) VALUES ('질문', 'question', 'admin', current_time, 'admin', current_time);
INSERT INTO category (name, type, created_by, created_at, updated_by, updated_at) VALUES ('자유주제', 'chat', 'admin', current_time, 'admin', current_time);
INSERT INTO category (name, type, created_by, created_at, updated_by, updated_at) VALUES ('스터디', 'study', 'admin', current_time, 'admin', current_time);

INSERT INTO roles (name) VALUES('ROLE_ADMIN');
INSERT INTO roles (name) VALUES('ROLE_MANAGER');
INSERT INTO roles (name) VALUES('ROLE_USER');

INSERT INTO account (email, name, password) VALUES ('user@email.com', '사용자', '{noop}password');
INSERT INTO account_roles (account_id, role_id) VALUES (1, 3);

INSERT INTO community (created_by, created_at, updated_by, updated_at, title, category_id, content) VALUES ('user@email.com', CURRENT_TIMESTAMP, 'user@email.com', CURRENT_TIMESTAMP, '영상에 관한 질문입니다.', 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac.')
