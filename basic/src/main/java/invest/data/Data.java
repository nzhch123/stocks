package invest.data;

import java.io.IOException;

public interface Data<T> {
    public  T getData() throws IOException;
}
