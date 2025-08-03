package rklab.utility.dto;

import lombok.Builder;
import lombok.Data;
import rklab.utility.services.AwsConfiguration;

@Data
@Builder
public class AwsRequest {

    private AwsConfiguration config;

    /**
     * Key is the full path of the file in s3
     */
    private String key;

}
