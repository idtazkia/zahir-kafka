create table invoice_configuration(
  id VARCHAR (36),
  invoice_type varchar(255) not null,
  department varchar(255) not null,
  product varchar(255) not null,
  customer varchar(255),
  primary key (id)
);

create table project(
  id VARCHAR(36),
  code VARCHAR(255) not null,
  name varchar (255) not null,
  primary key (id),
  unique (code)
);

create table invoice (
  id VARCHAR (36),
  sales_invoice_number varchar (255) not null ,
  invoice_number varchar (255) not null ,
  customer varchar (255) not null ,
  amount numeric (19,2) not null ,
  invoice_status varchar (255) not null ,
  primary key (id),
  unique (invoice_number)
);

create table bank (
  id varchar (36),
  account_name varchar (255) not null,
  account_number varchar (255) not null ,
  account_code varchar (255) not null ,
  primary key (id),
  unique (account_name),
  unique (account_number),
  unique (account_code)
);

create table invoice_payment (
  id varchar (36),
  id_invoice varchar(36) not null ,
  id_bank varchar (36) not null ,
  payment_time timestamp not null ,
  reference_number varchar (36) not null,
  amount numeric (19,2) not null,
  primary key (id),
  foreign key (id_invoice) references invoice(id),
  foreign key (id_bank) references bank(id)
);