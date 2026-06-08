
   --  alter table rfh_activitat 
   --     drop constraint if exists rfh_activitat_autoritza_aut_fk;

    alter table rfh_activitat 
       drop constraint if exists rfh_activitat_funcionari_fu_fk;

    alter table rfh_autoritzacio 
       drop constraint if exists rfh_autoritza_funcionari_funcionariid_fk;

    alter table rfh_autoritzacio 
       drop constraint if exists rfh_autoritza_lloc_fd_fk;

    alter table rfh_digitalib 
       drop constraint if exists rfh_scanweb_fitxer_fitxerid_fk;

    alter table rfh_digitalib 
       drop constraint if exists rfh_scanweb_usuari_usuariid_fk;

    alter table rfh_entitat 
       drop constraint if exists rfh_entitat_unitat_unitatid_fk;

    alter table rfh_funcionarilloc 
       drop constraint if exists rfh_funlloc_funcionari_f_fk;

    alter table rfh_funcionarilloc 
       drop constraint if exists rfh_funlloc_lloc_llocid_fk;

    alter table rfh_funcionarilloc 
       drop constraint if exists rfh_funlloc_usuari_usuid_fk;

    alter table rfh_historic 
       drop constraint if exists rfh_historic_funcionari_f_fk;

    alter table rfh_historic 
       drop constraint if exists rfh_historic_usuari_usuid_fk;

    alter table rfh_historiclloc 
       drop constraint if exists rfh_histolloc_lloc_llocid_fk;

    alter table rfh_historiclloc 
       drop constraint if exists rfh_histolloc_usuari_id_fk;

    alter table rfh_lloc 
       drop constraint if exists rfh_lloc_codillocpropi_uk;

    alter table rfh_lloc 
       drop constraint if exists rfh_lloc_entitat_entitatid_fk;

    alter table rfh_lloc 
       drop constraint if exists rfh_lloc_unitat_unitatid_fk;

    alter table rfh_llochabilitacio 
       drop constraint if exists rfh_llochabil_lloc_llocid_fk;

    alter table rfh_llochabilitacio 
       drop constraint if exists rfh_llochabil_hab_habid_fk;

    alter table rfh_plugin 
       drop constraint if exists rfh_plugin_entitat_entitati_fk;

    alter table rfh_sincrounitats 
       drop constraint if exists rfh_sncunitats_usuari_usuid_fk;

    alter table rfh_habilitacio 
       drop constraint if exists rfh_habil_traduccio_nomid_fk;

    alter table rfh_traducciomap 
       drop constraint if exists rfh_traducmap_traduccio_fk;

    alter table rfh_usuari 
       drop constraint if exists rfh_usuari_entitat_last_fk;

    alter table rfh_usuari 
       drop constraint if exists rfh_usuari_idioma_idiomaid_fk;

    alter table rfh_usuarientitat 
       drop constraint if exists rfh_usuarient_entitat_entit_fk;

    alter table rfh_usuarientitat 
       drop constraint if exists rfh_usuarient_usuari_usuari_fk;

    drop table if exists rfh_activitat cascade;

    drop table if exists rfh_autoritzacio cascade;

    drop table if exists rfh_digitalib cascade;

    drop table if exists rfh_entitat cascade;

    drop table if exists rfh_fitxer cascade;

    drop table if exists rfh_funcionari cascade;

    drop table if exists rfh_funcionarilloc cascade;

    drop table if exists rfh_historic cascade;

    drop table if exists rfh_historiclloc cascade;

    drop table if exists rfh_idioma cascade;

    drop table if exists rfh_lloc cascade;

    drop table if exists rfh_llochabilitacio cascade;

    drop table if exists rfh_plugin cascade;

    drop table if exists rfh_sincrounitats cascade;

    drop table if exists rfh_habilitacio cascade;

    drop table if exists rfh_traduccio cascade;

    drop table if exists rfh_traducciomap cascade;

    drop table if exists rfh_unitat cascade;

    drop table if exists rfh_usuari cascade;

    drop table if exists rfh_usuarientitat cascade;

    drop sequence if exists rfh_activitat_seq;

    drop sequence if exists rfh_autoritzacio_seq;

    drop sequence if exists rfh_digitalib_seq;

    drop sequence if exists rfh_entitat_seq;

    drop sequence if exists rfh_fitxer_seq;

    drop sequence if exists rfh_funcionari_seq;

    drop sequence if exists rfh_funcionarilloc_seq;

    drop sequence if exists rfh_historic_seq;

    drop sequence if exists rfh_historiclloc_seq;

    drop sequence if exists rfh_lloc_seq;

    drop sequence if exists rfh_llochabilitacio_seq;

    drop sequence if exists rfh_plugin_seq;

    drop sequence if exists rfh_sincrounitats_seq;

    drop sequence if exists rfh_habilitacio_seq;

    drop sequence if exists rfh_traduccio_seq;

    drop sequence if exists rfh_unitat_seq;

    drop sequence if exists rfh_usuari_seq;

    drop sequence if exists rfh_usuarientitat_seq;
