
-- INSERTS FORM OF PAYMENT

INSERT INTO medsoft.payment_type
(pt_name, pt_fee_per_use, pt_description, pt_created_date)
VALUES('Efectivo', 0, 'Efectivo', CURRENT_DATE);

INSERT INTO medsoft.payment_type
(pt_name, pt_fee_per_use, pt_description, pt_created_date)
VALUES('Tarjeta', 0.07, 'Tarjeta', CURRENT_DATE);

INSERT INTO medsoft.payment_type
(pt_name, pt_fee_per_use, pt_description, pt_created_date)
VALUES('Transferencia', 0, 'Transferencia', CURRENT_DATE);


-- INSERTS CATEGORIES

INSERT INTO medsoft.services_category
(sc_name, sc_description, sc_created_date)
VALUES('FACIAL', 'FACIAL', CURRENT_DATE);

INSERT INTO medsoft.services_category
(sc_name, sc_description, sc_created_date)
VALUES('REJUVENECIMIENTO FACIAL', 'REJUVENECIMIENTO FACIAL', CURRENT_DATE);

INSERT INTO medsoft.services_category
(sc_name, sc_description, sc_created_date)
VALUES('RELLENOS DE ACIDO HIALURONICO', 'RELLENOS DE ACIDO HIALURONICO', CURRENT_DATE);

INSERT INTO medsoft.services_category
(sc_name, sc_description, sc_created_date)
VALUES('HILOS', 'HILOS', CURRENT_DATE);

INSERT INTO medsoft.services_category
(sc_name, sc_description, sc_created_date)
VALUES('SERVICIO DE REDUCCIÓN', 'SERVICIO DE REDUCCIÓN', CURRENT_DATE);

INSERT INTO medsoft.services_category
(sc_name, sc_description, sc_created_date)
VALUES('MEGADOSIS DE VITAMINA C', 'MEGADOSIS DE VITAMINA C', CURRENT_DATE);

INSERT INTO medsoft.services_category
(sc_name, sc_description, sc_created_date)
VALUES('DEPILACION CLINICA 7 SESIONES', 'DEPILACION CLINICA 7 SESIONES', CURRENT_DATE);

INSERT INTO medsoft.services_category
(sc_name, sc_description, sc_created_date)
VALUES('PESTAÑAS PELO A PELO', 'PESTAÑAS PELO A PELO', CURRENT_DATE);

-- INSERT OF SERVICES

INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('CONSULTA MEDICA',1 , 'CONSULTA MEDICA',35 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Reduccion de PAPADA',1 , 'Reduccion de PAPADA',150 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Limpieza Facial Profunda',1 , 'Limpieza Facial Profunda',45 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Hidrafacial (Limpieza facial Premium)',1 , 'Hidrafacial (Limpieza facial Premium)',55 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Peeling Quimico',1 , 'Peeling Quimico',55 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Electroporacion ',1 , 'Electroporacion ',45 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES(' DERMAPEN (microaguja)',1 , ' DERMAPEN (microaguja)',100 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Plasma Rico en Plaquetas (caida cabello)',1 , 'Plasma Rico en Plaquetas (caida cabello)',75 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Plasma Rico en Plaquetas facial con dermapen',1 , 'Plasma Rico en Plaquetas facial con dermapen',100 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Carboxiterapia (Ojeras, Rostro, Papada y Cuello)',1 , 'Carboxiterapia (Ojeras, Rostro, Papada y Cuello)',50 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Verrugas en cuello',1 , 'Verrugas en cuello',60 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Terapia de luz led',1 , 'Terapia de luz led',35 , CURRENT_DATE);



INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Botox 1 area',2 , 'Botox 1 area',150 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Botox 2 areas ',2 , 'Botox 2 areas ',300 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Botox 3 areas',2 , 'Botox 3 areas',350 , CURRENT_DATE);


INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Labios',3 , 'Labios',350 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Ojeras',3 , 'Ojeras',350 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Nariz',3 , 'Nariz',425 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Pomulos ',3 , 'Pomulos ',350 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Menton',3 , 'Menton',350 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Manos',3 , 'Manos',350 , CURRENT_DATE);


INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Rinomodelacion Permanente con Hilos',4 , 'Rinomodelacion Permanente con Hilos',800 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Lifting facial (medio) ROSTRO',4 , 'Lifting facial (medio) ROSTRO',600 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Lifting facial (completo) ROSTRO',4 , 'Lifting facial (completo) ROSTRO',800 , CURRENT_DATE);



INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Carboxiterapia Corporal / 6 SESIONES',5 , 'Carboxiterapia Corporal / 6 SESIONES',210 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Carboxiterapia Corporal / 10 SESIONES',5 , 'Carboxiterapia Corporal / 10 SESIONES',350 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Mesoterapia Reductora grasa abdominal',5 , 'Mesoterapia Reductora grasa abdominal',80 , CURRENT_DATE);


INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Megadosis de Vitamina C',6 , 'Megadosis de Vitamina C',90 , CURRENT_DATE);



INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Axilas ',7 , 'Axilas ',200 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Vigote',7 , 'Vigote',150 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Brazos',7 , 'Brazos',400 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Pierna',7 , 'Pierna',475 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('1/2 Pierna',7 , '1/2 Pierna',400 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Bikini completo',7 , 'Bikini completo',360 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Espalda',7 , 'Espalda',400 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Delineado barba',7 , 'Delineado barba',200 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Patillas',7 , 'Patillas',150 , CURRENT_DATE);


INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Natural',8 , 'Natural',49 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Medium',8 , 'Medium',59 , CURRENT_DATE);
INSERT INTO medsoft.services (sv_name, sv_sc_id, sv_description, sv_cost, sv_created_date) VALUES('Rusas',8 , 'Rusas',69 , CURRENT_DATE);


-- agregando promociones
INSERT INTO medsoft.services_category (sc_name,sc_description) 	VALUES ('PROMOCIONES','PROMOCIONES VARIAS');


COMMIT;







--- NUEVOS CAMBIOS PARA LA NUEVA ENTREGA DE SABADO

ALTER TABLE medsoft.product ALTER COLUMN prd_name TYPE varchar(200) USING prd_name::varchar;
ALTER TABLE medsoft.product ALTER COLUMN prd_description TYPE varchar(200) USING prd_description::varchar;
ALTER TABLE medsoft.product ALTER COLUMN prd_pc_id DROP NOT NULL;

ALTER TABLE medsoft.product ALTER COLUMN prd_code TYPE varchar(50) USING prd_code::varchar;
ALTER TABLE medsoft.product ALTER COLUMN prd_promotion_price DROP NOT NULL;
