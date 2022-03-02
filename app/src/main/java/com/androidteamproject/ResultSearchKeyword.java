package com.androidteamproject;

import java.util.List;

public class ResultSearchKeyword {
    PlaceMeta metadata;
    List<Place> documents;

    public PlaceMeta getMetadata() {
        return metadata;
    }

    public void setMetadata(PlaceMeta metadata) {
        this.metadata = metadata;
    }

    public List<Place> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Place> documents) {
        this.documents = documents;
    }
}

class PlaceMeta{
    int total_count;
    int pageable_count;
    Boolean is_end;
    RegionInfo same_name;
}
class RegionInfo {
    List<String> region;
    String keyword;
    String selected_region;
}
class Place {
    String id;
    String place_name;
    String category_name;
    String category_group_code;
    String category_group_name;
    String phone;
    String address_name;
    String road_address_name;
    String x;
    String y;
    String place_url;
    String distanc;
}


