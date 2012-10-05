#!/bin/bash
#
# Description:
# Script to generate dot file of the architecture of an eclipse plugin.
# It parse MANIFEST.MF and feature.xml files of the project to build
# a graph with all plugins, features and fragments and their dependencies.
#
# To use this script, launch it from the root of your project tree.
#
# Script dependencies: sed, graphviz, pcre, xmlstarlet, bc
#


# Debug messages: 0 (false) or 1 (true)
DEBUG=1

# Find options to ignore some directories
IGNORE='-not -path "*/.svn/*" -not -path "*/target/*"'

# Initialize the dot file with some headers
function init() {
    print "digraph dependencies {"
    print "  splines=ortho;"
    print "  layout=dot;"
    # print "  overlap=portho;"
    # print "  concentrate=true;"
    print "  node [shape=box];"
    print "  edge [dir=both, arrowhead=open, arrowtail=none, arrowsize=0.5];"
    print ""
    # print "  core [width=2, height=3];"
    # print "  interfaces [width=2, height=3];"
    print ""
}

function debug() {
    if [ "c$DEBUG" == "c1" ]; then
        print "# $@" >&2
    fi
}

function find_files() {
    local name="$1"
    shift
    local paths="$@"
    eval "find $paths -name $name $IGNORE"
}

function print() {
    echo "${@//}"
}

function close() {
    print "}"
}

function getSize() {
    local id="$1"
    local size="${stats["$id"]:-0}"
    echo ", height=$(echo "$size/4 + 0.5" | bc), fontsize=$(echo "$size*1.5 + 14" | bc)"
}

function create_plugin() {
    debug "create_plugin $@"
    local id="$1"
    local name="$2"
    print "  $id [color=red, fontcolor=white, style=filled, label=\"$name\" $(getSize $id)];"
}

function create_fragment() {
    debug "create_fragment $@"
    local id="$1"
    local name="$2"
    print "  $id [color=purple, fontcolor=white, style=filled, label=\"$name\" $(getSize $id)];"
}

function create_feature() {
    debug "create_feature $@"
    local id="$1"
    local name="$2"
    print "  $id [color=orange, fontcolor=white, style=filled, label=\"$name\" $(getSize $id)];"
}

function create_extension() {
    debug "create_extension $@"
    local id="$1"
    local name="$2"
    print "  $id [color=green, fontcolor=white, style=filled, label=\"$name\" $(getSize $id)];"
}

function create_update_site() {
    debug "create_update_site $@"
    local id="$1"
    local name="$2"
    print "  $id [color=blue, fontcolor=white, style=filled, label=\"$name\" $(getSize $id)];"
}

function clean_id() {
    sed -rn 's/ *(fr.*coloane\.)?([^,]*)(;.*)?,?/\2/p' \
        | sed 's/fr.*pnml\.framework/pnml_f/' \
        | sed 's/org\.eclipse/o_e/' \
        | sed 's/-/_/g' \
        | sed 's/\./_/g'
}

function getManifestId() {
    local manifest="$1"
    sed -rn '/SymbolicName/ {s/.*: (fr.*coloane\.)?([^;]*)(;.*)?/\2/p}' $manifest | clean_id
}

function find_property() {
    local key="$1"
    shift
    local paths="$@"
    for f in $(find_files '*.properties' $paths); do
        if grep -q -E "^${key} *=.*" "$f"; then
            echo "$f"
        fi
    done
}

function getManifestName() {
    local manifest="$1"
    local name="$(sed -rn '/Bundle-Name/ {s/.*: (.*)/\1/p}' $manifest)"
    if [ "c${name:0:1}" == "c%" ]; then
        local key="${name:1}"
        local properties="$(find_property "$key" "$(dirname $manifest)/..")"
        if ([ -f "$properties" ] && grep -q "${name:1}" "$properties"); then
            pcregrep -o1 "${name:1} *= *(.*)" "$properties"
        fi
    elif [ "c$name" == "c" ]; then
        getManifestId $manifest
    else
        echo "$name"
    fi
}

function getManifestDependencies() {
    local manifest="$1"
    pcregrep -M -o1 'Require-Bundle: (([\w\.]+,\s*)*[\w\.]+)' $manifest | clean_id
}

function getFeatureId() {
    local feature="$1"
    xmlstarlet sel -t -v '/feature/@id' $feature | clean_id
}

