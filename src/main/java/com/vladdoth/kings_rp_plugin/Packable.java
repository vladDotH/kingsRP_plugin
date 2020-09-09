package com.vladdoth.kings_rp_plugin;

import org.bson.Document;

public interface Packable {
    Document packData();
    void setData(Document doc);
}
