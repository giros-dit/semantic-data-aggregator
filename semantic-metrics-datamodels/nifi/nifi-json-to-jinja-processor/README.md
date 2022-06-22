# JSON-to-Jinja2 NiFi Processor

Apache NiFi processor that consumes JSON-encoded Flowfiles and produces new Flowfiles by rendering with the specified Jinja2 template. This processor is a slight variant of the [nifi-file-from-template-processor](https://github.com/SwingDev/nifi-file-from-template-processor). The code has been modified to produce Flowfiles rather than sending the output to a specified file.

## Usage

The processor expects the input flowfile to be encoded in JSON format. The processor provides Jinja2 with the contents of the Flowfile, but also the attributes that may be attached. Refer to the following tags to access these data from the Jinja2 template:

- **content**: Contents of the Flowfile
- **attributes**: Attributes attached to the Flowfile
