ALTER TABLE cobrança
    DROP CONSTRAINT IF EXISTS cobrança_status_da_cobranca_check;

ALTER TABLE matricula DROP CONSTRAINT IF EXISTS cobrança_status_da_cobranca_check;

ALTER TABLE cobrança
    ADD CONSTRAINT cobrança_status_da_cobranca_check
        CHECK (status_da_cobranca IN ('PAGA', 'PENDENTE', 'CANCELADA'));

ALTER TABLE matricula
    DROP CONSTRAINT IF EXISTS matricula_status_check;

ALTER TABLE matricula
    ADD CONSTRAINT matricula_status_check
        CHECK (status IN ('ATIVO', 'INATIVO', 'CANCELADA'));