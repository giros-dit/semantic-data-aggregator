from .binding import logparser_instantiation_operation as logparser_model

def parse_event(obj):
    logparser = logparser_model()
    logparser.so_instantiation_metrics._set_current_time(obj['current_time'])
    logparser.so_instantiation_metrics._set_operation(obj['operation'])
    logparser.so_instantiation_metrics._set_nsID(obj['nsID'])
    logparser.so_instantiation_metrics._set_nsdID(obj['nsdID'])
    logparser.so_instantiation_metrics._set_total_instantiation_time(obj['total_instantiation_time'])
    logparser.so_instantiation_metrics._set_SOE_time(obj['SOE_time'])
    logparser.so_instantiation_metrics._set_ROE_time(obj['ROE_time'])
    logparser.so_instantiation_metrics._set_operation_ID_for_instantiation_op_datetime_difference(obj['operation_ID_for_instantiation_op_datetime_difference'])
    logparser.so_instantiation_metrics._set_hierarchical_SOE_dispatching_datetime_difference(obj['hierarchical_SOE_dispatching_datetime_difference'])
    logparser.so_instantiation_metrics._set_ROE_created_VLs_start_datetime_difference(obj['ROE_created_VLs_start_datetime_difference'])
    logparser.so_instantiation_metrics._set_ROE_retrieve_RL_resources_start_datetime_difference(obj['ROE_retrieve_RL_resources_start_datetime_difference'])
    logparser.so_instantiation_metrics._set_ROE_parsing_NSDs_start_datetime_difference(obj['ROE_parsing_NSDs_start_datetime_difference'])
    logparser.so_instantiation_metrics._set_ROE_updating_DBs_start_datetime_difference(obj['ROE_updating_DBs_start_datetime_difference'])
    logparser.so_instantiation_metrics._set_ROE_extract_VLs_start_datetime_difference(obj['ROE_extract_VLs_start_datetime_difference'])
    logparser.so_instantiation_metrics._set_retrieving_descriptor_from_catalogue_DBs_start_datetime_difference(obj['retrieving_descriptor_from_catalogue_DBs_start_datetime_difference'])
    logparser.so_instantiation_metrics._set_PA_calculation_start_datetime_difference(obj['PA_calculation_start_datetime_difference'])
    logparser.so_instantiation_metrics._set_create_threshold_based_alerts_start_datetime_difference(obj['create_threshold_based_alerts_start_datetime_difference'])
    logparser.so_instantiation_metrics._set_create_monitoring_jobs_start_datetime_difference(obj['create_monitoring_jobs_start_datetime_difference'])
    logparser.so_instantiation_metrics._set_create_AIML_alerts_start_datetime_difference(obj['create_AIML_alerts_start_datetime_difference'])
    logparser.so_instantiation_metrics._set_CoreMANO_wrapper_time(obj['CoreMANO_wrapper_time'])

    return logparser
