INSERT INTO author (name) VALUES ('name1');
INSERT INTO author (name) VALUES ('name2');
INSERT INTO author (name) VALUES ('name3');

INSERT INTO genre (name) VALUES ('genre1');
INSERT INTO genre (name) VALUES ('genre2');
INSERT INTO genre (name) VALUES ('genre3');

INSERT INTO translator (name) VALUES ('translator1');
INSERT INTO translator (name) VALUES ('translator2');
INSERT INTO translator (name) VALUES ('translator3');

INSERT INTO client (name) VALUES ('client1');
INSERT INTO client (name) VALUES ('client2');
INSERT INTO client (name) VALUES ('client3');


INSERT INTO book(name, description, publishing_house, publishing_year) VALUES ('book1', 'bookDiscr1', 'pubHouse1', 1987);
INSERT INTO book(name, description, publishing_house, publishing_year) VALUES ('book2', 'bookDiscr2', 'pubHouse2', 1997);
INSERT INTO book(name, description, publishing_house, publishing_year) VALUES ('book3', 'bookDiscr3', 'pubHouse3', 2007);

INSERT INTO  author_book(book_id, author_id) VALUES (
                                                        (SELECT book_id FROM book WHERE name = 'book1'),
                                                        (SELECT author_id FROM author WHERE name = 'name1')
                                                    );
INSERT INTO  author_book(book_id, author_id) VALUES (
                                                        (SELECT book_id FROM book WHERE name = 'book1'),
                                                        (SELECT author_id FROM author WHERE name = 'name2')
                                                    );
INSERT INTO  author_book(book_id, author_id) VALUES (
                                                        (SELECT book_id FROM book WHERE name = 'book2'),
                                                        (SELECT author_id FROM author WHERE name = 'name3')
                                                    );

INSERT INTO genre_book (genre_id, book_id) VALUES (
                                                      (SELECT genre_id FROM genre WHERE name = 'genre1'),
                                                      (SELECT book_id FROM book WHERE name = 'book1')
                                                  );
INSERT INTO genre_book (genre_id, book_id) VALUES (
                                                      (SELECT genre_id FROM genre WHERE name = 'genre2'),
                                                      (SELECT book_id FROM book WHERE name = 'book1')
                                                  );
INSERT INTO genre_book (genre_id, book_id) VALUES (
                                                      (SELECT genre_id FROM genre WHERE name = 'genre3'),
                                                      (SELECT book_id FROM book WHERE name = 'book2')
                                                  );

INSERT INTO translator_book (translator_id, book_id) VALUES (
                                                                (SELECT translator_id FROM translator WHERE name = 'translator1'),
                                                                (SELECT book_id FROM book WHERE name = 'book1')
                                                            );
INSERT INTO translator_book (translator_id, book_id) VALUES (
                                                                (SELECT translator_id FROM translator WHERE name = 'translator2'),
                                                                (SELECT book_id FROM book WHERE name = 'book1')
                                                            );
INSERT INTO translator_book (translator_id, book_id) VALUES (
                                                                (SELECT translator_id FROM translator WHERE name = 'translator3'),
                                                                (SELECT book_id FROM book WHERE name = 'book2')
                                                            );



INSERT INTO reserv (client_id, book_id, reservation_date, reservation_date_cancel) VALUES (
                                                                                           (SELECT client_id FROM client WHERE name = 'client1'),
                                                                                           (SELECT book_id FROM book WHERE name = 'book1'),
                                                                                           now(),
                                                                                           now()
                                                                                          );
INSERT INTO reserv (client_id, book_id, reservation_date, reservation_date_cancel) VALUES (
                                                                                           (SELECT client_id FROM client WHERE name = 'client1'),
                                                                                           (SELECT book_id FROM book WHERE name = 'book2'),
                                                                                           now(),
                                                                                           now()
                                                                                          );