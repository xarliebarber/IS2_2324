echo off

cd franquiciasuccommon
echo "Ejecutando en franquiciasuccommon"
mvn clean > franquiciasuccommon.log 2>&1
mvn compile >> franquiciasuccommon.log 2>&1
mvn package >> franquiciasuccommon.log 2>&1
mvn install >> franquiciasuccommon.log 2>&1
cd ..

cd franquiciasucdao
echo "Ejecutando en franquiciasucdao"
mvn clean > franquiciasucdao.log 2>&1
mvn compile >> franquiciasucdao.log 2>&1
mvn package >> franquiciasucdao.log 2>&1
mvn install >> franquiciasucdao.log 2>&1
cd ..

cd franquiciasucgui
echo "Ejecutando en franquiciasucgui"
mvn clean > franquiciasucgui.log 2>&1
mvn compile >> franquiciasucgui.log 2>&1
mvn package >> franquiciasucgui.log 2>&1
mvn install >> franquiciasucgui.log 2>&1
cd ..

cd franquiciasucbusiness
echo "Ejecutando en franquiciasucbusiness"
mvn clean > franquiciasucbusiness.log 2>&1
mvn compile >> franquiciasucbusiness.log 2>&1
mvn package >> franquiciasucbusiness.log 2>&1
mvn install >> franquiciasucbusiness.log 2>&1
cd ..

cd franquiciasucmain
echo "Ejecutando en franquiciasucmain"
mvn clean > franquiciasucmain.log 2>&1
mvn compile >> franquiciasucmain.log 2>&1
mvn package >> franquiciasucmain.log 2>&1
mvn install >> franquiciasucmain.log 2>&1
cd ..
