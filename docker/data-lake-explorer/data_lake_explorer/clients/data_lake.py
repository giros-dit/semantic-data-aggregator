import logging

import requests
from requests.adapters import HTTPAdapter
from requests.models import Response
from urllib3.util.retry import Retry

API_GATEWAY_URI = 'https://2yd7m1wqii.execute-api.eu-west-2.amazonaws.com/v1'
API_GATEWAY_KEY = 'NnYdCrSuGT8j9MAf9wWRVaFwqVwAzYMc27OUna3r'
AWS_REGION = 'eu-west-2'

logger = logging.getLogger(__name__)


class APIGateway():
    def __init__(
            self, url: str = API_GATEWAY_URI,
            api_key: str = API_GATEWAY_KEY,
            disable_ssl: bool = False,
            debug: bool = False):

        self.headers = {
            "x-api-key": api_key,
            "Accept": "application/json"
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
            import http.client as http_client
            import logging
            http_client.HTTPConnection.debuglevel = 1

            # You must initialize logging,
            # otherwise you'll not see debug output.
            logging.basicConfig()
            logging.getLogger().setLevel(logging.DEBUG)
            requests_log = logging.getLogger("requests.packages.urllib3")
            requests_log.propagate = True

    def list_buckets(self) -> Response:
        """
        This endpoint serves to get all
        the buckets from the data lake
        """
        response = self._session.get(
            "{0}/".format(self.url),
            verify=self.ssl_verification,
            headers=self.headers
        )
        return response

    def list_objects(self, bucket_name: str) -> Response:
        """
        This endpoint serves to get all objects
        pertaining to a bucket in the data lake
        """
        response = self._session.get(
            "{0}/{1}".format(self.url, bucket_name),
            verify=self.ssl_verification,
            headers=self.headers
        )
        return response

    def get_objects(self, bucket_name: str, object_key: str) -> Response:
        """
        This endpoint serves to get by key an object
        from the data lake
        """
        response = self._session.get(
            "{0}/{1}/{2}".format(self.url, bucket_name, object_key),
            verify=self.ssl_verification,
            headers=self.headers
        )
        return response
