create sequence rfh_activitat_seq start 1000 increment 1;
create sequence rfh_autoritzacio_seq start 1000 increment 1;
create sequence rfh_digitalib_seq start 1000 increment 1;
create sequence rfh_entitat_seq start 1000 increment 1;
create sequence rfh_fitxer_seq start 1000 increment 1;
create sequence rfh_funcionari_seq start 1000 increment 1;
create sequence rfh_funcionarilloc_seq start 1000 increment 1;
create sequence rfh_historic_seq start 1000 increment 1;
create sequence rfh_historiclloc_seq start 1000 increment 1;
create sequence rfh_lloc_seq start 1000 increment 1;
create sequence rfh_llocrol_seq start 1000 increment 1;
create sequence rfh_plugin_seq start 1000 increment 1;
create sequence rfh_rol_seq start 1000 increment 1;
create sequence rfh_traduccio_seq start 1000 increment 1;
create sequence rfh_unitat_seq start 1000 increment 1;
create sequence rfh_usuari_seq start 1000 increment 1;
create sequence rfh_usuarientitat_seq start 1000 increment 1;

    create table rfh_activitat (
       activitatid int8 DEFAULT nextval('rfh_activitat_seq'::regclass) not null,
        arxiudocumentid varchar(255),
        arxiuexpedientid varchar(255),
        autoritzacioid int8,
        codisia varchar(150),
        datacreacio timestamp not null,
        estat int4 not null,
        funcionariid int8 not null,
        interessatidentificacio varchar(50) not null,
        interessatllinatge1 varchar(255) not null,
        interessatllinatge2 varchar(255) not null,
        interessatnom varchar(255) not null,
        interessattipus int4 not null,
        registre varchar(50),
        representantidentificacio varchar(50),
        representantllinatge1 varchar(255),
        representantllinatge2 varchar(255),
        representantnom varchar(255),
        representanttipus int4,
        tipus int4 not null,
        tramit varchar(150),
        tramitversio int4,
        url varchar(255),
        CONSTRAINT rfh_activitat_pk primary key (activitatid)
    );

    create table rfh_autoritzacio (
       autoritzacioid int8 DEFAULT nextval('rfh_autoritzacio_seq'::regclass) not null,
        cai varchar(255) not null,
        codisia varchar(50) not null,
        datacreacio timestamp not null,
        datafi date,
        datainici date,
        funcionariid int8,
        llocid int8 not null,
        observacions text,
        procediment varchar(255) not null,
        usuariid int8,
        CONSTRAINT rfh_autoritzacio_pk primary key (autoritzacioid)
    );

    create table rfh_digitalib (
       digitalid int8 DEFAULT nextval('rfh_digitalib_seq'::regclass) not null,
        datacreacio timestamp,
        entitatid int8 not null,
        fileinfo text,
        fitxerid int8 not null,
        metadades text,
        missatge text,
        signedfileinfo text,
        status int8 not null,
        transactionid int8,
        transactionwebid varchar(255),
        usuariid int8 not null,
        CONSTRAINT rfh_digitalib_pk primary key (digitalid)
    );

    create table rfh_entitat (
       entitatid int8 DEFAULT nextval('rfh_entitat_seq'::regclass) not null,
        actiu boolean not null,
        databaixa timestamp,
        nom varchar(255),
        unitatid int8,
        CONSTRAINT rfh_entitat_pk primary key (entitatid)
    );

    create table rfh_fitxer (
       fitxerid int8 DEFAULT nextval('rfh_fitxer_seq'::regclass) not null,
        descripcio varchar(1000) DEFAULT NULL::character varying,
        mime varchar(255) not null,
        nom varchar(255) not null,
        tamany int8 not null,
        CONSTRAINT rfh_fitxer_pk primary key (fitxerid)
    );

    create table rfh_funcionari (
       funcionariid int8 DEFAULT nextval('rfh_funcionari_seq'::regclass) not null,
        correu varchar(255) not null,
        databaixa timestamp,
        datacreacio timestamp not null,
        entitatid int8 not null,
        identificador varchar(50) not null,
        llinatge1 varchar(255) not null,
        llinatge2 varchar(255),
        nom varchar(255) not null,
        numero varchar(10) not null,
        observacions text,
        tipusidentificador int4 not null,
        usuari varchar(50) not null,
        CONSTRAINT rfh_funcionari_pk primary key (funcionariid)
    );

    create table rfh_funcionarilloc (
       funcionarillocid int8 DEFAULT nextval('rfh_funcionarilloc_seq'::regclass) not null,
        datacreacio timestamp not null,
        datafi date,
        datainici date,
        funcionariid int8 not null,
        llocid int8 not null,
        usuariid int8,
        CONSTRAINT rfh_funcionarilloc_pk primary key (funcionarillocid)
    );

    create table rfh_historic (
       historicid int8 DEFAULT nextval('rfh_historic_seq'::regclass) not null,
        datacreacio timestamp not null,
        funcionariid int8 not null,
        numerocai varchar(50) not null,
        observacions text,
        usuariid int8,
        CONSTRAINT rfh_historic_pk primary key (historicid)
    );

    create table rfh_historiclloc (
       historicllocid int8 DEFAULT nextval('rfh_historiclloc_seq'::regclass) not null,
        datacreacio timestamp not null,
        llocid int8 not null,
        numerocai varchar(50) not null,
        observacions text,
        usuariid int8,
        CONSTRAINT rfh_historiclloc_pk primary key (historicllocid)
    );

    create table rfh_idioma (
       idiomaid varchar(5) not null,
        nom varchar(50) not null,
        ordre int4 default 0 not null,
        suportat boolean DEFAULT true not null,
        CONSTRAINT rfh_idioma_pk primary key (idiomaid)
    );

    create table rfh_lloc (
       llocid int8 DEFAULT nextval('rfh_lloc_seq'::regclass) not null,
        codilloc varchar(50) not null,
        codillocpropi varchar(50) not null,
        expansio varchar2(50 char),
        databaixa timestamp,
        datacreacio timestamp not null,
        dataalta timestamp,
        entitatid int8 not null,
        nom varchar(255) not null,
        observacions text,
        personaloamr int4 not null,
        unitatid int8 not null,
        CONSTRAINT rfh_lloc_pk primary key (llocid)
    );

    create table rfh_llocrol (
       llocrolid int8 DEFAULT nextval('rfh_llocrol_seq'::regclass) not null,
        datacreacio timestamp not null,
        llocid int8 not null,
        rolid int8 not null,
        CONSTRAINT rfh_llocrol_pk primary key (llocrolid)
    );

    create table rfh_plugin (
       pluginid int8 DEFAULT nextval('rfh_plugin_seq'::regclass) not null,
        actiu boolean not null,
        classe varchar(255),
        datacreacio timestamp,
        descripcio varchar(255) not null,
        entitatid int8 not null,
        nom varchar(255) not null,
        properties text,
        tipus varchar(50) not null,
        CONSTRAINT rfh_plugin_pk primary key (pluginid)
    );

    create table rfh_rol (
       rolid int8 DEFAULT nextval('rfh_rol_seq'::regclass) not null,
        codi varchar(50) not null,
        datacreacio timestamp,
        entitatid int8 not null,
        nomid int8 not null,
        CONSTRAINT rfh_rol_pk primary key (rolid)
    );

    create table rfh_traduccio (
       traduccioid int8 DEFAULT nextval('rfh_traduccio_seq'::regclass) not null,
       CONSTRAINT rfh_traduccio_pk primary key (traduccioid)
    );

    create table rfh_traducciomap (
       traducciomapid int8 not null,
        valor varchar(4000),
        idiomaid varchar(10) not null,
        CONSTRAINT rfh_traducmap_pk primary key (traducciomapid, idiomaid)
    );

    create table rfh_unitat (
       unitatid int8 DEFAULT nextval('rfh_unitat_seq'::regclass) not null,
        arrel varchar(50),
        arrelversio int4,
        codi varchar(50) not null,
        cooficial varchar(255),
        denominacio varchar(255) not null,
        estat varchar(5),
        superior varchar(50),
        superiorversio int4,
        versio int4 not null,
        CONSTRAINT rfh_unitat_pk primary key (unitatid)
    );

    create table rfh_usuari (
       usuariid int8 DEFAULT nextval('rfh_usuari_seq'::regclass) not null,
        actiu boolean not null,
        correu varchar(255) not null,
        darreraentitat int8,
        databaixa timestamp,
        datacreacio timestamp not null,
        idiomaid varchar(5) not null,
        llinatge1 varchar(255) not null,
        llinatge2 varchar(255),
        nif varchar(50) not null,
        nom varchar(255) not null,
        username varchar(255),
        CONSTRAINT rfh_usuari_pk primary key (usuariid)
    );

    create table rfh_usuarientitat (
       usuarientitatid int8 DEFAULT nextval('rfh_usuarientitat_seq'::regclass) not null,
        actiu boolean not null,
        entitatid int8 not null,
        usuariid int8 not null,
        CONSTRAINT rfh_usuarientitat_pk primary key (usuarientitatid)
    );
