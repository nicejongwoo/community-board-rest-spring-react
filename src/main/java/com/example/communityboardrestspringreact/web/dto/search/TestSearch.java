package com.example.communityboardrestspringreact.web.dto.search;

import com.example.communityboardrestspringreact.web.dto.search.BaseSearch;
import lombok.Data;

@Data
public class TestSearch extends BaseSearch {

    private String deleted;
    private String notice;

}
