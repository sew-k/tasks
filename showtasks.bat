call runcrud
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo There was an error.

:openbrowser
start opera "http://localhost:8080/crud/v1/tasks"

