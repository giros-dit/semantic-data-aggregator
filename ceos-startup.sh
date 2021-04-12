echo "Uploading ceos1 Arista router startup configuration..."
docker cp ./ceos-arista/ceos-startup-config/startup-config-ceos1 ceos1:/mnt/flash/startup-config
docker exec -it ceos1 Cli -p 15 -c "copy start run"

echo "Uploading ceos2 Arista router startup configuration..."
docker cp ./ceos-arista/ceos-startup-config/startup-config-ceos2 ceos2:/mnt/flash/startup-config
docker exec -it ceos2 Cli -p 15 -c "copy start run"
