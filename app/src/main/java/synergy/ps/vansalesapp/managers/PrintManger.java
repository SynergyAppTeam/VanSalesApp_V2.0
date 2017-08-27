package synergy.ps.vansalesapp.managers;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.zebra.sdk.comm.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

/**
 * Created by ahmadnasser on 3/30/16.
 */
public class PrintManger {
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;

    // needed for communication to bluetooth device / network
    public static OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;

    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;
    Context context;


    private ProgressDialog myDialog;
    private Connection connection = null;

    public PrintManger(Context context) {
        this.context = context;

    }

    // close the connection to bluetooth printer.
    public void closeBT() throws IOException {
        try {
            stopWorker = true;
            mmOutputStream.close();
            mmInputStream.close();
            mmSocket.close();
            //  myLabel.setText("Bluetooth Closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendData(String printText) {
        try {
            sendData(5,printText);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "error in connection with printer", Toast.LENGTH_LONG).show();
        }

    }

    public void sendData(int x,String str) throws IOException {



        try {


            int yIndex = 210;


            String Artemp =
                    "^XA^LRN^CI0^XZ\n" +
                    "^XA^CWZ,E:TT0003M_.FNT^FS^XZ\n" +
                    "^XA\n" +
                    "^FO100,0^XGE:PALGROUP.PCX^FS\n" +
                    "^PA1,1,1,1^FS ^FX Enables Advanced Text^FS\n" +
//                    "^FO210,130^CI28^AZN,20,20^F16^FD" + context.getString(R.string.authorized_dealer) + " 562464156^FS\n" +
                    "^FO210,130^CI28^AZN,20,20^F16^FD" +"VanSalesApp V2.0" + "^FS\n" +
                    "^FO245,230^CI28^AZN,20,20^F16^FD" +str+ "^FS\n" +

                    "^FO20,310^CI28^AZN,20,20^F16^FD-----------------------------------------------------------------------------------------------------------------^FS\n";

            NumberFormat formatter = DecimalFormat.getInstance(Locale.ENGLISH);
            formatter.setMaximumFractionDigits(2);

            String tempBody = "";
            int yIndex2 = 330;
            float allProductTotal = 0;

            Artemp += tempBody;
            Artemp += "^PQ1\n" +
                    "^XZ";
            String len = "! U1 setvar \"zpl.label_length\" \"" + (yIndex2 + 100) + "\"";
            String zplCode = len + Artemp;
            mmOutputStream.write(zplCode.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openBT() throws IOException {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {

                    if (mmSocket != null) {
                        mmSocket.close();
                    }
                    // Standard SerialPortService code
                    UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

                    mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
                    mmSocket.connect();
                    mmOutputStream = mmSocket.getOutputStream();
                    mmInputStream = mmSocket.getInputStream();

                    beginListenForData();

                    // myLabel.setText("Bluetooth Opened");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (mmOutputStream != null) {
                    Toast.makeText(context,"device Connected To Printer", Toast.LENGTH_SHORT).show();
                }

            }
        }.execute();

    }

    public void findBT() {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                    if (mBluetoothAdapter == null) {
                        //  myLabel.setText("No bluetooth adapter available");
                    }


                    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

                    if (pairedDevices.size() > 0) {
                        for (BluetoothDevice device : pairedDevices) {

                            // RPP300 is the name of the bluetooth printer device
                            // we got this name from the list of paired devices
                            Log.v("device", device.getName() + "\n");
                            if (device.getName().equals("mz")) {

                                mmDevice = device;
                                break;
                            }
                        }
                    }
                    if (mmOutputStream == null) {
                        openBT();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }

    void beginListenForData() {
        try {
            final Handler handler = new Handler();

            // this is the ASCII code for a newline character
            final byte delimiter = 10;

            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {
                public void run() {

                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {

                        try {

                            int bytesAvailable = mmInputStream.available();

                            if (bytesAvailable > 0) {

                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);

                                for (int i = 0; i < bytesAvailable; i++) {

                                    byte b = packetBytes[i];
                                    if (b == delimiter) {

                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length
                                        );

                                        // specify US-ASCII encoding
                                        final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;

                                        // tell the user data were sent to bluetooth printer device
                                        handler.post(new Runnable() {
                                            public void run() {

                                            }
                                        });

                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }

                        } catch (IOException ex) {
                            stopWorker = true;
                        }

                    }
                }
            });

            workerThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
