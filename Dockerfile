FROM payara/server-full

COPY target/digital_library-1.0-SNAPSHOT.war $DEPLOY_DIR
