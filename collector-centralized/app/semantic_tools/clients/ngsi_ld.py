from enum import Enum
import requests


class Options(Enum):
    keyValues = "keyValues"
    sysAttrs = "sysAttrs"


class ngsildClient():
    def __init__(
            self, url: str = "http://scorpio:9090",
            headers: dict = {},
            disable_ssl: bool = False,
            debug: bool = False,
            context: str = "https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld"):

        self.headers = headers
        self.url = url
        self.ssl_verification = not disable_ssl
        self._session = requests.Session()
        self.context = context
        self.headers['Link'] = ('<{0}>;'
                                ' rel="http://www.w3.org/ns/json-ld#context";'
                                ' type="application/ld+json'
                                ).format(self.context)
        self.debug = debug
        if self.debug:
            import logging
            # These two lines enable debugging at httplib level
            # (requests->urllib3->http.client)
            # You will see the REQUEST, including HEADERS and DATA,
            # and RESPONSE with HEADERS but without DATA.
            # The only thing missing will be
            # the response.body which is not logged.
            try:
                import http.client as http_client
            except ImportError:
                # Python 2
                import httplib as http_client
            http_client.HTTPConnection.debuglevel = 1

            # You must initialize logging,
            # otherwise you'll not see debug output.
            logging.basicConfig()
            logging.getLogger().setLevel(logging.DEBUG)
            requests_log = logging.getLogger("requests.packages.urllib3")
            requests_log.propagate = True

    # NGSI-LD Create Entity -> /entities
    def createEntity(self, entity: dict):
        """
        Create a new Entity within an NGSI-LD system
        """
        response = self._session.post(
            "{0}/ngsi-ld/v1/entities".format(self.url),
            verify=self.ssl_verification,
            headers=self.headers,
            json=entity
        )
        if response.status_code != 201:
            response.raise_for_status()

    # NGSI-LD Query Entity -> /entities
    def queryEntities(self, entityId: str = None, attrs: str = None,
                      type: str = None, options: Options = None) -> dict:
        """
        Retrieve a set of entities which matches
        a specific query from an NGSI-LD system
        """
        params = {}
        if attrs:
            params['attrs'] = attrs
        elif type:
            params['type'] = type
        elif options:
            params['options'] = options
        response = self._session.get(
            "{0}/ngsi-ld/v1/entities".format(self.url),
            verify=self.ssl_verification,
            headers=self.headers,
            params=params
        )
        if response.status_code == 200:
            return response.json()
        else:
            response.raise_for_status()

    # NGSI-LD Retrieve Entity -> /entities/{entityId}
    def retrieveEntityById(self, entityId: str, attrs: str = None,
                           type: str = None, options: Options = None) -> dict:
        """
        Retrieve an specific Entity from an NGSI-LD system.
        It's possible to specify the Entity attributes to be retrieved
        by using query parameters
        """
        params = {}
        if attrs:
            params['attrs'] = attrs
        elif type:
            params['type'] = type
        elif options:
            params['options'] = options
        response = self._session.get(
            "{0}/ngsi-ld/v1/entities/{1}".format(self.url, entityId),
            verify=self.ssl_verification,
            headers=self.headers,
            params=params
        )
        if response.status_code == 200:
            return response.json()
        else:
            response.raise_for_status()

    # NGSI-LD Update Entity Attributes -> /entities/{entityId}/attrs
    def updateEntityAttrs(self, entityId: str, fragment: dict):
        """
        Update existing Entity attributes within an NGSI-LD system
        """
        response = self._session.patch(
            "{0}/ngsi-ld/v1/entities/{1}/attrs/".format(self.url, entityId),
            verify=self.ssl_verification,
            headers=self.headers,
            json=fragment
        )
        if response.status_code != 204:
            response.raise_for_status()

    # NGSI-LD Delete Entity -> /entities/{entityId}
    def deleteEntity(self, entityId: str):
        """
        Delete an specific Entity from an NGSI-LD system.
        """
        response = self._session.delete(
            "{0}/ngsi-ld/v1/entities/{1}".format(self.url, entityId),
            verify=self.ssl_verification,
            headers=self.headers,
        )
        if response.status_code == 200:
            return response.json()
        else:
            return response.raise_for_status()
