# Running Python unit tests

This folder includes Python unit tests for the semantic data aggregator prototype.

## NGSI-LD Testing

Python library `test_ngsi_Ld.py` provides a variety of functions aim for testing the NGSI-LD API within the prototype. So far, we focus on testing on Scorpio Broker, although running them against Orion-LD Broker is just a matter of adapting the broker's port for the NGSI-LD API. Scorpio uses `9090` port whereas Orion-LD runs on `1026`.

Before we start running tests, move back to the root `app` folder. Then issue the following command to run the whole test case:
```bash
cd ../
python -m unittest test_ngsi_ld.TestScorpio
```

The following command shows an example of running a specific function within the unittest test case. This command can be particularly useful for generating sample NGSI-LD entities in prototype demo:
```bash
python -m unittest test_ngsi_ld.TestScorpio.test_create_demo_entities
```

Once you are done with the demo, clean the sample NGSI-LD entities running this command:
```bash
python -m unittest test_ngsi_ld.TestScorpio.test_delete_demo_entities
```
