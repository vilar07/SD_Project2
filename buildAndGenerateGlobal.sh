echo "Compiling source code."
javac -cp ".:./genclass.jar" */*.java */*/*.java


echo "Distributing intermediate code to the different execution environments."
echo "  General Repository of Information"
rm -rf dirGeneralRepos
mkdir -p dirGeneralRepos dirGeneralRepos/serverSide dirGeneralRepos/serverSide/main dirGeneralRepos/serverSide/entities dirGeneralRepos/serverSide/sharedRegions dirGeneralRepos/clientSide dirGeneralRepos/clientSide/entities dirGeneralRepos/commInfra
cp serverSide/main/SimulConsts.class serverSide/main/ServerGeneralRepos.class dirGeneralRepos/serverSide/main
cp serverSide/entities/GeneralReposClientProxy.class dirGeneralRepos/serverSide/entities
cp serverSide/sharedRegions/GeneralReposInterface.class serverSide/sharedRegions/GeneralRepos.class dirGeneralRepos/serverSide/sharedRegions
cp clientSide/entities/MasterStates.class clientSide/entities/OrdinaryStates.class dirGeneralRepos/clientSide/entities
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ServerCom.class dirGeneralRepos/commInfra


echo "  AssaultParty0"
rm -rf dirAP0
mkdir -p dirAP0 dirAP0/serverSide dirAP0/serverSide/main dirAP0/serverSide/entities dirAP0/serverSide/sharedRegions dirAP0/clientSide dirAP0/clientSide/entities dirAP0/clientSide/stubs dirAP0/commInfra
cp serverSide/main/SimulConsts.class serverSide/main/ServerAssaultParty.class dirAP0/serverSide/main
cp serverSide/entities/AssaultPartyClientProxy.class dirAP0/serverSide/entities
cp serverSide/sharedRegions/GeneralReposInterface.class serverSide/sharedRegions/AssaultPartyInterface.class serverSide/sharedRegions/AssaultParty.class dirAP0/serverSide/sharedRegions
cp clientSide/entities/MasterStates.class clientSide/entities/OrdinaryStates.class clientSide/entities/MasterCloning.class clientSide/entities/OrdinaryCloning.class dirAP0/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirAP0/clientSide/stubs
cp commInfra/*.class dirAP0/commInfra


echo "  AssaultParty1"
rm -rf dirAP1
mkdir -p dirAP1 dirAP1/serverSide dirAP1/serverSide/main dirAP1/serverSide/entities dirAP1/serverSide/sharedRegions dirAP1/clientSide dirAP1/clientSide/entities dirAP1/clientSide/stubs dirAP1/commInfra
cp serverSide/main/SimulConsts.class serverSide/main/ServerAssaultParty.class dirAP1/serverSide/main
cp serverSide/entities/AssaultPartyClientProxy.class dirAP1/serverSide/entities
cp serverSide/sharedRegions/GeneralReposInterface.class serverSide/sharedRegions/AssaultPartyInterface.class serverSide/sharedRegions/AssaultParty.class dirAP1/serverSide/sharedRegions
cp clientSide/entities/MasterStates.class clientSide/entities/OrdinaryStates.class clientSide/entities/MasterCloning.class clientSide/entities/OrdinaryCloning.class dirAP1/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirAP1/clientSide/stubs
cp commInfra/*.class dirAP1/commInfra


echo "  ConcentrationSite"
rm -rf dirCS
mkdir -p dirCS dirCS/serverSide dirCS/serverSide/main dirCS/serverSide/entities dirCS/serverSide/sharedRegions dirCS/clientSide dirCS/clientSide/entities dirCS/clientSide/stubs dirCS/commInfra
cp serverSide/main/SimulConsts.class serverSide/main/ServerConcentrationSite.class dirCS/serverSide/main
cp serverSide/entities/ConcentrationSiteClientProxy.class dirCS/serverSide/entities
cp serverSide/sharedRegions/GeneralReposInterface.class serverSide/sharedRegions/ConcentrationSiteInterface.class serverSide/sharedRegions/ConcentrationSite.class dirCS/serverSide/sharedRegions
cp clientSide/entities/MasterStates.class clientSide/entities/OrdinaryStates.class clientSide/entities/MasterCloning.class clientSide/entities/OrdinaryCloning.class dirCS/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirCS/clientSide/stubs
cp commInfra/*.class dirCS/commInfra


echo "  ControlCollectionSite"
rm -rf dirCCS
mkdir -p dirCCS dirCCS/serverSide dirCCS/serverSide/main dirCCS/serverSide/entities dirCCS/serverSide/sharedRegions dirCCS/clientSide dirCCS/clientSide/entities dirCCS/clientSide/stubs dirCCS/commInfra
cp serverSide/main/SimulConsts.class serverSide/main/ServerControlCollectionSite.class dirCCS/serverSide/main
cp serverSide/entities/ControlCollectionSiteClientProxy.class dirCCS/serverSide/entities
cp serverSide/sharedRegions/GeneralReposInterface.class serverSide/sharedRegions/ControlCollectionSiteInterface.class serverSide/sharedRegions/ControlCollectionSite.class dirCCS/serverSide/sharedRegions
cp clientSide/entities/MasterStates.class clientSide/entities/OrdinaryStates.class clientSide/entities/MasterCloning.class clientSide/entities/OrdinaryCloning.class dirCCS/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirCCS/clientSide/stubs
cp commInfra/*.class dirCCS/commInfra


echo "  Museum"
rm -rf dirMuseum
mkdir -p dirMuseum dirMuseum/serverSide dirMuseum/serverSide/main dirMuseum/serverSide/entities dirMuseum/serverSide/sharedRegions dirMuseum/clientSide dirMuseum/clientSide/entities dirMuseum/clientSide/stubs dirMuseum/commInfra
cp serverSide/main/SimulConsts.class serverSide/main/ServerMuseum.class dirMuseum/serverSide/main
cp serverSide/entities/MuseumClientProxy.class dirMuseum/serverSide/entities
cp serverSide/sharedRegions/GeneralReposInterface.class serverSide/sharedRegions/MuseumInterface.class serverSide/sharedRegions/Museum.class dirMuseum/serverSide/sharedRegions
cp clientSide/entities/MasterStates.class clientSide/entities/OrdinaryStates.class clientSide/entities/MasterCloning.class clientSide/entities/OrdinaryCloning.class dirMuseum/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirMuseum/clientSide/stubs
cp commInfra/*.class dirMuseum/commInfra


echo "  Master"
rm -rf dirMaster
mkdir -p dirMaster dirMaster/serverSide dirMaster/serverSide/main dirMaster/clientSide dirMaster/clientSide/main dirMaster/clientSide/entities dirMaster/clientSide/stubs dirMaster/commInfra
cp serverSide/main/SimulConsts.class dirMaster/serverSide/main
cp clientSide/main/ClientMaster.class dirMaster/clientSide/main
cp clientSide/entities/Master.class clientSide/entities/MasterStates.class dirMaster/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class clientSide/stubs/AssaultPartyStub.class clientSide/stubs/ConcentrationSiteStub.class clientSide/stubs/ControlCollectionSiteStub.class dirMaster/clientSide/stubs
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirMaster/commInfra


echo "  Ordinary"
rm -rf dirOrdinary
mkdir -p dirOrdinary dirOrdinary/serverSide dirOrdinary/serverSide/main dirOrdinary/clientSide dirOrdinary/clientSide/main dirOrdinary/clientSide/entities dirOrdinary/clientSide/stubs dirOrdinary/commInfra
cp serverSide/main/SimulConsts.class dirOrdinary/serverSide/main
cp clientSide/main/ClientOrdinary.class dirOrdinary/clientSide/main
cp clientSide/entities/Ordinary.class clientSide/entities/OrdinaryStates.class dirOrdinary/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class clientSide/stubs/AssaultPartyStub.class clientSide/stubs/ConcentrationSiteStub.class clientSide/stubs/ControlCollectionSiteStub.class clientSide/stubs/MuseumStub.class dirOrdinary/clientSide/stubs
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirOrdinary/commInfra



echo "Compressing execution environments."
echo "  General Repository of Information"
rm -f  dirGeneralRepos.zip
zip -rq dirGeneralRepos.zip dirGeneralRepos

echo "  AP0"
rm -f  dirAP0.zip
zip -rq dirAP0.zip dirAP0

echo "  AP1"
rm -f  dirAP1.zip
zip -rq dirAP1.zip dirAP1

echo "  ConcentrationSite"
rm -f  dirCS.zip
zip -rq dirCS.zip dirCS

echo "  ControlCollectionSite"
rm -f  dirCCS.zip
zip -rq dirCCS.zip dirCCS

echo "  Museum"
rm -f  dirMuseum.zip
zip -rq dirMuseum.zip dirMuseum

echo "  Master"
rm -f  dirMaster.zip
zip -rq dirMaster.zip dirMaster

echo "  Ordinary"
rm -f  dirOrdinary.zip
zip -rq dirOrdinary.zip dirOrdinary

echo "  Genclass"
rm -f  genclass.zip
zip -rq genclass.zip genclass.jar