<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<section class="container-fluid px-3 py-2">
  <h1 class="h3 mb-3">Declaracio d'accessibilitat</h1>

  <p class="mb-3">
    L'Agència Balear d'Innovació i tecnologia s’ha compromès a fer accessible
    els seus llocs web i les seves aplicacions per a dispositius mòbils, de
    conformitat amb el Reial Decret 1112/2018 de 7 de setembre, d'accessibilitat
    dels llocs web i aplicacions mòbils del sector públic. La present declaració
    d’accessibilitat s’aplica a la web https://www.intranet.caib.es/rfhab
  </p>

  <h2 class="h5 mb-2">Situació de compliment</h2>

  <p>
    Aquest lloc web és parcialment conforme amb el Reial Decret 1112/2018 a
    causa de les excepcions i la manca de conformitat dels aspectes que
    s'indiquen a continuació.
  </p>

  <h2 class="h5 mb-2">Contingut no accesible</h2>
  <p class="mb-3">
    El contingut que es recull a continuació no és accessible pels següents
    motius:
  </p>
  <p>
    <span class="font-weight-bold">Manca de conformitat amb</span>
    <a
      href="https://www.caib.es/seucaib/es/200/personas/tramites/tramite/4057494"
      target="goib"
    >
      RD 1112/2018</a
    >
  </p>

  <br />
  <ul class="mb-3">
    <li>
      <p>
        <span class="font-weight-bold">
          1. Ús d’encapçalaments: Absència d’un encapçalament principal H1 o
          estructura incorrecta.
        </span>
      </p>
      <p>
        <span>
          Els elements d’encapçalament (H1...H6) serveixen per identificar els
          títols de les diferents seccions en què s’estructura un document. El
          nivell emprat en cada encapçalament és el que definirà l’estructura
          jeràrquica de les seccions del document. Per tant, aquesta estructura
          d’encapçalaments i els nivells emprats ha de ser correcta, reflectint
          l’estructura lògica del contingut de la pàgina, identificant com a
          encapçalaments tots els títols de secció, sense emprar elements
          d’encapçalament únicament per crear efectes de presentació i sense
          saltar nivells intermedis en descendir en la jerarquia
          d’encapçalaments.
        </span>
      </p>
      <p>
        <span>
          S'ha detectat que la majoria de les pàgines analitzades no contenen
          cap element d'encapçalament, o els que hi ha no inclouen un
          encapçalament principal H1 que correspongui amb el títol de la pàgina.
          Addicionalment, en una de les pàgines s'han trobat encapçalaments
          consecutius del mateix nivell sense contingut entre ells, cosa que
          genera una estructura jeràrquica incorrecta.
        </span>
      </p>
      <p>
        <span>
          Una estructura correcta d’encapçalaments és de gran importància, ja
          que les aplicacions d’usuari i els productes de suport, com els
          lectors de pantalla, poden proporcionar mecanismes especials de
          navegació que permetin als usuaris accedir de manera ràpida a les
          diferents seccions que componen una pàgina web (p. ex. mitjançant un
          índex o mapa del document amb accessos directes a les diferents
          seccions).
        </span>
      </p>
    </li>
    <li>
      <p>
        <span class="font-weight-bold">
          2. Separació de contingut i presentació: Generació de contingut des de
          fulls d'estil.
        </span>
      </p>
      <p>
        <span>
          No s'han d'emprar els fulls d'estil per incloure contingut o
          informació, ja que d'aquesta manera no estarà disponible per als
          lectors de pantalla o altres aplicacions d'usuari no gràfiques. Tota
          la informació ha d'estar disponible en el contingut de la pàgina, en
          el codi HTML.
        </span>
      </p>
      <p>
        <span>
          S'ha detectat que el lloc web inclou text des de fulls d'estil CSS
          mitjançant els pseudoelements <code>:before</code> o
          <code>:after</code> i la propietat <code>content</code>. Això afecta
          components com els calendaris i selectors de data/hora.
        </span>
      </p>
    </li>
    <li>
      <p>
        <span class="font-weight-bold">
          3. Independència del dispositiu: Indicador del focus del teclat no
          visible.
        </span>
      </p>
      <p>
        <span>
          L'indicador del focus del teclat ha de ser visible perquè els usuaris
          que fan servir un navegador gràfic amb teclat o un dispositiu similar
          com a mitjà d'interacció (persones amb discapacitats motrius) puguin
          veure la posició del focus mentre tabulen pels elements d'interacció
          de la pàgina.
        </span>
      </p>
      <p>
        <span>
          S'ha detectat que el codi CSS del lloc web elimina l'indicador del
          focus per defecte dels navegadors (mitjançant propietats com
          <code>outline: none</code> o <code>outline: 0</code>) sense
          proporcionar cap estil alternatiu. Això fa que els usuaris que
          naveguen amb teclat no puguin veure en quin element es troben.
        </span>
      </p>
    </li>
    <li>
      <p>
        <span class="font-weight-bold">
          4. Múltiples vies de navegació: Absència d'un mapa web i d'un cercador
          al lloc.
        </span>
      </p>
      <p>
        <span>
          Com que els usuaris poden tenir diferents limitacions o necessitats,
          per facilitar que tothom pugui trobar la informació que busca en un
          lloc web és necessari proporcionar mecanismes alternatius al menú de
          navegació principal. A alguns usuaris els pot resultar més útil o
          fàcil consultar un mapa web o utilitzar una funció de cerca que
          interactuar amb els menús de navegació.
        </span>
      </p>
      <p>
        <span>
          S'ha detectat que algunes pàgines del lloc web (pàgines 2, 3, 6 i 7)
          no ofereixen cap mecanisme alternatiu de navegació, com ara un enllaç
          a un mapa web o una opció de cerca al lloc.
        </span>
      </p>
    </li>
    <li>
      <p>
        <span class="font-weight-bold">
          5. Formularis i etiquetes: Camps de formulari sense etiqueta o sense
          indicar quins són obligatoris.
        </span>
      </p>
      <p>
        <span>
          Els camps de formulari han de disposar d'un text a mode d'etiqueta que
          identifiqui quina és la seva funció, associat explícitament mitjançant
          l'element <code>LABEL</code> o, alternativament, amb atributs com
          <code>title</code> o <code>aria-label</code>. A més, en els formularis
          amb diversos camps s'ha de proporcionar informació perquè els usuaris
          puguin diferenciar entre els camps obligatoris i els opcionals.
        </span>
      </p>
      <p>
        <span>
          S'han detectat diverses incidències: hi ha camps de formulari (com
          caselles de selecció) que no tenen cap etiqueta associada que
          n'identifiqui la finalitat; hi ha formularis de filtres on no s'indica
          quins camps són obligatoris; i en una pàgina s'ha detectat que les
          etiquetes <code>LABEL</code> existeixen però estan ocultes visualment.
        </span>
      </p>
    </li>
    <li>
      <p>
        <span class="font-weight-bold">
          6. Taules de dades: Estructura incorrecta dels encapçalaments.
        </span>
      </p>
      <p>
        <span>
          Si en la primera fila d'una taula de dades hi ha alguna cel·la
          d'encapçalament (<code>TH</code>), llavors la resta de cel·les
          d'aquella fila que continguin text també han de ser encapçalaments. El
          mateix és aplicable per a la primera columna. És a dir, en la primera
          fila i en la primera columna no poden barrejar-se cel·les de dades i
          cel·les d'encapçalament. Les taules de dades han d'estar correctament
          estructurades perquè els productes de suport, com els lectors de
          pantalla, les puguin interpretar correctament.
        </span>
      </p>
      <p>
        <span>
          S'ha detectat una taula de dades on la primera fila barreja cel·les
          d'encapçalament amb cel·les de dades, cosa que dificulta la
          interpretació de la informació per part de les tecnologies de suport.
        </span>
      </p>
    </li>
    <li>
      <p>
        <span class="font-weight-bold">
          7. Títol de pàgina i de marcs: Absència d'un títol adequat per al marc
          iframe.
        </span>
      </p>
      <p>
        <span>
          S'ha d'evitar, en la mesura del possible, l'ús de marcs a les pàgines.
          De no ser possible evitar-ho, s'ha de proporcionar un títol a cada
          marc que descrigui la seva finalitat o el seu contingut, de manera que
          els usuaris de lectors de pantalla puguin decidir prèviament si volen
          accedir o no al seu contingut. Aquesta descripció s'ha d'indicar en
          l'atribut <code>title</code>.
        </span>
      </p>
      <p>
        <span>
          S'ha detectat un element <code>IFRAME</code> (utilitzat per mostrar
          documents PDF) que no disposa de l'atribut <code>title</code> que en
          descrigui el propòsit.
        </span>
      </p>
    </li>
    <li>
      <p>
        <span class="font-weight-bold">
          8. Compatibilitat: Errors en el codi CSS.
        </span>
      </p>
      <p>
        <span>
          El codi de les fulls d'estil ha d'estar lliure d'errors que puguin
          suposar una inconsistència en la seva interpretació per part de
          diferents navegadors o aplicacions d'usuari. El codi ha de poder ser
          processat sense inconsistències, és a dir, ha de ser com a mínim
          sintàcticament correcte.
        </span>
      </p>
      <p>
        <span>
          S'han detectat errors d'anàlisi (parse errors) en blocs d'estil
          incrustats a la pàgina, concretament en l'ús del selector
          <code>last-child</code>, que poden afectar la correcta visualització o
          funcionament de la pàgina en alguns navegadors.
        </span>
      </p>
    </li>
  </ul>
  <br />

  <h2 class="h5 mb-2">Preparació de la present declaració d'accessibilitat</h2>
  <p class="mb-3">
    La present declaració va ser preparada el 3 de novembre de 2026 mitjançant
    una autoevaluació del mateix organisme.
  </p>
  <p>Darrera revisió de la declaració: 3 de novembre de 2026.</p>

  <h2 class="h5 mb-2">Observacions i dades de contacte</h2>
  <p class="mb-3">
    Es pot entrar en contacte amb l'Agència Balear de Digitalització,
    Ciberseguretat i Telecomunicacions per realitzar comunicacions sobre
    requisits d'accessibilitat (article 10.2.a) del RD 1112/2018 com per
    exemple:
  </p>

  <ul>
    <li>
      Informar sobre qualsevol possible incompliment per part d'aquest lloc web.
    </li>
    <li>Transmetre altres dificultats d'accés al contingut.</li>
    <li>
      Formular qualsevol altre consulta o suggeriment relacionat amb
      l'accessibilitat del lloc web.
    </li>
  </ul>
  <p>
    A través del seguent formulari de contacte, o cridant al telèfon 971177140.
    Es pot presentar:
  </p>
  <ul>
    <li>Una queixa relativa al compliment dels requisits del RD 1112/2018 o</li>
    <li>
      Una sol·licitud de informació accesible relativa a:
      <ul class="ml-4 subcategories">
        <li>
          Continguts que estan exclosos de l'àmbit d'aplicació del RD 1112/2018,
          segons el que estableix l'article 3, apartat 4 o
        </li>

        <li>
          Continguts que es troben exents del compliment dels requisits
          d'accessibilitat, per imposar una càrrega desproporcionada.
        </li>
      </ul>
    </li>
  </ul>
  <p>
    A través del següent procediment:
    <a
      href="https://www.caib.es/seucaib/es/200/personas/tramites/tramite/4055271/"
      target="goib"
      >Peticions d'informació accessible i queixes relatives a accessibilitat de
      llocs web i aplicacions mòbils.</a
    >
  </p>

  <h2 class="h5 mb-2">Procediment d'aplicació</h2>
  <p class="mb-3">
    El procediment de reclamació recollit en l’article 13 del RD 1112/2018 va
    entrar en vigor el 20 de setembre de 2020. Si, una vegada presentada una
    sol·licitud d’informació accessible o una queixa, aquesta hagués estat
    desestimada, no s’estigués d’acord amb la decisió adoptada, o la resposta no
    complís els requisits previstos en l’article 12.5, la persona interessada
    podrà iniciar una reclamació. Igualment, es podrà iniciar una reclamació en
    cas que hagi transcorregut el termini de vint dies hàbils sense haver
    obtingut resposta. La reclamació es pot presentar a través del procediment
    <a
      href="https://www.caib.es/seucaib/es/200/personas/tramites/tramite/4057494"
      target="goib"
    >
      Reclamacions relatives a l’accessibilitat de llocs web i aplicacions
      mòbils.</a
    >
  </p>

  <h2 class="h5 mb-2">Contingut opcional</h2>
  <p class="mb-3">
    La darrera revisió d'accessibilitat s'ha realitzat al novembre de 2026.
    S'han dut a terme una validació manual amb eines assistides, i s'han
    corregit els errors per una autoevaluació duta a terme per l'organisme del
    sector públic. El resultat obtingut és el seguent:
  </p>
  <p>Nivell A WCAG 2.1</p>
  <p>Situació de compliment estimada: Parcialment Conforme.</p>
</section>
