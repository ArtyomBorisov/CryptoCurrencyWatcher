package watcher.service.exception;

public class EntityNotFound extends NullPointerException {
    public EntityNotFound() {
    }

    public EntityNotFound(String s) {
        super(s);
    }
}
