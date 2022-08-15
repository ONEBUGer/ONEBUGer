package coordinate.util;

import cn.hutool.core.io.IoUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import coordinate.dto.Beacon;
import coordinate.dto.Coordinate;
import coordinate.dto.ExportInfo;
import coordinate.dto.Property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhengChangBing
 * @Date 2022/8/12 18:42
 * @Description
 */
public class CoordinateUtil {

    public static void main(String[] args) throws FileNotFoundException {
        //需要转化蓝牙坐标文件的具体位置(暂时支持数组格式数据)
        String fileName = "D:\\蓝牙坐标信息.txt";

        FileInputStream fileInputStream = new FileInputStream(fileName);
        String read = IoUtil.read(fileInputStream, "UTF-8");
        List<Coordinate> coordinateList = JSONObject.parseArray(read, Coordinate.class);

        //对导出数据进行筛选（去除null数据）
        List<Coordinate> checkCoordinateList = new ArrayList<>();
        for (Coordinate coordinate : coordinateList) {
            if (coordinate.getBeacon() != null && coordinate.getProperty() != null){
                checkCoordinateList.add(coordinate);
            }
        }

        List<ExportInfo> exportInfoList = new ArrayList<>();
        for (Coordinate coordinate : checkCoordinateList) {
            Property property = coordinate.getProperty();
            Beacon beacon = coordinate.getBeacon();
            String positionX = beacon.getPositionX();
            String positionY = beacon.getPositionY();
//            String positionZ = beacon.getPositionZ();
            if (property != null){
                String uuid = property.getUuid();
                String major = property.getMajor();
                String minor = property.getMinor();

                //导出为excel文件格式
                ExportInfo exportInfo = new ExportInfo();
                exportInfo.setPositionX(positionX);
                exportInfo.setPositionY(positionY);
//                exportInfo.setPositionZ(positionZ);
                exportInfo.setUuid(uuid);
                exportInfo.setMajor(major);
                exportInfo.setMinor(minor);
                exportInfoList.add(exportInfo);
            }
        }
        //导出excel文件位置
        String resultFileName = "D:\\easyExcel.xlsx";
        EasyExcel.write(resultFileName,ExportInfo.class).sheet("坐标信息页").doWrite(exportInfoList);
    }
}
