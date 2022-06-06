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
    "edeucational_info" VARCHAR       NOT NULL,
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