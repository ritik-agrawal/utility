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

    @Override
    public String toString(){
        return String.format("[%d,%d] :: %s", this.row, this.col, this.errorMessage);
    }

}
