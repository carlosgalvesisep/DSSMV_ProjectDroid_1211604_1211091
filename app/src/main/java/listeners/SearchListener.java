package listeners;

import models.SearchResponse;

public interface SearchListener {
    void fetch (SearchResponse response, String message);
    void error (String message);
}
