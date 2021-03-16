package az.bakhishli.redditclone.exception;

public class AlreadyVotedPostException extends RuntimeException{
    public AlreadyVotedPostException(String message){
        super(message);
    }

}
