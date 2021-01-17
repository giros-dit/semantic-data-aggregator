from requests.adapters import HTTPAdapter
from requests.packages.urllib3.util.retry import Retry

import requests
import os.path

# Class built based on reference docs
# for the Flink REST API
# See https://ci.apache.org/projects/flink/flink-docs-release-1.12/ops/rest_api.html#api


class FlinkClient():
    def __init__(
            self, url: str = "http://flink-jobmanager:8081",
            headers: dict = {},
            disable_ssl: bool = False,
            debug: bool = False):

        self.headers = headers
        self.url = url
        self.ssl_verification = not disable_ssl
        # Retry strategy
        retry_strategy = Retry(
            total=10,
            status_forcelist=[429, 500, 502, 503, 504],
            method_whitelist=["HEAD", "GET", "PATCH", "PUT", "POST", "OPTIONS"],
            backoff_factor=5
        )
        self._session = requests.Session()
        self._session.mount(self.url, HTTPAdapter(max_retries=retry_strategy))
        self._session = requests.Session()
        self.headers['Accept'] = "application/json"
        self.headers['Content-Type'] = "application/json"
        self.debug = debug
        if self.debug:
            import logging
            # These two lines enable debugging at httplib level (requests->urllib3->http.client)
            # You will see the REQUEST, including HEADERS and DATA, and RESPONSE with HEADERS but without DATA.
            # The only thing missing will be the response.body which is not logged.
            try:
                import http.client as http_client
            except ImportError:
                # Python 2
                import httplib as http_client
            http_client.HTTPConnection.debuglevel = 1

            # You must initialize logging, otherwise you'll not see debug output.
            logging.basicConfig()
            logging.getLogger().setLevel(logging.DEBUG)
            requests_log = logging.getLogger("requests.packages.urllib3")
            requests_log.propagate = True


    # Get WebUI configuration
    def getWebUIConf(self):
        """
	Returns the configuration of the WebUI.
        """
        response = self._session.get("{0}/config".format(self.url),
                                     verify=self.ssl_verification,
                                     headers=self.headers)
        if response.status_code == 200:
            return response.json()
        else:
            return response.raise_for_status()

    # Get Flink application jars
    def getFlinkAppsJars(self):
        """
	Returns a list of all jars previously uploaded via '/jars/upload'.
        """
        response = self._session.get("{0}/jars".format(self.url),
                                     verify=self.ssl_verification,
                                     headers=self.headers)

        print(response.json())
        if response.status_code == 200:
            return response.json()
        else:
            return response.raise_for_status()

    # Get Flink jobs
    def getFlinkJobs(self):
        """
	Returns a description over all jobs and their current state.
        """
        response = self._session.get("{0}/jobs".format(self.url),
                                     verify=self.ssl_verification,
                                     headers=self.headers)
        if response.status_code == 200:
            return response.json()
        else:
            return response.raise_for_status()

    # Get Flink jobs overview
    def getFlinkJobsOverview(self):
        """
	Returns an overview over all jobs.
        """
        response = self._session.get("{0}/jobs/overview".format(self.url),
                                     verify=self.ssl_verification,
                                     headers=self.headers)
        if response.status_code == 200:
            return response.json()
        else:
            return response.raise_for_status()

    # Get Flink jobs metrics
    def getFlinkJobsMetrics(self):
        """
	Provides access to aggregated job metrics.
        """
        response = self._session.get("{0}/jobs/metrics".format(self.url),
                                     verify=self.ssl_verification,
                                     headers=self.headers)
        if response.status_code == 200:
            return response.json()
        else:
            return response.raise_for_status()

    # Upload an application jar to Flink cluster
    def uploadJar(self, jarfile):
        """
        Uploads a jar to the cluster. The jar must be sent as multi-part data. Make sure that the "Content-Type" header is set to "application/x-java-archive",
        as some http libraries do not add the header by default. Using 'curl' you can upload a jar via 'curl -X POST -H "Expect:" -F "jarfile=@path/to/flink-job.jar" http://hostname:port/jars/upload'.
        """
        response = self._session.post(
            "{0}/jars/upload".format(self.url),
            verify=self.ssl_verification,
	    #headers=self.headers,
            #files=files
            files={"jar": (os.path.basename(jarfile), open(jarfile, "rb"), "application/x-java-archive")}
        )
        if response.status_code == 200:
            return response.json()
        else:
            return response.raise_for_status()

    # Submit a Flink job
    def submitJob(self, jarid: str, entryclass: str = None):
        """
	Submits a job by running a jar previously uploaded via '/jars/upload'. Program arguments can be passed both via the JSON request (recommended) or query parameters.

	jarid - String value that identifies a jar. When uploading the jar a path is returned, where the filename is the ID. This value is equivalent to the `id` field in the list of uploaded jars (/jars).

	entry-class (optional): String value that specifies the fully qualified name of the entry point class. Overrides the class defined in the jar file manifest.
        """

        params = {}
        if entryclass:
            params['entry-class'] = entryclass

        response = self._session.post(
            "{0}/jars/{1}/run".format(self.url, jarid),
            verify=self.ssl_verification,
            headers=self.headers,
	    params=params
        )
        if response.status_code == 200:
            return response.json()
        else:
            return response.raise_for_status()

    # Delete a Flink job
    def deleteJob(self, jobid: str):
        """
	Cancel/Stop a Flink job.
        """

        params = {}
        params['mode'] = "cancel"

        response = self._session.patch(
            "{0}/jobs/{1}".format(self.url, jobid),
            verify=self.ssl_verification,
            headers=self.headers,
            params=params
        )
        if response.status_code == 200:
            return response.json()
        else:
            return response.raise_for_status()

