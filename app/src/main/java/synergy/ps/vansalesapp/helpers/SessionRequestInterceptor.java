package synergy.ps.vansalesapp.helpers;

import android.os.Build;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import synergy.ps.vansalesapp.BuildConfig;
import synergy.ps.vansalesapp.VanSalesApp;
import synergy.ps.vansalesapp.utils.DeviceUtil;

/**
 * Created by Ye3aqbi on 12/5/2016.
 */

public class SessionRequestInterceptor implements Interceptor {
    private VanSalesApp context;

    public SessionRequestInterceptor(VanSalesApp context) {
        this.setContext(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();
//        final String accessToken = getContext().getUserToken();
//        if (accessToken != null) {
//            final String accessTokenType = getContext().getUserTokenType();
//            String authHeaderValue = accessTokenType + " " + accessToken;
//            request.addHeader("Authorization", authHeaderValue);
//        }
        request.addHeader("ApplicationName", APIKeys.REQUEST_HEADER_APP_NAME);
        request.addHeader("Content-Type", APIKeys.REQUEST_HEADER_CONTENT_TYPE);
        request.addHeader("BasicAuthorization", APIKeys.REQUEST_HEADER_BASIC_AUTHORIZATION);
        request.addHeader("ApplicationVersion", BuildConfig.VERSION_NAME);
        request.addHeader("DeviceType", DeviceUtil.getDeviceName());
//        String deviceID = context.getDeviceIDForRequestHeader();
//        if(deviceID == null) {
//            deviceID = "";
//        }
//        request.addHeader("DeviceID", deviceID);

        request.addHeader("OS", "Android");
        request.addHeader("OSVersion", Build.VERSION.RELEASE);
        Response response = chain.proceed(request.build());
        return response;
    }

    public VanSalesApp getContext() {
        return context;
    }

    public void setContext(VanSalesApp context) {
        this.context = context;
    }
}