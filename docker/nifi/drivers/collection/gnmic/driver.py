
from binding import gnmi as gnmi_model

def parse_event(obj):
    gnmi = gnmi_model()
    gnmi.gnmi_event._set_name(obj['name'])
    gnmi.gnmi_event._set_timestamp(obj['timestamp'])
    for name, value in obj['tags'].items():
        gnmi.gnmi_event.tags.tag.add(name)
        gnmi.gnmi_event.tags.tag[name]._set_value(value)
    for name, value in obj['values'].items():
        gnmi.gnmi_event.values.value.add(name)
        gnmi.gnmi_event.values.value[name]._set_value(value)

    return gnmi