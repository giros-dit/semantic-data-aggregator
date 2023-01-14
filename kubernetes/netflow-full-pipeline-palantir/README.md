# SDA Netflow full data pipeline within PALANTIR
This folder provides a Kubernetes Helm Chart used to deploy the SDA-related components (i.e., GoFlow2 collector and Flink applications) for the Netflow full data pipeline processing on the PALANTIR platform.

To install de Helm Chart within the regarding K8s cluster, run the following command with the `helm` client:
```bash
helm *[--kubeconfig <kubeconfig-file>] *[-n <namespace>]* install sda-netflow . --values ./values.yaml
```

To uninstall it run the following command with the `helm` client:
```bash
helm *[--kubeconfig <kubeconfig-file>]* *[-n <namespace>]* uninstall sda-netflow
```

For the deployment of `SDA` components related to Netflow processing, the integration with `Threat Intelligence` components within the PALANTIR infrastructure is required. Specifically, the integration of the `SDA` with the `Data Collection and Data Preprocessing` component (i.e., DCP) is required. For the integration between the `SDA` and the `DCP`, the proposed architecture is as follows:

![Image](https://user-images.githubusercontent.com/55086789/211926367-4ea6bf5f-e9f5-4259-88bd-b9fd45c34d6e.png)

- In 1 the exported packets will be received by the `SDA` component using Goflow2 as a collector, this Netflow information will be aggregated to give bidirectional information and the aggregated features.

- The output of the `SDA` (2) will be written in PALANTIR’s Kafka with the schema `Raw Netflow Data` + `Aggregated features`:

```bash
ts,te,td,sa,da,sp,dp,pr,flg,fwd,stos,ipkt,ibyt,opkt,obyt,in,out,sas,das,smk,dmk,dtos,dir,nh,nhb,svln,dvln,ismc,odmc,idmc,osmc,mpls1,mpls2,mpls3,mpls4,mpls5,mpls6,mpls7,mpls8,mpls9,mpls10,cl,sl,al,ra,eng,exid,tr,zeek_field,pktips,pktops,bytips,bytops,bytippkt,bytoppkt,bytipo,pktipo
```

>The `zeek_extra_field` is an extra field to be added because is nedeed to be consider and filled by the Zeek >monitoring component in PALANTIR. The Consumer Driver adds the `$` default value to this `zeek_extra_field`

- The `DCP` component will consume this  `Raw Netflow Data` + `Aggregated features` schema as input and using the Preprocessing application and Anonymization service will produce the schema `Anonymized & Preprocessed Netflow Data` + `Aggregated features` (3):

```bash
ts,te,td,sa,da,sp,dp,pr,flg,fwd,stos,ipkt,ibyt,opkt,obyt,in,out,sas,das,smk,dmk,dtos,dir,nh,nhb,svln,dvln,ismc,odmc,idmc,osmc,mpls1,mpls2,mpls3,mpls4,mpls5,mpls6,mpls7,mpls8,mpls9,mpls10,cl,sl,al,ra,eng,exid,tr,pktips,pktops,bytips,bytops,bytippkt,bytoppkt,bytipo,pktipo,tpkt,tbyt,cp,prtcp,prudp,pricmp,prigmp,prother,flga,flgs,flgf,flgr,flgp,flgu
```