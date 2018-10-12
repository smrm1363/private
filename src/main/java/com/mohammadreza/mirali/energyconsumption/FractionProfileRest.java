//package com.mohammadreza.mirali.energyconsumption;
//
//import com.mohammadreza.mirali.energyconsumption.domain.*;
//import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileEntity;
//import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionDto;
//import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionEntity;
//import com.mohammadreza.mirali.energyconsumption.domain.profile.ProfileFractionService;
//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.io.InputStream;
//import java.util.List;
//
///**
// * Created by mmirali on 07/10/2018.
// */
//@Service("FractionProfileRest")
//@Path("/")
//public class FractionProfileRest {
//
//    @Autowired
//    ProfileFractionService profileFractionService;
//
//    @GET
//    @Path("/test")
//    public void transactions()
//    {
//        System.out.println("Thisi iiii   "+ MonthEnum.APR);
//        ProfileEntity profileEntity = new ProfileEntity();
//        profileEntity.setId("A");
//        profileFractionService.insertProfile(profileEntity);
//        ProfileFractionEntity profileFractionEntity = new ProfileFractionEntity();
//        profileFractionEntity.setFraction(.09);
//        profileFractionEntity.setMonth(MonthEnum.APR);
//        profileFractionEntity.setProfileEntity(profileEntity);
//
//    }
//
////    @POST
////    @Path("/sendcsv")
////    @Consumes(MediaType.MULTIPART_FORM_DATA)
////    public Response post(List<ProfileFractionDto> profileFractionDtos)
////    {
////        System.out.println("Size of list "+profileFractionDtos.size());
////        return Response.ok().build();
////    }
//
//
//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response uploadFile(@FormDataParam("file") InputStream fileInputStream,
//                               @FormDataParam("file") FormDataContentDisposition fileDisposition) {
//        System.out.println("OOOmade");
//        String fileName = fileDisposition.getFileName();
//        StringBuilder fileContents = new StringBuilder();
//        int read = 0;
//        int totalBytesRead = 0;
//        byte[] bytes = new byte[1024];
//
//
//        return Response.ok().build();
//    }
//
//
////    @Path("/file")
////    @POST
////    @Consumes(MediaType.MULTIPART_FORM_DATA)
////    public Response uploadFile(@DefaultValue("") @FormDataParam("tags") String tags,
////                               @FormDataParam("file") InputStream file,
////                               @FormDataParam("file") FormDataContentDisposition fileDisposition) {
////
////        String fileName = fileDisposition.getFileName();
////        return null;
////    }
//}