function getFeatureName() {
    local feature="$1"
    local name="$(xmlstarlet sel -t -v '/feature/@label' $feature)"
    if [ "c${name:0:1}" == "c%" ]; then
        local key="${name:1}"
        local properties="$(find_property "$key" "$(dirname $feature)")"
        if ([ -f "$properties" ] && grep -q "${name:1}" "$properties"); then
            pcregrep -o1 "${name:1} *= *(.*)" "$properties"
        fi
    else
        echo "$name"
    fi
}

function getFeatureDependencies() {
    local feature="$1"
    xmlstarlet sel \
        -t -v '/feature/requires/import/@plugin' -t -nl \
        -t -v '/feature/requires/import/@feature' -t -nl \
        -t -v '/feature/plugin/@id' $feature | clean_id
}

function getUpdateSiteId() {
    local site="$1"
    xmlstarlet sel -t -v '/site/description' "$site" | pcregrep -o1 "\s*(.*\w)\s*" | clean_id
}

function getUpdateSiteName() {
    local site="$1"
    xmlstarlet sel -t -v '/site/description' "$site" | pcregrep -o1 "\s*(.*\w)\s*"
}

function getUpdateSiteFeatures() {
    local site="$1"
    xmlstarlet sel -t -v '/site/feature/@id' "$site" | clean_id
}

declare -A stats
function addStat() {
    local name="$1"
    local value="${stats["$name"]}"
    if [ "c$value" == "c" ]; then
        stats["$name"]="1"
    else
        stats["$name"]=$((${stats["$name"]} + 1))
    fi
}

function main() {
    paths="$@"
    debug "main $@"
    init
    debug "plugin dependencies"
    for manifest in $(find_files MANIFEST.MF $paths); do
        id="$(getManifestId $manifest)"
        IFS=$'\n'
        deps="$(getManifestDependencies "$manifest")"
        unset IFS
        for dep in $deps; do
            print "  $id -> $dep;"
            addStat "$id"
            addStat "$dep"
            addStat "$dep"
        done
    done
    debug "feature dependencies"
    for feature in $(find_files feature.xml $paths); do
        id="$(getFeatureId $feature)"
        IFS=$'\n'
        deps="$(getFeatureDependencies "$feature")"
        unset IFS
        for dep in $deps; do
            print "  $id -> $dep [color=orange];"
            addStat "$id"
            addStat "$dep"
            addStat "$dep"
        done
    done
    debug "update-site dependencies"
    for site in $(find_files category.xml $paths); do
        debug "parse $site"
        id="$(getUpdateSiteId $site)"
        features="$(getUpdateSiteFeatures "$site")"
        for feature in $features; do
            debug "update-site feature declared : $feature"
            print "  $id -> $feature [style=dashed, color=grey];"
            addStat "$id"
            addStat "$feature"
        done
    done
    debug "create all default nodes"
    for node in ${!stats[@]}; do
        print "  $node [label=\"$node\" $(getSize $node)]"
    done
    debug "create nodes from manifests"
    for manifest in $(find_files MANIFEST.MF $paths); do
        debug "parse $manifest"
        id="$(getManifestId $manifest)"
        name="$(getManifestName $manifest)"
        if print $manifest | grep -q extension; then
            create_extension "$id" "$name"
        elif print $manifest | grep -q fragment; then
            create_fragment "$id" "$name"
        elif print $manifest | grep -q tool; then
            create_extension "$id" "$name"
        else
            addStat "$id"
            addStat "$id"
            addStat "$id"
            addStat "$id"
            create_plugin "$id" "$name"
        fi
    done
    debug "create nodes from features"
    for feature in $(find_files feature.xml $paths); do
        debug "parse $feature"
        id="$(getFeatureId $feature)"
        name="$(getFeatureName $feature)"
        create_feature "$id" "$name"
    done
    debug "create nodes from update-sites"
    for site in $(find_files category.xml $paths); do
        debug "parse $site"
        id="$(getUpdateSiteId $site)"
        name="$(getUpdateSiteName $site)"
        create_update_site "$id" "$name"
    done
    close
}

# cd $(dirname $0)
# main $@
main $@ | dot -Tpdf -odep.pdf
# main $@ | dot -Tsvg -odep.svg
