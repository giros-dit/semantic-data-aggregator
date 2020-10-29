from clients.ngsi_ld import ngsildClient


client = ngsildClient(url="http://localhost:9090", headers={"Accept": "application/ld+json"}, context="https://pastebin.com/raw/PCe63jxb", debug=True)

client.queryEntities(type="PrometheusMetric")
client.retrieveEntityById(entityId="urn:ngsi-ld:PrometheusMetric:1")