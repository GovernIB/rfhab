-- Es suposa l'execució prèvia dels scripts 01 fins al 07.

--
-- unitatid < 1000
--
INSERT INTO rfh_unitat(unitatid, codi, versio, denominacio, cooficial, arrel, superior, estat, superiorversio, arrelversio) VALUES (1, 'A04003003', 1, 'Govern de les Illes Balears', 'Govern de les Illes Balears', NULL, NULL, 'V', NULL, NULL);

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
INSERT INTO rfh_usuari(usuariid, nom, llinatge1, llinatge2, nif, correu, actiu, datacreacio, idiomaid, username, darreraentitat, databaixa) VALUES (1, 'NomUsuari', 'RFHAB', NULL, '99999999R', 'rfhab@fundaciobit.org', true, TO_TIMESTAMP('2024-06-19 14:42:30', 'YYYY-MM-DD HH24:MI:SS'), 'ca', 'rfhab', 1, NULL);

--
-- usuarientitatid < 1000
-- actiu: ha de ser 1 per a que l'usuari es consideri actiu
--
INSERT INTO rfh_usuarientitat(usuarientitatid, entitatid, usuariid, actiu) VALUES (1, 1, 1, true);
