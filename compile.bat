@echo off

echo optional parameters -Dcaib -Psqlgen

cmd /C mvn clean install -DskipTests %* 

if %errorlevel% EQU 0 (

	@echo off
	IF DEFINED RFHAB_DEPLOY_DIR (
      for /f "tokens=* delims=" %%x in (versio.txt) do set RFHAB_VERSIO=%%x
	  @echo on
	  echo --------- COPIANT EAR %RFHAB_VERSIO% ---------

	  xcopy /Y rfhab-ear\target\rfhab.ear %RFHAB_DEPLOY_DIR%

	) ELSE (
	  echo  =================================================================
	  echo    Definex la variable d'entorn RFHAB_DEPLOY_DIR apuntant al
	  echo    directori de deploy del JBOSS  i automaticament s'hi copiara
	  echo    l'ear generat.
	  echo  =================================================================
	) 

)