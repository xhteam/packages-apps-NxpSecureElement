/*
 * Copyright (C) 2010 NXP Semiconductors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nxp.nfc.se;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.widget.Toast;

public class NfcSecureElementBroadcastReceiver extends BroadcastReceiver {

    public void onReceive (Context context, Intent intent) {
        if(intent.getAction().equals(NfcAdapter.ACTION_TRANSACTION_DETECTED)) {
            byte [] aid = intent.getByteArrayExtra(NfcAdapter.EXTRA_AID);
            byte [] data = intent.getByteArrayExtra(NfcAdapter.EXTRA_DATA);
            
            Toast.makeText(context,"Transaction AID: "+toHexString(aid, 0, aid.length)+"\n"+"Transaction DATA: "+toHexString(data, 0, data.length),
                    Toast.LENGTH_LONG).show();
        } else if(intent.getAction().equals(NfcAdapter.ACTION_CONNECTIVITY_EVENT_DETECTED)) {
            Toast.makeText(context,"UICC Event Connectivity occured",
                    Toast.LENGTH_LONG).show();
        }

    }

    private String toHexString(byte[] buffer, int offset, int length) {
        final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
        char[] chars = new char[3 * length] ;
        for (int j = offset; j < offset + length; ++j) {
            chars[3 * j] = HEX_CHARS[(buffer[j] & 0xF0) >>> 4];
            chars[3 * j + 1] = HEX_CHARS[buffer[j] & 0x0F];
            chars[3 * j + 2] = ' ';
        }
        return new String(chars);
    }
}
