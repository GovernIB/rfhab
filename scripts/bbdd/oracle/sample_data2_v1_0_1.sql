-- Es suposa l'execució prèvia dels scripts 01 fins al 06 i de sample_sata_unitats_v1_0_1.


INSERT INTO RFH_TRADUCCIO (traduccioid)
SELECT 1
FROM dual
WHERE NOT EXISTS (
    SELECT 1 
    FROM RFH_TRADUCCIO 
    WHERE traduccioid = 1
);

INSERT INTO RFH_TRADUCCIO (traduccioid)
SELECT 2
FROM dual
WHERE NOT EXISTS (
    SELECT 1 
    FROM RFH_TRADUCCIO 
    WHERE traduccioid = 2
);

INSERT INTO RFH_TRADUCCIOMAP (traducciomapid, valor, idiomaid)
SELECT 1, 'Còpia autèntica', 'ca'
FROM dual
WHERE NOT EXISTS (
    SELECT 1 
    FROM RFH_TRADUCCIOMAP 
    WHERE traducciomapid = 1
);

INSERT INTO RFH_TRADUCCIOMAP (traducciomapid, valor, idiomaid)
SELECT 2, 'Copia auténtica', 'es'
FROM dual
WHERE NOT EXISTS (
    SELECT 1 
    FROM RFH_TRADUCCIOMAP 
    WHERE traducciomapid = 2
);


--
-- entitatid < 1000
--
INSERT INTO RFH_ENTITAT(entitatid, nom, actiu, unitatid, databaixa) 
VALUES (1, 'Govern de les Illes Balears', 1, 1, NULL);

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
INSERT INTO RFH_USUARI(usuariid, nom, llinatge1, llinatge2, nif, correu, actiu, datacreacio, idiomaid, username, darreraentitat, databaixa) 
VALUES (1, 'NomUsuari', 'RFHAB', NULL, '99999999R', 'rfhab@fundaciobit.org', 1, TO_TIMESTAMP('2024-06-19 14:42:30', 'YYYY-MM-DD HH24:MI:SS'), 'ca', 'rfhab', 1, NULL);

--
-- usuarientitatid < 1000
-- actiu: ha de ser 1 per a que l'usuari es consideri actiu
--
INSERT INTO RFH_USUARIENTITAT(usuarientitatid, entitatid, usuariid, actiu) VALUES (1, 1, 1, 1);

-- funcionari per a carrega massiva
INSERT INTO RFH_FUNCIONARI(funcionariid, correu, databaixa, datacreacio, entitatid, identificador, llinatge1, llinatge2, nom, numero, observacions, tipusidentificador, usuari) VALUES (1, 'rfhab@fundaciobit.org', NULL, TO_TIMESTAMP('2026-01-28 09:15:30', 'YYYY-MM-DD HH24:MI:SS'), 1, '99999999R', 'RFHAB', NULL, 'NomUsuari', 'FH_0000001', NULL, 1, 'rfhab');

-- lloc de feina per a carrega massiva
-- personaloamr = 1 --> NO Personal OAMR
-- personaloamr = 2 --> SÍ Personal OAMR
INSERT INTO RFH_LLOC(llocid, codilloc, codillocpropi, databaixa, datacreacio, dataalta, entitatid, expansio, nom, observacions, personaloamr, unitatid) 
             VALUES (1,
             'LF000001',
             'PFH_000001',
             null, TO_TIMESTAMP('2026-02-03 23:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2026-02-03 23:00:00', 'YYYY-MM-DD HH24:MI:SS') 
             ,1,1,  'Placeholder carrega de dades massiva', 'Lloc de feina temporal per a la carrega inicial de dades', 1, 1);

-- Assignacio de funcionari a lloc de feina per a carrega massiva
INSERT INTO RFH_FUNCIONARILLOC(funcionarillocid, datacreacio, datafi, datainici, funcionariid, llocid, usuariid) 
             VALUES (1, TO_TIMESTAMP('2026-02-03 23:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2030-12-31 23:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2026-02-03 23:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1, 1);


INSERT INTO RFH_HABILITACIO(habilitacioid, codi, datacreacio, entitatid, nomid) 
             VALUES (1, 'CEA', TO_TIMESTAMP('2026-02-03 23:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1);     