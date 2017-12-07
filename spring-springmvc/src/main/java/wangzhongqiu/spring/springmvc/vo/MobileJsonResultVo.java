package wangzhongqiu.spring.springmvc.vo;

import com.hoomsun.common.Message;
import com.hoomsun.framework.common.CommonRuntimeException;
import com.hoomsun.framework.service.CallbackResult;
import com.hoomsun.framework.service.util.CommonRuntimeExceptionFactory;
import com.hoomsun.mobile.enums.RequestStatusEnum;
import com.hoomsun.util.MappingUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MobileJsonResultVo extends JsonResultVo {
    /**
     * 构建一个成功结果的对象
     * 
     * @return
     */
    public static MobileJsonResultVo buildSuccessVo(String message) {
        MobileJsonResultVo vo = new MobileJsonResultVo();
        vo.setStatus(SUCCESS);
        vo.setMessage(message);
        return vo;
    }
    public static MobileJsonResultVo buildSuccessVo(int status,CallbackResult callbackResult)
    {
        MobileJsonResultVo result=buildSuccessVo(status);
//        result.setBusinessData(callbackResult.getBusinessObject());
        result.addData("content",callbackResult.getBusinessObject());
        return  result;
    }
    public static MobileJsonResultVo buildSuccessVo(int status) {
        MobileJsonResultVo vo = new MobileJsonResultVo();
        vo.setStatus(SUCCESS);
        vo.setMessage(Message.resources.getMessage(status + "", null, Locale.getDefault()));
        return vo;
    }

    /**
     * 构建一个成功结果的对象
     * 
     * @return
     */
    public static MobileJsonResultVo buildSuccessVo() {
        return buildSuccessVo("success");
    }
    
    /**
     * 根据Service层返回的数据模型构造返回JSON对象
     * @param result
     * @return
     */
    public static MobileJsonResultVo buildSuccessVoFromResult(CallbackResult result) {
        MobileJsonResultVo json = MobileJsonResultVo.buildSuccessVo();
        json = wrapSuccessVo(json,result);
        return json;
    }
    
    /**
     * 根据result构造json
     * @param json
     * @param result
     * @return
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public static MobileJsonResultVo wrapSuccessVo(MobileJsonResultVo json, CallbackResult result) {
        
        Map<String,Object> map = (Map<String,Object>) result.getBusinessObject();
//      Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
//      while (iterator.hasNext()) {
//           Map.Entry<String,Object> entry = iterator.next();
//           json.addData(entry.getKey().toString(), entry.getValue());
//      }
        json.setData(map);
        return json;
    }

    /**
     * 构建一个失败结果的对象
     * 
     * @param status
     * @param message
     * @return
     */
    public static MobileJsonResultVo buildErrorVo(int status, String message) {
        MobileJsonResultVo vo = new MobileJsonResultVo();
        vo.setStatus(status);
        vo.setMessage(message);
        return vo;
    }

    /**
     * 构建一个失败结果的对象
     *
     * @param e
     * @return
     */
    public static MobileJsonResultVo buildErrorVo(CommonRuntimeException e) {
        MobileJsonResultVo vo = new MobileJsonResultVo();
        vo.setStatus(e.getErrorCode());
        vo.setMessage(e.getMessage());
        return vo;
    }

    /**
     * 构建一个失败结果的对象
     * 
     * @param status
     * @return
     */
    public static MobileJsonResultVo buildErrorVo(int status) {
        MobileJsonResultVo vo = new MobileJsonResultVo();
        vo.setStatus(status);
        vo.setMessage(Message.resources.getMessage(status + "", null, Locale.getDefault()));
        return vo;
    }

    /**
     * 构建一个失败结果的对象
     * 
     * @param status
     * @param args
     *            字符串格式化占位符对应的参数
     * @return
     */
    public static MobileJsonResultVo buildErrorVoFormat(int status, Object... args) {
        MobileJsonResultVo vo = new MobileJsonResultVo();
        vo.setStatus(status);
        vo.setMessage(String.format(Message.resources.getMessage(status + "", null, Locale.getDefault()), args));
        return vo;
    }

    /**
     * 构建一个失败结果的对象
     * 
     * @param message
     * @return
     */
    public static MobileJsonResultVo buildErrorVo(String message) {
        return buildErrorVo(ERROR, message);
    }

    /**
     * @Title: buildErrorVoFromResult 
     * @Description: 根据CallbackResult构建异常对象
     * @param result
     * @return 
     * @return MobileJsonResultVo
     * @throws
     */
    public static MobileJsonResultVo buildErrorVoFromResult(CallbackResult result){
        return buildErrorVo((CommonRuntimeException) result.getThrowable());
    }
    
    @Override
    public MobileJsonResultVo addData(String key, Object value) {
        data.put(key, value);
        return this;
    }

    /**
     * 从Servce返回的ModleMap里构造JSON节点(字符串类型)
     * @param key
     * @param result
     * @return
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public MobileJsonResultVo addModelData(String key, CallbackResult result) {
        Map<String,Object> modelMap = (Map<String, Object>) result.getBusinessObject();
        data.put(key, modelMap.get(key));
        return this;
    }
    
    /**
     * 从Servce返回的ModleMap里构造JSON节点(字符串类型)
     * @param key
     * @param modelMap
     * @return
     * 
     * 
     */
//    public MobileJsonResultVo addModelData(String key, Map<String,Object> modelMap) {
//        data.put(key, modelMap.get(key));
//        return this;
//    }
    

    /**
     * 增加一个对象
     * 
     * @param key
     * @param name
     * @param version
     * @param value
     * @return
     */
    public MobileJsonResultVo addObjectData(String key, String name, String version, Object value) {
        data.put(key, MappingUtil.convertObject(name, version, value));
        return this;
    }
    
    /**
     * 从Servce返回的ModleMap里构造JSON节点(对象类型)
     * @param key
     * @param modelMap
     * @return
     * 
     * 
     */
//    public MobileJsonResultVo addModelObjectData(String key, String name, String version, Map<?, ?> modelMap) {
//        data.put(key, MappingUtil.convertObject(name, version, modelMap.get(key)));
//        return this;
//    }
    
    /**
     * 从Servce返回的ModleMap里构造JSON节点(对象类型)
     * @param key
     * @return
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public MobileJsonResultVo addModelObjectData(String key, String name, String version,CallbackResult result) {
        Map<String,Object> modelMap = (Map<String,Object>) result.getBusinessObject();
        data.put(key, MappingUtil.convertObject(name, version, modelMap.get(key)));
        return this;
    }

    /**
     * 增加一个列表
     * 
     * @param key
     * @param name
     * @param version
     * @param list
     * @return
     */
    public MobileJsonResultVo addListData(String key, String name, String version, List<?> list) {
        data.put(key, MappingUtil.convertList(name, version, list));
        return this;
    }
    
    /**
     * 从Servce返回的ModleMap里构造JSON节点(List类型)
     * @param key
     * @param modelMap
     * @return
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public MobileJsonResultVo addModelListData(String key, String name, String version, CallbackResult result) {
        Map<String,Object> modelMap = (Map<String,Object>) result.getBusinessObject();
        data.put(key, MappingUtil.convertList(name, version, (List<?>)modelMap.get(key)));
        return this;
    }

    public MobileJsonResultVo addModelListData(String nodeName, String key, String name, String version, CallbackResult result) {
        Map<String,Object> modelMap = (Map<String,Object>) result.getBusinessObject();
        data.put(nodeName, MappingUtil.convertList(name, version, (List<?>)modelMap.get(key)));
        return this;
    }

    /**
     * 从Servce返回的ModleMap里构造JSON节点(List类型)
     * @param key
     * @param modelMap
     * @return
     * 
     * 
     */
//    public MobileJsonResultVo addModelListData(String key, String name, String version, Map<?, ?> modelMap) {
//        data.put(key, MappingUtil.convertList(name, version, (List<?>)modelMap.get(key)));
//        return this;
//    }

    @Override
    public int getStatus() {
        return super.getStatus();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public Map<String, Object> getData() {
        return super.getData();
    }

    public static MobileJsonResultVo buildErrorVoFromException(Throwable e) {

        if(e instanceof CommonRuntimeException){
            return buildErrorVo((CommonRuntimeException) e);
        }else{
            return buildErrorVo(CommonRuntimeExceptionFactory.getException(RequestStatusEnum.SERVER_ERROR));
        }
    }

}
