package com.bfil.uilibrary.printer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bfil.uilibrary.R;
import com.bfil.uilibrary.dialogs.DialogHelper;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.bfil.uilibrary.printer.PrinterCommands.SELECT_BIT_IMAGE_MODE;


/**
 * Created by Martin Forejt on 28.06.2017.
 * forejt.martin97@gmail.com
 */

public class BluetoothPrinter extends BroadcastReceiver {

    static final String TAG = BluetoothPrinter.class.getCanonicalName();

    Context context;
    Adapter mAdapter;
    
    DialogHelper dialogHelper;
    CustomDialog customDialog;

    public static final int ALIGN_CENTER = 100;
    private static final int ALIGN_RIGHT = 101;
    public static final int ALIGN_LEFT = 102;

    private static final byte[] FEED_LINE = {10};
    private static final byte[] NEW_LINE = {0x0D};
    public static final byte[] ESC_ALIGN_CENTER = new byte[]{0x1b, 'a', 0x01};
    private static final byte[] ESC_ALIGN_RIGHT = new byte[]{0x1b, 'a', 0x02};
    private static final byte[] ESC_ALIGN_LEFT = new byte[]{0x1b, 'a', 0x00};

    BluetoothAdapter mBluetoothAdapter;
    List<BluetoothDevice> deviceArray = new ArrayList<>();
    BluetoothDevice printer;
    BluetoothSocket btSocket = null;
    static OutputStream btOutputStream = null;
    static UUID applicationUUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");

    public static final int REQUEST_BLUETOOTH = 99;

    BtConnectListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "inside onReceive--------->");
        try {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Log.i(TAG, "inside onReceive ACTION_FOUND------->");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (!deviceArray.contains(device)){
                    mAdapter.addItem(device);
                }
            } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)
                    || BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {
                Log.i(TAG, "inside onReceive ACTION_ACL_DISCONNECTED " +
                        "|| ACTION_ACL_DISCONNECT_REQUESTED------->");
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface BtConnectListener {
        void onConnected(BluetoothSocket socket);
        void onFailed();
    }

    public void setListener(BtConnectListener listener){
        this.listener = listener;
    }

    public BluetoothPrinter() { }

    public BluetoothPrinter(Context context) {
        this.context = context;
        dialogHelper = new DialogHelper(context);
        customDialog = new CustomDialog(context);
    }

    private void showToast(String message) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDevices() {
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter != null){
                if (mBluetoothAdapter.isEnabled()) {
                    dialogHelper.getLoadingDialog().showProgress("Getting Bluetooth devices... Please wait...");
                    deviceArray.clear();
                    Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
                    if (devices.size() > 0) {
                        deviceArray.addAll(devices);
                    }
                    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                    ((AppCompatActivity)context).registerReceiver(this, filter);
                    mBluetoothAdapter.startDiscovery();
                    showDevices();
                } else {
                    /*Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    ((AppCompatActivity)context).startActivityForResult(enableBT, REQUEST_BLUETOOTH);*/
                    showToast("Please enable Bluetooth in TAB");
                }
            } else {
                showToast("Bluetooth adapter not found in your device");
            }
        } catch (Exception e) {
            showToast("Something went wrong. Please try again later");
            e.printStackTrace();
        }
    }

    private void showDevices() {
        try {
//            final List<String> devicesList = new ArrayList<>();
//            for (BluetoothDevice device : deviceArray){
//                devicesList.add(device.getName() + "\n" + device.getAddress());
//            }

            View view = LayoutInflater.from(context).inflate(R.layout.device_list, null);
            RecyclerView recyclerView = view.findViewById(R.id.paired_devices);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            recyclerView.addItemDecoration(new DividerItemDecoration(context, R.drawable.line_divider_black,
                    false, false));
            mAdapter = new Adapter(context, deviceArray);
            recyclerView.setAdapter(mAdapter);

            /*ArrayAdapter<String> mPairedDevicesArrayAdapter = new ArrayAdapter<String>(context,
                    R.layout.device_name, devicesList);
            mPairedListView.setAdapter(mPairedDevicesArrayAdapter);
            mPairedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    cancelDiscovery();
                    customDialog.closeDialog();
                    try {
                        String mDeviceInfo = ((TextView) view).getText().toString();
                        String mDeviceAddress = mDeviceInfo.substring(mDeviceInfo.length() - 17);
                        Log.v(TAG, "Device_Address " + mDeviceAddress);
                        Log.v(TAG, "Coming incoming address " + mDeviceAddress);
                        printer = mBluetoothAdapter.getRemoteDevice(mDeviceAddress);
                        new ConnectTask().execute(printer);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        showToast(Constants.ERROR_SOMETHING_WRONG);
                    }
                }
            });*/
            Button btnCancel = view.findViewById(R.id.btn_cancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (customDialog != null){
                            customDialog.closeDialog();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            dialogHelper.closeDialog();
            customDialog.show(false, view);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    
//    private class ConnectTask extends AsyncTask<BluetoothDevice, Void, BluetoothSocket> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            loadingDialog.show("Connecting to Printer...");
//        }
//
//        @Override
//        
//        protected BluetoothSocket doInBackground(BluetoothDevice... bluetoothDevices) {
//            BluetoothDevice bDevice = bluetoothDevices[0];
//            BluetoothSocket socket = null;
//            boolean connected = true;
//
//            if (bDevice != null){
//                try {
//                    socket = bDevice.createRfcommSocketToServiceRecord(applicationUUID);
//                } catch (IOException e) {
//                    Log.i("SOCKET EXCEPTION", "Bluetooth Socket not created");
//                    e.printStackTrace();
//                }
//                try {
//                    socket.connect();
//                } catch (IOException e) {
//                    try {
//                        Log.i(TAG, "inside IOException Bluetooth Socket not created");
//                        OutputStream outputStream = socket.getOutputStream();
//                        if (outputStream != null){
//                            outputStream.close();
//                        }
//                        socket.close();
//                        socket = bDevice.createRfcommSocketToServiceRecord(applicationUUID);
//                        socket.connect();
//                    } catch (Exception e2) {
//                        connected = false;
//                    }
//                }
//            }
//
//            return connected ? socket : null;
//        }
//
//        @Override
//        
//        protected void onPostExecute(BluetoothSocket bluetoothSocket) {
//            try {
//                loadingDialog.closeDialog();
//                if (bluetoothSocket != null){
//                    btSocket = bluetoothSocket;
//                    btOutputStream = bluetoothSocket.getOutputStream();
//                    Toast.makeText(context, "Device Successfully Connected", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "Unable to connect to Printer", Toast.LENGTH_SHORT).show();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private static class ConnectTask extends AsyncTask<BluetoothDevice, Void, BluetoothSocket> {
        private BtConnectListener listener;

        ConnectTask(BtConnectListener listener) {
            this.listener = listener;
        }

        @Override
        protected BluetoothSocket doInBackground(BluetoothDevice... bluetoothDevices) {
            BluetoothDevice device = bluetoothDevices[0];
            BluetoothSocket socket = null;
            boolean connected = true;

            if (device != null){
                try {
                    socket = device.createRfcommSocketToServiceRecord(applicationUUID);
                } catch (IOException e) {
                    Log.i("SOCKET EXCEPTION", "Bluetooth Socket not created");
                    e.printStackTrace();
                }
                try {
                    socket.connect();
                } catch (IOException e) {
                    try {
                        Log.i(TAG, "inside IOException Bluetooth Socket not created");
                        OutputStream outputStream = socket.getOutputStream();
                        if (outputStream != null){
                            outputStream.close();
                        }
                        socket.close();
                        socket = device.createRfcommSocketToServiceRecord(applicationUUID);
                        socket.connect();
                    } catch (Exception e2) {
                        connected = false;
                    }
                }
            }

            return connected ? socket : null;
        }

        @Override
        protected void onPostExecute(BluetoothSocket bluetoothSocket) {
            if (listener != null) {
                if (bluetoothSocket != null) listener.onConnected(bluetoothSocket);
                else listener.onFailed();
            }
        }

        private interface BtConnectListener {
            void onConnected(BluetoothSocket socket);

            void onFailed();
        }
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private Context context;
        List<BluetoothDevice> itemList = new ArrayList<>();

        Adapter(Context context, List<BluetoothDevice> reportDetails) {
            this.context = context;
            this.itemList = reportDetails;
        }

        @Override
        public Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_name, parent, false);
            return new Adapter.Holder(view);
        }

        @Override
        public void onBindViewHolder(final Adapter.Holder holder, int position) {
            try {
                if (itemList.size() > 0){
                    BluetoothDevice device = itemList.get(position);
                    if (device != null){
                        String dDetails = device.getName() + "\n" + device.getAddress();
                        holder.txtDeviceName.setText(dDetails);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cancelDiscovery();
                                customDialog.closeDialog();
                                try {
                                    BluetoothDevice device1 = itemList.get(holder.getAdapterPosition());
                                    printer = mBluetoothAdapter.getRemoteDevice(device1.getAddress());
                                    Log.i(TAG, "Device Name------> " + printer.getName());
                                    Log.i(TAG, "Device Address------> " + printer.getAddress());
                                    Log.i(TAG, "Device State------> " + printer.getBondState());
                                    connect(printer);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    showToast("Something went wrong. Please try again later");
                                }
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        public void addItem(BluetoothDevice bluetoothDevice){
            try {
                itemList.add(bluetoothDevice);
                notifyItemInserted(itemList.size() - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        class Holder extends RecyclerView.ViewHolder {

            TextView txtDeviceName;

            Holder(View itemView) {
                super(itemView);
                txtDeviceName = (TextView) itemView.findViewById(R.id.txt_device_name);
            }
        }
    }

    void connect(final BluetoothDevice printer){
        try {
            dialogHelper.getLoadingDialog().showProgress("Connecting to Printer...");
            new ConnectTask(new ConnectTask.BtConnectListener() {
                @Override
                public void onConnected(BluetoothSocket socket) {
                    try {
                        dialogHelper.closeDialog();
                        if (socket != null){
                            btSocket = socket;
                            btOutputStream = btSocket.getOutputStream();
                            showToast("Device successfully connected");
                        } else {
                            showToast("Unable to connect to printer");
                            btSocket = null;
                            btOutputStream = null;
                            BluetoothPrinter.this.printer = null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailed() {
                    try {
                        dialogHelper.closeDialog();
                        showToast("Unable to connect to printer");
                        btSocket = null;
                        btOutputStream = null;
                        BluetoothPrinter.this.printer = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).execute(printer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public class BReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            try {
//                String action = intent.getAction();
//                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                    if (!deviceArray.contains(device)){
//                        mAdapter.addItem(device);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public final BroadcastReceiver bReceiver = new BroadcastReceiver() {
//        public void onReceive(Context context, Intent intent) {
//            try {
//                String action = intent.getAction();
//                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                    if (!deviceArray.contains(device)){
//                        mAdapter.addItem(device);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };

    public BluetoothPrinter(BluetoothDevice printer) {
        this.printer = printer;
    }

    public void cancelDiscovery() {
        try {
            context.unregisterReceiver(this);
            mBluetoothAdapter.cancelDiscovery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        boolean isIt = false;
        try {
            if (getSocket() != null && getSocket().isConnected()){
                if (getDevice().getName().startsWith("PB")){
                    isIt = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isIt;
    }

    public void finish() {
        try {
            if (getSocket() != null || btOutputStream != null) {
                btOutputStream.close();
                if (getSocket() != null){
                    getSocket().close();
                }
                btSocket = null;
                btOutputStream = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean printText(String text) {
        try {
            btOutputStream.write(encodeNonAscii(text).getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean printUnicode(byte[] data) {
        try {
            btOutputStream.write(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean printByte(byte data) {
        try {
            btOutputStream.write(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean printLine(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++){
            builder.append("-");
        }
        return printText(builder.toString());
    }

    public boolean addNewLine() {
        return printUnicode(NEW_LINE);
    }

    public int addNewLines(int count) {
        int success = 0;
        for (int i = 0; i < count; i++) {
            if (addNewLine()) success++;
        }

        return success;
    }

    public boolean printImage(Bitmap bitmap) {
        byte[] command = decodeBitmap(getResizedBitmap(bitmap));
        return printUnicode(command);
    }

    public Bitmap getResizedBitmap(Bitmap bm) {
        int reqWidth = Math.round((float) (48 * 8));
        int width = bm.getWidth();
        int height = bm.getHeight();
        if (width == reqWidth) {
            return bm;
        }
        float scaleWidth;
        float scaleHeight;
        Matrix matrix;
        Bitmap resizedBitmap;
        if (width < reqWidth && width > 16) {
            int diff = width % 8;
            if (diff == 0) {
                return bm;
            }
            scaleWidth = ((float) (width - diff)) / ((float) width);
            scaleHeight = ((float) (((width - diff) * height) / width)) / ((float) height);
            matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
            bm.recycle();
            return resizedBitmap;
        } else if (width <= 16) {
            return bm;
        } else {
            scaleWidth = ((float) reqWidth) / ((float) width);
            scaleHeight = ((float) ((reqWidth * height) / width)) / ((float) height);
            matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
            bm.recycle();
            return resizedBitmap;
        }
    }

    public void setAlign(int alignType) {
        byte[] d;
        switch (alignType) {
            case ALIGN_CENTER:
                d = ESC_ALIGN_CENTER;
                break;
            case ALIGN_LEFT:
                d = ESC_ALIGN_LEFT;
                break;
            case ALIGN_RIGHT:
                d = ESC_ALIGN_RIGHT;
                break;
            default:
                d = ESC_ALIGN_LEFT;
                break;
        }

        try {
            btOutputStream.write(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLineSpacing(int lineSpacing) {
        byte[] cmd = new byte[]{0x1B, 0x33, (byte) lineSpacing};
        printUnicode(cmd);
    }

    public void setBold(boolean bold) {
        byte[] cmd = new byte[]{0x1B, 0x45, bold ? (byte) 1 : 0};
        printUnicode(cmd);
    }

    String commandSet = "";
    public String feed(byte lines) {
        final byte[] Feed = {27,100,lines};
        String s = new String(Feed);
        commandSet += s;
        return s;
    }

    public String getLabelString(String string){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        try {
            int avialLength = 12;
            int count = avialLength - string.length();
            for (int i = 0; i < count; i++){
                stringBuilder.append(" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String getResultString(String string){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int avialLength = 20;
            int count = avialLength - string.length();
            for (int i = 0; i < count; i++){
                stringBuilder.append(" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        stringBuilder.append(string);
        return stringBuilder.toString();
    }

    private static String encodeNonAscii(String text) {
        return text.replace('á', 'a')
                .replace('č', 'c')
                .replace('ď', 'd')
                .replace('é', 'e')
                .replace('ě', 'e')
                .replace('í', 'i')
                .replace('ň', 'n')
                .replace('ó', 'o')
                .replace('ř', 'r')
                .replace('š', 's')
                .replace('ť', 't')
                .replace('ú', 'u')
                .replace('ů', 'u')
                .replace('ý', 'y')
                .replace('ž', 'z')
                .replace('Á', 'A')
                .replace('Č', 'C')
                .replace('Ď', 'D')
                .replace('É', 'E')
                .replace('Ě', 'E')
                .replace('Í', 'I')
                .replace('Ň', 'N')
                .replace('Ó', 'O')
                .replace('Ř', 'R')
                .replace('Š', 'S')
                .replace('Ť', 'T')
                .replace('Ú', 'U')
                .replace('Ů', 'U')
                .replace('Ý', 'Y')
                .replace('Ž', 'Z');
    }

    private static byte[] decodeBitmap(Bitmap bmp) {
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();

        List<String> list = new ArrayList<>();
        StringBuffer sb;
        int zeroCount = bmpWidth % 8;
        String zeroStr = "";
        if (zeroCount > 0) {
            for (int i = 0; i < (8 - zeroCount); i++) zeroStr = zeroStr + "0";
        }

        for (int i = 0; i < bmpHeight; i++) {
            sb = new StringBuffer();
            for (int j = 0; j < bmpWidth; j++) {
                int color = bmp.getPixel(j, i);
                int r = (color >> 16) & 0xff;
                int g = (color >> 8) & 0xff;
                int b = color & 0xff;
                if (r > 160 && g > 160 && b > 160) sb.append("0");
                else sb.append("1");
            }
            if (zeroCount > 0) sb.append(zeroStr);
            list.add(sb.toString());
        }

        List<String> bmpHexList = binaryListToHexStringList(list);
        String commandHexString = "1D763000";
        String widthHexString = Integer
                .toHexString(bmpWidth % 8 == 0 ? bmpWidth / 8 : (bmpWidth / 8 + 1));
        if (widthHexString.length() > 2) {
            return null;
        } else if (widthHexString.length() == 1) {
            widthHexString = "0" + widthHexString;
        }
        widthHexString = widthHexString + "00";

        String heightHexString = Integer.toHexString(bmpHeight);
        if (heightHexString.length() > 2) {
            return null;
        } else if (heightHexString.length() == 1) {
            heightHexString = "0" + heightHexString;
        }
        heightHexString = heightHexString + "00";

        List<String> commandList = new ArrayList<>();
        commandList.add(commandHexString + widthHexString + heightHexString);
        commandList.addAll(bmpHexList);

        return hexList2Byte(commandList);
    }

    private static List<String> binaryListToHexStringList(List<String> list) {
        List<String> hexList = new ArrayList<>();
        for (String binaryStr : list) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < binaryStr.length(); i += 8) {
                String str = binaryStr.substring(i, i + 8);
                String hexString = strToHexString(str);
                sb.append(hexString);
            }
            hexList.add(sb.toString());
        }
        return hexList;
    }

    private static String hexStr = "0123456789ABCDEF";
    private static String[] binaryArray = {"0000", "0001", "0010", "0011",
            "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011",
            "1100", "1101", "1110", "1111"};

    private static String strToHexString(String binaryStr) {
        String hex = "";
        String f4 = binaryStr.substring(0, 4);
        String b4 = binaryStr.substring(4, 8);
        for (int i = 0; i < binaryArray.length; i++) {
            if (f4.equals(binaryArray[i]))
                hex += hexStr.substring(i, i + 1);
        }
        for (int i = 0; i < binaryArray.length; i++) {
            if (b4.equals(binaryArray[i]))
                hex += hexStr.substring(i, i + 1);
        }

        return hex;
    }

    private static byte[] hexList2Byte(List<String> list) {
        List<byte[]> commandList = new ArrayList<>();
        for (String hexStr : list) commandList.add(hexStringToBytes(hexStr));
        return sysCopy(commandList);
    }

    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) return null;
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte[] sysCopy(List<byte[]> srcArrays) {
        int len = 0;
        for (byte[] srcArray : srcArrays) {
            len += srcArray.length;
        }
        byte[] destArray = new byte[len];
        int destLen = 0;
        for (byte[] srcArray : srcArrays) {
            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
            destLen += srcArray.length;
        }

        return destArray;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public BluetoothSocket getSocket() {
        return btSocket;
    }

    private BluetoothDevice getDevice() {
        return printer;
    }

    public void printCustom(String msg, int size, int align) {
        //Print config "mode"
        byte[] cc = new byte[]{0x1B, 0x21, 0x03};  // 0- normal size text
        //byte[] cc1 = new byte[]{0x1B,0x21,0x00};  // 0- normal size text
        byte[] bb = new byte[]{0x1B, 0x21, 0x08};  // 1- only bold text
        byte[] bb2 = new byte[]{0x1B, 0x21, 0x20}; // 2- bold with medium text
        byte[] bb3 = new byte[]{0x1B, 0x21, 0x10}; // 3- bold with large text
        try {
            switch (size) {
                case 0:
                    printUnicode(cc);
                    break;
                case 1:
                    printUnicode(bb);
                    break;
                case 2:
                    printUnicode(bb2);
                    break;
                case 3:
                    printUnicode(bb3);
                    break;
            }

            switch (align) {
                case 0:
                    //left align
                    printUnicode(PrinterCommands.ESC_ALIGN_LEFT);
                    break;
                case 1:
                    //center align
                    printUnicode(PrinterCommands.ESC_ALIGN_CENTER);
                    break;
                case 2:
                    //right align
                    printUnicode(PrinterCommands.ESC_ALIGN_RIGHT);
                    break;
            }
            printUnicode(msg.getBytes());
            btOutputStream.write(PrinterCommands.LF);
//            printByte(PrinterCommands.LF);
            //outputStream.write(cc);
            //printNewLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTextAsImage(String text, boolean isHeader, int textSize) {
        try {
            Bitmap bmp = drawText(text, textSize, isHeader);
            convertBitmap(bmp);
            byte widthLSB = (byte) (bmp.getWidth() & 0xFF);
            byte widthMSB = (byte) ((bmp.getWidth() >> 8) & 0xFF);
            byte[] selectBitImageModeCommand = PrinterCommands(SELECT_BIT_IMAGE_MODE, (byte) 33, widthLSB, widthMSB);
            btOutputStream.write(PrinterCommands.SET_LINE_SPACING_24);
            int offset = 0;
            while (offset < bmp.getHeight()) {
                btOutputStream.write(selectBitImageModeCommand);
                for (int x = 0; x < bmp.getWidth(); ++x) {
                    for (int k = 0; k < 3; ++k) {
                        byte slice = 0;
                        for (int b = 0; b < 8; ++b) {
                            int y = (((offset / 8) + k) * 8) + b;
                            int i = (y * bmp.getWidth()) + x;
                            boolean v = false;
                            if (i < dots.length()) {
                                v = dots.get(i);
                            }
                            slice |= (byte) ((v ? 1 : 0) << (7 - b));
                        }
                        btOutputStream.write(slice);
                    }
                }
                offset += 24;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Exception", e.toString());
        }
    }

    private byte[] PrinterCommands(byte[] command, byte... args) {
        byte[] posCommand = new byte[command.length + args.length];

        System.arraycopy(command, 0, posCommand, 0, command.length);
        System.arraycopy(args, 0, posCommand, command.length, args.length);

        return posCommand;
    }

    BitSet dots;
    public Bitmap drawText(String text, int textWidth, boolean isHeader) {
        // Get text dimensions
        TextPaint textPaint = null;
        if (isHeader){
            textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG
                    | Paint.LINEAR_TEXT_FLAG | Paint.FAKE_BOLD_TEXT_FLAG );
        } else {
            textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG
                    | Paint.LINEAR_TEXT_FLAG);
        }
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(textWidth);

        StaticLayout mTextLayout = new StaticLayout(text, textPaint, 380, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, false);

        // Create bitmap and canvas to draw to
        Bitmap b = null;
        if (isHeader){
            b = Bitmap.createBitmap(380, textWidth, Bitmap.Config.ARGB_8888);
        } else {
            b = Bitmap.createBitmap(380, mTextLayout.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas c = new Canvas(b);

        // Draw background
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
        c.drawPaint(paint);

        // Draw text
        c.save();
        c.translate(0, 0);
        mTextLayout.draw(c);
        c.restore();

        return b;
    }

    private String convertBitmap(Bitmap inputBitmap) {
        try {
            int mWidth = inputBitmap.getWidth();
            int mHeight = inputBitmap.getHeight();
            convertrgbToGrayscale(inputBitmap, mWidth, mHeight);
        } catch (Exception e) {
            Log.e("Test", e.toString());
        }
        String mStatus = "ok";
        return mStatus;
    }

    private void convertrgbToGrayscale(Bitmap bmpOriginal, int width, int height) {
        int pixel;
        int k = 0;
        int B = 0, G = 0, R = 0;
        dots = new BitSet();
        try {
            for (int x = 0; x < height; x++) {
                for (int y = 0; y < width; y++) {
                    pixel = bmpOriginal.getPixel(y, x);
                    R = Color.red(pixel);
                    G = Color.green(pixel);
                    B = Color.blue(pixel);
                    R = G = B = (int) (0.299 * R + 0.587 * G + 0.114 * B);
                    if (R < 55) {
                        dots.set(k);
                    }
                    k++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("exception", e.toString());
        }
    }
}
