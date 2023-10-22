package com.saartak.el.encryption;


import java.security.MessageDigest;

public class SHA256Encrypt {
    private static final String TAG = "SHA256Encrypt";
    
    //static String dummyRequest = "{\"UniqueId\":\"15042021080825197\",\"ResponseCode\":\"200\",\"ResponseMessage\":\"\",\"ErrorCode\":\"\",\"ErrorMessage\":\"\",\"ApiResponse\":{\"UserListResponse\":{\"Status\":\"1\",\"Message\":\"\",\"UserId\":\"SIF1012800\",\"Key\":\"GetRoles\",\"Roles\":[{\"RoleId\":\"9100\",\"RoleName\":\"RM\"}],\"P1\":\"\",\"P2\":\"\",\"P3\":\"\",\"D1\":\"NjY=\",\"Z1\":\"CC\",\"M1\":\"MzMg\"}}}";
    static  String dummyRequest="{\"ClientID\":\"351210621104217\",\"CreatedBy\":\"RVF\",\"CreatedByProject\":\"\",\"CreatedDate\":\"2021-06-25T17:32:02\",\"ExternalCustomerId\":\"\",\"KYCId\":\"878612243160\",\"RequestString\":{\"hunterVerificationRetail\":{\"ModuleType\":\"Applicant\",\"appDte\":\"2021-06-24\",\"appVal\":\"0\",\"assOrigVal\":\"0\",\"classification\":\"ACCEPTED\",\"count\":\"1\",\"date\":\"2021-06-24\",\"identifier\":\"\",\"Item\":{\"JointApplicant\":[{\"DLNo\":\"\",\"Passport_Number\":\"\",\"RationCard_Number\":\"\",\"UId\":\"\",\"VoterId\":\"\",\"firstName\":\"BATTHINI\",\"income\":\"0\",\"ModuleType\":\"\",\"nationality\":\"INDIAN\",\"pan\":\"EAMPK9643D\",\"residentialAddress\":{\"address\":\"1 7 175,musheerabad  near pochamma templ\",\"city\":\"Hyderabad\",\"country\":\"INDIA\",\"pincode\":\"500020\",\"state\":\"Andhra Pradesh\"}}],\"MainApplicant\":{\"bankAccount\":{\"accountInteger\":\"\",\"bankName\":\"\"},\"businessTelephone\":\"\",\"dateOfBirth\":\"1949-11-19\",\"email\":{\"emailAddress\":\"\"},\"employer\":{\"employerAddress\":{\"address\":\"\",\"city\":\"\",\"country\":\"\",\"pincode\":\"\",\"state\":\"\"},\"orgName\":\"\"},\"firstName\":\"BATTHINI\",\"idDocument\":{\"docNumber\":\"\",\"recDocCode\":\"\"},\"lastName\":\"BAI\",\"mobile\":\"8919736877\",\"residentialAddress\":{\"address\":\"1 7 175,Bakaram  near by pochamma templ\",\"city\":\"Musheerabad\",\"country\":\"INDIA\",\"pincode\":\"500020\",\"state\":\"Andhra Pradesh\"}}},\"originator\":\"RBL\",\"product\":\"PL_I_ST\",\"submissionNotificationRqd\":\"1\",\"term\":\"0\"}},\"ServiceType\":\"HunterVerificationRetail\",\"UniqueId\":\"1624535282355351210621104217\",\"AADHAR\":\"878612243160\"}";
     public static void main(String[] args) {
        //Log.d(TAG, "main: "+sha256("Hello world Testing"));
        System.out.println("SHA256-- "+sha256(dummyRequest));
    }
    public static String sha256(final String base) {
        //Log.d(TAG, "sha256: base string"+base);

        try{
            //String  finalString = "base SHA256"+base;
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
