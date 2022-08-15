package coordinate.dto;

import lombok.Data;

/**
 * @author ZhengChangBing
 * @Date 2022/8/12 18:41
 * @Description
 */
@Data
public class Property {

    private String beaconPropertyId;

    private String beaconId;

    private String mapId;

    private String uuid;

    private String major;

    private String minor;

    private String startTime;

    private String operatorId;

    private String status;

    private String createTime;
}
