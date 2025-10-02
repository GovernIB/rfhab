@echo off
REM Comprova si el fitxer existeix
if not exist "%1" goto notfound
REM Cerca l'apòstrof (')
findstr "'" "%1" >nul
if %errorlevel%==0 goto found
exit /b 0

:found
echo.
echo ==================== ERROR ====================
echo ERROR: S'ha trobat un apòstrof (') en el fitxer:
echo   %1
echo ===============================================
exit /b 1

:notfound
echo ERROR: Fitxer no trobat: %1
exit /b 1