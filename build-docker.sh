#!/usr/bin/env bash
#
# Build docker images script
#
# Portions of this code are borrowed / inspired from https://github.com/hortonworks/registry/blob/master/docker/kdc-registry.sh

# Image name variables
registry_image="schema-registry"
# Image paths
registry_image_path="schema-registry"
# Image URLs
schema_registry_download_url="https://github.com/hortonworks/registry/releases/download/0.9.0/hortonworks-registry-0.9.0.tar.gz"
# Standard output variable
std_output="/dev/null"

function registryVersion {
    filename=$(echo ${schema_registry_download_url} | cut -d '/' -f9)
    rversion=$(echo ${filename} | awk -F "hortonworks-registry-" '{print $2}' | awk -F ".tar.gz" '{print $1}')
    echo "${rversion}"
}

function buildSchemaRegistry {
    rversion=$(registryVersion)
    filename=$(echo ${schema_registry_download_url} | cut -d '/' -f9)
    if [[ "$(docker images -q ${registry_image}:${rversion} 2> ${std_output})" == "" ]]; then
        echo "Downloading Schema Registry distribution from URL :: " ${schema_registry_download_url}
        wget -q --show-progress "${schema_registry_download_url}"
        mv ${filename} ${registry_image_path}/
        docker build -t ${registry_image}:${rversion} ${registry_image_path} --build-arg "REGISTRY_VERSION=${rversion}"
        echo "Cleanup downloaded tar file ${registry_image_path}/${filename}"
        rm ${registry_image_path}/${filename}
    else
        echo "Schema registry image ${registry_image}:${rversion} already available, build skipped" \
            "If you want to re-build, remove the existing image and build again"
    fi
}

usage() {
    local exit_status="${1}"
    cat <<EOF

Usage: $0 [command] [options]

help|-h|--help
    Display this help message

build-registry
    Builds Docker image for HWX Schema Registry
EOF
    exit "${exit_status}"
}

option="${1}"
shift
case "${option}" in
    h|-h|--help|help)
        usage 0
        ;;
    build-registry)
        buildSchemaRegistry
        ;;
esac

