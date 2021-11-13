create database wecode;
create user usr_appmedsoft with PASSWORD '@ppu3r';
create schema medsoft;
GRANT CONNECT  on database   wecode to usr_appmedsoft;
GRANT USAGE ON SCHEMA medsoft TO usr_appmedsoft;
grant select, insert, update, delete on all tables in schema medsoft to usr_appmedsoft;


create table medsoft.payment_type(
	PT_ID serial primary key,
	PT_NAME VARCHAR(50) unique not null,
	PT_FEE_PER_USE numeric null,
	PT_DESCRIPTION VARCHAR(50) null,
	PT_CREATED_DATE date not null default CURRENT_DATE 
);

create table medsoft.services_category (
	SC_ID SERIAL primary key,
	SC_NAME VARCHAR(50) unique not null,
	SC_DESCRIPTION VARCHAR(50) null,
	SC_CREATED_DATE date not null default CURRENT_DATE 
);


create table medsoft.services(
	SV_ID SERIAL primary key,
	SV_NAME VARCHAR(200) unique not null,
	SV_SC_ID INT not null,
	SV_DESCRIPTION VARCHAR(200) not null,
	SV_COST numeric not null,
	SV_VALID boolean NOT NULL DEFAULT true,
	SV_CREATED_DATE DATE not null default current_DATE,
	FOREIGN KEY (SV_SC_ID)
      REFERENCES medsoft.services_category (SC_ID)
);

create table medsoft.transaction_category(
	TC_ID SERIAL primary key,
	TC_NAME VARCHAR(50) unique not null,
	TC_DESCRIPTION VARCHAR(50) null,
	TC_CREATED_DATE date not null default CURRENT_DATE
);

create table medsoft.transaction(
	TX_ID SERIAL primary key,
	TX_TC_ID INT not null,
	TX_PT_ID INT not null,
	TX_DATE TIMESTAMP not null,
	TX_TRANS_SUBTOTAL numeric not null,
	TX_DISCOUNT_PERCENTAGE numeric null,
	TX_TRANS_DISCOUNT numeric null,
	TX_TRANS_CLIENT_TOTAL numeric not null,
	TX_TRANS_FEE numeric null,
	TX_TRANS_TOTAL numeric not null,	
	FOREIGN KEY (TX_TC_ID)
      REFERENCES medsoft.transaction_category (TC_ID),
    foreign key (TX_PT_ID)
      references medsoft.payment_type(PT_ID)
);

create table medsoft.transaction_detail (
	TXD_ID SERIAL primary key,
	TXD_TD_ID INT not null,
	TX_SV_ID INT not null,
	TX_CREATED_DATE date not null default CURRENT_DATE,
	FOREIGN KEY (TXD_TD_ID)
      REFERENCES medsoft.transaction (TX_ID),
    FOREIGN KEY (TX_SV_ID)
      REFERENCES medsoft.services (SV_ID)
);


create table medsoft.product_category(
	PC_ID serial primary key,
	PC_NAME VARCHAR(50) unique not null,
	PC_DESCRIPTION VARCHAR(50) null,
	PC_CREATED_DATE date not null default CURRENT_DATE 
);

create table medsoft.product(
	PRD_ID serial primary key,
	PRD_CODE VARCHAR(10) NULL,
	PRD_PC_ID INT not null,
	PRD_NAME VARCHAR(50) unique not null,
	PRD_DESCRIPTION VARCHAR(50) null,
	PRD_INVENTORY int not null,
	PRD_COST numeric not null,
	PRD_SELLING_PRICE numeric not null,
	PRD_VALID boolean NOT NULL DEFAULT true,
	PC_CREATED_DATE date not null default CURRENT_DATE,
	FOREIGN KEY (PRD_PC_ID)
      REFERENCES medsoft.product_category (PC_ID)
);

ALTER TABLE medsoft.transaction ADD tx_nombres varchar(200) NULL;
ALTER TABLE medsoft.transaction ADD tx_apellidos varchar(200) NULL;
ALTER TABLE medsoft.transaction ADD tx_telefono varchar(9) NULL;
