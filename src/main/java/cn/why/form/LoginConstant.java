package cn.why.form;

/**
 * form相关常量
 * Created by why on 2016/5/4.
 */
public class LoginConstant {

    /**
     * TODO:填写相关常量信息
     */
    /**
     * 用于区分不同的key
     */
    public static final String KEY_NAME_BEIJING = "key_name_beijing";

    public static final String KEY_NAME_NANJING = "key_name_nanjing";

    //登陆请求的url
    public static final String LOGIN_SUBMIT_URL = "http://sbsq.saic.gov.cn:9080/tmoas/wssqsy_getlogin.xhtml";

    //登陆后的请求
    public static final String LOGIN_SUCESS_URL = "http://sbsq.saic.gov.cn:9080/tmoas/welcome.jsp";

    //表单提交请求
    public static final String FROM_SUBMIT_URL = "http://sbsq.saic.gov.cn:9080/tmoas/sbzcsq_getPreview.xhtml";

    //表单提交后的请求
    public static final String FORM_SUCCESS_URL = "";

    public static final String BEIJING_LOGIN_SIGN_CERT = "MIIDmTCCAwKgAwIBAgIIIBYCKQACYzgwDQYJKoZIhvcNAQEFBQAwgaAxDTALBgNVBAYeBE4tVv0xDTALBgNVBAgeBF" +
            "MXTqwxDTALBgNVBAceBFMXTqwxIzAhBgNVBAoeGlMXTqxeAol/V85TOk4Jkcxss04cje9Ra1P3MSUwIwYDVQQLHhxW/Vu2XeVVRohMZT97oXQGYDtcQABDAEFOLV" +
            "/DMSUwIwYDVQQDHhxW/Vu2XeVVRohMZT97oXQGYDtcQABDAEFOLV/DMB4XDTE2MDIyOTA2MzIzNVoXDTIxMDMwMTA2MzIzNVowggEBMQswCQYDVQQGEwJD" +
            "TjEPMA0GA1UECAwG5YyX5LqsMQ8wDQYDVQQHDAbljJfkuqwxIzAhBgNVBAoMGuWbveWutuW3peWVhuaAu+WxgENB5Lit5b+DMUgwRgYDVQQLDD/ljJfkuqzluILmtbf" +
            "mt4DljLrljJflm5vnjq/opb/ot6855Y+3OOWxgjgxNOWupC0wNDHlj7fku7vpuL/mu6gxEDAOBgNVBAMMB2Z1eW91QkoxFDASBgNVBAMMCzE4NjAwNzc3OTQyMTkwN" +
            "wYDVQQDDDDmibblj4vvvIjljJfkuqzvvInnn6Xor4bkuqfmnYPku6PnkIbmnInpmZDlhazlj7gwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAIXLcL/7Gad1gidrkWhT8" +
            "ki4JnZcvKYEa0pOgwfh5aLo6yjdbHfTQ39LBi+hwbKStAbYQoF+ZJv8hhNwR86L5mLz7m0yD1LnODvU7sxHvWk6HcX9T0scHM7XN8UtC+1mNlsQZEhaX2xdxtAqahp4Ta" +
            "r9k789cN6YhwzAWBqpzGg1AgMBAAGjeDB2MB0GA1UdDgQWBBTTVZuVRjuwWaEjMWqnZYRGmEEMrzAfBgNVHSMEGDAWgBSN0aOs2h/snKACqkJ5Zhr+4xL4zTA" +
            "RBgUqVhUBAQQIMTAwMDcxNzYwFAYFKlYVAQIEC3NhaWMuZ292LmNuMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQUFAAOBgQCrhAUaf3s8eguFIja2BYpEBPHjgF" +
            "K2VV0V+MNIujjDvJxHA5Qct9qK" +
            "PuDFgglx0+E2CwkuWPCjgHSN0LnJcYq21tXU5JL5ykcCDpgcCR9L14xPuLBgyEWMVbfWeC78bkfzjtXuW+o7kAamSadmr6QBvTdbgNQqAm/zVVgDPyZvdg==";

