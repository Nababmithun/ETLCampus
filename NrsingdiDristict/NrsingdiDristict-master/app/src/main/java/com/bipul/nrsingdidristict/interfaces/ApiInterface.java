package com.bipul.nrsingdidristict.interfaces;

import com.bipul.nrsingdidristict.modelAdminLoginPOST.LoginResponse;
import com.bipul.nrsingdidristict.modelForAppoinmentSubjectSavePOST.AppointmentSubjectSaveResponse;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectDeletePOST.AppointmentSubjectDeleteResponse;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectFetchAllGET.AppointmentSubjectFetchAllResponse;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectGET.AppointmentSubjecResponse;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectUpdatePOST.AppointmentSubjectUpdateResponse;
import com.bipul.nrsingdidristict.modelForComplainFechAllGET.ComplainResponse;
import com.bipul.nrsingdidristict.modelForComplainSavePOST.ComplainSaveResponse;
import com.bipul.nrsingdidristict.modelForDCMessageGET.DCMessageResponse;
import com.bipul.nrsingdidristict.modelForEmployeeGET.EmployeeResponse;
import com.bipul.nrsingdidristict.modelForPublicHearingDeletePOST.SetPublicHearingDeleteResponse;
import com.bipul.nrsingdidristict.modelForPublicHearingViewFetchGET.GetPublicHearingViewResponse;
import com.bipul.nrsingdidristict.modelForSMSSendPOST.SMSSendResponse;
import com.bipul.nrsingdidristict.modelForWorkSchedulePOST.WorkScheduleSaveResponse;
import com.bipul.nrsingdidristict.modelPublicHeadingGET.PublicHearingResponse;
import com.bipul.nrsingdidristict.modelPublicHeadingPOST.PublicHearingSaveResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("employee")
    Call<EmployeeResponse> getEmployeeResponse(@Header("app-key") String appKey);


    @GET("dc/message")
    Call<DCMessageResponse> getDCMessageResponse(@Header("app-key") String appKey);


   /* @GET("district/save")
    Call<DCMessageResponse> getDCMessageResponse(@Header("app-key") String appKey);*/

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> setUserInfoForLogin(@Header("app-key") String appKey,
                                            @Field("username") String usename,
                                            @Field("password") String password);

    @FormUrlEncoded
    @POST("public_hearing/save")
    Call<PublicHearingSaveResponse> setPublicHearing(@Header("app-key") String appKey,
                                                     @Field("name") String name,
                                                     @Field("subject") String subject,
                                                     @Field("mobile_no") String mobileNo,
                                                     @Field("address") String address,
                                                     @Field("description") String description,
                                                     @Field("user_id") int user_id);


    @GET("public_hearing/")
    Call<PublicHearingResponse> getPublicHearingResponse(@Header("app-key") String appKey,
                                                         @Query("user_id") int user_id);

    @GET("public_hearing/view/{id}")
    Call<GetPublicHearingViewResponse> getPublicHearingViewResponse(@Header("app-key") String appKey,
                                                                    @Path("id") int id,
                                                                    @Query("user_id") int user_id);



    @FormUrlEncoded
    @POST("appointment/subject/save")
    Call<AppointmentSubjectSaveResponse> setAppointmentSubjectSaveResponse(
            @Header("app-key") String appKey,
            @Field("user_id") String user_id,
            @Field("name") String name);


    @GET("appointment/subject")
    Call<AppointmentSubjectFetchAllResponse> getAppointmentSubjectFetchResponse(@Header("app-key") String appKey);

    //appointment/subject/delete/

    @FormUrlEncoded
    @POST("appointment/subject/delete/{id}")
    Call<AppointmentSubjectDeleteResponse> setAppointmentSubjectDeleteResponse(
            @Header("app-key") String appKey,
            @Path("id") int id,
            @Field("user_id") String user_id);

    //public_hearing/delete/2

    @FormUrlEncoded
    @POST("public_hearing/delete/{id}")
    Call<SetPublicHearingDeleteResponse> setPublicHearingDeleteResponse(
            @Header("app-key") String appKey,
            @Path("id") int id,
            @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("appointment/subject/update/{id}")
    Call<AppointmentSubjectUpdateResponse> setAppointmentSubjectUpdateResponse(
            @Header("app-key") String appKey,
            @Path("id") int id,
            @Field("user_id") String user_id,
            @Field("name") String name);

    @GET("appointment/subject")
    Call<AppointmentSubjecResponse> getComplainOfDivisions(@Header("app-key") String appKey);


    @FormUrlEncoded
    @POST("complain/save")
    Call<ComplainSaveResponse> setComplain(@Header("app-key") String appKey,
                                           @Field("employee_id") int employee_id,
                                           @Field("subject_id") int subject_id,
                                           @Field("complainant_name") String complainant_name,
                                           @Field("defendant_name") String defendant_name,
                                           @Field("mobile_no") String mobile_no,
                                           @Field("complain") String complain,
                                           @Field("complainant_address") String complainant_address,
                                           @Field("defendant_address") String defendant_address,
                                           @Field("picture") String picture);

    //complain/?user_id=1
    @GET("complain/")
    Call<ComplainResponse> getComplainResponse(@Header("app-key") String appKey,
                                               @Query("user_id") int user_id);


    @FormUrlEncoded
    @POST("work/schedule/save")
    Call<WorkScheduleSaveResponse> setWorkScheduleSaveResponse(
            @Header("app-key") String appKey,

            @Field("user_id") int user_id,
            @Field("employee_id") int employee_id,
            @Field("subject") String subject,
            @Field("schedule_date") String user_schedule_dateid,
            @Field("place") String place,
            @Field("details") String details);


    @FormUrlEncoded
    @POST("sms")
    Call<SMSSendResponse> setSMSSendResponse(
            @Header("app-key") String appKey,
            @Field("user_id") int user_id,
            @Field("mobile_no") String mobile_no,
            @Field("message") String message);



}
