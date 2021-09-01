
from binding import gnmic as gnmic_model

def parse_event(obj):
    gnmic = gnmic_model()
    gnmic.gnmic_event._set_name(obj['name'])
    gnmic.gnmic_event._set_timestamp(obj['timestamp'])
    for name, value in obj['tags'].items():
        gnmic.gnmic_event.tags.tag.add(name)
        gnmic.gnmic_event.tags.tag[name]._set_value(value)
    for name, value in obj['values'].items():
        gnmic.gnmic_event.values.value.add(name)
        gnmic.gnmic_event.values.value[name]._set_value(value)

    return gnmic