    public static final String BEIJING_LOGIN_SIGN_DATA = "2/rZeIDbnC3DP5vHqvxix0RZxuESFVqVZZjxlbLiuk4T9brkUi9qjL4RuSeM8vvVGgA2beknms9C7EwrMZHS5uhyGdONep8Zkvc" +
            "yKmiXlNkVK8NNS1F9IY1sr6yyYpCSkaBnOw4poi8/yqsivOB2olBT7KHV0JHax+OFpZCNuyY=";
    public static final String BEIJING_LOGIN_RANDOM = "123456";
    public static final String BEIJING_LOGIN_VALID_DATE= "20210301";
    public static final String BEIJING_LOGIN_START_VALID_DATE= "20160229";
    public static final String BEIJING_LOGIN_CERT_INFO = "<certinfo><version>3</version><serialnum>2016022900026338</serialnum><algorithm>SHA1RSA</algorithm><issuer>" +
            "<dn_c>中国</dn_c><dn_s>北京</dn_s><dn_l>北京</dn_l><dn_o>北京市西城区三里河东路八号</dn_o><dn_ou>国家工商行政管理总局CA中心</dn_ou><dn_cn>国家工商行政管理总局CA中心</dn_cn>" +
            "</issuer><validity><notbefore>2016年02月29日</notbefore><notafter>2021年03月01日</notafter></validity><subject><dn_c>CN</dn_c><dn_s>北京</dn_s>" +
            "<dn_l>北京</dn_l><dn_o>国家工商总局CA中心</dn_o><dn_ou>北京市海淀区北四环西路9号8层814室-041号任鸿滨</dn_ou><dn_cn>fuyouBJ</dn_cn>" +
            "<dn_cn>18600777942</dn_cn><dn_cn>扶友（北京）知识产权代理有限公司</dn_cn></subject>" +
            "<publickey>3081890281810085cb70bffb19a77582276b916853f248b826765cbca6046b4a4e8307e1e5a2e8eb28dd6c77d3437f4b06" +
            "2fa1c1b292b406d842817e649bfc86137047ce8be662f3ee6d320f52e7383bd4eecc47bd693a1dc5fd4f4b1c1cced737c52d0bed66365b1064485a5" +
            "f6c5dc6d02a6a1a784daafd93bf3d70de98870cc0581aa9cc68350203010001</publickey><extensions><extension><ext_oid>主体密钥标识符</ext_oid>" +
            "<ext_value>0414d3559b95463bb059a123316aa765844698410caf</ext_value></extension><extension><ext_oid>权威密钥标识符</ext_oid><ext_value>301680148dd1a3acda1fec9ca002aa4279661afee312f8cd</ext_value>" +
            "</extension><extension><ext_oid>2a56150101</ext_oid><ext_value>3130303037313736</ext_value></extension><extension><ext_oid>2a56150102</ext_oid><ext_value>736169632e676f762e636e</ext_value>" +
            "</extension><extension><ext_oid>密钥用途</ext_oid><ext_value>030206c0</ext_value></extension></extensions></certinfo>";
    public static final String BEIJING_LOGIN_USERNAME= "fuyouBJ";
    public static final String BEIJING_LOGIN_NAME= "扶友（北京）知识产权代理有限公司";
    public static final String BEIJING_LOGIN_PIN_LOGIN= "key";
    public static final String BEIJING_LOGIN_KEY = "123456";


