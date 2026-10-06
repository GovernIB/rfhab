INSERT INTO rfh_idioma(idiomaid, nom, suportat, ordre) VALUES ('ca', 'Català', 1, 0);
INSERT INTO rfh_idioma(idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', 1, 1);

INSERT INTO RFH_UNITAT(unitatid, codi, versio, denominacio, cooficial, arrel, superior, estat, superiorversio, arrelversio) VALUES(1, 'A04003003', 1, 'Govern de les Illes Balears', 'Govern de les Illes Balears', NULL, NULL, 'V', null,NULL);

INSERT INTO RFH_ENTITAT(entitatid, nom, actiu, unitatid, databaixa) 
VALUES (1, 'Govern de les Illes Balears', 1, 1, NULL);

INSERT INTO RFH_USUARI(usuariid, nom, llinatge1, llinatge2, nif, correu, actiu, datacreacio, idiomaid, username, darreraentitat, databaixa) 
VALUES (1, 'RFHAB', 'NomUsuari', '', '99999999R', 'rfhab@fundaciobit.org', 1, TO_TIMESTAMP('2026-06-22 14:42:30', 'YYYY-MM-DD HH24:MI:SS'), 'ca', 'rfhab', 1, NULL);

INSERT INTO rfh_usuarientitat (
	usuarientitatid,
	actiu,
	entitatid,
	usuariid
) VALUES (
	1,
	1,
	1,
	1
);



INSERT INTO rfh_lloc (
    llocid,
    codilloc,
    codillocpropi,
    databaixa,
    datacreacio,
    dataalta,
    entitatid,
    expansio,
    nom,
    observacions,
    personaloamr,
    unitatid    
) VALUES (
    1,
    'LF000001',
    'PFH_000001',
    null,
    TO_TIMESTAMP('2026-06-22 14:42:30', 'YYYY-MM-DD HH24:MI:SS'),
    TO_TIMESTAMP('2026-06-22 14:42:30', 'YYYY-MM-DD HH24:MI:SS'),
    1,
    1,
    'Placeholder carrega de dades massiva',
    'Lloc de feina temporal per a la carrega inicial de dades',
    0,
    1    
);

INSERT INTO rfh_habilitacio (
    habilitacioid,
    codi,
    datacreacio,
    entitatid,
    nomid
) VALUES (
    1,
    'CEA',
    TO_TIMESTAMP('2026-06-22 14:42:30', 'YYYY-MM-DD HH24:MI:SS'),
    1,
    'ca'
);





