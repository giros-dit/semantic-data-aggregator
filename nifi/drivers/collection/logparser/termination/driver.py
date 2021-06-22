from .binding import logparser_termination_operation as logparser_model

def parse_event(obj):
    logparser = logparser_model()
    logparser.so_termination_metrics._set_current_time(obj['current_time'])
    logparser.so_termination_metrics._set_operation(obj['operation'])
    logparser.so_termination_metrics._set_nsID(obj['nsID'])
    logparser.so_termination_metrics._set_nsdID(obj['nsdID'])
    logparser.so_termination_metrics._set_total_termination_time(obj['total_termination_time'])
    logparser.so_termination_metrics._set_SOE_time(obj['SOE_time'])
    logparser.so_termination_metrics._set_ROE_time(obj['ROE_time'])
    logparser.so_termination_metrics._set_operation_ID_for_termination_op_datetime_difference(obj['operation_ID_for_termination_op_datetime_difference'])
    logparser.so_termination_metrics._set_hierarchical_SOE_dispatching_datetime_difference(obj['hierarchical_SOE_dispatching_datetime_difference'])
    logparser.so_termination_metrics._set_ROE_deleting_LLs_stop_datetime_difference(obj['ROE_deleting_LLs_stop_datetime_difference'])
    logparser.so_termination_metrics._set_ROE_updating_DBs_stop_datetime_difference(obj['ROE_updating_DBs_stop_datetime_difference'])
    logparser.so_termination_metrics._set_terminating_threshold_based_alerts_stop_datetime_difference(obj['terminating_threshold_based_alerts_stop_datetime_difference'])
    logparser.so_termination_metrics._set_terminating_monitoring_jobs_stop_datetime_difference(obj['terminating_monitoring_jobs_stop_datetime_difference'])
    logparser.so_termination_metrics._set_terminating_AIML_jobs_stop_datetime_difference(obj['terminating_AIML_jobs_stop_datetime_difference'])
    logparser.so_termination_metrics._set_CoreMANO_wrapper_time(obj['CoreMANO_wrapper_time'])

    return logparser
