package rklab.utility.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import rklab.utility.services.AwsConfiguration;

import java.time.Duration;

@Getter
@Setter
public class AwsPreSignedUrlRequest extends AwsRequest{

    /**
     * Duration for which the url is valid
     */
    private Duration duration;

    @Builder(builderMethodName = "awsPreSignedUrlRequestBuilder")
    public AwsPreSignedUrlRequest(AwsConfiguration config, String key, Duration duration) {
        super(config, key);
        this.duration = duration;
    }
}
