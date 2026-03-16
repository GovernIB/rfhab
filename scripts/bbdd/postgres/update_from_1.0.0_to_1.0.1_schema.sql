ALTER TABLE rfh_lloc ALTER COLUMN codilloc DROP NOT NULL;
ALTER TABLE rfh_lloc ADD CONSTRAINT rfh_lloc_codillocpropi_uk UNIQUE (codillocpropi);

ALTER TABLE rfh_rol RENAME TO rfh_habilitacio;
ALTER TABLE rfh_habilitacio RENAME COLUMN rolid TO habilitacioid;
ALTER SEQUENCE rfh_rol_seq RENAME TO rfh_habilitacio_seq;
ALTER TABLE rfh_habilitacio ALTER COLUMN habilitacioid SET DEFAULT nextval('rfh_habilitacio_seq'::regclass);
ALTER TABLE rfh_habilitacio RENAME CONSTRAINT rfh_rol_pk TO rfh_habilitacio_pk;
ALTER TABLE rfh_habilitacio RENAME CONSTRAINT rfh_rol_traduccio_nomid_fk TO rfh_habil_traduccio_nomid_fk;
ALTER INDEX rfh_rol_nomid_fk_i RENAME TO rfh_habilitacio_nomid_fk_i;
ALTER INDEX rfh_rol_pk_i RENAME TO rfh_habilitacio_pk_i;

ALTER TABLE rfh_llocrol RENAME TO rfh_llochabilitacio;
ALTER TABLE rfh_llochabilitacio RENAME COLUMN llocrolid TO llochabilitacioid;
ALTER SEQUENCE rfh_llocrol_seq RENAME TO rfh_llochabilitacio_seq;
ALTER TABLE rfh_llochabilitacio ALTER COLUMN llochabilitacioid SET DEFAULT nextval('rfh_llochabilitacio_seq'::regclass);
ALTER TABLE rfh_llochabilitacio RENAME COLUMN rolid TO habilitacioid;
ALTER TABLE rfh_llochabilitacio RENAME CONSTRAINT rfh_llocrol_pk TO rfh_llochabilitacio_pk;
ALTER TABLE rfh_llochabilitacio RENAME CONSTRAINT rfh_llocrol_lloc_llocid_fk TO rfh_llochabil_lloc_llocid_fk;
ALTER TABLE rfh_llochabilitacio RENAME CONSTRAINT rfh_llocrol_rol_rolid_fk TO rfh_llochabil_hab_habid_fk;
ALTER INDEX rfh_llocrol_llocid_fk_i RENAME TO rfh_llochabil_llocid_fk_i;
ALTER INDEX rfh_llocrol_pk_i RENAME TO rfh_llochabilitacio_pk_i;
ALTER INDEX rfh_llocrol_rolid_fk_i RENAME TO rfh_llochabil_hab_habid_fk_i;
