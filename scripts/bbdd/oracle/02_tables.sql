
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
        url varchar2(255 char)
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
        observacions clob,
        procediment varchar2(255 char) not null,
        usuariid number(19,0)
    );

    create table rfh_digitalib (
       digitalid number(19,0) not null,
        datacreacio timestamp,
        entitatid number(19,0) not null,
        fileinfo clob,
        fitxerid number(19,0) not null,
        metadades clob,
        missatge clob,
        signedfileinfo clob,
        status number(19,0) not null,
        transactionid number(19,0),
        transactionwebid varchar2(255 char),
        usuariid number(19,0)
    );

    create table rfh_entitat (
       entitatid number(19,0) not null,
        actiu number(1,0) not null,
        databaixa timestamp,
        nom varchar2(255 char),
        unitatid number(19,0)
    );

    create table rfh_fitxer (
       fitxerid number(19,0) not null,
        descripcio varchar2(1000 char),
        mime varchar2(255 char) not null,
        nom varchar2(255 char) not null,
        tamany number(19,0) not null
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
        numero number(10,0) not null,
        observacions clob,
        tipusidentificador number(10,0) not null,
        usuari varchar2(50 char) not null
    );

    create table rfh_funcionarilloc (
       funcionarillocid number(19,0) not null,
        datacreacio timestamp not null,
        datafi date,
        datainici date,
        funcionariid number(19,0) not null,
        llocid number(19,0) not null,
        usuariid number(19,0)
    );

    create table rfh_funcionarirol (
       funcionarirolid number(19,0) not null,
        datacreacio timestamp not null,
        funcionariid number(19,0) not null,
        rolid number(19,0) not null
    );

    create table rfh_historic (
       historicid number(19,0) not null,
        datacreacio timestamp not null,
        funcionariid number(19,0) not null,
        numerocai varchar2(50 char) not null,
        observacions clob,
        usuariid number(19,0)
    );

    create table rfh_historiclloc (
       historicllocid number(19,0) not null,
        datacreacio timestamp not null,
        llocid number(19,0) not null,
        numerocai varchar2(50 char) not null,
        observacions clob,
        usuariid number(19,0)
    );

    create table rfh_idioma (
       idiomaid varchar2(5 char) not null,
        nom varchar2(50 char) not null,
        ordre number(10,0) default 0 not null,
        suportat number(1,0) not null
    );

    create table rfh_lloc (
       llocid number(19,0) not null,
        codilloc varchar2(50 char) not null,
        databaixa timestamp,
        datacreacio timestamp not null,
        entitatid number(19,0) not null,
        nom varchar2(255 char) not null,
        observacions clob,
        personaloamr number(10,0) not null,
        unitatid number(19,0) not null
    );

    create table rfh_plugin (
       pluginid number(19,0) not null,
        actiu number(1,0) not null,
        classe varchar2(255 char),
        datacreacio timestamp,
        descripcio varchar2(255 char) not null,
        entitatid number(19,0) not null,
        nom varchar2(255 char) not null,
        properties clob,
        tipus varchar2(50 char)
    );

    create table rfh_rol (
       rolid number(19,0) not null,
        codi varchar2(50 char) not null,
        datacreacio timestamp,
        entitatid number(19,0),
        nomid number(19,0) not null
    );

    create table rfh_traduccio (
       traduccioid number(19,0) not null
    );

    create table rfh_traducciomap (
       traducciomapid number(19,0) not null,
        valor varchar2(4000 char),
        idiomaid varchar2(255 char) not null
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
        versio number(10,0) not null
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
        username varchar2(255 char)
    );

    create table rfh_usuarientitat (
       usuarientitatid number(19,0) not null,
        actiu number(1,0) not null,
        entitatid number(19,0) not null,
        usuariid number(19,0) not null
    );






