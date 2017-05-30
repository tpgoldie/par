package com.tpg.par.web.components;

import com.tpg.par.domain.SearchType;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class SearchTypeCheckBox {
    private final SearchType searchType;

    public SearchTypeCheckBox(SearchType searchType) {
        this.searchType = searchType;
    }

    public String getLabel() { return searchType.name(); }

    public String getName() { return searchType.name().toLowerCase(); }

    public int getOrdinal() { return searchType.ordinal(); }

    public SearchType getSearchType() { return searchType; }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof SearchTypeCheckBox)) { return false; }

        SearchTypeCheckBox that = (SearchTypeCheckBox) obj;

        return new EqualsBuilder()
            .append(this.searchType, that.searchType)
            .isEquals();
    }

    @Override
    public String toString() { return searchType.name(); }
}
