# local execution
#echo "Executing the master node."
#cd /home/marco/Desktop/MuseumHeist/dirMaster
#java -cp "../genclass.jar:." clientSide.main.ClientMaster 127.0.0.1 22313 127.0.0.1 22314 127.0.0.1 22315 127.0.0.1 22316 127.0.0.1 22312
#echo "Master Server shutdown."

# global execution
echo "Transfering data to the Master node."
sshpass -f password ssh sd202@l040101-ws08.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd202@l040101-ws08.ua.pt 'rm -rf test/MuseumHeist/*'
shpass -f password scp dirMaster.zip sd202@l040101-ws08.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Master node."
sshpass -f password ssh sd202@l040101-ws08.ua.pt 'cd test/MuseumHeist ; unzip -uq dirMaster.zip'
sshpass -f password scp genclass.zip sd202@l040101-ws08.ua.pt:test/MuseumHeist/dirMaster
sshpass -f password ssh sd202@l040101-ws08.ua.pt 'cd test/MuseumHeist/dirMaster ; unzip -uq genclass.zip'
echo "Executing program at the Master node."
sshpass -f password ssh sd202@l040101-ws08.ua.pt 'cd test/MuseumHeist/dirMaster ; java clientSide.main.ClientMaster l040101-ws06.ua.pt 22426 l040101-ws05.ua.pt 22425 l040101-ws03.ua.pt 22423 l040101-ws02.ua.pt 22422 l040101-ws07.ua.pt 22427'