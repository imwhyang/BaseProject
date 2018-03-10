package com.example.wayne.base.basehttp;

import com.example.wayne.base.basebean.BaseBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Author:Wayne
 * Time:2018/3/8 17:41
 * Description: This is RequstInterface
 */

public interface RequstInterface {
    String baseUrl = "http://test.shiangou.com.cn/";

    // @GET注解的作用:采用Get方法发送网络请求
    @GET("app/index.php")
    Call<BaseBean<String>> getCall();


    @HTTP(method = "GET", path = "app/index.php?curpage={id}", hasBody = false)
    Call<BaseBean<String>> getHomeMsg1(@Path("id") int id);

//    @FormUrlEncoded
//    作用：表示发送form-encoded的数据
    @POST("app/index.php")
    @FormUrlEncoded
    Call<BaseBean<String>> getHomeMsg2(@Field("curpage") String name);


//    @Multipart
//    作用：表示发送form-encoded的数据（适用于 有文件 上传的场景）

    /**
     * 表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
     * <code>Field("username")</code> 表示将后面的 <code>String name</code> 中name的取值作为 username 的值
     */
    @POST("/form")
    @FormUrlEncoded
    Call<BaseBean<String>> testFormUrlEncoded1(@Field("username") String name, @Field("age") int age);
//使用service.testFormUrlEncoded1("Carson", 24);

    /**
     * {@link Part} 后面支持三种类型，{@link RequestBody}、{@link okhttp3.MultipartBody.Part} 、任意类型
     * 除 {@link okhttp3.MultipartBody.Part} 以外，其它类型都必须带上表单字段({@link okhttp3.MultipartBody.Part}
     * 中已经包含了表单字段的信息)，
     */
    @POST("/form")
    @Multipart
    Call<ResponseBody> testFileUpload1(@Part("name") RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);
    //  @Multipart
//    RequestBody name = RequestBody.create(textType, "Carson");
//    RequestBody age = RequestBody.create(textType, "24");
//
//    MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.txt", file);
//    service.testFileUpload1(name, age, filePart);
}
