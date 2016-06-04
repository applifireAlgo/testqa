




echo $PATH
DB_PATH=/tmp/applifire/db/ECLHC5FBFRYCIJJZ4VOXDQ/95607486-2F3F-46E1-91E9-8365BE80F1F8
MYSQL=/usr/bin
USER=algo
PASSWORD=algo
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'