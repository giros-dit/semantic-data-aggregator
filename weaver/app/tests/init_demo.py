from create_entities import createEntities
from semantic_tools.clients.ngsi_ld import NGSILDClient

import logging

logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s")
logger = logging.getLogger(__name__)


def main(ngsi):
    # Load demo NGSI-LD entities
    # Wait until Scorpio is up
    ngsi.checkScorpioHealth()
    # Create pre-defined MetricSources for demo
    try:
        createEntities(ngsi)
    except Exception:
        logger.warning("Could not load demo NGSI-LD entities. Keep running...")


if __name__ == '__main__':
    # Init NGSI-LD API Client
    ngsi = NGSILDClient(url="http://scorpio:9090",
                        headers={"Accept": "application/json"},
                        context="http://context-catalog:8080/context.jsonld")
    # Run main function
    main(ngsi)
