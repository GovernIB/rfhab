ALTER TABLE rfh_activitat ADD arxiureintents integer NULL;
ALTER TABLE rfh_activitat ADD arxiuestat integer NULL;
ALTER TABLE rfh_activitat ADD arxiudarrerintent timestamp without time zone NULL;

CREATE SEQUENCE rfh_sincrounitats_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE rfh_sincrounitats (
    sincrounitatsid bigint DEFAULT nextval('rfh_sincrounitats_seq'::regclass) NOT NULL,
    datacreacio timestamp without time zone NOT NULL,
    datadarrerasincro timestamp without time zone,
    dataprimerasincro timestamp without time zone,
    codientitat character varying(50) NOT NULL,
    observacions text,
    usuariid bigint
);

ALTER TABLE ONLY rfh_sincrounitats
    ADD CONSTRAINT rfh_sincrounitats_pk PRIMARY KEY (sincrounitatsid);
CREATE INDEX rfh_sincrounitats_pk_i ON rfh_sincrounitats USING btree (sincrounitatsid);
ALTER TABLE ONLY rfh_sincrounitats
    ADD CONSTRAINT rfh_sncunitats_usuari_usuid_fk FOREIGN KEY (usuariid) REFERENCES rfh_usuari(usuariid);
create index rfh_sncunitats_usuariid_fk_i on rfh_sincrounitats USING btree (usuariid);