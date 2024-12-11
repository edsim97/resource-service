CREATE TABLE IF NOT EXISTS "unit" (

    "code"      VARCHAR(5) NOT NULL,
    "full_name" TEXT,
    "name"      TEXT NOT NULL,
    "synonym"   TEXT,

    CONSTRAINT "pk_unit" PRIMARY KEY ("code")
);