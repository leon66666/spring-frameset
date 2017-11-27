package wangzhongqiu.spring.redis.constant;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;
import zhongqiu.javautils.StringUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 存管接口的基本配置
 *
 * luzongwei
 */
public class SupervisionConfig {
    private static Logger logger = Logger.getLogger(SupervisionConfig.class);

    /**
     * 商户编码, 红小宝写死0007
     */
    public static final String SECU_NO = "0007";

    // p2p商户的配置
    public static String BASE_CERT_PATH = SupervisionConfig.class.getResource("/").getPath() + "/cert/";

    /**
     * 交易银行测试环境地址
     */
    public static String TRADE_BANK_URL;

    /**
     * 返回路径, hoomsun-api-escrow
     */
    public static String RETURN_URL;

    /**
     * 异步通知路径, 用于返回成功或失败, hoomsun-api-escrow
     */
    public static String NOTIFY_URL;

    /**
     * 异常测试配置, 测试环境true 正式环境false
     */
    public static boolean ESCROW_DEV_MODE = false;

    /**
     * 同步请求后缀.json
     * APP请求后缀.json
     * 异步请求后缀.html
     */
    public static enum SyncOrAsyncRequest {
        SYNC() { // 既不是主账户也不是子账户
            @Override
            public String toString() {
                return ".json";
            }
        },
        ASYNC() { // 主账户PointType
            @Override
            public String toString() {
                return ".html";
            }
        };

        @Override
        public abstract String toString();
    }

    // 商户私钥证书
    public static final String CUST_SM2_PATH = BASE_CERT_PATH + SECU_NO + ".sm2";
    // 商户私钥证书密码
    public static String CUST_SM2_PASS;
    // 商户公钥证书
    public static final String CUST_CER_PATH = BASE_CERT_PATH + SECU_NO + ".cer";
    // 存管公钥证书
    public static String ESCROW_CER_FILENAME = "tbank.cer";

    public static String ESCROW_CER_PATH = BASE_CERT_PATH + ESCROW_CER_FILENAME;

    public static String INTERFACE_VERSION;

    public static final String SIGN_NAME = "sign";
    public static final String BODY_NAME = "body";

    public static final String CONTEXT_NAME = "context";

    /**
     * p2p平台提交的参数名称
     */
    public static String PARAM_CUST_NO = "secuNo";
    public static String PARAM_CONTEXT = "context";
    public static String PARAM_SIGN = "sign";
    public static String PARAM_BODY = "body";

    /*
     * 返回界面的参数名称
     */
    public static final String PARAM_RET_CODE = "retCode";
    public static final String PARAM_RET_MSG = "retMsg";
    public static final String PARAM_P2P_PLAT_NAME = "p2pPlatName";
    public static final String PARAM_COMPANY_NAME = "companyName";

    public static final String RET_SUCCESS = "0000";
    public static final String RET_FAIL = "9999";

    public static final String FILE_SPLIT = "#";
    public static final String FILE_UNION = "-";

    /**
     * 对帐文件目录
     */
    public static String BASE_FILE_PATH = null;

    static {
        try {
            init();
        } catch (ConfigurationException e) {
			e.printStackTrace();
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * 初始化配置文件并加载配置
     * @throws IOException
     * @throws FileNotFoundException
     */
	public static void init() throws ConfigurationException, IOException {
        InputStream fis = SupervisionConfig.class.getClassLoader().getResourceAsStream("api_escrow.properties");
        Properties prop = new Properties();
        prop.load(fis);
        RETURN_URL = prop.getProperty("RETURN_URL");
		NOTIFY_URL = prop.getProperty("NOTIFY_URL");
		TRADE_BANK_URL = prop.getProperty("TRADE_BANK_URL");
		CUST_SM2_PASS = prop.getProperty("CUST_SM2_PASS");
		INTERFACE_VERSION = prop.getProperty("INTERFACE_VERSION");
        BASE_FILE_PATH = prop.getProperty("BASE_FILE_PATH");
        if (StringUtil.isNotEmpty(prop.getProperty("ESCROW_CER_FILENAME"))) {
            ESCROW_CER_FILENAME = prop.getProperty("ESCROW_CER_FILENAME");
            ESCROW_CER_PATH = BASE_CERT_PATH + ESCROW_CER_FILENAME;
        }
        String exceptionTest = prop.getProperty("ESCROW_DEV_MODE");
        if(exceptionTest!=null && ("true").equalsIgnoreCase(exceptionTest)){
            ESCROW_DEV_MODE = true;
        }else{
            ESCROW_DEV_MODE = false;
        }

		logger.info("------------- api_escrow.properties load success ----------------");
	}

}
