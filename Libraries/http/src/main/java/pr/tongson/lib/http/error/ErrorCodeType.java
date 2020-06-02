package pr.tongson.lib.http.error;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/23
 * @Version
 * @Since
 * @Description
 */
public class ErrorCodeType {

    public enum HttpError implements IErrorCodeType {
        //请求返回错误码
        UNAUTHORIZED(401, "当前请求需要用户验证"),// 未经授权
        FORBIDDEN(403, "服务器已经理解请求，但是拒绝执行它"), // 禁止
        NOT_FOUND(404, "服务器异常，请稍后再试"), // 404
        REQUEST_TIMEOUT(408, "请求超时"), // 超时
        INTERNAL_SERVER_ERROR(500, "作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS）收到响应"), // 内部服务器错误
        BAD_GATEWAY(502, "作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应"),// 错误的网关
        SERVICE_UNAVAILABLE(503, "由于临时的服务器维护或者过载，服务器当前无法处理请求"),// 暂停服务
        GATEWAY_TIMEOUT(504, "作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS）收到响应"), // 网关超时
        //(, ""),//
        ;

        private int code;
        private String msg;

        HttpError(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return msg;
        }
    }

    public enum LocalError implements IErrorCodeType {
        //响应错误码
        SUCCESS(0, "成功"),// 成功
        UNKNOWN(1001, "未知错误"), // 未知错误
        PARSE_ERROR(1002, "解析错误"), // 解析错误
        EMPTY_BEAN(1003, "解析对象为空"),// 解析对象为空
        //(, ""),//
        ;

        private int code;
        private String msg;

        LocalError(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return msg;
        }
    }

    public static int getCode(HttpError error) {
        return error.getCode();
    }

    public static String getMsg(HttpError error) {
        return error.getDescription();
    }

    /**
     * 根据编码获取对应描述。
     *
     * @param code 码
     * @return 信息。
     */
    public static String getHttpErrorDescriptionByCode(int code) {
        for (HttpError s : HttpError.values()) {
            if (s.getCode() == code) {
                return s.getDescription();
            }
        }
        return null;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 码。
     * @return 枚举。
     */
    public static HttpError getHttpErrorByCode(int code) {
        for (HttpError value : HttpError.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        return null;
    }

    /**
     * 枚举是否包含此code
     *
     * @param code 码。
     * @return 结果
     */
    public static Boolean contains(int code) {
        for (HttpError value : HttpError.values()) {
            if (code == value.getCode()) {
                return true;
            }
        }
        return false;
    }
}
