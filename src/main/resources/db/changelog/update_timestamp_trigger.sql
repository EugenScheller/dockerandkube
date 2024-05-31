CREATE TRIGGER update_timestamp
    BEFORE UPDATE ON book
    FOR EACH ROW CALL "de.eugen.brownbag.bookstoreserver.repository.UpdateTrigger"