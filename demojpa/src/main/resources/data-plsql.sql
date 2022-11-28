---- PL/SQL LOOP, FOR문 ----
-- insert into book(id, title, contents) values (book_seq.nextval, '달러구트 꿈 백화점', '잠들어야만 입장 가능한 꿈 백화점에서 일어나는 비밀스럽고도 기묘하며 가슴 뭉클한 판타지 소설');

DECLARE
 vn_data1 VARCHAR2(255) := '책제목_';
 vn_data2 VARCHAR2(4000) := '책내용_';
BEGIN
	 FOR i IN 1..3000
	 LOOP
        INSERT INTO book(id, title, contents)
        VALUES
        (book_seq.nextval, vn_data1 || i, vn_data2 || i);
	END LOOP;
    COMMIT;
END;
