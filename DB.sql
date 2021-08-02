CREATE TABLE cargo 	
(id_cargo serial NOT NULL PRIMARY KEY,
nombre VARCHAR(100));

CREATE TABLE usuario 
(id_usuario serial NOT NULL PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
edad integer NOT NULL,
pk_id_cargo integer NOT NULL,
fecha_ingreso timestamp without time zone default ('now'::text)::timestamp with time zone,
foreign key(pk_id_cargo) references cargo(id_cargo));

CREATE TABLE producto 
(id_producto serial NOT NULL PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
cantidad integer NOT NULL,
fecha_ingreso date NOT NULL,
pk_id_usuario_registra integer,
fecha_usuario_registra timestamp without time zone default ('now'::text)::timestamp with time zone,
pk_id_usuario_modifica integer,
fecha_usuario_modifica timestamp without time zone,
foreign key(pk_id_usuario_registra) references usuario(id_usuario),
foreign key(pk_id_usuario_modifica) references usuario(id_usuario));


----
insert into cargo (nombre) values ('admin');
insert into cargo (nombre) values ('dev');

insert into usuario (nombre,edad,pk_id_cargo) values ('Andres',25,'1');
insert into usuario (nombre,edad,pk_id_cargo) values ('Carlos',22,'2');




