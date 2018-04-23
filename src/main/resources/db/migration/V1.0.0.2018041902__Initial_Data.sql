insert into invoice_configuration(id, invoice_type, department, product, project, customer)
values (
'pmb2017','pmb2017',
'7a9112ef-70a0-408f-89bb-efd7dbe0fc73','b07129d6-dd9b-47c4-a6b6-cba9c6680940',
'7cce6262-286d-4dd5-86e2-c3638a81fa44','e7d73eb7-25de-48a0-b1a3-f0b6f9b2416f');

insert into bank (id, account_name, account_number, account_code)
values ('bsm001', 'BSM', '111200600', 'df81d233-2f7e-4a27-86dc-d8d161abd33b');

insert into bank (id, account_name, account_number, account_code)
values ('bnisy001', 'BNI Syariah', '111200400', 'b4f6003c-4c0e-4a2e-8b20-9d36507b3c34');

insert into invoice(id, sales_invoice_number, invoice_number, customer, amount, invoice_status)
values ('719eac42-5ef5-470c-92a5-46b51716564e', '00000087', '2018042301000003', 'e7d73eb7-25de-48a0-b1a3-f0b6f9b2416f', 300000.00, 'UNPAID');
