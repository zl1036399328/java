
package weather;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "WeatherWebServiceHttpPost", targetNamespace = "http://WebXml.com.cn/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WeatherWebServiceHttpPost {


    /**
     * <br /><h3>查询本天气预报Web Services支持的国内外城市或地区信息</h3><p>输入参数：byProvinceName = 指定的洲或国内的省份，若为ALL或空则表示返回全部城市；返回数据：一个一维字符串数组 String()，结构为：城市名称(城市代码)。</p><br />
     * 
     * @param byProvinceName
     * @return
     *     returns weather.ArrayOfString
     */
    @WebMethod
    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    public ArrayOfString getSupportCity(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "byProvinceName")
        String byProvinceName);

    /**
     * <br /><h3>获得本天气预报Web Services支持的洲、国内外省份和城市信息</h3><p>输入参数：无； 返回数据：一个一维字符串数组 String()，内容为洲或国内省份的名称。</p><br />
     * 
     * @return
     *     returns weather.ArrayOfString
     */
    @WebMethod
    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    public ArrayOfString getSupportProvince();

    /**
     * <br><h3>获得本天气预报Web Services支持的洲、国内外省份和城市信息</h3><p>输入参数：无；返回：DataSet 。DataSet.Tables(0) 为支持的洲和国内省份数据，DataSet.Tables(1) 为支持的国内外城市或地区数据。DataSet.Tables(0).Rows(i).Item("ID") 主键对应 DataSet.Tables(1).Rows(i).Item("ZoneID") 外键。<br />Tables(0)：ID = ID主键，Zone = 支持的洲、省份；Tables(1)：ID 主键，ZoneID = 对应Tables(0)ID的外键，Area = 城市或地区，AreaCode = 城市或地区代码。</p><br />
     * 
     * @return
     *     returns weather.DataSet
     */
    @WebMethod
    @WebResult(name = "DataSet", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    public DataSet getSupportDataSet();

    /**
     * <br><h3>根据城市或地区名称查询获得未来三天内天气情况、现在的天气实况、天气和生活指数</h3><p>调用方法如下：输入参数：theCityName = 城市中文名称(国外城市可用英文)或城市代码(不输入默认为上海市)，如：上海 或 58367，如有城市名称重复请使用城市代码查询(可通过 getSupportCity 或 getSupportDataSet 获得)；返回数据： 一个一维数组 String(22)，共有23个元素。<br />String(0) 到 String(4)：省份，城市，城市代码，城市图片名称，最后更新时间。String(5) 到 String(11)：当天的 气温，概况，风向和风力，天气趋势开始图片名称(以下称：图标一)，天气趋势结束图片名称(以下称：图标二)，现在的天气实况，天气和生活指数。String(12) 到 String(16)：第二天的 气温，概况，风向和风力，图标一，图标二。String(17) 到 String(21)：第三天的 气温，概况，风向和风力，图标一，图标二。String(22) 被查询的城市或地区的介绍 <br /><a href="http://www.webxml.com.cn/images/weather.zip">下载天气图标<img src="http://www.webxml.com.cn/images/download_w.gif" border="0" align="absbottom" /></a>(包含大、中、小尺寸) <a href="http://www.webxml.com.cn/zh_cn/weather_icon.aspx" target="_blank">天气图例说明</a> <a href="http://www.webxml.com.cn/files/weather_eg.zip">调用此天气预报Web Services实例下载</a> (VB ASP.net 2.0)</p><br />
     * 
     * @param theCityName
     * @return
     *     returns weather.ArrayOfString
     */
    @WebMethod
    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    public ArrayOfString getWeatherbyCityName(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "theCityName")
        String theCityName);

    /**
     * <br><h3>根据城市或地区名称查询获得未来三天内天气情况、现在的天气实况、天气和生活指数（For商业用户）</h3><p>调用方法同 getWeatherbyCityName，输入参数：theUserID = 商业用户ID</p><br />
     * 
     * @param theCityName
     * @param theUserID
     * @return
     *     returns weather.ArrayOfString
     */
    @WebMethod
    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    public ArrayOfString getWeatherbyCityNamePro(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "theCityName")
        String theCityName,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "theUserID")
        String theUserID);

}
