package rklab.utility.utilities;

import rklab.utility.annotations.ColumnNotEmpty;
import rklab.utility.dto.ExcelParseDto;
import rklab.utility.dto.InvalidExcelRecordDto;
import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class ExcelUtils {

    public static <T> ExcelParseDto<T> validateAndParseToDto(InputStream excelFile, Class<T> dtoClass, int sheetNumber)  {
        var errorList = new LinkedHashSet<InvalidExcelRecordDto>();
        var dtoSet = new LinkedHashSet<T>();
        List<Field> validatedFields = Arrays.stream(dtoClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ColumnNotEmpty.class))
                .toList();
        try(Workbook workbook = WorkbookFactory.create(excelFile)){
            Sheet sheet = workbook.getSheetAt(sheetNumber);
            for(int rowIdx = 1; rowIdx <= sheet.getLastRowNum(); rowIdx++){
                Row row = sheet.getRow(rowIdx);
                if(row == null)continue;
                var dto = dtoClass.getDeclaredConstructor().newInstance();
                boolean isValid = true;

                for(Field field : validatedFields){
                    ColumnNotEmpty annotation = field.getAnnotation(ColumnNotEmpty.class);
                    int col = annotation.column();
                    String errorMessage = annotation.message();

                    Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = getCellValueAsString(cell);
                    if(cellValue.isEmpty()){
                        errorList.add(new InvalidExcelRecordDto(rowIdx+1, col, errorMessage));
                        isValid = false;
                    }else {
                        field.setAccessible(true);
                        field.set(dto, cellValue);
                    }
                }

                if(isValid)dtoSet.add(dto);
            }

        }catch(IOException e){
            throw new RuntimeException("Failed to read Excel file", e);
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Invalid sheet number: " + sheetNumber, e);
        }catch (Exception e){
            throw new RuntimeException("Error during Excel validation", e);
        }
        return new ExcelParseDto<>(dtoSet, errorList);
    }

    private static String getCellValueAsString(Cell cell) {
        if(cell == null || cell.getCellType() == CellType.BLANK) return "";

        return switch (cell.getCellType()) {
            case NUMERIC -> {
                double numValue = cell.getNumericCellValue();
                DecimalFormat df = new DecimalFormat("#");
                df.setMaximumFractionDigits(0);
                yield df.format(numValue);
            }
            case STRING -> cell.getStringCellValue().trim();
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

}