create index rfh_activitat_pk_i on rfh_activitat (activitatid);
create index rfh_activitat_fun_fk_i on rfh_activitat (funcionariid);
create index rfh_activitat_autoritzaid_fk_i on rfh_activitat (autoritzacioid);
create index rfh_autoritzacio_pk_i on rfh_autoritzacio (autoritzacioid);
create index rfh_autoritza_funid_fk_i on rfh_autoritzacio (llocid);
create index rfh_autoritza_funcid_fk_i on rfh_autoritzacio (funcionariid);
create index rfh_digitalib_pk_i on rfh_digitalib (digitalid);
create index rfh_digitalib_fitxerid_fk_i on rfh_digitalib (fitxerid);
create index rfh_digitalib_usuariid_fk_i on rfh_digitalib (usuariid);
create index rfh_entitat_pk_i on rfh_entitat (entitatid);
create index rfh_entitat_unitatid_fk_i on rfh_entitat (unitatid);
create index rfh_fitxer_pk_i on rfh_fitxer (fitxerid);
create index rfh_funcionari_pk_i on rfh_funcionari (funcionariid);
   alter table rfh_funcionari
      add constraint rfh_funcionari_identific_uk unique (identificador);
create index rfh_funcionarientitat_pk_i on rfh_funcionarilloc (funcionarillocid);
create index rfh_funcionarilloc_llocid_fk_i on rfh_funcionarilloc (llocid);
create index rfh_funlloc_funcionariid_fk_i on rfh_funcionarilloc (funcionariid);
create index rfh_funlloc_usuariid_fk_i on rfh_funcionarilloc (usuariid);

