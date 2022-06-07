CREATE TYPE DOCUMENT_TYPE AS ENUM ('passport', 'birth_certificate', 'id_card');
CREATE TYPE GENDER AS ENUM ('male', 'female');

CREATE TABLE "users"
(
    "id"        UUID PRIMARY KEY,
    "role"      role    NOT NULL,
    "name"      VARCHAR NOT NULL,
    "email"     VARCHAR NOT NULL,
    "password"  VARCHAR NOT NULL,
    UNIQUE (email)
);

INSERT INTO "users" ("id", "role", "name", "email", "password")
VALUES ('c1039d34-425b-4f78-9a7f-893f5b4df478', 'admin', 'Admin', 'personInfo@gmail.com',
        '$s0$e0801$5JK3Ogs35C2h5htbXQoeEQ==$N7HgNieSnOajn1FuEB7l4PhC6puBSq+e1E8WUaSJcGY=');

CREATE TABLE "personal_data"
(
    "id"                UUID PRIMARY KEY,
    "document_type"     DOCUMENT_TYPE NOT NULL,
    "document_number"   VARCHAR       NOT NULL,
    "first_name"        VARCHAR       NOT NULL,
    "last_name"         VARCHAR       NOT NULL,
    "fathers_name"      VARCHAR       NOT NULL,
    "living_place"      VARCHAR       NOT NULL,
    "phone_number"      VARCHAR NULL,
    "gender"            GENDER        NOT NULL,
    "street"            VARCHAR       NOT NULL,
    "house_number"      INT           NOT NULL,
    "employment_status" VARCHAR       NOT NULL,
    "educational_info"  VARCHAR       NOT NULL,
    "family_status"     VARCHAR       NOT NULL,
    "health_status"     VARCHAR       NOT NULL,
    "youth_note"        VARCHAR       NOT NULL,
    "iron_note"         VARCHAR       NOT NULL,
    "women_note"        VARCHAR       NOT NULL
);

CREATE TABLE "applications"
(
    "id"           UUID PRIMARY KEY,
    "first_name"   VARCHAR NOT NULL,
    "last_name"    VARCHAR NOT NULL,
    "phone_number" VARCHAR NOT NULL,
    "description"  VARCHAR NOT NULL
);

CREATE TABLE "news_events"
(
    "id"        UUID PRIMARY KEY,
    "title"     VARCHAR NOT NULL,
    "text"      TEXT    NOT NULL,
    "image_url" VARCHAR NOT NULL
);

DROP TABLE "users";
DROP TABLE "news_events";
DROP TABLE "personal_data";
DROP TABLE "applications";