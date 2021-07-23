from .binding import logparser_instantiation_operation as logparser_model

def parse_event(obj):
    logparser = logparser_model()
    logparser.so_instantiation_metrics._set_Current_time(obj['Current_time'])
    logparser.so_instantiation_metrics._set_Operation(obj['Operation'])
    logparser.so_instantiation_metrics._set_NS_ID(obj['NS_ID'])
    logparser.so_instantiation_metrics._set_NSD_ID(obj['NSD_ID'])
    logparser.so_instantiation_metrics._set_Total_instantiation_time(obj['Total_instantiation_time'])
    logparser.so_instantiation_metrics._set_SOE_time(obj['SOE_time'])
    logparser.so_instantiation_metrics._set_ROE_time(obj['ROE_time'])
    logparser.so_instantiation_metrics._set_Operation_ID_for_instantiation_op(obj['Operation_ID_for_instantiation_op'])
    logparser.so_instantiation_metrics._set_Hierarchical_SOE_dispatching_SOEpSOEc(obj['Hierarchical_SOE_dispatching_SOEpSOEc'])
    logparser.so_instantiation_metrics._set_ROE_created_VLs(obj['ROE_created_VLs'])
    logparser.so_instantiation_metrics._set_ROE_retrieve_RL_resources(obj['ROE_retrieve_RL_resources'])
    logparser.so_instantiation_metrics._set_ROE_parsing_NSDs(obj['ROE_parsing_NSDs'])
    logparser.so_instantiation_metrics._set_ROE_updating_DBs(obj['ROE_updating_DBs'])
    logparser.so_instantiation_metrics._set_ROE_extract_VLs(obj['ROE_extract_VLs'])
    logparser.so_instantiation_metrics._set_Retrieving_descriptor_from_catalogue_DBs(obj['Retrieving_descriptor_from_catalogue_DBs'])
    logparser.so_instantiation_metrics._set_PA_calculation(obj['PA_calculation'])
    logparser.so_instantiation_metrics._set_Create_threshold_based_alerts(obj['Create_threshold_based_alerts'])
    logparser.so_instantiation_metrics._set_Create_monitoring_jobs(obj['Create_monitoring_jobs'])
    logparser.so_instantiation_metrics._set_Create_AIML_alerts(obj['Create_AIML_alerts'])
    logparser.so_instantiation_metrics._set_CoreMANO_Wrapper_time(obj['CoreMANO_Wrapper_time'])

    return logparser
