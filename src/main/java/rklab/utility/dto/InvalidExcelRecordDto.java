package rklab.utility.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class InvalidExcelRecordDto {
    private int row;
    private int col;
    private String errorMessage;
}
