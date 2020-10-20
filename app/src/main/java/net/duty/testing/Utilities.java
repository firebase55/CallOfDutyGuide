package net.duty.testing;

import android.content.SharedPreferences;

class Utilities {

    static String crAdr;
    static int crCnt;
    static SharedPreferences shdPreferences;


    static void setStats() {
        if (!shdPreferences.getString("cr_adr", "127.0.0.1").equals(crAdr)) {
            SharedPreferences.Editor shdEditor = shdPreferences.edit();
            shdEditor.putString("cr_adr", crAdr);
            shdEditor.putInt("cr_cnt", 0);
            shdEditor.apply();
            refStats(crAdr, 0);
        } else {
            refStats(crAdr, shdPreferences.getInt("cr_cnt", 0));
        }
    }

    static void refStats(String crAdr, int crCnt) {
        Utilities.crAdr = crAdr;
        Utilities.crCnt = crCnt;
    }

    static void incCrCnt() {
        int tpCnt = shdPreferences.getInt("cr_cnt", 0);
        if (shdPreferences.getString("cr_adr", "127.0.0.1").equals(crAdr)) {
            tpCnt++;
            SharedPreferences.Editor shdEditor = shdPreferences.edit();
            shdEditor.putInt("cr_cnt", tpCnt);
            shdEditor.apply();
        }
        refStats(crAdr, tpCnt);
    }

    static String getStats() {
        return "IP : " + shdPreferences.getString("cr_adr", "127.0.0.1") + "\nCount : " + shdPreferences.getInt("cr_cnt", 0);
    }

}
