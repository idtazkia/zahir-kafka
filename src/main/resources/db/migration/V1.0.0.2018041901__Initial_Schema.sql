create table invoice_configuration(
  id VARCHAR (36),
  invoice_type varchar(255) not null,
  department varchar(255) not null,
  product varchar(255) not null,
  project varchar(255) not null,
  customer varchar(255),
  primary key (id)
);

create table invoice (
  id VARCHAR (36),
  sales_invoice varchar (255) not null ,
  sales_invoice_number varchar (255) not null ,
  invoice_number varchar (255) not null ,
  customer varchar (255) not null ,
  amount numeric (19,2) not null ,
  primary key (id)
);