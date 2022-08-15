package coordinate.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ZhengChangBing
 * @Date 2022/8/12 18:42
 * @Description
 */
@Data
public class ExportInfo {

    @ExcelProperty(index = 0,value = "uuid")
    private String uuid;

    @ExcelProperty(index = 1,value = "major")
    private String major;

    @ExcelProperty(index = 2,value = "minor")
    private String minor;

    @ExcelProperty(index = 3,value = "X")
    private String positionX;

    @ExcelProperty(index = 4,value = "Y")
    private String positionY;
    //根据情况判断是否添加Z坐标数据
//    @ExcelProperty(index = 5,value = "Z")
//    private String positionZ;

}