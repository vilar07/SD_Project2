# local execution
#echo "Executing the GeneraRepos node."
#cd /home/marco/Desktop/MuseumHeist/dirGeneralRepos
#java -cp "../genclass.jar:."  serverSide.main.ServerGeneralRepos 22312
#echo "GeneralRepos Server shutdown."

# global execution
echo "Transfering data to the repos node."
sshpass -f password ssh sd43@l040101-ws07.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd43@l040101-ws07.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirGeneralRepos.zip sd43@l040101-ws07.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the repos node."
sshpass -f password ssh sd43@l040101-ws07.ua.pt 'cd test/MuseumHeist ; unzip -uq dirGeneralRepos.zip'
sshpass -f password scp genclass.zip sd43@l040101-ws07.ua.pt:test/MuseumHeist/dirGeneralRepos
sshpass -f password ssh sd43@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirGeneralRepos ; unzip -uq genclass.zip'
echo "Executing program at the repos node."
sshpass -f password ssh sd43@l040101-ws07.ua.pt 'cd test/MuseumHeist/dirGeneralRepos ; java serverSide.main.ServerGeneralRepos 22427'