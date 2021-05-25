from binding import logparser as logparser_model

def parse_event(obj):
    logparser = logparser_model()
    logparser.so_log._set_current_time(obj['current_time'])
    logparser.so_log._set_nsID(obj['nsID'])
    logparser.so_log._set_nsdID(obj['nsdID'])
    if 'total_instantiation_time' in obj:
        logparser.so_log._set_total_instantiation_time(obj['total_instantiation_time'])
    if 'operation_ID_for_instantiation_op_datetime_difference' in obj:
        logparser.so_log._set_operation_ID_for_instantiation_op_datetime_difference(obj['operation_ID_for_instantiation_op_datetime_difference'])
    if 'hierarchical_SOE_dispatching_SOEpSOEc_datetime_difference' in obj:
        logparser.so_log._set_hierarchical_SOE_dispatching_SOEpSOEc_datetime_difference(obj['hierarchical_SOE_dispatching_SOEpSOEc_datetime_difference'])
    if 'retrieving_descriptor_from_catalogue_DBs_start_datetime_difference' in obj:
        logparser.so_log._set_retrieving_descriptor_from_catalogue_DBs_start_datetime_difference(obj['retrieving_descriptor_from_catalogue_DBs_start_datetime_difference'])
    if 'ROE_parsing_NSDs_start_datetime_difference' in obj:
        logparser.so_log._set_ROE_parsing_NSDs_start_datetime_difference(obj['ROE_parsing_NSDs_start_datetime_difference'])
    if 'ROE_retrieve_RL_resources_start_datetime_difference' in obj:
        logparser.so_log._set_ROE_retrieve_RL_resources_start_datetime_difference(obj['ROE_retrieve_RL_resources_start_datetime_difference'])
    if 'PA_calculation_start_datetime_difference' in obj:
        logparser.so_log._set_PA_calculation_start_datetime_difference(obj['PA_calculation_start_datetime_difference'])
    if 'creating_networks_at_OSM_wrapper_start_datetime_difference' in obj:
        logparser.so_log._set_creating_networks_at_OSM_wrapper_start_datetime_difference(obj['creating_networks_at_OSM_wrapper_start_datetime_difference'])
    if 'creating_VNFs_at_OSM_wrapper_start_datetime_difference' in obj:
        logparser.so_log._set_creating_VNFs_at_OSM_wrapper_start_datetime_difference(obj['creating_VNFs_at_OSM_wrapper_start_datetime_difference'])
    if 'OSM_wrapper_updating_DBs_start_datetime_difference' in obj:
        logparser.so_log._set_OSM_wrapper_updating_DBs_start_datetime_difference(obj['OSM_wrapper_updating_DBs_start_datetime_difference'])
    if 'ROE_extract_VLs_start_datetime_difference' in obj:
        logparser.so_log._set_ROE_extract_VLs_start_datetime_difference(obj['ROE_extract_VLs_start_datetime_difference'])
    if 'ROE_created_VLs_start_datetime_difference' in obj:
        logparser.so_log._set_ROE_created_VLs_start_datetime_difference(obj['ROE_created_VLs_start_datetime_difference'])
    if 'ROE_updating_DBs_start_datetime_difference' in obj:
        logparser.so_log._set_ROE_updating_DBs_start_datetime_difference(obj['ROE_updating_DBs_start_datetime_difference'])
    if 'create_monitoring_jobs_start_datetime_difference' in obj:
        logparser.so_log._set_create_monitoring_jobs_start_datetime_difference(obj['create_monitoring_jobs_start_datetime_difference'])
    if 'create_threshold_based_alerts_start_datetime_difference' in obj:
        logparser.so_log._set_create_threshold_based_alerts_start_datetime_difference(obj['create_threshold_based_alerts_start_datetime_difference'])
    if 'create_AIML_alerts_start_datetime_difference' in obj:
        logparser.so_log._set_create_AIML_alerts_start_datetime_difference(obj['create_AIML_alerts_start_datetime_difference'])
    if 'wrapper_time_start_datetime_difference' in obj:
        logparser.so_log._set_wrapper_time_start_datetime_difference(obj['wrapper_time_start_datetime_difference'])
    if 'SOE_time' in obj:
        logparser.so_log._set_SOE_time(obj['SOE_time'])
    if 'ROE_time' in obj:
        logparser.so_log._set_ROE_time(obj['ROE_time'])

    return logparser
