-- Es suposa l'execució prèvia dels scripts 01 fins al 06 i de sample_sata_unitats_v1_0_1.

INSERT INTO rfh_traduccio (traduccioid)
VALUES (1)
ON CONFLICT (traduccioid) DO NOTHING;

INSERT INTO rfh_traduccio (traduccioid)
VALUES (2)
ON CONFLICT (traduccioid) DO NOTHING;

INSERT INTO rfh_traducciomap (traducciomapid, valor, idiomaid)
VALUES (1, 'Còpia autèntica', 'ca')
ON CONFLICT (traducciomapid) DO NOTHING;

INSERT INTO rfh_traducciomap (traducciomapid, valor, idiomaid)
VALUES (2, 'Copia auténtica', 'es')
ON CONFLICT (traducciomapid) DO NOTHING;

--
-- entitatid < 1000
--
INSERT INTO rfh_entitat(entitatid, nom, actiu, unitatid, databaixa) VALUES (1, 'Govern de les Illes Balears', true, 1, NULL);

--
-- usuariid < 1000
-- actiu: ha de ser 1 per a que l'usuari es consideri actiu
-- databaixa: ha de ser NULL per a que l'usuari es consideri actiu
--
-- TODO: Modificar i posar les dades de qualque usuari existent a keycloak del qual en conegueu les credencials (pot ser qualcun dels vostres usuaris). A més, aquest usuari que poseu hauria de tenir assignats els rols d'aplicació RFH_ADMIN, RFH_USER, RFH_SUPER i RFH_W.
-- Camps a modificar:
--  - nom
--  - llinatge1
--  - llinatge2
--  - nif
--  - correu
--  - username
--
INSERT INTO rfh_usuari(usuariid, nom, llinatge1, llinatge2, nif, correu, actiu, datacreacio, idiomaid, username, darreraentitat, databaixa) VALUES (1, 'NomUsuari', 'RFHAB', NULL, '99999999R', 'rfhab@fundaciobit.org', true, '2024-06-19 14:42:30', 'ca', 'rfhab', 1, NULL);

--
-- usuarientitatid < 1000
-- actiu: ha de ser 1 per a que l'usuari es consideri actiu
--
INSERT INTO rfh_usuarientitat(usuarientitatid, entitatid, usuariid, actiu) VALUES (1, 1, 1, true);

-- funcionari per a carrega massiva
INSERT INTO rfh_funcionari(funcionariid, correu, databaixa, datacreacio, entitatid, identificador, llinatge1, llinatge2, nom, numero, observacions, tipusidentificador, usuari) VALUES (1, 'rfhab@fundaciobit.org', NULL, '2026-01-28 09:15:30.001', 1, '99999999R', 'RFHAB', NULL, 'NomUsuari', 'FH_0000001', NULL, 1, 'rfhab');


-- lloc de feina per a carrega massiva
-- personaloamr = 1 --> NO Personal OAMR
-- personaloamr = 2 --> SÍ Personal OAMR
INSERT INTO RFH_LLOC(llocid, codilloc, codillocpropi, databaixa, datacreacio, dataalta, entitatid, expansio, nom, observacions, personaloamr, unitatid) 
             VALUES (1,
             'LF000001',
             'PFH_000001',
             null, '2026-02-03 23:00:00', '2026-02-03 23:00:00',
             ,1,1,  'Placeholder carrega de dades massiva', 'Lloc de feina temporal per a la carrega inicial de dades', 1, 1);

-- Assignacio de funcionari a lloc de feina per a carrega massiva
INSERT INTO RFH_FUNCIONARILLOC(funcionarillocid, datacreacio, datafi, datainici, funcionariid, llocid, usuariid) 
             VALUES (1, '2026-02-03 23:00:00', '2030-12-31 23:00:00', '2026-02-03 23:00:00', 1, 1, 1);


INSERT INTO RFH_HABILITACIO(habilitacioid, codi, datacreacio, entitatid, nomid) 
             VALUES (1, 'CEA', '2026-02-03 23:00:00', 1, 1);