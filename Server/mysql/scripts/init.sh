#!/bin/bash


SCRIPT_DIR=`dirname "$BASH_SOURCE"`
SCHEMAS_DIR="$SCRIPT_DIR/../schemas"


MYSQL_USER="root"
MYSQL_PASS="Pa$$w0rd"

cd $SCHEMAS_DIR

mysql -u $MYSQL_USER -p < init_schema.sql
mysql -u $MYSQL_USER -p< maps.sql
mysql -u $MYSQL_USER -p< strats.sql

#mysql -h overwatchroulette.cdlwhilaiijd.us-west-1.rds.amazonaws.com -u $MYSQL_USER -p < init_schema.sql
#mysql -h overwatchroulette.cdlwhilaiijd.us-west-1.rds.amazonaws.com -u $MYSQL_USER -p< maps.sql
#mysql -h overwatchroulette.cdlwhilaiijd.us-west-1.rds.amazonaws.com -u $MYSQL_USER -p< strats.sql


#MYSQL_USER = dbmaster

