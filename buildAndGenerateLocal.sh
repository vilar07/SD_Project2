echo "Compiling source code."
javac */*.java */*/*.java
echo "Distributing intermediate code to the different execution environments."
echo "  General Repository of Information"
rm -rf dirGeneralRepos
mkdir -p dirGeneralRepos dirGeneralRepos/serverSide dirGeneralRepos/serverSide/entities dirGeneralRepos/serverSide/sharedRegions
cp serverSide/entities/ServerProxyAgent.class dirGeneralRepos/serverSide/entities
cp serverSide/sharedRegions/GeneralRepos.class dirGeneralRepos/serverSide/sharedRegions
echo "  Ordinary Thief"
rm -rf dirOrdinaryThief
mkdir -p dirOrdinaryThief dirOrdinaryThief/serverSide dirOrdinaryThief/serverSide/entities dirOrdinaryThief/serverSide/sharedRegions dirOrdinaryThief/serverSide/interfaces \
         dirOrdinaryThief/clientSide dirOrdinaryThief/clientSide/entities dirOrdinaryThief/clientSide/stubs \
         dirOrdinaryThief/commInfra
cp serverSide/entities/ServerProxyAgent.class dirBarberShop/serverSide/entities
cp serverSide/sharedRegions/GeneralRepository.class serverSide/sharedRegions/AssaultParty.class serverSide/sharedRegions/CollectionSite.class serverSide/sharedRegions/ConcentrationSite.class serverSide/sharedRegions/Museum.class dirOrdinaryThief/serverSide/sharedRegions
cp clientSide/entities/OrdinaryThief.class dirOrdinaryThief/clientSide/entities
cp clientSide/stubs/*.class dirOrdinaryThief/clientSide/stubs
cp commInfra/*.class dirOrdinaryThief/commInfra
echo "  Master Thief"
rm -rf dirMasterThief
mkdir -p dirMasterThief/clientSide dirMasterThief/clientSide/stubs dirMasterThief/clientSide/entities \
         dirMasterThief/clientSide/stubs dirMasterThief/commInfra \
         dirMasterThief/utils
cp serverSide/main/SimulPar.class dirBarbers/serverSide/main
cp clientSide/main/ClientSleepingBarbersBarber.class dirMasterThief/clientSide/main
cp clientSide/entities/MasterThief.class clientSide/entities/OrdinaryThief.class dirMasterThief/clientSide/entities
cp clientSide/stubs/AssaultPartyStub.class clientSide/stubs/CollectionSiteStub.class clientSide/stubs/ConcentrationSiteStub.class clientSide/stubs/MuseumStub.class dirMasterThief/clientSide/stubs
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirMasterThief/commInfra
cp utils/Constants.class dirMasterThief/utils
echo "  Customers"
rm -rf dirCustomers
mkdir -p dirCustomers dirCustomers/serverSide dirCustomers/serverSide/main dirCustomers/clientSide dirCustomers/clientSide/main dirCustomers/clientSide/entities \
         dirCustomers/clientSide/stubs dirCustomers/commInfra
cp serverSide/main/SimulPar.class dirCustomers/serverSide/main
cp clientSide/main/ClientSleepingBarbersCustomer.class dirCustomers/clientSide/main
cp clientSide/entities/Customer.class clientSide/entities/CustomerStates.class dirCustomers/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class clientSide/stubs/BarberShopStub.class dirCustomers/clientSide/stubs
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirCustomers/commInfra
echo "Compressing execution environments."
echo "  General Repository of Information"
rm -f  dirGeneralRepos.zip
zip -rq dirGeneralRepos.zip dirGeneralRepos
echo "  Barber Shop"
rm -f  dirBarberShop.zip
zip -rq dirBarberShop.zip dirBarberShop
echo "  Barbers"
rm -f  dirBarbers.zip
zip -rq dirBarbers.zip dirBarbers
echo "  Customers"
rm -f  dirCustomers.zip
zip -rq dirCustomers.zip dirCustomers
echo "Deploying and decompressing execution environments."
mkdir -p /home/ruib/test/SleepingBarbers
rm -rf /home/ruib/test/SleepingBarbers/*
cp dirGeneralRepos.zip /home/ruib/test/SleepingBarbers
cp dirBarberShop.zip /home/ruib/test/SleepingBarbers
cp dirBarbers.zip /home/ruib/test/SleepingBarbers
cp dirCustomers.zip /home/ruib/test/SleepingBarbers
cd /home/ruib/test/SleepingBarbers
unzip -q dirGeneralRepos.zip
unzip -q dirBarberShop.zip
unzip -q dirBarbers.zip
unzip -q dirCustomers.zip
