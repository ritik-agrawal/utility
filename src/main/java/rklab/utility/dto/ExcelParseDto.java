package rklab.utility.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcelParseDto<T> {

    private Set<T> parsedDtoSet;
    private Set<InvalidExcelRecordDto> invalidExcelRecordDtoList;
}
