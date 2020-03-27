package servlet.helper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mengyuantan
 */
public class IpUtils {
    public String getVisitorIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ipAddress;
        if (realIp == null) {
            if (forwarded == null) {
                ipAddress = remoteAddr;
            } else {
                ipAddress = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ipAddress = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ipAddress = realIp + "/" + forwarded;
            }
        }
        return ipAddress;
    }
}
