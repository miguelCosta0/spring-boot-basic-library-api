package library.service;

public interface LibraryService {

    public void linkBookAndAuthor(long bookId, long authorId);

    public void unlinkBookAndAuthor(long bookId, long authorId);

}
