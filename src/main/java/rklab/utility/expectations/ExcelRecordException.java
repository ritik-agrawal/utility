package rklab.utility.expectations;

import lombok.Data;
import lombok.EqualsAndHashCode;
import rklab.utility.dto.InvalidExcelRecordDto;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class ExcelRecordException extends RuntimeException {
    private List<String> errors;

    public ExcelRecordException(String message) {
        super(message);
    }

    public ExcelRecordException(List<InvalidExcelRecordDto> errors) {
        super("Excel processing failed");
        this.errors = errors.stream()
                .map(InvalidExcelRecordDto::toString)
                .toList();
    }
}
