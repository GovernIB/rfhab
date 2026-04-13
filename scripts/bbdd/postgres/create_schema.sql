--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-10-09 13:22:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 217 (class 1259 OID 16386)
-- Name: rfh_activitat_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_activitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_table_access_method = heap;

--
-- TOC entry 234 (class 1259 OID 16403)
-- Name: rfh_activitat; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_activitat (
    activitatid bigint DEFAULT nextval('public.rfh_activitat_seq'::regclass) NOT NULL,
    arxiudocumentid character varying(255),
    arxiuexpedientid character varying(255),
    autoritzacioid bigint,
    codisia character varying(150),
    datacreacio timestamp without time zone NOT NULL,
    estat integer NOT NULL,
    funcionariid bigint NOT NULL,
    interessatidentificacio character varying(50),
    interessatllinatge1 character varying(255),
    interessatllinatge2 character varying(255),
    interessatnom character varying(255),
    interessattipus integer,
    registre character varying(50),
    representantidentificacio character varying(50),
    representantllinatge1 character varying(255),
    representantllinatge2 character varying(255),
    representantnom character varying(255),
    representanttipus integer,
    tipus integer NOT NULL,
    tramit character varying(150),
    tramitversio integer,
    url character varying(255),
    dataactivitat timestamp without time zone NOT NULL,
    idactuaciotramit character varying(255),
    procediment character varying(150)
);


--
-- TOC entry 218 (class 1259 OID 16387)
-- Name: rfh_autoritzacio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_autoritzacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 235 (class 1259 OID 16410)
-- Name: rfh_autoritzacio; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_autoritzacio (
    autoritzacioid bigint DEFAULT nextval('public.rfh_autoritzacio_seq'::regclass) NOT NULL,
    cai character varying(255) NOT NULL,
    codisia character varying(50) NOT NULL,
    datacreacio timestamp without time zone NOT NULL,
    datafi date,
    datainici date,
    funcionariid bigint,
    llocid bigint NOT NULL,
    observacions text,
    procediment character varying(255) NOT NULL,
    usuariid bigint
);


--
-- TOC entry 219 (class 1259 OID 16388)
-- Name: rfh_digitalib_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_digitalib_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 236 (class 1259 OID 16417)
-- Name: rfh_digitalib; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_digitalib (
    digitalid bigint DEFAULT nextval('public.rfh_digitalib_seq'::regclass) NOT NULL,
    datacreacio timestamp without time zone,
    entitatid bigint NOT NULL,
    fileinfo text,
    fitxerid bigint NOT NULL,
    metadades text,
    missatge text,
    signedfileinfo text,
    status bigint NOT NULL,
    transactionid bigint,
    transactionwebid character varying(255),
    usuariid bigint NOT NULL
);


--
-- TOC entry 220 (class 1259 OID 16389)
-- Name: rfh_entitat_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_entitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 237 (class 1259 OID 16424)
-- Name: rfh_entitat; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_entitat (
    entitatid bigint DEFAULT nextval('public.rfh_entitat_seq'::regclass) NOT NULL,
    actiu boolean NOT NULL,
    databaixa timestamp without time zone,
    nom character varying(255),
    unitatid bigint
);


