CREATE TABLE IF NOT EXISTS "author" (
                          "author_id" BIGSERIAL PRIMARY KEY,
                          "name" varchar
);

CREATE TABLE IF NOT EXISTS "author_book" (
                               "author_id" BIGINT,
                               "book_id" BIGINT
);

CREATE TABLE IF NOT EXISTS "translator" (
                              "translator_id" BIGSERIAL PRIMARY KEY,
                              "name" varchar
);

CREATE TABLE IF NOT EXISTS "translator_book" (
                                   "translator_id" BIGINT,
                                   "book_id" BIGINT
);

CREATE TABLE IF NOT EXISTS "genre" (
                         "genre_id" BIGSERIAL PRIMARY KEY,
                         "name" varchar
);

CREATE TABLE IF NOT EXISTS "genre_book" (
                              "genre_id" BIGINT,
                              "book_id" BIGINT
);

CREATE TABLE IF NOT EXISTS "book" (
                        "book_id" BIGSERIAL PRIMARY KEY,
                        "name" varchar,
                        "publishing_house" varchar,
                        "publishing_year" int,
                        "description" text
);

CREATE TABLE IF NOT EXISTS "client" (
                          "client_id" BIGSERIAL PRIMARY KEY,
                          "name" varchar
);

CREATE TABLE IF NOT EXISTS "reserv" (
                          "reserv_id" BIGSERIAL PRIMARY KEY,
                          "client_id" BIGINT,
                          "book_id" BIGINT,
                          "reservation_date" date,
                          "reservation_date_cancel" date
);

ALTER TABLE "author_book" ADD FOREIGN KEY ("author_id") REFERENCES "author" ("author_id");

ALTER TABLE "author_book" ADD FOREIGN KEY ("book_id") REFERENCES "book" ("book_id");

ALTER TABLE "translator_book" ADD FOREIGN KEY ("translator_id") REFERENCES "translator" ("translator_id");

ALTER TABLE "translator_book" ADD FOREIGN KEY ("book_id") REFERENCES "book" ("book_id");

ALTER TABLE "genre_book" ADD FOREIGN KEY ("genre_id") REFERENCES "genre" ("genre_id");

ALTER TABLE "genre_book" ADD FOREIGN KEY ("book_id") REFERENCES "book" ("book_id");

ALTER TABLE "reserv" ADD FOREIGN KEY ("book_id") REFERENCES "book" ("book_id");

ALTER TABLE "reserv" ADD FOREIGN KEY ("client_id") REFERENCES "client" ("client_id");
