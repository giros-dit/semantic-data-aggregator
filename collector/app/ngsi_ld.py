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

    def retrieveEntityById(self, entityId: str, attrs: str = None,
                           type: str = None, options: Options = None):
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
            return response.raise_for_status()
