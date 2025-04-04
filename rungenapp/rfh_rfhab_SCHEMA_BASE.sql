
CREATE SEQUENCE rfh_fitxer_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_traduccio_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_activitat_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_autoritzacio_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_entitat_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_funcionarientitat_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_usuarientitat_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_funcionari_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_historic_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_operacio_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_plugin_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_pluginentitat_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE rfh_usuari_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;

CREATE TABLE rfh_fitxer (
    fitxerid bigint DEFAULT nextval('rfh_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);

CREATE TABLE rfh_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);

CREATE TABLE rfh_traduccio (
    traduccioid bigint DEFAULT nextval('rfh_traduccio_seq'::regclass) NOT NULL
);

CREATE TABLE rfh_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);

CREATE TABLE rfh_activitat (
    activitatid bigint DEFAULT nextval('rfh_activitat_seq'::regclass) NOT NULL,
    funcionariid bigint NOT NULL,
    tipus integer NOT NULL,
    registre character varying(50),
    tramit character varying(150),
    codisia character varying(150),
    autoritzacioid bigint,
	datacreacio timestamp without time zone NOT NULL
);

CREATE TABLE rfh_autoritzacio (
    autoritzacioid bigint DEFAULT nextval('rfh_autoritzacio_seq'::regclass) NOT NULL,
    funcionariid bigint NOT NULL,
    codisia character varying(50) NOT NULL,
    procediment character varying(255) NOT NULL,
    cai character varying(255) NOT NULL,
    datainici date,
    datafi date,
    observacions text,
    datacreacio timestamp without time zone NOT NULL
);

CREATE TABLE rfh_entitat (
    entitatid bigint DEFAULT nextval('rfh_entitat_seq'::regclass) NOT NULL,
    codidir3 character varying(15) NOT NULL,
    nom character varying(255),
    actiu boolean,
    email character varying(255)
);

CREATE TABLE rfh_funcionarientitat (
    funcionarientitatid bigint DEFAULT nextval('rfh_funcionarientitat_seq'::regclass) NOT NULL,
    entitatid bigint NOT NULL,
    funcionariid bigint NOT NULL
);

CREATE TABLE rfh_usuarientitat (
    usuarientitatid bigint DEFAULT nextval('rfh_usuarientitat_seq'::regclass) NOT NULL,
    entitatid bigint NOT NULL,
    usuariid bigint NOT NULL,
	actiu boolean NOT NULL
);

CREATE TABLE rfh_funcionari (
    funcionariid bigint DEFAULT nextval('rfh_funcionari_seq'::regclass) NOT NULL,
    numero integer NOT NULL,
    nom character varying(255) NOT NULL,
    llinatge1 character varying(255) NOT NULL,
    llinatge2 character varying(255),
    identificador character varying(50) NOT NULL,
    tipusidentificador integer NOT NULL,
    usuari character varying(50) NOT NULL,
    correu character varying(255) NOT NULL,
    codidir3 character varying(15) NOT NULL,
    codillocfeina character varying(50),
    nomllocfeina character varying(255),
    rol integer NOT NULL,
    personaloamr boolean NOT NULL,
    datacreacio timestamp without time zone NOT NULL
);

CREATE TABLE rfh_historic (
    historicid bigint DEFAULT nextval('rfh_historic_seq'::regclass) NOT NULL,
    funcionariid bigint NOT NULL,
    operacioid bigint NOT NULL,
    numerocai character varying(50) NOT NULL,
    observacions text,
    datacreacio timestamp without time zone NOT NULL
);

