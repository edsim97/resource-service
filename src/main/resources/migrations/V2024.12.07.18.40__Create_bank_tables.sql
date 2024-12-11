CREATE TABLE IF NOT EXISTS "bank_rus" (

    "id"              UUID NOT NULL,
    "bic"             VARCHAR(9) NOT NULL,
    "name"            VARCHAR(1024) NOT NULL,
    "zip"             VARCHAR(7),
    "settlement_type" TEXT,
    "settlement_name" TEXT,
    "address"         TEXT,
    "account"         TEXT,
    "created_date"    TIMESTAMP NOT NULL DEFAULT NOW(),
    "annulling_date"  TIMESTAMP,

    CONSTRAINT "pk_bank_rus" PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "bank_kaz" (

    "id"             UUID NOT NULL,
    "bic"            TEXT NOT NULL,
    "bin"            TEXT NOT NULL,
    "category"       TEXT,
    "city"           TEXT,
    "country"        TEXT,
    "dsc"            TEXT,
    "house"          TEXT,
    "bank_id"        TEXT,
    "kato"           TEXT,
    "name"           TEXT,
    "rnn"            TEXT,
    "zip"            TEXT,
    "street"         TEXT,
    "created_date"   TIMESTAMP NOT NULL DEFAULT NOW(),
    "annulling_date" TIMESTAMP,

    CONSTRAINT "pk_bank_kaz" PRIMARY KEY ("id")
);