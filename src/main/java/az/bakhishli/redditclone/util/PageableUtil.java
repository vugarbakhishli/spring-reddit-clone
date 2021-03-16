package az.bakhishli.redditclone.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.HttpStatus.OK;
import java.util.Collection;

@UtilityClass
public class PageableUtil {
    public <C extends Collection<P>, P> ResponseEntity<C> getPageResponse(C page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.size()));
        return ResponseEntity.status(OK).headers(headers).body(page);
    }
}
