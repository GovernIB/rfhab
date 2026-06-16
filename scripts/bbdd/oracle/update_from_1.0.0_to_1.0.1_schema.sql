ALTER TABLE RFH_LLOC ALTER COLUMN codilloc DROP NOT NULL;
ALTER TABLE RFH_LLOC ADD CONSTRAINT rfh_lloc_codillocpropi_uk UNIQUE (codillocpropi);

ALTER TABLE RFH_ROL RENAME TO rfh_habilitacio;
ALTER TABLE RFH_HABILITACIO RENAME COLUMN rolid TO habilitacioid;
ALTER SEQUENCE RFH_ROL_SEQ RENAME TO rfh_habilitacio_seq;
ALTER TABLE RFH_HABILITACIO ALTER COLUMN habilitacioid SET DEFAULT nextval('rfh_habilitacio_seq'::regclass);
ALTER TABLE RFH_HABILITACIO RENAME CONSTRAINT rfh_rol_pk TO rfh_habilitacio_pk;
ALTER TABLE RFH_HABILITACIO RENAME CONSTRAINT rfh_rol_traduccio_nomid_fk TO rfh_habil_traduccio_nomid_fk;
ALTER INDEX RFH_ROL_NOMID_FK_I RENAME TO rfh_habilitacio_nomid_fk_i;
--ALTER INDEX RFH_ROL_PK_I RENAME TO rfh_habilitacio_pk_i;

ALTER TABLE RFH_LLOCROL RENAME TO rfh_llochabilitacio;
ALTER TABLE RFH_LLOCHABILITACIO RENAME COLUMN llocrolid TO llochabilitacioid;
ALTER SEQUENCE RFH_LLOCROL_SEQ RENAME TO rfh_llochabilitacio_seq;
ALTER TABLE RFH_LLOCHABILITACIO ALTER COLUMN llochabilitacioid SET DEFAULT nextval('rfh_llochabilitacio_seq'::regclass);
ALTER TABLE RFH_LLOCHABILITACIO RENAME COLUMN rolid TO habilitacioid;
ALTER TABLE RFH_LLOCHABILITACIO RENAME CONSTRAINT rfh_llocrol_pk TO rfh_llochabilitacio_pk;
ALTER TABLE RFH_LLOCHABILITACIO RENAME CONSTRAINT rfh_llocrol_lloc_llocid_fk TO rfh_llochabil_lloc_llocid_fk;
ALTER TABLE RFH_LLOCHABILITACIO RENAME CONSTRAINT rfh_llocrol_rol_rolid_fk TO rfh_llochab_hab_habilitacid_fk;
ALTER INDEX RFH_LLOCROL_LLOCID_FK_I RENAME TO rfh_llochabil_llocid_fk_i;
--ALTER INDEX RFH_LLOCROL_PK_I RENAME TO rfh_llochabilitacio_pk_i;
ALTER INDEX RFH_LLOCROL_ROLID_FK_I RENAME TO rfh_llochabil_hab_habid_fk_i;
