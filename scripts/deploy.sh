#!/bin/sh

if [ "X$1" == "X" ] ; then
    echo "ERROR: no command specified!"
    exit 1
fi

case $1 in
    deploy)
        cmd=$1
        ;;
    undeploy)
        cmd=$1
        ;;
    redeploy)
        cmd=$1
        ;;
    *)
        echo "ERROR: wrong command (deploy|undeploy|redeploy)"
        exit 2
        ;;
esac

if [ "X$2" == "X" ] ; then
    echo "ERROR: no artefact specified!"
    exit 3
fi

if [ -f $2 ] ; then
    artefact=$2
else
    artefact=$(find . -name "$2")
fi

wc=$(echo "$artefact" | wc -l)

if [ "X$artefact" == "X" ] ; then
    echo "ERROR: no artefact found!"
    exit 4
fi

if [ $wc -gt 1 ] ; then
    echo "ERROR: multiple artefacts found!"
    exit 5
fi

name=$(basename $artefact .war)

echo "$cmd : $name : $artefact"

container=docker_payara_1

case $cmd in
    deploy)
        docker cp $artefact $container:/tmp/
        cp $artefact /tmp/payara-apps/
        docker exec -it $container /opt/payara/appserver/bin/asadmin -u admin $cmd /tmp/$name.war
        docker exec -it -u 0 $container bash -c "rm -f /tmp/$name.war"
        ;;
    undeploy)
        docker exec -it $container /opt/payara/appserver/bin/asadmin -u admin $cmd $name
        docker exec -it -u 0 $container bash -c "rm -f /tmp/$name.war"
        ;;
    redeploy)
        docker cp $artefact $container:/tmp/
        cp $artefact /tmp/payara-apps/
        docker exec -it $container /opt/payara/appserver/bin/asadmin -u admin $cmd --name $name /tmp/$name.war
        docker exec -it -u 0 $container bash -c "rm -f /tmp/$name.war"
        ;;
esac
