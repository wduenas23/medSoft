
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
ALTER TABLE medsoft.product ADD prd_lot varchar(50) NULL;
ALTER TABLE medsoft.product DROP CONSTRAINT product_prd_name_key;


CREATE TABLE medsoft.transaction_detail_sales (
	txds_id serial4 NOT NULL,
	txds_td_id int4 NOT NULL,
	txds_prd_id int4 NOT NULL,
	txds_created_date date NOT NULL DEFAULT CURRENT_DATE,
	CONSTRAINT transaction_detail_sales_pkey PRIMARY KEY (txds_id)
);


-- medsoft.transaction_detail_sales foreign keys

ALTER TABLE medsoft.transaction_detail_sales ADD CONSTRAINT transaction_detail_sales_tx_prd_id_fkey FOREIGN KEY (txds_prd_id) REFERENCES medsoft.product(prd_id);
ALTER TABLE medsoft.transaction_detail_sales ADD CONSTRAINT transaction_detail_sales_tx_id_fkey FOREIGN KEY (txds_td_id) REFERENCES medsoft."transaction"(tx_id);

ALTER TABLE medsoft."transaction" ADD tx_sale_comission numeric NULL;



-- Tercera entrega

CREATE TABLE medsoft.parameters (
	pmt_id varchar(50) primary key,
	pmt_value varchar(50) NOT NULL,
	pmt_context varchar(50) NOT NULL,
	pmt_created_date date NOT NULL DEFAULT CURRENT_DATE
);

-- Auto-generated SQL script #202202222217
INSERT INTO medsoft.parameters (pmt_id,pmt_value,pmt_context) VALUES ('COMISION','0.03','COMISION VENTAS');
INSERT INTO medsoft.parameters (pmt_id,pmt_value,pmt_context) VALUES ('COMISION_TARJETA','0.07','COMISION PAGO TARJETA');
INSERT INTO medsoft.parameters (pmt_id,pmt_value,pmt_context) VALUES ('HABILITAR_INVENTARIO','SI','HABILITAR INVENTARIO	');

-- Auto-generated SQL script #202203012242
INSERT INTO medsoft.authorizations (auth_name,auth_parent,auth_display_name,auth_icon,auth_order,auth_created_date,auth_parent_id,auth_link)
	VALUES ('ADMON. PARAMETROS',false,'Admon. Parametros','edit',2.4,'2022-03-01',2,'./parametros');

	-- Auto-generated SQL script #202203012243
INSERT INTO medsoft.role_authorization (rauth_auth_id,rauth_role_id)
	VALUES (13,2);
-- Auto-generated SQL script #202203022151
INSERT INTO medsoft.role_authorization (rauth_auth_id,rauth_role_id)
	VALUES (8,2);
-- Auto-generated SQL script #202203022152
INSERT INTO medsoft.role_authorization (rauth_auth_id,rauth_role_id)
	VALUES (5,3);
	
	

ALTER TABLE medsoft.transaction ADD tx_delete_flag int NULL;
ALTER TABLE medsoft.transaction ADD tx_delete_date date NULL;
ALTER TABLE medsoft.transaction ADD tx_login_user varchar(25) NULL;

ALTER TABLE medsoft.product ADD prd_login_user varchar(25) NULL;

CREATE TABLE medsoft.product_audit (
	prd_audit_id serial primary key,
	prd_id int4 NULL,
	prd_code varchar(50) NULL,
	prd_pc_id int4 NULL,
	prd_ft_id int4 NULL,
	prd_name varchar(200) NULL,
	prd_description varchar(200) NULL,
	prd_inventory int4  NULL,
	prd_inventory_new int4  NULL,
	prd_expiration date NULL,
	prd_expiration_new date NULL,
	prd_image_url varchar(200) NULL,
	prd_cost numeric NULL,
	prd_cost_new numeric NULL,
	prd_selling_price numeric NULL,
	prd_selling_price_new numeric NULL,
	prd_promotion_price numeric NULL,
	prd_active bool NULL,
	prd_created_date date  NULL DEFAULT CURRENT_DATE,
	prd_lot varchar(50) NULL,
	prd_audit_login_user varchar(25) null,
	prd_audit_created_date date  NULL DEFAULT CURRENT_DATE
);

CREATE OR REPLACE FUNCTION product_audit()
  RETURNS TRIGGER 
  LANGUAGE PLPGSQL
  AS
$$
BEGIN
	
	INSERT INTO medsoft.product_audit
	(prd_id, prd_code, prd_pc_id, prd_ft_id, prd_name, prd_description, prd_inventory,prd_inventory_new, prd_expiration,prd_expiration_new, prd_image_url, 
	prd_cost,prd_cost_new, prd_selling_price,prd_selling_price_new, prd_promotion_price, prd_active, prd_created_date, prd_lot, prd_audit_login_user, prd_audit_created_date)
	VALUES(old.prd_id, old.prd_code ,old.prd_pc_id, old.prd_ft_id,old.prd_name,old.prd_description,old.prd_inventory,new.prd_inventory,old.prd_expiration,new.prd_expiration,old.prd_image_url,
	old.prd_cost,new.prd_cost, old.prd_selling_price,new.prd_selling_price,old.prd_promotion_price, old.prd_active, old.prd_created_date, old.prd_lot, new.prd_login_user, CURRENT_DATE);

	RETURN NEW;
END;
$$

CREATE TRIGGER product_audit 
  BEFORE UPDATE
  ON  medsoft.product
  FOR EACH ROW
  EXECUTE PROCEDURE product_audit();

