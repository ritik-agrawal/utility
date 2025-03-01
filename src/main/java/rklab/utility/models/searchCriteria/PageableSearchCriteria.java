package rklab.utility.models.searchCriteria;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort.Direction;

import static rklab.utility.constants.GlobalConstants.CREATED_AT;


@Data
@NoArgsConstructor
public class PageableSearchCriteria {

    private int perPage = 99999;
    private int pageNo = 1;
    private String sortBy = CREATED_AT;
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
