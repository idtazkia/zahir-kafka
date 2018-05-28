create table incoming_invoice (
  id varchar (36),
  status varchar(30) not null,
  sukses boolean,
  debitur varchar(100),
  jenis_tagihan varchar(100),
  kode_biaya varchar(100),
  nomor_tagihan varchar(100),
  keterangan varchar(100),
  tanggal_tagihan date,
  tanggal_jatuh_tempo date,
  nilai_tagihan numeric (19,2),
  primary key (id)
);