create index rfh_historic_pk_i on rfh_historic (historicid);
create index rfh_historic_funcionariid_fk_i on rfh_historic (funcionariid);
create index rfh_historic_usuariid_fk_i on rfh_historic (usuariid);
create index rfh_historiclloc_pk_i on rfh_historiclloc (historicllocid);
create index rfh_historiclloc_llocid_fk_i on rfh_historiclloc (llocid);
create index rfh_historiclloc_usuariid_fk_i on rfh_historiclloc (usuariid);
create index rfh_idioma_pk_i on rfh_idioma (idiomaid);
create index rfh_lloc_pk_i on rfh_lloc (llocid);
create index rfh_lloc_entitatid_fk_i on rfh_lloc (entitatid);
create index rfh_lloc_unitatid_fk_i on rfh_lloc (unitatid);

    alter table rfh_lloc 
       add constraint rfh_lloc_codillocexpansio_uk unique (codilloc, expansio);
create index rfh_llocrol_pk_i on rfh_llocrol (llocrolid);
create index rfh_llocrol_llocid_fk_i on rfh_llocrol (llocid);
create index rfh_llocrol_rolid_fk_i on rfh_llocrol (rolid);
create index rfh_plugin_pk_i on rfh_plugin (pluginid);
create index rfh_plugin_entitatid_fk_i on rfh_plugin (entitatid);
create index rfh_rol_pk_i on rfh_rol (rolid);
create index rfh_rol_nomid_fk_i on rfh_rol (nomid);
create index rfh_traduccio_pk_i on rfh_traduccio (traduccioid);
CREATE INDEX rfh_traducciomap_idiomaid_fk_i ON rfh_traducciomap (idiomaid);
CREATE INDEX rfh_traducciomap_pk_i ON rfh_traducciomap (traducciomapid);
create index rfh_unitat_pk_i on rfh_unitat (unitatid);
create index rfh_usuari_pk_i on rfh_usuari (usuariid);
create index rfh_usuari_idiomaid_fk_i on rfh_usuari (idiomaid);
create index rfh_usuari_darreraentitat_fk_i on rfh_usuari (darreraentitat);

    alter table rfh_usuari 
       add constraint rfh_usuari_username_uk unique (username);
