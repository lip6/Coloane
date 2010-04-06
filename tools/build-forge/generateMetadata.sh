#!/bin/zsh

ECLIPSE_FOLDER=/data/forge/eclipse

version=$1
directory=$2

update_site_publisher() {
    repository_name=$1
    update_site=$2
    rm -f $update_site/artifacts.xml $update_site/content.xml
    (
    cd $ECLIPSE_FOLDER
    java -jar plugins/org.eclipse.equinox.launcher_*.jar \
        -application org.eclipse.equinox.p2.publisher.UpdateSitePublisher \
        -metadataRepository file:$update_site \
        -artifactRepository file:$update_site \
        -source $update_site \
        -repositoryName "$repository_name" \
        -configs gtk.linux.x86 \
        -publishArtifacts
    )
}

# Calcul des chemins en fonction SNAPSHOT / RELEASE
if [ `echo $version | grep "SNAPSHOT"` ]; then
    repository_name="Coloane Night-updates Site"
    branch="night-updates"
elif [ `echo $version | grep "INCUBATION"` ]; then
    repository_name="Coloane Incubation-updates Site"
    branch="incubation-updates"
else
    repository_name="Coloane Updates Site"
    branch="updates"
fi

update_site_publisher \
    "$repository_name" \
    "$directory/$branch"

