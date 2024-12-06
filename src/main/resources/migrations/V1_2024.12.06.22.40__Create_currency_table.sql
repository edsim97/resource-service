CREATE TABLE IF NOT EXISTS "currency" (

    "alpha_3_code" VARCHAR(3) NOT NULL,
    "num_code" VARCHAR(3),
    "name_rus" TEXT,
    "name_eng" TEXT,

    CONSTRAINT "pk_currency" PRIMARY KEY ("alpha_3_code")
);