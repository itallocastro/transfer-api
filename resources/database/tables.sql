-- -------------------------------------------------------------
-- TablePlus 3.1.2(296)
--
-- https://tableplus.com/
--
-- Database: postgres
-- Generation Time: 2021-04-03 15:38:20.3830
-- -------------------------------------------------------------


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."users" (
    "id" uuid NOT NULL,
    "name" varchar NOT NULL,
    "created_at" timestamp,
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."accounts" (
    "id" uuid NOT NULL,
    "balance" double precision NOT NULL DEFAULT 0,
    "account_number" varchar  NOT NULL,
    "agency_number" varchar NOT NULL,
    "user_id" uuid NOT NULL,
    "created_at" timestamp,
    PRIMARY KEY ("id"),
    FOREIGN KEY (user_id) REFERENCES "public"."users"(id)
);

CREATE TABLE "public"."pix_keys" (
    "id" uuid NOT NULL,
    "key" varchar NOT NULL UNIQUE,
    "account_id" uuid NOT NULL,
    "created_at" timestamp,
    PRIMARY KEY ("id"),
    FOREIGN KEY (account_id) REFERENCES "public"."accounts"(id)
);