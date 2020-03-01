\connect patientdb;

CREATE TABLE patients (
    id          varchar(255) PRIMARY KEY,
    first_name  varchar(255) NOT NULL,
    last_name   varchar(255) NOT NULL,
    age         smallint NOT NULL
);

CREATE TABLE prescriptions (
   id           varchar(255) PRIMARY KEY,
   patient_id   varchar(255) NOT NULL,
   name         varchar(255) NOT NULL,
   price        float NOT NULL
);

CREATE MATERIALIZED VIEW IF NOT EXISTS patient_ids AS
SELECT
    id
FROM patients;

CREATE MATERIALIZED VIEW IF NOT EXISTS dispenses AS
SELECT
    patient_id as id,
    SUM(price) AS costs,
    COUNT(id) AS prescription_count
FROM prescriptions
GROUP BY patient_id;

CREATE UNIQUE INDEX IF NOT EXISTS patient_id_idx ON patient_ids (id);
CREATE UNIQUE INDEX IF NOT EXISTS dispense_id_idx ON dispenses (id);

CREATE OR REPLACE FUNCTION refresh_patient_ids()
    RETURNS TRIGGER LANGUAGE plpgsql
AS $$
BEGIN
    REFRESH MATERIALIZED VIEW CONCURRENTLY patient_ids;
    RETURN NULL;
END $$;

CREATE OR REPLACE FUNCTION refresh_dispenses()
    RETURNS TRIGGER LANGUAGE plpgsql
AS $$
BEGIN
    REFRESH MATERIALIZED VIEW CONCURRENTLY dispenses;
    RETURN NULL;
END $$;

CREATE TRIGGER on_prescription_change
    AFTER INSERT OR UPDATE OR DELETE OR TRUNCATE
    ON prescriptions
    FOR EACH STATEMENT
EXECUTE PROCEDURE refresh_dispenses();

CREATE TRIGGER on_patient_change
    AFTER INSERT OR UPDATE OR DELETE OR TRUNCATE
    ON patients
    FOR EACH STATEMENT
EXECUTE PROCEDURE refresh_patient_ids();