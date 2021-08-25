ALTER TABLE book ADD isbn VARCHAR (50);

UPDATE book
SET isbn = CONCAT(book_id, '_vc1879esd65c14g5d');
