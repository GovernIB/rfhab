create sequence rfh_activitat_seq start with 1000 increment by  1;
create sequence rfh_autoritzacio_seq start with 1000 increment by  1;
create sequence rfh_digitalib_seq start with 1000 increment by  1;
create sequence rfh_entitat_seq start with 1000 increment by  1;
create sequence rfh_fitxer_seq start with 1000 increment by  1;
create sequence rfh_funcionari_seq start with 1000 increment by  1;
create sequence rfh_funcionarilloc_seq start with 1000 increment by  1;
create sequence rfh_historic_seq start with 1000 increment by  1;
create sequence rfh_historiclloc_seq start with 1000 increment by  1;
create sequence rfh_lloc_seq start with 1000 increment by  1;
create sequence rfh_llocrol_seq start with 1000 increment by  1;
create sequence rfh_plugin_seq start with 1000 increment by  1;
create sequence rfh_rol_seq start with 1000 increment by  1;
create sequence rfh_traduccio_seq start with 1000 increment by  1;
create sequence rfh_unitat_seq start with 1000 increment by  1;
create sequence rfh_usuari_seq start with 1000 increment by  1;
create sequence rfh_usuarientitat_seq start with 1000 increment by  1;

    create table rfh_activitat (
       activitatid number(19,0) not null,
        arxiudocumentid varchar2(255 char),
        arxiuexpedientid varchar2(255 char),
        autoritzacioid number(19,0),
        codisia varchar2(150 char),
        datacreacio timestamp not null,
        estat number(10,0) not null,
        funcionariid number(19,0) not null,
        interessatidentificacio varchar2(50 char) not null,
        interessatllinatge1 varchar2(255 char) not null,
        interessatllinatge2 varchar2(255 char) not null,
        interessatnom varchar2(255 char) not null,
        interessattipus number(10,0) not null,
        registre varchar2(50 char),
        representantidentificacio varchar2(50 char),
        representantllinatge1 varchar2(255 char),
        representantllinatge2 varchar2(255 char),
        representantnom varchar2(255 char),
        representanttipus number(10,0),
        tipus number(10,0) not null,
        tramit varchar2(150 char),
        tramitversio number(10,0),
        url varchar2(255 char),
        primary key (activitatid)
    );

    create table rfh_autoritzacio (
       autoritzacioid number(19,0) not null,
        cai varchar2(255 char) not null,
        codisia varchar2(50 char) not null,
        datacreacio timestamp not null,
        datafi date,
        datainici date,
        funcionariid number(19,0),
        llocid number(19,0) not null,
        observacions long,
        procediment varchar2(255 char) not null,
        usuariid number(19,0),
        primary key (autoritzacioid)
    );

    create table rfh_digitalib (
       digitalid number(19,0) not null,
        datacreacio timestamp,
        entitatid number(19,0) not null,
        fileinfo long,
        fitxerid number(19,0) not null,
        metadades long,
        missatge long,
        signedfileinfo long,
        status number(19,0) not null,
        transactionid number(19,0),
        transactionwebid varchar2(255 char),
        usuariid number(19,0) not null,
        primary key (digitalid)
    );

    create table rfh_entitat (
       entitatid number(19,0) not null,
        actiu number(1,0) not null,
        databaixa timestamp,
        nom varchar2(255 char),
        unitatid number(19,0),
        primary key (entitatid)
    );

    create table rfh_fitxer (
       fitxerid number(19,0) not null,
        descripcio varchar2(1000 char),
        mime varchar2(255 char) not null,
        nom varchar2(255 char) not null,
        tamany number(19,0) not null,
        primary key (fitxerid)
    );

    create table rfh_funcionari (
       funcionariid number(19,0) not null,
        correu varchar2(255 char) not null,
        databaixa timestamp,
        datacreacio timestamp not null,
        entitatid number(19,0) not null,
        identificador varchar2(50 char) not null,
        llinatge1 varchar2(255 char) not null,
        llinatge2 varchar2(255 char),
        nom varchar2(255 char) not null,
        numero varchar2(10 char) not null,
        observacions long,
        tipusidentificador number(10,0) not null,
        usuari varchar2(50 char) not null,
        primary key (funcionariid)
    );

    create table rfh_funcionarilloc (
       funcionarillocid number(19,0) not null,
        datacreacio timestamp not null,
        datafi date,
        datainici date,
        funcionariid number(19,0) not null,
        llocid number(19,0) not null,
        usuariid number(19,0),
        primary key (funcionarillocid)
    );

    create table rfh_historic (
       historicid number(19,0) not null,
        datacreacio timestamp not null,
        funcionariid number(19,0) not null,
        numerocai varchar2(50 char) not null,
        observacions long,
        usuariid number(19,0),
        primary key (historicid)
    );

    create table rfh_historiclloc (
       historicllocid number(19,0) not null,
        datacreacio timestamp not null,
        llocid number(19,0) not null,
        numerocai varchar2(50 char) not null,
        observacions long,
        usuariid number(19,0),
        primary key (historicllocid)
    );

    create table rfh_idioma (
       idiomaid varchar2(5 char) not null,
        nom varchar2(50 char) not null,
        ordre number(10,0) default 0 not null,
        suportat number(1,0) not null,
        primary key (idiomaid)
    );

    create table rfh_lloc (
       llocid number(19,0) not null,
        codilloc varchar2(50 char) not null,
        databaixa timestamp,
        datacreacio timestamp not null,
        entitatid number(19,0) not null,
        nom varchar2(255 char) not null,
        observacions long,
        personaloamr number(10,0) not null,
        unitatid number(19,0) not null,
        primary key (llocid)
    );

    create table rfh_llocrol (
       llocrolid number(19,0) not null,
        datacreacio timestamp not null,
        llocid number(19,0) not null,
        rolid number(19,0) not null,
        primary key (llocrolid)
    );

    create table rfh_plugin (
       pluginid number(19,0) not null,
        actiu number(1,0) not null,
        classe varchar2(255 char),
        datacreacio timestamp,
        descripcio varchar2(255 char) not null,
        entitatid number(19,0) not null,
        nom varchar2(255 char) not null,
        properties long,
        tipus varchar2(50 char) not null,
        primary key (pluginid)
    );

    create table rfh_rol (
       rolid number(19,0) not null,
        codi varchar2(50 char) not null,
        datacreacio timestamp,
        entitatid number(19,0) not null,
        nomid number(19,0) not null,
        primary key (rolid)
    );

    create table rfh_traduccio (
       traduccioid number(19,0) not null,
        primary key (traduccioid)
    );

    create table rfh_traducciomap (
       traducciomapid number(19,0) not null,
        valor varchar2(4000 char),
        idiomaid varchar2(255 char) not null,
        primary key (traducciomapid, idiomaid)
    );

    create table rfh_unitat (
       unitatid number(19,0) not null,
        arrel varchar2(50 char),
        arrelversio number(10,0),
        codi varchar2(50 char) not null,
        cooficial varchar2(255 char),
        denominacio varchar2(255 char) not null,
        estat varchar2(5 char),
        superior varchar2(50 char),
        superiorversio number(10,0),
        versio number(10,0) not null,
        primary key (unitatid)
    );

    create table rfh_usuari (
       usuariid number(19,0) not null,
        actiu number(1,0) not null,
        correu varchar2(255 char) not null,
        darreraentitat number(19,0),
        databaixa timestamp,
        datacreacio timestamp not null,
        idiomaid varchar2(5 char) not null,
        llinatge1 varchar2(255 char) not null,
        llinatge2 varchar2(255 char),
        nif varchar2(50 char) not null,
        nom varchar2(255 char) not null,
        username varchar2(255 char),
        primary key (usuariid)
    );

    create table rfh_usuarientitat (
       usuarientitatid number(19,0) not null,
        actiu number(1,0) not null,
        entitatid number(19,0) not null,
        usuariid number(19,0) not null,
        primary key (usuarientitatid)
    );
