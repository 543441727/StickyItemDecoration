package com.qianmo.stickyitemdecoration.api;

import com.qianmo.stickyitemdecoration.base.BaseResponse;
import com.qianmo.stickyitemdecoration.bean.MovieBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by wangjitao on 2017/3/3 0003.
 * E-Mail：543441727@qq.com
 * 电影网API
 */

public interface MovieApiService {

    @GET("v2/list/rt/order/coming.json?ci=1&limit=12&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=xiaomi&utm_medium=android&utm_term=6.8.0&utm_content=868030022327462&net=255&dModel=MI%205&uuid=0894DE03C76F6045D55977B6D4E32B7F3C6AAB02F9CEA042987B380EC5687C43&lat=40.100673&lng=116.378619&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463704714271&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=1a0b4a9b-44ec-42fc-b110-ead68bcc2824&__skcy=sXcDKbGi20CGXQPPZvhCU3%2FkzdE%3D")
    Observable<BaseResponse<MovieBean>> getMovieDatas();
}
