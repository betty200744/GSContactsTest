/*
 * Copyright 2015, The Android Open Source Project
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

package com.example.android.testing.uiautomator.contactstest;

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.String;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.TestSuite;


/**
 * Basic sample for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Unit extends UiAutomatorTestCase {

//    @Before
//    public void startTestFromHomeScreen() throws UiObjectNotFoundException {
//
//        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        devicesInit();
//        mDevice.pressHome();
//    }



    public void scrollScreen(String direction, int steps) {
        try {

            int step = steps;
            String sw = direction;
            switch (sw) {
                case "right":
                    new UiScrollable(new UiSelector()).setAsHorizontalList().swipeRight(step);
                    break;
                case "left":
                    new UiScrollable(new UiSelector()).setAsHorizontalList().swipeLeft(step);
                    break;
                case "up":
                    new UiScrollable(new UiSelector()).setAsHorizontalList().swipeUp(step);
                    break;
                case "down":
                    new UiScrollable(new UiSelector()).setAsHorizontalList().swipeDown(step);
                    break;
                default:
                    new UiScrollable(new UiSelector()).setAsHorizontalList().swipeDown(step);
                    break;
            }
            Thread.sleep(1000);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void contactsEnter() throws UiObjectNotFoundException {
        try {
            new UiScrollable(new UiSelector().className("android.widget.RelativeLayout")).scrollForward(900);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        UiObject iconContacts = new UiObject(new UiSelector().text("Contacts"));
        iconContacts.click();

    }


    public void contactsEnterLocal() throws UiObjectNotFoundException {
        contactsEnter();
        UiObject iconContactsLocal = new UiObject((new UiSelector().text("Local Contacts")));
        iconContactsLocal.click();
    }


    public void contactsEnterLDAP() throws UiObjectNotFoundException {
        contactsEnter();
        UiObject iconContactsLDAP = new UiObject((new UiSelector().text("LDAP Contacts")));
        iconContactsLDAP.click();

    }

    public void contactsDownload(String url) throws UiObjectNotFoundException {
        String downloadServerUrl = url;
        contactsEnterLocal();
        mDevice.pressMenu();
        UiObject menuContactsDownload = new UiObject((new UiSelector().text("Download")));
        menuContactsDownload.click();
        new UiScrollable(new UiSelector().resourceId("android:id/content")).scrollBackward(900);
        UiObject textContactsDownloadServer = new UiObject((new UiSelector().text("Download Server")));
        textContactsDownloadServer.setText(downloadServerUrl);

    }

    public void contactsDeleteAll() throws UiObjectNotFoundException {
        contactsEnterLocal();
        UiObject iconContactsLDAP = new UiObject((new UiSelector().text("Local Contacts")));


    }

    public void contactsInit() {


    }

    public void devicesInit() throws UiObjectNotFoundException {
        languageSettings();


    }

    public void languageSettings() throws UiObjectNotFoundException {
        mDevice.pressHome();
        scrollScreen("left", 60);
        String language = getLanguage();
        if (!language.equals("en")) {
            UiObject iconSettingsCn = new UiObject((new UiSelector().text("设置")));
            iconSettingsCn.clickAndWaitForNewWindow();
            listViewGetByText("语言 & 键盘");
            UiObject languageicon = new UiObject((new UiSelector().text("语言")));
            languageicon.click();
            listViewGetByText("English (United States)");
        } else {
            return;
        }

    }

    public void listViewGetByText(String text) throws UiObjectNotFoundException {
        String tView = text;
        UiScrollable textList = new UiScrollable(new UiSelector()
                .className("android.widget.ListView"));
        UiObject textNote = null;
        if (textList.exists()){
            textNote = textList.getChildByText(new UiSelector().
                    className("android.widget.TextView"),tView,true);
            textNote.click();
        }else {
            textNote = new UiObject(new UiSelector().text(tView));
            textNote.click();
        }

    }

    public String getLanguage() {
        try {
            String line;
            java.lang.Process p = Runtime.getRuntime().exec("getprop");
            BufferedReader input =
                    new BufferedReader(new
                            InputStreamReader(p.getInputStream()));
            String currentLanguage = "";
            while ((line = input.readLine()) != null) {
                Matcher matcher = Pattern.compile("\\[persist.sys.language\\]: \\[(\\w+)\\]")
                        .matcher(line);
                if (matcher.find()) {
                    currentLanguage = matcher.group(1);
                }
            }
            input.close();
            return currentLanguage;
        } catch (Exception err) {
            err.printStackTrace();
        }
        throw new IllegalStateException("Language not found.");
    }


}
