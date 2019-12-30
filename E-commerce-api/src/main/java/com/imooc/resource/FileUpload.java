package com.imooc.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:file-upload-dev.properties")
@ConfigurationProperties(prefix = "file")
public class FileUpload {

    private String imageUserFaceLocation;

    private String imageServerUrl;

    private String paymentUrl;

    private String paymentReturnUrl;

    public String getImageUserFaceLocation() {
        return imageUserFaceLocation;
    }

    public void setImageUserFaceLocation(String imageUserFaceLocation) {
        this.imageUserFaceLocation = imageUserFaceLocation;
    }

    public String getImageServerUrl() {
        return imageServerUrl;
    }

    public void setImageServerUrl(String imageServerUrl) {
        this.imageServerUrl = imageServerUrl;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getPaymentReturnUrl() {
        return paymentReturnUrl;
    }

    public void setPaymentReturnUrl(String paymentReturnUrl) {
        this.paymentReturnUrl = paymentReturnUrl;
    }
}
