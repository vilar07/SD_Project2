# local execution
#echo "Executing the ap1 node."
#cd /home/marco/Desktop/MuseumHeist//dirAP1
#java -cp "../genclass.jar:." serverSide.main.ServerAssaultParty 22314 127.0.0.1 22312
#echo "AP1 Server shutdown."



# global execution
echo "Transfering data to the ap1 node."
sshpass -f password ssh sd202@l040101-ws05.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd202@l040101-ws05.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirAP1.zip sd202@l040101-ws05.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the AP1 node."
sshpass -f password ssh sd202@l040101-ws05.ua.pt 'cd test/MuseumHeist ; unzip -uq dirAP1.zip'
sshpass -f password scp genclass.zip sd202@l040101-ws05.ua.pt:test/MuseumHeist/dirAP1
sshpass -f password ssh sd202@l040101-ws05.ua.pt 'cd test/MuseumHeist/dirAP1 ; unzip -uq genclass.zip'
echo "Executing program at the AP1 node."
sshpass -f password ssh sd202@l040101-ws05.ua.pt 'cd test/MuseumHeist/dirAP1 ; java serverSide.main.ServerAssaultParty 22425 l040101-ws07.ua.pt 22427'