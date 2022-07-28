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

# Data format and integration with PALANTIR
Due to the presentation at WP5 of PALANTIR on 12/07/2022 the Cryptomining Detection System need some changes so it can be integrated with the PALANTIR project. Three solutions are proposed:

- Don't do any modification but to read from a specific topic and write in another topic with an specific format, the format will be "_Anonymized & Preprocessed Netflow Data_" available in confluence [Schema form messages in Kafka](https://confluence.i2cat.net/pages/viewpage.action?pageId=129796187), the format will be:
   ```
   ts,te,td,sa,da,sp,dp,pr,flg,fwd,stos,ipkt,ibyt,opkt,obyt,in,out,sas,das,smk,dmk,dtos,dir,nh,nhb,svln,dvln,ismc,odmc,idmc,osmc,mpls1,mpls2,mpls3,mpls4,mpls5,mpls6,mpls7,mpls8,mpls9,mpls10,cl,sl,al,ra,eng,exid,tr,tpkt,tbyt,cp,prtcp,prudp,pricmp,prigmp,prother,flga,flgs,flgf,flgr,flgp,flgu
   ```
   In this solution the CDS component is running in parallel with the MAD component, so all the traffic from topic A will be read from the CDS. This may cause problems if the CDS is not powerfull enough to handle the rate needed.

- Due to the rate problem mentioned before a solution will be to move the implementation from python to Spark so that the implementation allow for a higher message rate.

- The other solution to solve the high rate will be to add the CDS after the MAD so all the traffic is filtered and CDS only receives the Anomalies detected. In this solution it will be necessary to use the samples available to train another MAD module able to detect crytpomining attack as an anomaly.

![CDS-TCAM-background](https://user-images.githubusercontent.com/96416803/179494204-e67254d9-4ee6-4e39-baf5-2f802469f8f4.png)

The implementation will start with the first solution, the following adjustments have therefore been made:

- Modify the input as if we were to receive "_Anonymized & Preprocessed Netflow Data_" format
- Modify the output to send "_Anonymized & Preprocessed Netflow Data_ + Threat_Label + Threat_Category + Classification Confidence". 
   ```
   Threat_Label="Crypto"
   Threat_Category="Malware"
   ```
- Add the possibility of changing the read and write topics from the Kubernetes template
