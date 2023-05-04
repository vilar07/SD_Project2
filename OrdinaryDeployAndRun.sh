# global execution
echo "Transfering data to the ordinary node."
sshpass -f password ssh sd107@l040101-ws01.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd107@l040101-ws01.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirOrdinary.zip sd107@l040101-ws01.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Ordinary node."
sshpass -f password ssh sd107@l040101-ws01.ua.pt 'cd test/MuseumHeist ; unzip -uq dirOrdinary.zip'
echo "Executing program at the Ordinary node."
#ver main para cada um , ver argumentos q se tem de passar
sshpass -f password ssh sd107@l040101-ws01.ua.pt 'cd test/MuseumHeist/dirOrdinary ; java clientSide.main.ClientOrdinary l040101-ws06.ua.pt 22166 l040101-ws05.ua.pt 22425 l040101-ws03.ua.pt 22423 l040101-ws02.ua.pt 22422 l040101-ws01.ua.pt 22421 l040101-ws07.ua.pt 22427'