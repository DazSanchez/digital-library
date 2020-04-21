FROM payara/server-full

COPY target/digital_library.war $DEPLOY_DIR
