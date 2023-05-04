# local execution
#echo "Executing the Museum node."
#cd /home/marco/Desktop/MuseumHeist/dirMuseum
#java -cp "../genclass.jar:." serverSide.main.ServerMuseum 22317 127.0.0.1 22312
#echo "Museum Server shutdown."



# global execution
echo "Transfering data to the Museum node."
sshpass -f password ssh sd202@l040101-ws01.ua.pt 'mkdir -p test/MuseumHeist'
sshpass -f password ssh sd202@l040101-ws01.ua.pt 'rm -rf test/MuseumHeist/*'
sshpass -f password scp dirMuseum.zip sd202@l040101-ws01.ua.pt:test/MuseumHeist
echo "Decompressing data sent to the Museum node."
sshpass -f password ssh sd202@l040101-ws01.ua.pt 'cd test/MuseumHeist ; unzip -uq dirMuseum.zip'
sshpass -f password scp genclass.zip sd202@l040101-ws01.ua.pt:test/MuseumHeist/dirMuseum
sshpass -f password ssh sd202@l040101-ws01.ua.pt 'cd test/MuseumHeist/dirMuseum ; unzip -uq genclass.zip'
echo "Executing program at the Museum node."
sshpass -f password ssh sd202@l040101-ws01.ua.pt 'cd test/MuseumHeist/dirMuseum ; java serverSide.main.ServerMuseum 22421 l040101-ws07.ua.pt 22427'