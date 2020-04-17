set PGPASSWORD=postgres123
cd bdutil
pg_restore.exe -c --host localhost --port 5432 --username "postgres" --dbname "pristoredb" --verbose --no-password "bkp.sql"