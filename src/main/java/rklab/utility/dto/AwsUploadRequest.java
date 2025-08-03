package rklab.utility.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import rklab.utility.services.AwsConfiguration;

import java.io.InputStream;

@Getter
@Setter
public class AwsUploadRequest extends AwsRequest{

    /**
     * Input Stream of the object to be uploaded
     */
    private byte[] content;

    private String contentType;

    @Builder(builderMethodName = "awsUploadRequestBuilder")
    public AwsUploadRequest(
            AwsConfiguration config,
            String key,
            byte[] content,
            String contentType
    ) {
        super(config, key);
        this.content = content;
        this.contentType = contentType;
    }
}
