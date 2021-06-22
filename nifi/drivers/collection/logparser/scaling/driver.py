from .binding import logparser_scaling_operation as logparser_model

def parse_event(obj):
    logparser = logparser_model()
    logparser.so_scaling_metrics._set_current_time(obj['current_time'])
    logparser.so_scaling_metrics._set_operation(obj['operation'])
    logparser.so_scaling_metrics._set_nsID(obj['nsID'])
    logparser.so_scaling_metrics._set_nsdID(obj['nsdID'])
    logparser.so_scaling_metrics._set_total_scaling_time(obj['total_scaling_time'])
    logparser.so_scaling_metrics._set_SOE_time(obj['SOE_time'])
    logparser.so_scaling_metrics._set_ROE_time(obj['ROE_time'])
    logparser.so_scaling_metrics._set_operation_ID_for_scaling_op_datetime_difference(obj['operation_ID_for_scaling_op_datetime_difference'])
    logparser.so_scaling_metrics._set_hierarchical_SOE_dispatching_datetime_difference(obj['hierarchical_SOE_dispatching_datetime_difference'])
    logparser.so_scaling_metrics._set_SOEc_preparing_info_for_scaling_scale_datetime_difference(obj['SOEc_preparing_info_for_scaling_scale_datetime_difference'])
    logparser.so_scaling_metrics._set_ROE_checking_resource_availability_scale_datetime_difference(obj['ROE_checking_resource_availability_scale_datetime_difference'])
    logparser.so_scaling_metrics._set_ROE_extracted_scaled_VLs_at_RL_scale_datetime_difference(obj['ROE_extracted_scaled_VLs_at_RL_scale_datetime_difference'])
    logparser.so_scaling_metrics._set_ROE_scaled_LLs_at_RL_scale_datetime_difference(obj['ROE_scaled_LLs_at_RL_scale_datetime_difference'])
    logparser.so_scaling_metrics._set_ROE_updating_DBs_scale_datetime_difference(obj['ROE_updating_DBs_scale_datetime_difference'])
    logparser.so_scaling_metrics._set_scale_monitoring_jobs_scale_datetime_difference(obj['scale_monitoring_jobs_scale_datetime_difference'])
    logparser.so_scaling_metrics._set_scale_AIML_jobs_scale_datetime_difference(obj['scale_AIML_jobs_scale_datetime_difference'])
    logparser.so_scaling_metrics._set_CoreMANO_wrapper_time(obj['CoreMANO_wrapper_time'])

    return logparser
