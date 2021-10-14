create database wecode;
create user usr_appmedsoft with PASSWORD '@ppu3r';
create schema medsoft;
GRANT CONNECT  on database   wecode to usr_appmedsoft;
grant select, insert, update, delete on all tables in schema medsoft to usr_appmedsoft;


create table medsoft.PAYMENT_TYPE(
	PT_ID serial primary key,
	PT_NAME VARCHAR(50) unique not null,
	PT_FEE_PER_USE numeric null,
	PT_DESCRIPTION VARCHAR(50) null,
	PT_CREATED_DATE date not null default CURRENT_DATE 
);

create table medsoft.SERVICES_CATEGORY(
	SC_ID SERIAL primary key,
	SC_NAME VARCHAR(50) unique not null,
	SC_DESCRIPTION VARCHAR(50) null,
	SC_CREATED_DATE date not null default CURRENT_DATE 
);

create table medsoft.SERVICES(
	SV_ID SERIAL primary key,
	SV_NAME VARCHAR(200) unique not null,
	SV_SC_ID INT not null,
	SV_DESCRIPTION VARCHAR(200) not null,
	SV_COST numeric not null,
	SV_CREATED_DATE DATE not null default current_DATE,
	FOREIGN KEY (SV_SC_ID)
      REFERENCES SERVICES_CATEGORY (SC_ID)
);


create table medsoft.TANSACTION_CATEGORY(
	TC_ID SERIAL primary key,
	TC_NAME VARCHAR(50) unique not null,
	TC_DESCRIPTION VARCHAR(50) null,
	TC_CREATED_DATE date not null default CURRENT_DATE
);

create table medsoft.TRANSACTION(
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
      REFERENCES TANSACTION_CATEGORY (TC_ID),
    foreign key (TX_PT_ID)
      references PAYMENT_TYPE(PT_ID)
);

create table medsoft.TRANSACTION_DETAIL(
	TXD_ID SERIAL primary key,
	TXD_TD_ID INT not null,
	TX_SV_ID INT not null,
	TX_CREATED_DATE date not null default CURRENT_DATE,
	FOREIGN KEY (TXD_TD_ID)
      REFERENCES TRANSACTION (TX_ID),
    FOREIGN KEY (TX_SV_ID)
      REFERENCES SERVICES (SV_ID)
);