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
    private InputStream inputStream;

    /**
     * Object size in bytes
     */
    private long contentLength;

    /**
     * Object MIME type
     */
    private String contentType;

    @Builder(builderMethodName = "awsUploadRequestBuilder")
    public AwsUploadRequest(
            AwsConfiguration config,
            String key,
            InputStream inputStream,
            long contentLength,
            String contentType
    ) {
        super(config, key);
        this.contentLength = contentLength;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }
}
