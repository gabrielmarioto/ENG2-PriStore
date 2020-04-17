set PGPASSWORD=postgres123
cd bdutil
pg_restore.exe -c --host localhost --port 5432 --username "postgres" --dbname "tabelaPriStore" --verbose --no-password "bkp.sql"