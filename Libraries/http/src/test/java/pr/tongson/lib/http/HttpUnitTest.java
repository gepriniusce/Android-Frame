package pr.tongson.lib.http;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/4/30
 * @Version
 * @Since
 * @Description
 */
public class HttpUnitTest {
    /**
     * 同步 ResponseBody
     */
    @Test
    public void test() {
        Call call = BaseApiManager.getInstance().getApiService().getAritrilList(1);
        try {
            Response response = call.execute();
            System.out.println("onSuccess:" + response.isSuccessful());
            System.out.println("code:" + response.code());
            if (response.isSuccessful()) {
                ResponseBody responseBody = (ResponseBody) response.body();
                System.out.println("responseBody:" + responseBody.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步 TestResultBean
     */
    @Test
    public void testJson() {
        System.out.println("testJson");
        Call<TestResultBean> call = BaseApiManager.getInstance().getApiService().getAritrilListGson(1);
        try {
            Response<TestResultBean> response = call.execute();
            System.out.println("onSuccess:" + response.isSuccessful());
            System.out.println("code:" + response.code());
            if (response.isSuccessful()) {
                TestResultBean resultBean = response.body();
                System.out.println("resultBean:" + resultBean);
                System.out.println("resultBean:" + resultBean.getData().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步
     */
    @Test
    public void test3() {
        System.out.println("test enqueue");
        Call<ResponseBody> call = BaseApiManager.getInstance().getApiService().getAritrilList(1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("onSuccess:" + response.isSuccessful());
                System.out.println("code:" + response.code());
                try {
                    if (response.isSuccessful()) {
                        ResponseBody responseBody = (ResponseBody) response.body();
                        System.out.println("responseBody:" + responseBody.string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
