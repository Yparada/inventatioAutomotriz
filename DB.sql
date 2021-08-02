drop table if exists producto;
drop table if exists usuario;
drop table if exists cargo;

select * from cargo;
select * from usuario;
select * from producto where fecha_ingreso = '2021-07-30'

select a.nombre,a.edad,b.nombre as cargo from usuario a inner join cargo b on (b.id_cargo = a.pk_id_cargo);

CREATE TABLE cargo 	
(id_cargo serial NOT NULL PRIMARY KEY,
nombre VARCHAR(100));

CREATE TABLE usuario 
(id_usuario serial NOT NULL PRIMARY KEY,
nombre VARCHAR(100),
edad integer NOT NULL,
pk_id_cargo integer NOT NULL,
fecha_ingreso timestamp without time zone default ('now'::text)::timestamp with time zone,
foreign key(pk_id_cargo) references cargo(id_cargo));

CREATE TABLE producto 
(id_producto serial NOT NULL PRIMARY KEY,
nombre VARCHAR(100),
cantidad integer,
fecha_ingreso /*date NOT NULL*/ date,
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

insert into producto (nombre,cantidad,fecha_ingreso,pk_id_usuario_registra) 
values ('Manzana',323,'2021-07-30',1)
insert into producto (nombre,cantidad,fecha_ingreso,pk_id_usuario_registra) 
values ('Mango',112,'2021-07-30',1)


insert into producto (nombre,cantidad,fecha_ingreso,pk_id_usuario_registra) 
values ('Cafe',112,'2021-07-29',2)

delete from producto



