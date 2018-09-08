@echo off
for /F "usebackq tokens=1,2 delims==" %%i in (`wmic os get LocalDateTime /VALUE 2^>NUL`) do if '.%%i.'=='.LocalDateTime.' set ldt=%%j
set ldt=%ldt:~0,4%-%ldt:~4,2%-%ldt:~6,2%_%ldt:~8,2%-%ldt:~10,2%-%ldt:~12,6%

@echo on

pan.bat /rep:FoodEngineETL /user:admin /pass: /trans:\Load_SubscribeUser /level:Basic /logfile:.\FoodEngineDWH_logs\DWH_Load_SubscribeUser_%ldt%.log