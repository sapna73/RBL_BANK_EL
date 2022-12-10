package com.swadhaar.los.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.chaos.view.PinView;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.OTPVerificationTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

import static com.swadhaar.los.constants.AppConstant.USER_API_KEY;

public class OTPVerificationAdapter extends RecyclerView.Adapter<OTPVerificationAdapter.OTPVerificationViewHolder> {
    private static final String TAG =OTPVerificationAdapter.class.getCanonicalName() ;
    private Context context;
    List<OTPVerificationTable> otpVerificationTableList=new ArrayList<>();
    OTPInterface otpInterface;
    AppHelper appHelper;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    public OTPVerificationAdapter(Context context, List<OTPVerificationTable> otpVerificationTableList, OTPInterface otpInterface, AppHelper appHelper) {
        this.context = context;
        this.otpVerificationTableList = otpVerificationTableList;
        this.otpInterface = otpInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public OTPVerificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.otp_verification_row_item,parent,false);
        return new OTPVerificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OTPVerificationViewHolder holder, int position) {
        try{
            if(otpVerificationTableList !=null && otpVerificationTableList.size()>0) {
                OTPVerificationTable otpVerificationTable= otpVerificationTableList.get(position);
                if(otpVerificationTable !=null) {

                    if( ! TextUtils.isEmpty(otpVerificationTable.getMobileNumber())){
                        holder.tvMobileNumber.setText(otpVerificationTable.getMobileNumber());
                    }

                    if( ! TextUtils.isEmpty(otpVerificationTable.getName())){
                        holder.tvName.setText(otpVerificationTable.getName());
                    }


                    if( otpVerificationTable.isOTPVerified()){
                        holder.llOtpGenerate.setVisibility(View.GONE);
                        holder.llOtpVerify.setVisibility(View.GONE);
                        holder.pvEnteredOTP.setEnabled(false);
                        holder.llsuccess.setVisibility(View.VISIBLE);

                        // TODO: END TIMER
                        entTimer(holder);

                    }else if( otpVerificationTable.isOTPGenerated()){
                        holder.llOtpGenerate.setVisibility(View.GONE);
                        holder.tvResendOTP.setVisibility(View.GONE);
                        holder.llsuccess.setVisibility(View.GONE);
                        holder.pvEnteredOTP.setEnabled(true);
                        holder.llOtpVerify.setVisibility(View.VISIBLE);

                        Long startTime=appHelper.getSharedPrefObj().getLong(holder.tvMobileNumber.getText().toString(),0);
                        if(startTime==0){
                            holder.pvEnteredOTP.setText("");
                            holder.pvEnteredOTP.setEnabled(false);
                            holder.tvResendOTP.setVisibility(View.VISIBLE);
                        }else{
                            // TODO: START TIMER
                            startTimer(holder, startTime);
                        }
                    }else{
                        holder.pvEnteredOTP.setText("");
                        holder.pvEnteredOTP.setEnabled(true);
                        holder.llOtpVerify.setVisibility(View.GONE);
                        holder.llsuccess.setVisibility(View.GONE);
                        holder.tvResendOTP.setVisibility(View.GONE);
                        holder.llOtpGenerate.setVisibility(View.VISIBLE);
                    }



                    holder.btnOTPTrigger.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.pvEnteredOTP.setEnabled(true);
                            // TODO: SETTING TIME
                            appHelper.getSharedPrefObj().edit().putLong(holder.tvMobileNumber.getText().toString(),120L).apply();
                            otpInterface.generateOTP(position, otpVerificationTable,holder);
                        }
                    });

                    holder.tvResendOTP.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.pvEnteredOTP.setEnabled(true);
                            // TODO: SETTING TIME
                            appHelper.getSharedPrefObj().edit().putLong(holder.tvMobileNumber.getText().toString(),120L).apply();
                            otpInterface.regenerateOTP(position, otpVerificationTable,holder);
                        }
                    });

                    holder.pvEnteredOTP.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if( ! TextUtils.isEmpty(s)){
                                String enteredOtp=s.toString();
                                if( ! TextUtils.isEmpty(enteredOtp) && enteredOtp.length()==6) {
                                    otpInterface.verifyOTP(position, otpVerificationTable, enteredOtp,holder);
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return otpVerificationTableList.size();
    }

    public void setItems(List<OTPVerificationTable> list){
        try {
            if (list != null && list.size() > 0){
                otpVerificationTableList.clear();
                otpVerificationTableList=list;
                notifyDataSetChanged();
                /*for (OTPVerificationTable otpVerificationTable : list){
                    addItem(otpVerificationTable);
                }*/
            }else{
                otpVerificationTableList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addItem(OTPVerificationTable otpVerificationTable){
        if (otpVerificationTable != null){
            otpVerificationTableList.add(otpVerificationTable);
            notifyDataSetChanged();
        }
    }


    public class OTPVerificationViewHolder extends RecyclerView.ViewHolder{
        Button btnOTPTrigger;
        public TextView tvResendOTP,tvMobileNumber,tvName,tvTimer;
        LinearLayout llOtpGenerate,llOtpVerify,llsuccess;
        PinView pvEnteredOTP;
        Disposable disposable;
        public OTPVerificationViewHolder(@NonNull View itemView) {
            super(itemView);
            btnOTPTrigger=(Button)itemView.findViewById(R.id.btn_generate_otp);
            tvResendOTP=(TextView)itemView.findViewById(R.id.tv_re_generate_otp);
            tvMobileNumber=(TextView)itemView.findViewById(R.id.tv_mobile_number);
            tvName=(TextView)itemView.findViewById(R.id.tv_name);
            tvTimer=(TextView)itemView.findViewById(R.id.tv_timer);
            llOtpGenerate=(LinearLayout)itemView.findViewById(R.id.ll_generate_otp);
            llOtpVerify=(LinearLayout)itemView.findViewById(R.id.ll_otp_verify);
            llsuccess=(LinearLayout)itemView.findViewById(R.id.ll_verification_success);
            pvEnteredOTP=(PinView) itemView.findViewById(R.id.pv_enter_otp);
        }
    }

    public interface OTPInterface {
        void generateOTP(int position, OTPVerificationTable otpVerificationTable,OTPVerificationViewHolder holder);
        void regenerateOTP(int position, OTPVerificationTable otpVerificationTable,OTPVerificationViewHolder holder);
        void verifyOTP(int position, OTPVerificationTable otpVerificationTable,String enteredOtp,OTPVerificationViewHolder holder);
    }

    private void startTimer(OTPVerificationViewHolder holder,Long startingTime){
        try{
            holder.tvResendOTP.setVisibility(View.GONE);

             holder.disposable= Observable.interval(0,1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .take(startingTime + 1)
                    .map(new Function<Long, Long>() {
                        @Override
                        public Long apply(Long aLong) throws Exception {
                            Log.d(TAG,"holder position " + holder.getItemId());
                            Log.d(TAG,"apply before ===> " + aLong);
                            Long res=startingTime-aLong;
                            Log.d(TAG,"apply after ===> "+ res);
                            return res;
                        }
                    })
                    .filter(new Predicate<Long>() {
                        @Override
                        public boolean test(Long aLong) throws Exception {
                            Log.d(TAG,"test ===> "+ aLong);
                            return aLong>=0;
                        }
                    })
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long remainingTime) throws Exception {
                            Log.d(TAG,"accept ===> "+ remainingTime);

                            // TODO: Storing in shared preference
                            appHelper.getSharedPrefObj().edit().putLong(holder.tvMobileNumber.getText().toString(),remainingTime).apply();

//                           long hours = remainingTime / 3600;
                            long minutes = (remainingTime % 3600) / 60;
                            long seconds = remainingTime % 60;

                            String  timeString = String.format(Locale.ENGLISH,"%02d:%02d", minutes, seconds);

                            holder.tvTimer.setText(timeString);
                            if(remainingTime==0){
                                Log.d(TAG," TIME UP ");

                                holder.pvEnteredOTP.setText("");
                                holder.pvEnteredOTP.setEnabled(false);
                                holder.tvResendOTP.setVisibility(View.VISIBLE);

                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        "TIME UP !!!", new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {


                                            }
                                        });
                            }
                        }
                    });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void entTimer(OTPVerificationViewHolder holder){
        try{
//            compositeDisposable.clear();
            // TODO: Dispose
            if( holder.disposable !=null && ! holder.disposable.isDisposed()){
                holder.disposable.dispose();
            }
            holder.tvTimer.setText("00:00");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



}
