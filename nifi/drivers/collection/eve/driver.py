
from binding import eve as eve_model

def parse_event(obj):
    eve = eve_model()
    if 'metric_value' in obj:
        eve.eve_record._set_value(obj['metric_value'])
    elif 'kpi_value' in obj:
        eve.eve_record._set_value(obj['kpi_value'])
    eve.eve_record._set_timestamp(obj['timestamp'])
    eve.eve_record._set_unit(obj['unit'])
    if 'device_id' in obj:
        eve.eve_record._set_device_id(obj['device_id'])
    if 'context' in obj:
        labels = obj['context'].split()
        for label in labels:
            name, value = label.split("=")
            eve.eve_record.labels.label.add(name)
            eve.eve_record.labels.label[name]._set_value(value)

    return eve