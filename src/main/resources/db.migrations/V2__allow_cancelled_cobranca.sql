ALTER TABLE cobrança
    DROP CONSTRAINT IF EXISTS cobrança_status_da_cobranca_check;

ALTER TABLE matricula DROP CONSTRAINT IF EXISTS cobrança_status_da_cobranca_check;

ALTER TABLE cobrança
    ADD CONSTRAINT cobrança_status_da_cobranca_check
        CHECK (status_da_cobranca IN ('PAGA', 'PENDENTE', 'CANCELADA'));