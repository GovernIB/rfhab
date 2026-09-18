INSERT INTO rfh_idioma(idiomaid, nom, suportat, ordre) VALUES ('ca', 'Català', 1, 0);
INSERT INTO rfh_idioma(idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', 1, 1);

------------------------------------------------------------------------------
-- RFH_UNITAT
------------------------------------------------------------------------------

INSERT INTO RFH_UNITAT(unitatid, codi, versio, denominacio, cooficial, arrel, superior, estat, superiorversio, arrelversio) VALUES(1, 'A04003003', 1, 'Govern de les Illes Balears', 'Govern de les Illes Balears', NULL, NULL, 'V', null,NULL);

------------------------------------------------------------------------------
-- RFH_ENTITAT
------------------------------------------------------------------------------

INSERT INTO RFH_ENTITAT(entitatid, nom, actiu, unitatid, databaixa) 
VALUES (1, 'Govern de les Illes Balears', 1, 1, NULL);