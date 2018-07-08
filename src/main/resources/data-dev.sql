delete from user_role;
delete from roles;
delete from user;
INSERT INTO roles (id, name) VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER');
INSERT INTO user (id, E_mail, password) VALUES
  (1, 'admin@gmail.com', '$2a$10$klhTla/8jIGjvFhfsdqTxuvpbhlSYNJVI5hzq6dlMzHGfJAhXjbWi'
   ),
  (2, 'siva@gmail.com', '$2a$10$UFEPYW7Rx1qZqdHajzOnB.VBR3rvm7OI7uSix4RadfQiNhkZOi2fi'
  ),
  (3, 'user@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by'
   );
insert into user_role(user_id, role_id) values
  (1,1),
  (1,2),
  (3,2);