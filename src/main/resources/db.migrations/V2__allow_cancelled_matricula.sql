ALTER TABLE matricula
    DROP CONSTRAINT IF EXISTS matricula_status_check;

ALTER TABLE matricula
    ADD CONSTRAINT matricula_status_check
        CHECK (status IN ('ATIVO', 'INATIVO', 'CANCELADA'));