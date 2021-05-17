from binding import prometheus

def parse_event(obj):
    pm = prometheus()
    pm.metric._set_timestamp(obj['value'][0])
    pm.metric._set_value(obj['value'][1])
    for name, value in obj['metric'].items():
        if "__name__" == name:
            pm.metric._set_name(value)
        else:
            pm.metric.labels.label.add(name)
            pm.metric.labels.label[name]._set_value(value)

    return pm
