#!/usr/bin/python3

from pyangbind.lib.yangtypes import safe_name

import binding
import copy
import json
import pyangbind.lib.pybindJSON as pybindJSON
import sys

obj = json.load(sys.stdin.buffer)


# Expect parent to be binding module
# and yang_base the name of the Python class
# Data shall be the array of updates
def build_tree(data, parent, yang_base, obj=None, path_helper=None,
               extmethods=None, overwrite=False, skip_unknown=False):

    if obj is None:
        base_mod_cls = getattr(parent, safe_name(yang_base))
        tmp = base_mod_cls(path_helper=False)

        if path_helper is not None:
            # check that this path doesn't already exist in the
            # tree, otherwise we create a duplicate.
            existing_objs = path_helper.get(tmp._path())
            if len(existing_objs) == 0:
                obj = base_mod_cls(path_helper=path_helper,
                                   extmethods=extmethods)
            elif len(existing_objs) == 1:
                obj = existing_objs[0]
            else:
                raise Exception
        else:
            # in this case, we cannot check for an existing object
            obj = base_mod_cls(path_helper=path_helper, extmethods=extmethods)

    set_via_stdmethod = True
    #
    # NOTE:
    # The following code is atrocious.
    # Please, be kind.
    #
    # Populate tree with update elements
    if "prefix" in data:
        for elem in data["prefix"]["elem"]:
            cdata = copy.deepcopy(data)
            cdata["prefix"]["elem"].remove(elem)
            del data["prefix"]["elem"][0]
            del data["prefix"]["elem"][1:]
            # Exclude current element for next iteration
            key = elem["name"]
            child = getattr(obj, "_get_%s" % safe_name(key), None)
            if child is None and skip_unknown is False:
                raise AttributeError(
                    "JSON object contained a key that " +
                    "did not exist (%s)" % (key))
            elif child is None and skip_unknown:
                # skip unknown elements if we are asked to by the user`
                continue
            chobj = child()
            pybind_attr = getattr(chobj, "_pybind_generated_by", None)
            if pybind_attr in ["container"]:
                build_tree(cdata, None, None, obj=chobj,
                           path_helper=path_helper, skip_unknown=skip_unknown)
                set_via_stdmethod = False

            elif pybind_attr in ["YANGListType", "list"]:
                # we need to add each key to the list and then skip
                # a level in the JSON hierarchy
                list_obj = getattr(obj, safe_name(key), None)
                if list_obj is None and skip_unknown is False:
                    raise Exception("Could not load list object " +
                                    "with name %s" % key)
                child_key = elem["key"]["name"]
                if child_key not in chobj:
                    chobj.add(child_key)

                build_tree(cdata, None, None, obj=chobj[child_key],
                           path_helper=path_helper, skip_unknown=skip_unknown)
                set_via_stdmethod = False

    if set_via_stdmethod:
        for update in data["update"]:
            for elem in update["path"]["elem"]:
                key = elem["name"]

                set_method = getattr(obj, "_set_%s" % safe_name(key))
                set_method(update["val"]["stringVal"])

    return obj


# Each update represents a snapshot fo the tree
# Fixed to openconfig-interfaces for testing
tree = build_tree(
    obj["update"],
    binding,
    "openconfig_interfaces"
)
ietf_json = pybindJSON.dumps(tree, mode="ietf")
# Remove root container
# Represented by top Avro record
# (same as Protobuf ang gNMI)
ietf_json_dict = json.loads(ietf_json)
root_container = list(ietf_json_dict)[0]
ietf_json_dict = ietf_json_dict[root_container]
output_json = json.dumps(ietf_json_dict)
# Replace dashes with underscores
# This is a quick hack that
# could break some values
output_json = output_json.replace("-", "_")
sys.stdout.write("{0}\n".format(output_json))
