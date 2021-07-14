# Semantic-tools

Set of libraries used by the microservices within the SDA.

## Building the package

The package and its dependencies are managed with [poetry](https://python-poetry.org/).

Poetry automatically creates a virtual environment for us. When installing the package - in a container for instance -  disable this option by running the following command:
```bash
poetry config virtualenvs.create false
```

Then proceed to install the package (from package directory):
```bash
poetry install
```
