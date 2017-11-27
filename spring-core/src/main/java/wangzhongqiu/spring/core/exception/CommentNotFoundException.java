package wangzhongqiu.spring.core.exception;


public class CommentNotFoundException extends ServiceException {

    private static final long serialVersionUID = 3375979959164455699L;

    public CommentNotFoundException(int id) {
        super("Comment <" + id + "> not found");
    }
}