CREATE TABLE rfh_operacio (
    operacioid bigint DEFAULT nextval('rfh_operacio_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    datacreacio timestamp without time zone NOT NULL
);

CREATE TABLE rfh_plugin (
    pluginid bigint DEFAULT nextval('rfh_plugin_seq'::regclass) NOT NULL,
    classe character varying(255),
    properties text,
    nom character varying(255) NOT NULL,
    descripcio character varying(255) NOT NULL,
    actiu boolean NOT NULL,
    entitatid bigint NOT NULL,
	datacreacio timestamp without time zone
);

CREATE TABLE rfh_pluginentitat (
	pluginentitatid bigint DEFAULT nextval('rfh_pluginentitat_seq'::regclass) NOT NULL,
	pluginid bigint NOT NULL,
	entitatid bigint NOT NULL,
	actiu boolean NOT NULL
);

CREATE TABLE rfh_usuari (
    usuariid bigint DEFAULT nextval('rfh_usuari_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    llinatge1 character varying(255) NOT NULL,
    llinatge2 character varying(255),
    nif character varying(50) NOT NULL,
    correu character varying(255) NOT NULL,
	idiomaid character varying(5) NOT NULL,
    actiu boolean NOT NULL,
    datacreacio timestamp without time zone NOT NULL
);

ALTER TABLE ONLY rfh_fitxer
    ADD CONSTRAINT rfh_fitxer_pk PRIMARY KEY (fitxerid);

ALTER TABLE ONLY rfh_idioma
    ADD CONSTRAINT rfh_idioma_pk PRIMARY KEY (idiomaid);

ALTER TABLE ONLY rfh_traduccio
    ADD CONSTRAINT rfh_traduccio_pk PRIMARY KEY (traduccioid);
	
ALTER TABLE ONLY rfh_activitat
    ADD CONSTRAINT rfh_activitat_pk PRIMARY KEY (activitatid);
	
ALTER TABLE ONLY rfh_autoritzacio
    ADD CONSTRAINT rfh_autoritzacio_pk PRIMARY KEY (autoritzacioid);

ALTER TABLE ONLY rfh_entitat
    ADD CONSTRAINT rfh_entitat_pk PRIMARY KEY (entitatid);

ALTER TABLE ONLY rfh_traducciomap
    ADD CONSTRAINT rfh_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);
	
ALTER TABLE ONLY rfh_funcionarientitat
    ADD CONSTRAINT rfh_funcionarientitat_pk PRIMARY KEY (funcionarientitatid);

ALTER TABLE ONLY rfh_usuarientitat
    ADD CONSTRAINT rfh_usuarientitat_pk PRIMARY KEY (usuarientitatid);

ALTER TABLE ONLY rfh_funcionari
    ADD CONSTRAINT rfh_funcionari_pk PRIMARY KEY (funcionariid);
	
ALTER TABLE ONLY rfh_historic
    ADD CONSTRAINT rfh_historic_pk PRIMARY KEY (historicid);

ALTER TABLE ONLY rfh_operacio
    ADD CONSTRAINT rfh_operacio_pk PRIMARY KEY (operacioid);
	
ALTER TABLE ONLY rfh_plugin
    ADD CONSTRAINT rfh_plugin_pk PRIMARY KEY (pluginid);
	
ALTER TABLE ONLY rfh_pluginentitat
    ADD CONSTRAINT rfh_pluginentitat_pk PRIMARY KEY (pluginentitatid);
	
ALTER TABLE ONLY rfh_usuari
    ADD CONSTRAINT rfh_usuari_pk PRIMARY KEY (usuariid);

CREATE INDEX rfh_fitxer_pk_i ON rfh_fitxer USING btree (fitxerid);

CREATE INDEX rfh_idioma_pk_i ON rfh_idioma USING btree (idiomaid);

CREATE INDEX rfh_traduccio_pk_i ON rfh_traduccio USING btree (traduccioid);

CREATE INDEX rfh_traducciomap_idiomaid_fk_i ON rfh_traducciomap USING btree (idiomaid);

CREATE INDEX rfh_traducciomap_pk_i ON rfh_traducciomap USING btree (traducciomapid);

CREATE INDEX rfh_activitat_pk_i ON rfh_activitat USING btree (activitatid);

CREATE INDEX rfh_autoritzacio_pk_i ON rfh_autoritzacio USING btree (autoritzacioid);

CREATE INDEX rfh_entitat_pk_i ON rfh_entitat USING btree (entitatid);

CREATE INDEX rfh_funcionarientitat_pk_i ON rfh_funcionarientitat USING btree (funcionarientitatid);

CREATE INDEX rfh_usuarientitat_pk_i ON rfh_usuarientitat USING btree (usuarientitatid);

CREATE INDEX rfh_funcionari_pk_i ON rfh_funcionari USING btree (funcionariid);
	
CREATE INDEX rfh_historic_pk_i ON rfh_historic USING btree (historicid);

CREATE INDEX rfh_operacio_pk_i ON rfh_operacio USING btree (operacioid);
	
CREATE INDEX rfh_plugin_pk_i ON rfh_plugin USING btree (pluginid);

CREATE INDEX rfh_pluginentitat_pk_i ON rfh_pluginentitat USING btree (pluginentitatid);
	
CREATE INDEX rfh_usuari_pk_i ON rfh_usuari USING btree (usuariid);

ALTER TABLE ONLY rfh_funcionarientitat
    ADD CONSTRAINT rfh_funent_ent_fun_uk UNIQUE (entitatid, funcionariid);

ALTER TABLE ONLY rfh_usuarientitat
    ADD CONSTRAINT rfh_usuent_usuari_entitat_uk UNIQUE (entitatid, usuariid);
	
ALTER TABLE ONLY rfh_pluginentitat
    ADD CONSTRAINT rfh_pluent_plugin_entitat_uk UNIQUE (pluginid, entitatid);

ALTER TABLE ONLY rfh_traducciomap
    ADD CONSTRAINT rfh_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES rfh_traduccio(traduccioid);

ALTER TABLE ONLY rfh_activitat
    ADD CONSTRAINT rfh_act_autoritzacioid_fk FOREIGN KEY (autoritzacioid) REFERENCES rfh_autoritzacio(autoritzacioid);

ALTER TABLE ONLY rfh_activitat
    ADD CONSTRAINT rfh_act_funcionariid_fk FOREIGN KEY (funcionariid) REFERENCES rfh_funcionari(funcionariid);

ALTER TABLE ONLY rfh_autoritzacio
    ADD CONSTRAINT rfh_aut_funcionariid_fk FOREIGN KEY (funcionariid) REFERENCES rfh_funcionari(funcionariid);

ALTER TABLE ONLY rfh_funcionarientitat
    ADD CONSTRAINT rfh_funent_entitatid_fk FOREIGN KEY (entitatid) REFERENCES rfh_entitat(entitatid);

ALTER TABLE ONLY rfh_funcionarientitat
    ADD CONSTRAINT rfh_funent_funcionariid_fk FOREIGN KEY (funcionariid) REFERENCES rfh_funcionari(funcionariid);

ALTER TABLE ONLY rfh_usuarientitat
    ADD CONSTRAINT rfh_usuent_entitatid_fk FOREIGN KEY (entitatid) REFERENCES rfh_entitat(entitatid);

ALTER TABLE ONLY rfh_usuarientitat
    ADD CONSTRAINT rfh_usuent_usuariid_fk FOREIGN KEY (usuariid) REFERENCES rfh_usuari(usuariid);

ALTER TABLE ONLY rfh_historic
    ADD CONSTRAINT rfh_historic_funcionariid_fk FOREIGN KEY (funcionariid) REFERENCES rfh_funcionari(funcionariid);
	
ALTER TABLE ONLY rfh_historic
    ADD CONSTRAINT rfh_historic_operacioid_fk FOREIGN KEY (operacioid) REFERENCES rfh_operacio(operacioid);

ALTER TABLE ONLY rfh_plugin
    ADD CONSTRAINT rfh_plugin_entitatid_fk FOREIGN KEY (entitatid) REFERENCES rfh_entitat(entitatid);
	
ALTER TABLE ONLY rfh_pluginentitat
	ADD CONSTRAINT rfh_pluent_pluginid_fk FOREIGN KEY (pluginid) REFERENCES rfh_plugin(pluginid);
	
ALTER TABLE ONLY rfh_pluginentitat
	ADD CONSTRAINT rfh_pluent_entitatid_fk FOREIGN KEY (entitatid) REFERENCES rfh_entitat(entitatid);

INSERT INTO rfh_idioma(idiomaid, nom, ordre) VALUES ('ca', 'Català', 0);
INSERT INTO rfh_idioma(idiomaid, nom, ordre) VALUES ('es', 'Castellano', 1);
    
    
