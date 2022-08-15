package coordinate.dto;

import lombok.Data;

/**
 * @author ZhengChangBing
 * @Date 2022/8/12 18:39
 * @Description
 */
@Data
public class Beacon {

    private String beaconId;

    private String mapId;

    private String positionX;

    private String positionY;

    private String positionZ;

    private String category;

    private String code;

    private String description;

    private String creatorId;

    private String updaterId;

    private String status;

    private String createTime;

    private String updateTime;

}
