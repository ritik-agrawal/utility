package rklab.utility.expectations;

import lombok.Data;
import rklab.utility.dto.InvalidExcelRecordDto;
import java.util.List;


@Data
public class ExcelRecordException extends RuntimeException {
    private List<InvalidExcelRecordDto> errors;

    public ExcelRecordException(String message) {
        super(message);
    }

    public ExcelRecordException(List<InvalidExcelRecordDto> errors) {
        super("Excel processing failed");
        this.errors = errors;
    }
}
