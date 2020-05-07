package com.grechur.login.net;


import com.grechur.login.bean.UserInfo;
import com.grechur.net.BaseResponse;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: IUserService
 * @Description: 用户相关的网络接口
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:23
 */
public interface IUserService {

    @FormUrlEncoded
    @POST("/user/login")
    Observable<BaseResponse<UserInfo>> login(@Field("username") String username, @Field("password") String password);
}
