# global execution
echo "Transfering data to the ap0 node."
sshpass -f password ssh sd43@l040101-ws06.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd43@l040101-ws06.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirAP0.zip sd43@l040101-ws06.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the AP0 node."
sshpass -f password ssh sd43@l040101-ws06.ua.pt 'cd test/MuseumHeist ; unzip -uq dirAP0.zip'
sshpass -f password scp genclass.zip sd43@l040101-ws06.ua.pt:test/MuseumHeist/dirAP0
sshpass -f password ssh sd43@l040101-ws06.ua.pt 'cd test/MuseumHeist/dirAP0 ; unzip -uq genclass.zip'
echo "Executing program at the AP0 node."
sshpass -f password ssh sd43@l040101-ws06.ua.pt 'cd test/MuseumHeist/dirAP0 ; java serverSide.main.ServerAssaultParty 22426 l040101-ws07.ua.pt 22427'
