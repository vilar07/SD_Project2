# local execution
#echo "Executing the ccs node."
#cd /home/marco/Desktop/MuseumHeist/dirCCS
#java -cp "../genclass.jar:." serverSide.main.ServerControlCollectionSite 22316 127.0.0.1 22312
#echo "CCS Server shutdown."



# global execution
echo "Transfering data to the ccs node."
sshpass -f password ssh sd202@l040101-ws02.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd202@l040101-ws02.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirCCS.zip sd202@l040101-ws02.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the CCS node."
sshpass -f password ssh sd202@l040101-ws02.ua.pt 'cd test/MuseumHeist ; unzip -uq dirCCS.zip'
sshpass -f password scp genclass.zip sd202@l040101-ws02.ua.pt:test/MuseumHeist/dirCCS
sshpass -f password ssh sd202@l040101-ws02.ua.pt 'cd test/MuseumHeist/dirCCS ; unzip -uq genclass.zip'
echo "Executing program at the CCS node."
sshpass -f password ssh sd202@l040101-ws02.ua.pt 'cd test/MuseumHeist/dirCCS ; java serverSide.main.ServerControlCollectionSite 22422 l040101-ws07.ua.pt 22427'