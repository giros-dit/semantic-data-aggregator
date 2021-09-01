from .binding import logparser_termination_operation as logparser_model

def parse_event(obj):
    logparser = logparser_model()
    logparser.so_termination_metrics._set_Current_time(obj['Current_time'])
    logparser.so_termination_metrics._set_Operation(obj['Operation'])
    logparser.so_termination_metrics._set_NS_ID(obj['NS_ID'])
    logparser.so_termination_metrics._set_NSD_ID(obj['NSD_ID'])
    logparser.so_termination_metrics._set_Total_termination_time(obj['Total_termination_time'])
    logparser.so_termination_metrics._set_SOE_time(obj['SOE_time'])
    logparser.so_termination_metrics._set_ROE_time(obj['ROE_time'])
    logparser.so_termination_metrics._set_Operation_ID_for_Termination_op(obj['Operation_ID_for_Termination_op'])
    logparser.so_termination_metrics._set_Hierarchical_SOE_dispatching_SOEpSOEc(obj['Hierarchical_SOE_dispatching_SOEpSOEc'])
    logparser.so_termination_metrics._set_ROE_deleting_LLs(obj['ROE_deleting_LLs'])
    logparser.so_termination_metrics._set_ROE_updating_DBs(obj['ROE_updating_DBs'])
    logparser.so_termination_metrics._set_Terminating_Threshold_based_alerts(obj['Terminating_Threshold_based_alerts'])
    logparser.so_termination_metrics._set_Terminating_Monitoring_jobs(obj['Terminating_Monitoring_jobs'])
    logparser.so_termination_metrics._set_Terminating_AIML_alert_jobs(obj['Terminating_AIML_alert_jobs'])
    logparser.so_termination_metrics._set_CoreMANO_Wrapper_time(obj['CoreMANO_Wrapper_time'])

    return logparser
