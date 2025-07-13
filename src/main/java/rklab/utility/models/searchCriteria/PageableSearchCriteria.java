package rklab.utility.models.searchCriteria;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort.Direction;


@Data
@NoArgsConstructor
public class PageableSearchCriteria {

    private int perPage = 20;
    private int pageNo = 1;
    private String sortBy = "createdAt";
    private Direction sortDirection = Direction.ASC;

    @Builder
    public PageableSearchCriteria(
            final int perPage,
            final int pageNo,
            final String sortBy,
            final Direction sortDirection
    ){
        this.perPage = perPage;
        this.pageNo = pageNo;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

}
