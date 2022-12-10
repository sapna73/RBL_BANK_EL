package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class PincodeResponseDTO {
    @Expose
    private String Message;
    @Expose
    private String Status;
    @Expose
    public ArrayList<PostOffice> PostOffice = new ArrayList<PostOffice>();


    public ArrayList<PincodeResponseDTO.PostOffice> getPostOffice() {
        return PostOffice;
    }

    public void setPostOffice(ArrayList<PincodeResponseDTO.PostOffice> postOffice) {
        PostOffice = postOffice;
    }


    // Getter Methods

    public String getMessage() {
        return Message;
    }

    public String getStatus() {
        return Status;
    }

    // Setter Methods

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public class PostOffice {
        @Expose
        private String Name;
        @Expose
        private String Description;
        @Expose
        private String BranchType;
        @Expose
        private String DeliveryStatus;
        @Expose
        private String Taluk;
        @Expose
        private String Circle;
        @Expose
        private String District;
        @Expose
        private String Division;
        @Expose
        private String Region;
        @Expose
        private String State;
        @Expose
        private String Country;


        // Getter Methods

        public String getName() {
            return Name;
        }

        public String getDescription() {
            return Description;
        }

        public String getBranchType() {
            return BranchType;
        }

        public String getDeliveryStatus() {
            return DeliveryStatus;
        }

        public String getTaluk() {
            return Taluk;
        }

        public String getCircle() {
            return Circle;
        }

        public String getDistrict() {
            return District;
        }

        public String getDivision() {
            return Division;
        }

        public String getRegion() {
            return Region;
        }

        public String getState() {
            return State;
        }

        public String getCountry() {
            return Country;
        }

        // Setter Methods

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public void setBranchType(String BranchType) {
            this.BranchType = BranchType;
        }

        public void setDeliveryStatus(String DeliveryStatus) {
            this.DeliveryStatus = DeliveryStatus;
        }

        public void setTaluk(String Taluk) {
            this.Taluk = Taluk;
        }

        public void setCircle(String Circle) {
            this.Circle = Circle;
        }

        public void setDistrict(String District) {
            this.District = District;
        }

        public void setDivision(String Division) {
            this.Division = Division;
        }

        public void setRegion(String Region) {
            this.Region = Region;
        }

        public void setState(String State) {
            this.State = State;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }
    }
}
