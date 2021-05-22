INSERT INTO user (nombre,apellido,email,create_at)
VALUES ('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');

INSERT INTO user (nombre,apellido,email,create_at)
VALUES ('Ana', 'Torroja', 'annimos.@gmail.com', '2018-01-03');

insert into task (title,description,finish,estado,motivo,created_date,last_modified_by,last_modified_date)
VALUES ('Cirugía','Castracion Juanito',false,0,NOW(),NOW(),'Linus','2021-05-30');

insert into user_task  (user_id,task_id) values (1,1);
insert into user_task  (user_id,task_id) values (2,1);

insert into mensajes (task_id,user_id,description) values (1,1,'"Linus te toca rayosss"');

