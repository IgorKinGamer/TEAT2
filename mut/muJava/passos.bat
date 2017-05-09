@echo off

rem Definir
for /f "delims=" %%p in ('cd') ^
do (
	set muJava=%%p
)
set eclipse=%1
for /f %%i in ('dir /b %eclipse%\plugins\org.junit_*') ^
do (
	set JUnitJar=%eclipse%\plugins\%%i\junit.jar
)
for /f %%i in ('dir /b %eclipse%\plugins\org.hamcrest.core_*.jar') ^
do (
	set HamcrestJar=%eclipse%\plugins\%%i
)

rem Baixar
powershell -Command "wget https://cs.gmu.edu/~offutt/mujava/mujava.jar    -OutFile mujava.jar"
powershell -Command "wget https://cs.gmu.edu/~offutt/mujava/openjava.jar  -OutFile openjava.jar"

rem CLASSPATH
set CLASSPATH=.;%muJava%\mujava.jar
set CLASSPATH=%CLASSPATH%;%muJava%\openjava.jar
set CLASSPATH=%CLASSPATH%;%JAVA_HOME%\lib\tools.jar
rem JUnit
set CLASSPATH=%CLASSPATH%;%JUnitJar%
rem Hamcrest
set CLASSPATH=%CLASSPATH%;%HamcrestJar%
rem ..\..\sc_instrumenter\deps\junit-4.10.jar
rem ..\..\sc_instrumenter\deps\hamcrest-core-1.3.jar

rem Criar %muJava%\mujava.config
echo MuJava_HOME=%muJava%> "%muJava%\mujava.config"