CREATE TABLE IF NOT EXISTS "unit" (

    "id"        UUID NOT NULL,
    "name"      TEXT NOT NULL,
    "okei_code" VARCHAR(5),
    "synonym"   TEXT,

    CONSTRAINT "pk_unit" PRIMARY KEY ("id")
);