--
-- TOC entry 221 (class 1259 OID 16390)
-- Name: rfh_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 238 (class 1259 OID 16429)
-- Name: rfh_fitxer; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_fitxer (
    fitxerid bigint DEFAULT nextval('public.rfh_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


--
-- TOC entry 222 (class 1259 OID 16391)
-- Name: rfh_funcionari_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_funcionari_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 239 (class 1259 OID 16436)
-- Name: rfh_funcionari; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_funcionari (
    funcionariid bigint DEFAULT nextval('public.rfh_funcionari_seq'::regclass) NOT NULL,
    correu character varying(255) NOT NULL,
    databaixa timestamp without time zone,
    datacreacio timestamp without time zone NOT NULL,
    entitatid bigint NOT NULL,
    identificador character varying(50) NOT NULL,
    llinatge1 character varying(255) NOT NULL,
    llinatge2 character varying(255),
    nom character varying(255) NOT NULL,
    numero character varying(10) NOT NULL,
    observacions text,
    tipusidentificador integer NOT NULL,
    usuari character varying(50) NOT NULL
);


--
-- TOC entry 223 (class 1259 OID 16392)
-- Name: rfh_funcionarilloc_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_funcionarilloc_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 240 (class 1259 OID 16443)
-- Name: rfh_funcionarilloc; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_funcionarilloc (
    funcionarillocid bigint DEFAULT nextval('public.rfh_funcionarilloc_seq'::regclass) NOT NULL,
    datacreacio timestamp without time zone NOT NULL,
    datafi date,
    datainici date,
    funcionariid bigint NOT NULL,
    llocid bigint NOT NULL,
    usuariid bigint,
    numerocai character varying(50)
);


--
-- TOC entry 224 (class 1259 OID 16393)
-- Name: rfh_historic_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_historic_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 241 (class 1259 OID 16448)
-- Name: rfh_historic; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_historic (
    historicid bigint DEFAULT nextval('public.rfh_historic_seq'::regclass) NOT NULL,
    datacreacio timestamp without time zone NOT NULL,
    funcionariid bigint NOT NULL,
    numerocai character varying(50) NOT NULL,
    observacions text,
    usuariid bigint
);


--
-- TOC entry 225 (class 1259 OID 16394)
-- Name: rfh_historiclloc_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_historiclloc_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 242 (class 1259 OID 16455)
-- Name: rfh_historiclloc; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_historiclloc (
    historicllocid bigint DEFAULT nextval('public.rfh_historiclloc_seq'::regclass) NOT NULL,
    datacreacio timestamp without time zone NOT NULL,
    llocid bigint NOT NULL,
    numerocai character varying(50) NOT NULL,
    observacions text,
    usuariid bigint
);


--
-- TOC entry 243 (class 1259 OID 16462)
-- Name: rfh_idioma; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    ordre integer DEFAULT 0 NOT NULL,
    suportat boolean DEFAULT true NOT NULL
);


--
-- TOC entry 226 (class 1259 OID 16395)
-- Name: rfh_lloc_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_lloc_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 244 (class 1259 OID 16468)
-- Name: rfh_lloc; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_lloc (
    llocid bigint DEFAULT nextval('public.rfh_lloc_seq'::regclass) NOT NULL,
    codilloc character varying(50),
    databaixa timestamp without time zone,
    datacreacio timestamp without time zone NOT NULL,
    entitatid bigint NOT NULL,
    nom character varying(255) NOT NULL,
    observacions text,
    personaloamr integer NOT NULL,
    unitatid bigint NOT NULL,
    dataalta timestamp without time zone,
    codillocpropi character varying(50) NOT NULL,
    expansio character varying(50)
);


--
-- TOC entry 227 (class 1259 OID 16396)
-- Name: rfh_llochabilitacio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_llochabilitacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 245 (class 1259 OID 16475)
-- Name: rfh_llochabilitacio; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_llochabilitacio (
    llochabilitacioid bigint DEFAULT nextval('public.rfh_llochabilitacio_seq'::regclass) NOT NULL,
    datacreacio timestamp without time zone NOT NULL,
    llocid bigint NOT NULL,
    habilitacioid bigint NOT NULL
);


--
-- TOC entry 228 (class 1259 OID 16397)
-- Name: rfh_plugin_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_plugin_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 246 (class 1259 OID 16480)
-- Name: rfh_plugin; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_plugin (
    pluginid bigint DEFAULT nextval('public.rfh_plugin_seq'::regclass) NOT NULL,
    actiu boolean NOT NULL,
    classe character varying(255),
    datacreacio timestamp without time zone,
    descripcio character varying(255) NOT NULL,
    entitatid bigint NOT NULL,
    nom character varying(255) NOT NULL,
    properties text,
    tipus character varying(50) NOT NULL
);


--
-- TOC entry 229 (class 1259 OID 16398)
-- Name: rfh_habilitacio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_habilitacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 247 (class 1259 OID 16487)
-- Name: rfh_habilitacio; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_habilitacio (
    habilitacioid bigint DEFAULT nextval('public.rfh_habilitacio_seq'::regclass) NOT NULL,
    codi character varying(50) NOT NULL,
    datacreacio timestamp without time zone,
    entitatid bigint NOT NULL,
    nomid bigint NOT NULL
);


--
-- TOC entry 230 (class 1259 OID 16399)
-- Name: rfh_traduccio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 248 (class 1259 OID 16492)
-- Name: rfh_traduccio; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_traduccio (
    traduccioid bigint DEFAULT nextval('public.rfh_traduccio_seq'::regclass) NOT NULL
);


--
-- TOC entry 249 (class 1259 OID 16497)
-- Name: rfh_traducciomap; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_traducciomap (
    traducciomapid bigint NOT NULL,
    valor character varying(4000),
    idiomaid character varying(10) NOT NULL
);


--
-- TOC entry 231 (class 1259 OID 16400)
-- Name: rfh_unitat_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_unitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 250 (class 1259 OID 16504)
-- Name: rfh_unitat; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_unitat (
    unitatid bigint DEFAULT nextval('public.rfh_unitat_seq'::regclass) NOT NULL,
    arrel character varying(50),
    arrelversio integer,
    codi character varying(50) NOT NULL,
    cooficial character varying(255),
    denominacio character varying(255) NOT NULL,
    estat character varying(5),
    superior character varying(50),
    superiorversio integer,
    versio integer NOT NULL
);


--
-- TOC entry 232 (class 1259 OID 16401)
-- Name: rfh_usuari_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_usuari_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 251 (class 1259 OID 16511)
-- Name: rfh_usuari; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_usuari (
    usuariid bigint DEFAULT nextval('public.rfh_usuari_seq'::regclass) NOT NULL,
    actiu boolean NOT NULL,
    correu character varying(255) NOT NULL,
    darreraentitat bigint,
    databaixa timestamp without time zone,
    datacreacio timestamp without time zone NOT NULL,
    idiomaid character varying(5) NOT NULL,
    llinatge1 character varying(255) NOT NULL,
    llinatge2 character varying(255),
    nif character varying(50) NOT NULL,
    nom character varying(255) NOT NULL,
    username character varying(255)
);


--
-- TOC entry 233 (class 1259 OID 16402)
-- Name: rfh_usuarientitat_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.rfh_usuarientitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 252 (class 1259 OID 16518)
-- Name: rfh_usuarientitat; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.rfh_usuarientitat (
    usuarientitatid bigint DEFAULT nextval('public.rfh_usuarientitat_seq'::regclass) NOT NULL,
    actiu boolean NOT NULL,
    entitatid bigint NOT NULL,
    usuariid bigint NOT NULL
);


--
-- TOC entry 4751 (class 2606 OID 16409)
-- Name: rfh_activitat rfh_activitat_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_activitat
    ADD CONSTRAINT rfh_activitat_pk PRIMARY KEY (activitatid);


--
-- TOC entry 4756 (class 2606 OID 16416)
-- Name: rfh_autoritzacio rfh_autoritzacio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_autoritzacio
    ADD CONSTRAINT rfh_autoritzacio_pk PRIMARY KEY (autoritzacioid);


--
-- TOC entry 4760 (class 2606 OID 16423)
-- Name: rfh_digitalib rfh_digitalib_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_digitalib
    ADD CONSTRAINT rfh_digitalib_pk PRIMARY KEY (digitalid);


--
-- TOC entry 4764 (class 2606 OID 16428)
-- Name: rfh_entitat rfh_entitat_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_entitat
    ADD CONSTRAINT rfh_entitat_pk PRIMARY KEY (entitatid);


--
-- TOC entry 4768 (class 2606 OID 16435)
-- Name: rfh_fitxer rfh_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_fitxer
    ADD CONSTRAINT rfh_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 4771 (class 2606 OID 16940)
-- Name: rfh_funcionari rfh_funcionari_identific_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_funcionari
    ADD CONSTRAINT rfh_funcionari_identific_uk UNIQUE (identificador);


--
-- TOC entry 4773 (class 2606 OID 16442)
-- Name: rfh_funcionari rfh_funcionari_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_funcionari
    ADD CONSTRAINT rfh_funcionari_pk PRIMARY KEY (funcionariid);


--
-- TOC entry 4778 (class 2606 OID 16447)
-- Name: rfh_funcionarilloc rfh_funcionarilloc_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_funcionarilloc
    ADD CONSTRAINT rfh_funcionarilloc_pk PRIMARY KEY (funcionarillocid);


--
-- TOC entry 4783 (class 2606 OID 16454)
-- Name: rfh_historic rfh_historic_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_historic
    ADD CONSTRAINT rfh_historic_pk PRIMARY KEY (historicid);


--
-- TOC entry 4788 (class 2606 OID 16461)
-- Name: rfh_historiclloc rfh_historiclloc_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_historiclloc
    ADD CONSTRAINT rfh_historiclloc_pk PRIMARY KEY (historicllocid);


--
-- TOC entry 4792 (class 2606 OID 16467)
-- Name: rfh_idioma rfh_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_idioma
    ADD CONSTRAINT rfh_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 4795 (class 2606 OID 33338)
-- Name: rfh_lloc rfh_lloc_codillocexpansio_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_lloc
    ADD CONSTRAINT rfh_lloc_codillocexpansio_uk UNIQUE (expansio, codilloc);


--
-- Name: rfh_lloc rfh_lloc_codillocpropi_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_lloc
    ADD CONSTRAINT rfh_lloc_codillocpropi_uk UNIQUE (codillocpropi);


--
-- TOC entry 4798 (class 2606 OID 16474)
-- Name: rfh_lloc rfh_lloc_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_lloc
    ADD CONSTRAINT rfh_lloc_pk PRIMARY KEY (llocid);


--
-- TOC entry 4803 (class 2606 OID 16479)
-- Name: rfh_llochabilitacio rfh_llochabilitacio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_llochabilitacio
    ADD CONSTRAINT rfh_llochabilitacio_pk PRIMARY KEY (llochabilitacioid);


--
-- TOC entry 4808 (class 2606 OID 16486)
-- Name: rfh_plugin rfh_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_plugin
    ADD CONSTRAINT rfh_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 4812 (class 2606 OID 16491)
-- Name: rfh_habilitacio rfh_habilitacio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_habilitacio
    ADD CONSTRAINT rfh_habilitacio_pk PRIMARY KEY (habilitacioid);


--
-- TOC entry 4815 (class 2606 OID 16496)
-- Name: rfh_traduccio rfh_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_traduccio
    ADD CONSTRAINT rfh_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 4820 (class 2606 OID 16736)
-- Name: rfh_traducciomap rfh_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_traducciomap
    ADD CONSTRAINT rfh_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 4822 (class 2606 OID 16510)
-- Name: rfh_unitat rfh_unitat_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_unitat
    ADD CONSTRAINT rfh_unitat_pk PRIMARY KEY (unitatid);


--
-- TOC entry 4827 (class 2606 OID 16517)
-- Name: rfh_usuari rfh_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_usuari
    ADD CONSTRAINT rfh_usuari_pk PRIMARY KEY (usuariid);


--
-- TOC entry 4830 (class 2606 OID 16703)
-- Name: rfh_usuari rfh_usuari_username_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_usuari
    ADD CONSTRAINT rfh_usuari_username_uk UNIQUE (username);


--
-- TOC entry 4833 (class 2606 OID 16571)
-- Name: rfh_usuarientitat rfh_usuarient_multiple_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_usuarientitat
    ADD CONSTRAINT rfh_usuarient_multiple_uk UNIQUE (entitatid, usuariid);


--
-- TOC entry 4836 (class 2606 OID 16522)
-- Name: rfh_usuarientitat rfh_usuarientitat_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_usuarientitat
    ADD CONSTRAINT rfh_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
-- TOC entry 4749 (class 1259 OID 16524)
-- Name: rfh_activitat_fun_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_activitat_fun_fk_i ON public.rfh_activitat USING btree (funcionariid);


--
-- TOC entry 4752 (class 1259 OID 16523)
-- Name: rfh_activitat_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_activitat_pk_i ON public.rfh_activitat USING btree (activitatid);


--
-- TOC entry 4753 (class 1259 OID 16528)
-- Name: rfh_autoritza_funcid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_autoritza_funcid_fk_i ON public.rfh_autoritzacio USING btree (funcionariid);


--
-- TOC entry 4754 (class 1259 OID 16527)
-- Name: rfh_autoritza_funid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_autoritza_funid_fk_i ON public.rfh_autoritzacio USING btree (llocid);


--
-- TOC entry 4757 (class 1259 OID 16526)
-- Name: rfh_autoritzacio_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_autoritzacio_pk_i ON public.rfh_autoritzacio USING btree (autoritzacioid);


--
-- TOC entry 4758 (class 1259 OID 16530)
-- Name: rfh_digitalib_fitxerid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_digitalib_fitxerid_fk_i ON public.rfh_digitalib USING btree (fitxerid);


--
-- TOC entry 4761 (class 1259 OID 16529)
-- Name: rfh_digitalib_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_digitalib_pk_i ON public.rfh_digitalib USING btree (digitalid);


--
-- TOC entry 4762 (class 1259 OID 16531)
-- Name: rfh_digitalib_usuariid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_digitalib_usuariid_fk_i ON public.rfh_digitalib USING btree (usuariid);


--
-- TOC entry 4765 (class 1259 OID 16532)
-- Name: rfh_entitat_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_entitat_pk_i ON public.rfh_entitat USING btree (entitatid);


--
-- TOC entry 4766 (class 1259 OID 16533)
-- Name: rfh_entitat_unitatid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_entitat_unitatid_fk_i ON public.rfh_entitat USING btree (unitatid);


--
-- TOC entry 4769 (class 1259 OID 16534)
-- Name: rfh_fitxer_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_fitxer_pk_i ON public.rfh_fitxer USING btree (fitxerid);


--
-- TOC entry 4774 (class 1259 OID 16535)
-- Name: rfh_funcionari_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_funcionari_pk_i ON public.rfh_funcionari USING btree (funcionariid);


--
-- TOC entry 4775 (class 1259 OID 16536)
-- Name: rfh_funcionarientitat_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_funcionarientitat_pk_i ON public.rfh_funcionarilloc USING btree (funcionarillocid);


--
-- TOC entry 4776 (class 1259 OID 16537)
-- Name: rfh_funcionarilloc_llocid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_funcionarilloc_llocid_fk_i ON public.rfh_funcionarilloc USING btree (llocid);


--
-- TOC entry 4779 (class 1259 OID 16538)
-- Name: rfh_funlloc_funcionariid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_funlloc_funcionariid_fk_i ON public.rfh_funcionarilloc USING btree (funcionariid);


--
-- TOC entry 4780 (class 1259 OID 16946)
-- Name: rfh_funlloc_usuariid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_funlloc_usuariid_fk_i ON public.rfh_funcionarilloc USING btree (usuariid);


--
-- TOC entry 4781 (class 1259 OID 16542)
-- Name: rfh_historic_funcionariid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_historic_funcionariid_fk_i ON public.rfh_historic USING btree (funcionariid);


--
-- TOC entry 4784 (class 1259 OID 16541)
-- Name: rfh_historic_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_historic_pk_i ON public.rfh_historic USING btree (historicid);


--
-- TOC entry 4785 (class 1259 OID 16543)
-- Name: rfh_historic_usuariid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_historic_usuariid_fk_i ON public.rfh_historic USING btree (usuariid);


--
-- TOC entry 4786 (class 1259 OID 16545)
-- Name: rfh_historiclloc_llocid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_historiclloc_llocid_fk_i ON public.rfh_historiclloc USING btree (llocid);


--
-- TOC entry 4789 (class 1259 OID 16544)
-- Name: rfh_historiclloc_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_historiclloc_pk_i ON public.rfh_historiclloc USING btree (historicllocid);


--
-- TOC entry 4790 (class 1259 OID 16546)
-- Name: rfh_historiclloc_usuariid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_historiclloc_usuariid_fk_i ON public.rfh_historiclloc USING btree (usuariid);


--
-- TOC entry 4793 (class 1259 OID 16547)
-- Name: rfh_idioma_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_idioma_pk_i ON public.rfh_idioma USING btree (idiomaid);


--
-- TOC entry 4796 (class 1259 OID 16549)
-- Name: rfh_lloc_entitatid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_lloc_entitatid_fk_i ON public.rfh_lloc USING btree (entitatid);


--
-- TOC entry 4799 (class 1259 OID 16548)
-- Name: rfh_lloc_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_lloc_pk_i ON public.rfh_lloc USING btree (llocid);


--
-- TOC entry 4800 (class 1259 OID 16550)
-- Name: rfh_lloc_unitatid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_lloc_unitatid_fk_i ON public.rfh_lloc USING btree (unitatid);


--
-- TOC entry 4801 (class 1259 OID 16554)
-- Name: rfh_llochabil_llocid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_llochabil_llocid_fk_i ON public.rfh_llochabilitacio USING btree (llocid);


--
-- TOC entry 4804 (class 1259 OID 16553)
-- Name: rfh_llochabilitacio_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_llochabilitacio_pk_i ON public.rfh_llochabilitacio USING btree (llochabilitacioid);


--
-- TOC entry 4805 (class 1259 OID 16555)
-- Name: rfh_llochabil_hab_habid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_llochabil_hab_habid_fk_i ON public.rfh_llochabilitacio USING btree (habilitacioid);


--
-- TOC entry 4806 (class 1259 OID 16557)
-- Name: rfh_plugin_entitatid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_plugin_entitatid_fk_i ON public.rfh_plugin USING btree (entitatid);


--
-- TOC entry 4809 (class 1259 OID 16556)
-- Name: rfh_plugin_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_plugin_pk_i ON public.rfh_plugin USING btree (pluginid);


--
-- TOC entry 4810 (class 1259 OID 16559)
-- Name: rfh_habilitacio_nomid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_habilitacio_nomid_fk_i ON public.rfh_habilitacio USING btree (nomid);


--
-- TOC entry 4813 (class 1259 OID 16558)
-- Name: rfh_habilitacio_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_habilitacio_pk_i ON public.rfh_habilitacio USING btree (habilitacioid);


--
-- TOC entry 4816 (class 1259 OID 16560)
-- Name: rfh_traduccio_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_traduccio_pk_i ON public.rfh_traduccio USING btree (traduccioid);


--
-- TOC entry 4817 (class 1259 OID 16737)
-- Name: rfh_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_traducciomap_idiomaid_fk_i ON public.rfh_traducciomap USING btree (idiomaid);


--
-- TOC entry 4818 (class 1259 OID 16734)
-- Name: rfh_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_traducciomap_pk_i ON public.rfh_traducciomap USING btree (traducciomapid);


--
-- TOC entry 4823 (class 1259 OID 16561)
-- Name: rfh_unitat_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_unitat_pk_i ON public.rfh_unitat USING btree (unitatid);


--
-- TOC entry 4824 (class 1259 OID 16564)
-- Name: rfh_usuari_darreraentitat_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_usuari_darreraentitat_fk_i ON public.rfh_usuari USING btree (darreraentitat);


--
-- TOC entry 4825 (class 1259 OID 16563)
-- Name: rfh_usuari_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_usuari_idiomaid_fk_i ON public.rfh_usuari USING btree (idiomaid);


--
-- TOC entry 4828 (class 1259 OID 16562)
-- Name: rfh_usuari_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_usuari_pk_i ON public.rfh_usuari USING btree (usuariid);


--
-- TOC entry 4831 (class 1259 OID 16568)
-- Name: rfh_usuarient_entitatid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_usuarient_entitatid_fk_i ON public.rfh_usuarientitat USING btree (entitatid);


--
-- TOC entry 4834 (class 1259 OID 16569)
-- Name: rfh_usuarient_usuariid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_usuarient_usuariid_fk_i ON public.rfh_usuarientitat USING btree (usuariid);


--
-- TOC entry 4837 (class 1259 OID 16567)
-- Name: rfh_usuarientitat_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rfh_usuarientitat_pk_i ON public.rfh_usuarientitat USING btree (usuarientitatid);


--
-- TOC entry 4838 (class 2606 OID 16577)
-- Name: rfh_activitat rfh_activitat_funcionari_fu_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_activitat
    ADD CONSTRAINT rfh_activitat_funcionari_fu_fk FOREIGN KEY (funcionariid) REFERENCES public.rfh_funcionari(funcionariid);


--
-- TOC entry 4839 (class 2606 OID 16725)
-- Name: rfh_autoritzacio rfh_autoritza_funcionari_i_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_autoritzacio
    ADD CONSTRAINT rfh_autoritza_funcionari_i_fk FOREIGN KEY (funcionariid) REFERENCES public.rfh_funcionari(funcionariid);


--
-- TOC entry 4840 (class 2606 OID 16587)
-- Name: rfh_autoritzacio rfh_autoritza_lloc_fd_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_autoritzacio
    ADD CONSTRAINT rfh_autoritza_lloc_fd_fk FOREIGN KEY (llocid) REFERENCES public.rfh_lloc(llocid);


--
-- TOC entry 4843 (class 2606 OID 16602)
-- Name: rfh_entitat rfh_entitat_unitat_unitatid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_entitat
    ADD CONSTRAINT rfh_entitat_unitat_unitatid_fk FOREIGN KEY (unitatid) REFERENCES public.rfh_unitat(unitatid);


--
-- TOC entry 4844 (class 2606 OID 16607)
-- Name: rfh_funcionarilloc rfh_funlloc_funcionari_f_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_funcionarilloc
    ADD CONSTRAINT rfh_funlloc_funcionari_f_fk FOREIGN KEY (funcionariid) REFERENCES public.rfh_funcionari(funcionariid);


--
-- TOC entry 4845 (class 2606 OID 16612)
-- Name: rfh_funcionarilloc rfh_funlloc_lloc_llocid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_funcionarilloc
    ADD CONSTRAINT rfh_funlloc_lloc_llocid_fk FOREIGN KEY (llocid) REFERENCES public.rfh_lloc(llocid);


--
-- TOC entry 4846 (class 2606 OID 16941)
-- Name: rfh_funcionarilloc rfh_funlloc_usuari_usuid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_funcionarilloc
    ADD CONSTRAINT rfh_funlloc_usuari_usuid_fk FOREIGN KEY (usuariid) REFERENCES public.rfh_usuari(usuariid);


--
-- TOC entry 4849 (class 2606 OID 16627)
-- Name: rfh_historiclloc rfh_histolloc_lloc_llocid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_historiclloc
    ADD CONSTRAINT rfh_histolloc_lloc_llocid_fk FOREIGN KEY (llocid) REFERENCES public.rfh_lloc(llocid);


--
-- TOC entry 4850 (class 2606 OID 16632)
-- Name: rfh_historiclloc rfh_histolloc_usuari_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_historiclloc
    ADD CONSTRAINT rfh_histolloc_usuari_id_fk FOREIGN KEY (usuariid) REFERENCES public.rfh_usuari(usuariid);


--
-- TOC entry 4847 (class 2606 OID 16617)
-- Name: rfh_historic rfh_historic_funcionari_f_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_historic
    ADD CONSTRAINT rfh_historic_funcionari_f_fk FOREIGN KEY (funcionariid) REFERENCES public.rfh_funcionari(funcionariid);


--
-- TOC entry 4848 (class 2606 OID 16622)
-- Name: rfh_historic rfh_historic_usuari_usuid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_historic
    ADD CONSTRAINT rfh_historic_usuari_usuid_fk FOREIGN KEY (usuariid) REFERENCES public.rfh_usuari(usuariid);


--
-- TOC entry 4851 (class 2606 OID 16637)
-- Name: rfh_lloc rfh_lloc_entitat_entitatid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_lloc
    ADD CONSTRAINT rfh_lloc_entitat_entitatid_fk FOREIGN KEY (entitatid) REFERENCES public.rfh_entitat(entitatid);


--
-- TOC entry 4852 (class 2606 OID 16642)
-- Name: rfh_lloc rfh_lloc_unitat_unitatid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_lloc
    ADD CONSTRAINT rfh_lloc_unitat_unitatid_fk FOREIGN KEY (unitatid) REFERENCES public.rfh_unitat(unitatid);


--
-- TOC entry 4853 (class 2606 OID 16647)
-- Name: rfh_llochabilitacio rfh_llochabil_lloc_llocid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_llochabilitacio
    ADD CONSTRAINT rfh_llochabil_lloc_llocid_fk FOREIGN KEY (llocid) REFERENCES public.rfh_lloc(llocid);


--
-- TOC entry 4854 (class 2606 OID 16652)
-- Name: rfh_llochabilitacio rfh_llochabil_hab_habid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_llochabilitacio
    ADD CONSTRAINT rfh_llochabil_hab_habid_fk FOREIGN KEY (habilitacioid) REFERENCES public.rfh_habilitacio(habilitacioid);


--
-- TOC entry 4855 (class 2606 OID 16657)
-- Name: rfh_plugin rfh_plugin_entitat_entitati_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_plugin
    ADD CONSTRAINT rfh_plugin_entitat_entitati_fk FOREIGN KEY (entitatid) REFERENCES public.rfh_entitat(entitatid);


--
-- TOC entry 4856 (class 2606 OID 16662)
-- Name: rfh_habilitacio rfh_habil_traduccio_nomid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_habilitacio
    ADD CONSTRAINT rfh_habil_traduccio_nomid_fk FOREIGN KEY (nomid) REFERENCES public.rfh_traduccio(traduccioid);


--
-- TOC entry 4841 (class 2606 OID 16592)
-- Name: rfh_digitalib rfh_scanweb_fitxer_fitxerid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_digitalib
    ADD CONSTRAINT rfh_scanweb_fitxer_fitxerid_fk FOREIGN KEY (fitxerid) REFERENCES public.rfh_fitxer(fitxerid);


--
-- TOC entry 4842 (class 2606 OID 16597)
-- Name: rfh_digitalib rfh_scanweb_usuari_usuariid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_digitalib
    ADD CONSTRAINT rfh_scanweb_usuari_usuariid_fk FOREIGN KEY (usuariid) REFERENCES public.rfh_usuari(usuariid);


--
-- TOC entry 4857 (class 2606 OID 16667)
-- Name: rfh_traducciomap rfh_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_traducciomap
    ADD CONSTRAINT rfh_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.rfh_traduccio(traduccioid);


--
-- TOC entry 4858 (class 2606 OID 16672)
-- Name: rfh_usuari rfh_usuari_entitat_last_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_usuari
    ADD CONSTRAINT rfh_usuari_entitat_last_fk FOREIGN KEY (darreraentitat) REFERENCES public.rfh_entitat(entitatid);


--
-- TOC entry 4859 (class 2606 OID 16677)
-- Name: rfh_usuari rfh_usuari_idioma_idiomaid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_usuari
    ADD CONSTRAINT rfh_usuari_idioma_idiomaid_fk FOREIGN KEY (idiomaid) REFERENCES public.rfh_idioma(idiomaid);


--
-- TOC entry 4860 (class 2606 OID 16682)
-- Name: rfh_usuarientitat rfh_usuarient_entitat_entit_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_usuarientitat
    ADD CONSTRAINT rfh_usuarient_entitat_entit_fk FOREIGN KEY (entitatid) REFERENCES public.rfh_entitat(entitatid);


--
-- TOC entry 4861 (class 2606 OID 16687)
-- Name: rfh_usuarientitat rfh_usuarient_usuari_usuari_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.rfh_usuarientitat
    ADD CONSTRAINT rfh_usuarient_usuari_usuari_fk FOREIGN KEY (usuariid) REFERENCES public.rfh_usuari(usuariid);


-- Completed on 2025-10-09 13:22:08

--
-- PostgreSQL database dump complete
--

