# Cryptomining Detection System (CDS)
This is an example of a consumer for the SDA, it uses the [Netflow2CDS](https://github.com/giros-dit/semantic-metrics-datamodels/tree/main/drivers/consumers/Netflow2CDS) driver to obtain the features in CSV format. The input features are:

```
[
    'inbound_packets_per_second',
    'outbound_packets_per_second',
    'inbound_unique_bytes_per_second',
    'outbound_unique_bytes_per_second',
    'inbound_bytes_per_packet',
    'outbound_bytes_per_packet',
    'ratio_bytes_inbound/bytes_outbound',
    'ratio_packets_inbound/packets_outbound'
]
```

The application reads from a kafka topic and writes the result in other topic.