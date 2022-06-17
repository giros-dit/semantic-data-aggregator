from requests.adapters import HTTPAdapter
from requests.packages.urllib3.util.retry import Retry

import json
import logging
import requests

logger = logging.getLogger(__name__)


class DCMAPI():
    def __init__(
            self, url: str,
            disable_ssl: bool = False,
            debug: bool = False):

        self.headers = {
            "Content-Type": "application/json"
        }
        self.url = url
        self.ssl_verification = not disable_ssl
        # Retry strategy
        retry_strategy = Retry(
            total=10,
            status_forcelist=[429, 500, 502, 503, 504],
            method_whitelist=["HEAD", "GET", "PUT", "POST", "OPTIONS"],
            backoff_factor=5
        )
        self._session = requests.Session()
        self._session.mount(self.url, HTTPAdapter(max_retries=retry_strategy))

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

    def get_topics_by_usecase(self, usecase: str) -> dict:
        """
        Returns list of Kafka topics by filtering
        their names with a given usecase string
        """
        response = self._session.get(
                    "{0}/list/{1}".format(self.url, usecase),
                    verify=self.ssl_verification,
                    headers=self.headers
                )
        return response.json()
