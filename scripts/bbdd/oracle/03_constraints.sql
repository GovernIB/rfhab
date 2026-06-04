
 -- INICI PKs
    alter table rfh_activitat add constraint rfh_activitat_pk primary key (activitatid);

    alter table rfh_autoritzacio add constraint rfh_autoritzacio_pk primary key (autoritzacioid);

    alter table rfh_digitalib add constraint rfh_digitalib_pk primary key (digitalid);

    alter table rfh_entitat add constraint rfh_entitat_pk primary key (entitatid);

    alter table rfh_fitxer add constraint rfh_fitxer_pk primary key (fitxerid);

    alter table rfh_funcionari add constraint rfh_funcionari_pk primary key (funcionariid);

    alter table rfh_funcionarilloc add constraint rfh_funcionarilloc_pk primary key (funcionarillocid);

    alter table rfh_historic add constraint rfh_historic_pk primary key (historicid);

    alter table rfh_historiclloc add constraint rfh_historiclloc_pk primary key (historicllocid);

    alter table rfh_idioma add constraint rfh_idioma_pk primary key (idiomaid);

    alter table rfh_lloc add constraint rfh_lloc_pk primary key (llocid);

    alter table rfh_llochabilitacio add constraint rfh_llochabilitacio_pk primary key (llochabilitacioid);

    alter table rfh_plugin add constraint rfh_plugin_pk primary key (pluginid);

    alter table rfh_habilitacio add constraint rfh_habilitacio_pk primary key (habilitacioid);

    alter table rfh_traduccio add constraint rfh_traduccio_pk primary key (traduccioid);

    alter table rfh_traducciomap add constraint rfh_traducmap_pk primary key (traducciomapid, idiomaid);

    alter table rfh_unitat add constraint rfh_unitat_pk primary key (unitatid);

    alter table rfh_usuari add constraint rfh_usuari_pk primary key (usuariid);

    alter table rfh_usuarientitat add constraint rfh_usuarientitat_pk primary key (usuarientitatid);

    alter table rfh_sincrounitats add constraint rfh_sincrounitats_pk primary key (sincrounitatsid);

 -- FINAL PKs


 -- INICI FKs

   --  alter table rfh_activitat 
   --     add constraint rfh_activitat_autoritza_aut_fk 
   --     foreign key (autoritzacioid) 
   --     references rfh_autoritzacio;

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

    alter table rfh_llochabilitacio 
       add constraint rfh_llochabil_lloc_llocid_fk 
       foreign key (llocid) 
       references rfh_lloc;

    alter table rfh_llochabilitacio 
       add constraint rfh_llochabil_hab_habid_fk 
       foreign key (habilitacioid) 
       references rfh_habilitacio;

    alter table rfh_plugin 
       add constraint rfh_plugin_entitat_entitati_fk 
       foreign key (entitatid) 
       references rfh_entitat;

    alter table rfh_habilitacio 
       add constraint rfh_habil_traduccio_nomid_fk 
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

   alter table rfh_sincrounitats 
       add constraint rfh_sncunitats_usuari_usuid_fk 
       foreign key (usuariid) 
       references rfh_usuari;

 -- FINAL FKs


 -- INICI UNIQUEs

   alter table rfh_funcionari
      add constraint rfh_funcionari_identific_uk unique (identificador);

    alter table rfh_lloc 
       add constraint rfh_lloc_codillocexpansio_uk unique (expansio, codilloc);

    alter table rfh_lloc 
       add constraint rfh_lloc_codillocpropi_uk unique (codillocpropi);

    alter table rfh_usuari 
       add constraint rfh_usuari_username_uk unique (username);

    alter table rfh_usuarientitat 
       add constraint rfh_usuarient_multiple_uk unique (entitatid, usuariid);
 -- FINAL UNIQUEs