create index rfh_usuarientitat_pk_i on rfh_usuarientitat (usuarientitatid);
create index rfh_usuarient_entitatid_fk_i on rfh_usuarientitat (entitatid);
create index rfh_usuarient_usuariid_fk_i on rfh_usuarientitat (usuariid);

    alter table rfh_usuarientitat 
       add constraint rfh_usuarient_multiple_uk unique (entitatid, usuariid);

    alter table rfh_activitat 
       add constraint rfh_activitat_autoritza_aut_fk 
       foreign key (autoritzacioid) 
       references rfh_autoritzacio;

    alter table rfh_activitat 
       add constraint rfh_activitat_funcionari_fu_fk 
       foreign key (funcionariid) 
       references rfh_funcionari;

    alter table rfh_autoritzacio 
       add constraint rfh_autoritza_funcionari_i_fk 
       foreign key (funcionariid) 
       references rfh_funcionari;

    alter table rfh_autoritzacio 
       add constraint rfh_autoritza_lloc_fd_fk 
       foreign key (llocid) 
       references rfh_lloc;

    alter table rfh_digitalib 
       add constraint rfh_scanweb_fitxer_fitxerid_fk 
       foreign key (fitxerid) 
       references rfh_fitxer;

    alter table rfh_digitalib 
       add constraint rfh_scanweb_usuari_usuariid_fk 
       foreign key (usuariid) 
       references rfh_usuari;

    alter table rfh_entitat 
       add constraint rfh_entitat_unitat_unitatid_fk 
       foreign key (unitatid) 
       references rfh_unitat;

    alter table rfh_funcionarilloc 
       add constraint rfh_funlloc_funcionari_f_fk 
       foreign key (funcionariid) 
       references rfh_funcionari;

    alter table rfh_funcionarilloc 
       add constraint rfh_funlloc_lloc_llocid_fk 
       foreign key (llocid) 
       references rfh_lloc;

    alter table rfh_funcionarilloc 
       add constraint rfh_funlloc_usuari_usuid_fk 
       foreign key (usuariid) 
       references rfh_usuari;

    alter table rfh_historic 
       add constraint rfh_historic_funcionari_f_fk 
       foreign key (funcionariid) 
       references rfh_funcionari;

    alter table rfh_historic 
       add constraint rfh_historic_usuari_usuid_fk 
       foreign key (usuariid) 
       references rfh_usuari;

    alter table rfh_historiclloc 
       add constraint rfh_histolloc_lloc_llocid_fk 
       foreign key (llocid) 
       references rfh_lloc;

    alter table rfh_historiclloc 
       add constraint rfh_histolloc_usuari_id_fk 
       foreign key (usuariid) 
       references rfh_usuari;

    alter table rfh_lloc 
       add constraint rfh_lloc_entitat_entitatid_fk 
       foreign key (entitatid) 
       references rfh_entitat;

    alter table rfh_lloc 
       add constraint rfh_lloc_unitat_unitatid_fk 
       foreign key (unitatid) 
       references rfh_unitat;

    alter table rfh_llocrol 
       add constraint rfh_llocrol_lloc_llocid_fk 
       foreign key (llocid) 
       references rfh_lloc;

    alter table rfh_llocrol 
       add constraint rfh_llocrol_rol_rolid_fk 
       foreign key (rolid) 
       references rfh_rol;

    alter table rfh_plugin 
       add constraint rfh_plugin_entitat_entitati_fk 
       foreign key (entitatid) 
       references rfh_entitat;

    alter table rfh_rol 
       add constraint rfh_rol_traduccio_nomid_fk 
       foreign key (nomid) 
       references rfh_traduccio;

    alter table rfh_traducciomap 
       add constraint rfh_traducmap_traduccio_fk 
       foreign key (traducciomapid) 
       references rfh_traduccio;

    alter table rfh_usuari 
       add constraint rfh_usuari_entitat_last_fk 
       foreign key (darreraentitat) 
       references rfh_entitat;

    alter table rfh_usuari 
       add constraint rfh_usuari_idioma_idiomaid_fk 
       foreign key (idiomaid) 
       references rfh_idioma;

    alter table rfh_usuarientitat 
       add constraint rfh_usuarient_entitat_entit_fk 
       foreign key (entitatid) 
       references rfh_entitat;

    alter table rfh_usuarientitat 
       add constraint rfh_usuarient_usuari_usuari_fk 
       foreign key (usuariid) 
       references rfh_usuari;
