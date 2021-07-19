echo "ceos1 Arista cEOS router startup configuration:"
echo "Uploading gNMI management protocol configuration..."
docker cp ../ceos-arista/ceos-startup-config/gnmi-config-ceos1 ceos1:/mnt/flash/startup-config
docker exec -it ceos1 Cli -p 15 -c "copy start run"
echo "Uploading addressing configuration..."
docker cp ../ceos-arista/ceos-startup-config/addressing-config-ceos1 ceos1:/mnt/flash/startup-config
docker exec -it ceos1 Cli -p 15 -c "copy start run"

echo ""

echo "ceos2 Arista cEOS router startup configuration:"
echo "Uploading gNMI management protocol configuration..."
docker cp ../ceos-arista/ceos-startup-config/gnmi-config-ceos2 ceos2:/mnt/flash/startup-config
docker exec -it ceos2 Cli -p 15 -c "copy start run"
echo "Uploading addressing configuration..."
docker cp ../ceos-arista/ceos-startup-config/addressing-config-ceos2 ceos2:/mnt/flash/startup-config
docker exec -it ceos2 Cli -p 15 -c "copy start run"