create index rfh_activitat_pk_i on rfh_activitat (activitatid);
create index rfh_activitat_fun_fk_i on rfh_activitat (funcionariid);
create index rfh_activitat_autoritzaid_fk_i on rfh_activitat (autoritzacioid);
create index rfh_autoritzacio_pk_i on rfh_autoritzacio (autoritzacioid);
create index rfh_autoritza_funid_fk_i on rfh_autoritzacio (llocid);
create index rfh_autoritza_funcionariid_fk_i on rfh_autoritzacio (funcionariid);
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

    alter table rfh_funcionarilloc 
       add constraint rfh_funlloc_multiple_uk unique (llocid, funcionariid);
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
       add constraint rfh_lloc_codilloc_uk unique (codilloc);
create index rfh_llocrol_pk_i on rfh_llocrol (llocrolid);
create index rfh_llocrol_llocid_fk_i on rfh_llocrol (llocid);
create index rfh_llocrol_rolid_fk_i on rfh_llocrol (rolid);
create index rfh_plugin_pk_i on rfh_plugin (pluginid);
create index rfh_plugin_entitatid_fk_i on rfh_plugin (entitatid);
create index rfh_rol_pk_i on rfh_rol (rolid);
create index rfh_rol_nomid_fk_i on rfh_rol (nomid);
create index rfh_traduccio_pk_i on rfh_traduccio (traduccioid);
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
       add constraint rfh_autoritza_funcionari_funcionariid_fk 
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
