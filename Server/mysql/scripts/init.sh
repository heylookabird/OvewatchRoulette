#!/bin/bash


SCRIPT_DIR=`dirname "$BASH_SOURCE"`
SCHEMAS_DIR="$SCRIPT_DIR/../schemas"


MYSQL_USER="root"
MYSQL_PASS="pass"

cd $SCHEMAS_DIR

mysql -u $MYSQL_USER -p < init_schema.sql
mysql -u $MYSQL_USER -p< maps.sql
mysql -u $MYSQL_USER -p< strats.sql


