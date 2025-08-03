package rklab.utility.services;

public interface AwsConfiguration {

    String getEndpoint();

    String getRegion();

    String getBucket();

    String getAccessKey();

    String getSecretKey();

    boolean forcePathStyleUrl();

}
