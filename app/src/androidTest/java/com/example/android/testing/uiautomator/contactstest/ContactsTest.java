package com.example.android.testing.uiautomator.contactstest;

//import org.junit.Test;


import android.test.InstrumentationTestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by betty on 6/30/15.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({BaseFunctionTest.class,BugVerifyTest.class})
public class ContactsTest {

//    public void testRunTestSuites() throws UiObjectNotFoundException {
//        TestSuite suite = new TestSuite();
//        suite.addTestSuite(BugVerifyTest.class);
//    }
}
