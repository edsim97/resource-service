CREATE TABLE IF NOT EXISTS "country" (
    "alpha_3_code" VARCHAR(3) NOT NULL,
    "alpha_2_code" VARCHAR(2) NOT NULL,
    "code" VARCHAR(3) NOT NULL,
    "short_name" TEXT NOT NULL,
    "full_name" TEXT NOT NULL,
    "name_eng" TEXT NOT NULL,

    CONSTRAINT "pk_country" PRIMARY KEY ("alpha_3_code")
);