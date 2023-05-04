xterm  -T "General Repository" -hold -e "./GenReposDeployAndRun.sh" &
sleep 1
xterm  -T "AP0" -hold -e "./AP0DeployAndRun.sh" &
xterm  -T "AP1" -hold -e "./AP1DeployAndRun.sh" &
xterm  -T "CS" -hold -e "./ConcentrationSiteDeployAndRun.sh" &
xterm  -T "CCS" -hold -e "./ControlCollectionSiteDeployAndRun.sh" &
xterm  -T "MUSEUM" -hold -e "./MuseumDeployAndRun.sh" &
sleep 1
xterm  -T "Master" -hold -e "./MasterDeployAndRun.sh" &
xterm  -T "Ordinary" -hold -e "./OrdinaryDeployAndRun.sh" &