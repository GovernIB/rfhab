ALTER TABLE rfh_activitat ADD arxiureintents number(10,0) NULL;
ALTER TABLE rfh_activitat ADD arxiuestat number(10,0) NULL;
ALTER TABLE rfh_activitat ADD arxiudarrerintent timestamp NULL;

create sequence rfh_sincrounitats_seq start with 1000 increment by  1;
create table rfh_sincrounitats (
    sincrounitatsid number(19,0) not null,
    datacreacio timestamp not null,
    datadarrerasincro timestamp,
    dataprimerasincro timestamp,
    codientitat varchar2(50 char) not null,
    observacions clob,
    usuariid number(19,0) not null
);
alter table rfh_sincrounitats add constraint rfh_sincrounitats_pk primary key (sincrounitatsid);
alter table rfh_sincrounitats 
    add constraint rfh_sncunitats_usuari_usuid_fk 
    foreign key (usuariid) 
    references rfh_usuari;
create index rfh_sncunitats_usuariid_fk_i on rfh_sincrounitats (usuariid);
grant select on rfh_sincrounitats_seq to www_rfhab;
grant select,insert,delete,update on rfh_sincrounitats to www_rfhab;

ALTER TABLE RFH_LLOCHABILITACIO RENAME CONSTRAINT rfh_llochab_hab_habilitacid_fk TO rfh_llochabil_hab_habid_fk;
