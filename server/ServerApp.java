/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp_v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rmarzolo1
 */
public class ServerApp {

    int port;
    DataInputStream in;
    DataOutputStream out;
    ServerSocket server;
    Socket socket;
    int BanCheckTimeout;

    Date NextBanManagerRunTime;
    
    List<BannedItem> BanList;

    public ServerApp() {
        port = 8000;
        BanCheckTimeout = 5000;
    }

    

    public ServerApp(int port) {
        this.port = port;
    }

    public void InitServer() {
        try {
            server = new ServerSocket(port);
            //initialize banlist
            BanList = Collections.synchronizedList(new ArrayList<BannedItem>());
            BanManager();
            // socket = server.accept();
            // in = new DataInputStream(socket.getInputStream());
            //out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Server Running...");
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateBanList(BannedItem newBan)
    {
        if(!BanList.contains(newBan))
        {
            BanList.add(newBan);
        }
    }
    
    void BanManager() {
        //loop every 5 minutes and remove the "softban" items from the list
        while(true)
        {
            Iterator looper = BanList.iterator();
            while(looper.hasNext())
            { 
               BannedItem itm = (BannedItem)looper.next();
               if(itm.BanType.equals(BanType.SoftBan) && itm.ExpireDate.after(new Date()))
               {
                   System.out.println("removed:" + itm.BannedIP);
                   looper.remove();
               }
            }
        }
    }

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
}