    /**
     * 北京卓唐知识产权代理有限公司相关key
     */
    public static final String NANJING_LOGIN_SIGN_CERT = "MIIDkTCCAvqgAwIBAgIIIBYCJgACYwgwDQYJKoZIhvcNAQEFBQAwgaAxDTALBgNVBAYeBE4tVv0xDTALBgNVBAgeBFMXTqwxDTALBgNVBAceBFMXTqwxIzAhBgNVBAoeGlMXTqxeAol/V85TOk4Jkcxss04cje9Ra1P3MSUwIwYDVQQLHhxW/Vu2XeVVRohMZT97oXQGYDtcQABDAEFOLV/DMSUwIwYDVQQDHhxW/Vu2XeVVRohMZT97oXQGYDtcQABDAEFOLV/DMB4XDTE2MDIyNjA3MDMwN1oXDTIxMDIyNjA3MDMwN1owgfoxCzAJBgNVBAYTAkNOMQ8wDQYDVQQIDAbljJfkuqwxDzANBgNVBAcMBuWMl+S6rDEjMCEGA1UECgwa5Zu95a625bel5ZWG5oC75bGAQ0HkuK3lv4MxPjA8BgNVBAsMNeWMl+S6rOW4gua1t+a3gOWMuua4heays+S4ieihlzcy5Y+3MjPlj7fmpbwy5bGCMjgw5a6kMRgwFgYDVQQDDA9ianpodW90YW5nXzIwMTYxFTATBgNVBAMMDDAxMC01OTg2NDY4MDEzMDEGA1UEAwwq5YyX5Lqs5Y2T5ZSQ55+l6K+G5Lqn5p2D5Luj55CG5pyJ6ZmQ5YWs5Y+4MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAfoFLTw91uXMBqEdYZ1vdRWUF1ak46PMqyQcZpVbVLzWz/jD1SZ6LXCMLqQnZAlRQygXxaoDhwTYurYcndPQp4v45kyGI5mWOx5dAQRdLy8BjRhhdSC2o3mVYxxZ8BXMW6EETNOmzFkP1xVnJBAxkeyOQ3ibf28EarWJfCw0cRwIDAQABo3gwdjAdBgNVHQ4EFgQU5IhZS0GNO3RRV0bs4pzuZY1zaEgwHwYDVR0jBBgwFoAUjdGjrNof7JygAqpCeWYa/uMS+M0wEQYFKlYVAQEECDEwMDExMTQ4MBQGBSpWFQECBAtzYWljLmdvdi5jbjALBgNVHQ8EBAMCBsAwDQYJKoZIhvcNAQEFBQADgYEAZkt6vRNdbSI5Jm0wvoCJR22FD8jHbk056KdTZbWH5gk37mpmCq2rJqjION0Mc+kXxkvZlZRKiEB+dP8nt2S6ykBNDUllqtkMnSNLGpNUwLdXoEOhB2sdEHgvZqC2NMdwt/mnJjMQr4bkx8QwO9s27WvhW+eKwq+gm1ZL2HODxuY=";
    public static final String NANJING_LOGIN_SIGN_DATA = "E3eTk2fe5SQTskqUFkPRxoue7indXy+qfhjzeABxvoQpyoYORyHBpz3lVRcSm99FHnCRA/lJmeEWbE8YdEoAcm4MF+1vThEHY91a0hFKx5flgDEN3F7NnJSXk5C5Qw2UWBA6LNMIMZNW3RIHJ2oBaNRiLC+affJWh1RDAtl2QbE=";
    public static final String NANJING_LOGIN_RANDOM = "123456";
    public static final String NANJING_LOGIN_VALID_DATE= "20210226";
    public static final String NANJING_LOGIN_START_VALID_DATE= "20160226";
    public static final String NANJING_LOGIN_CERT_INFO = "<certinfo><version>3</version><serialnum>2016022600026308</serialnum><algorithm>SHA1RSA</algorithm><issuer><dn_c>中国</dn_c><dn_s>北京</dn_s><dn_l>北京</dn_l><dn_o>北京市西城区三里河东路八号</dn_o><dn_ou>国家工商行政管理总局CA中心</dn_ou><dn_cn>国家工商行政管理总局CA中心</dn_cn></issuer><validity><notbefore>2016年02月26日</notbefore><notafter>2021年02月26日</notafter></validity><subject><dn_c>CN</dn_c><dn_s>北京</dn_s><dn_l>北京</dn_l><dn_o>国家工商总局CA中心</dn_o><dn_ou>北京市海淀区清河三街72号23号楼2层280室</dn_ou><dn_cn>bjzhuotang_2016</dn_cn><dn_cn>010-59864680</dn_cn><dn_cn>北京卓唐知识产权代理有限公司</dn_cn></subject><publickey>30818902818100c07e814b4f0f75b97301a84758675bdd456505d5a938e8f32ac90719a556d52f35b3fe30f5499e8b5c230ba909d9025450ca05f16a80e1c1362ead872774f429e2fe39932188e6658ec7974041174bcbc06346185d482da8de6558c7167c057316e8411334e9b31643f5c559c9040c647b2390de26dfdbc11aad625f0b0d1c470203010001</publickey><extensions><extension><ext_oid>主体密钥标识符</ext_oid><ext_value>0414e488594b418d3b74515746ece29cee658d736848</ext_value></extension><extension><ext_oid>权威密钥标识符</ext_oid><ext_value>301680148dd1a3acda1fec9ca002aa4279661afee312f8cd</ext_value></extension><extension><ext_oid>2a56150101</ext_oid><ext_value>3130303131313438</ext_value></extension><extension><ext_oid>2a56150102</ext_oid><ext_value>736169632e676f762e636e</ext_value></extension><extension><ext_oid>密钥用途</ext_oid><ext_value>030206c0</ext_value></extension></extensions></certinfo>";
    public static final String NANJING_LOGIN_USERNAME= "bjzhuotang_2016";
    public static final String NANJING_LOGIN_NAME= "北京卓唐知识产权代理有限公司";
    public static final String NANJING_LOGIN_PIN_LOGIN= "key";
    public static final String NANJING_LOGIN_KEY = "123456";


}
