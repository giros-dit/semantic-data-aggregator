import requests

# Class built based on reference docs
# for the Kafka Connect API provided by Confluent
# See https://docs.confluent.io/current/connect/references/restapi.html


class kafkaConnectClient():
    def __init__(
            self, url: str = "http://localhost:8083",
            headers: dict = {},
            disable_ssl: bool = False,
            debug: bool = False):

        self.headers = headers
        self.url = url
        self.ssl_verification = not disable_ssl
        self._session = requests.Session()
        self.headers['Accept'] = "application/json"
        self.headers['Content-Type'] = "application/json"
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

    # Connect Cluster
    def getCluster(self):
        response = self._session.get("{0}/".format(self.url),
                                     verify=self.ssl_verification,
                                     headers=self.headers)
        if response.status_code == 200:
            return response.json()
        else:
            response.raise_for_status()

    # Connectors
    def createConnector(self, name: str, config: dict):
        response = self._session.post(
            "{0}/connectors".format(self.url),
            verify=self.ssl_verification,
            headers=self.headers,
            json=config
        )
        if response.status_code == 200:
            return response.json()
        else:
            response.raise_for_status()

    def deleteConnector(self, name: str):
        response = self._session.delete(
            "{0}/connectors/{1}".format(self.url, name),
            verify=self.ssl_verification,
            headers=self.headers
        )
        if response.status_code == 200:
            return response.json()
        else:
            response.raise_for_status()

    def getConnector(self, name: str):
        response = self._session.get(
            "{0}/connectors/{1}".format(self.url, name),
            verify=self.ssl_verification,
            headers=self.headers
        )
        if response.status_code == 200:
            return response.json()
        else:
            response.raise_for_status()

    def getConnectors(self):
        response = self._session.get("{0}/connectors".format(self.url),
                                     verify=self.ssl_verification,
                                     headers=self.headers)
        if response.status_code == 200:
            return response.json()
        else:
            response.raise_for_status()

    # Topics
    def getTopics(self, name: str):
        response = self._session.get(
            "{0}/connectors/{1}/topics".format(self.url, name),
            verify=self.ssl_verification,
            headers=self.headers
        )
        if response.status_code == 200:
            return response.json()
        else:
            response.raise_for_status()