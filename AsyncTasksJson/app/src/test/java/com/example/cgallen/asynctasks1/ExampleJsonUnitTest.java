package com.example.cgallen.asynctasks1;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * <p>
 * see https://medium.com/@yair.kukielka/android-unit-tests-explained-219b04dc55b5 for
 * explaination of why unit tests dont work with raw json
 * By default, the Android Plug-in for Gradle executes  your local unit tests against a modified version of the android.jar
 * this fails because https://blog.fossasia.org/unit-testing-json-files-in-assets-folder-of-android-app/
 * The JSONObject and JSONArray classes of
 * Android are part of android.jar, and henceJSONObject myJson = new JSONObject(someString);
 * The above code will not work when running unit tests on local JVM.
 */
public class ExampleJsonUnitTest {
    @Test
    public void jsonExampleTest() throws Exception {
        try {

            String json = "[" +
                    "{ 'name' : 'Tim Smith', 'username': '2smitt82', 'year': '2', 'course': 'Computing', 'phone': '07282 282282', 'modules': ['CDA600','MAD'] } ," +
                    "{ 'name' : 'David Osguthorpe', 'username' : '1osgud69', 'year': '3', 'course': 'Chemistry', 'phone': '07969 969969' }," +
                    "{ 'name': 'Deep Patel', 'username': '0pated61', 'year': '1', 'course' : 'Web Design', 'phone': '07761 061061' }"
                    + "]";

            System.out.println("original json string:" + json);

            JSONArray jsonArr = new JSONArray(json);

            System.out.println("parsed json object.toString:" + jsonArr.toString(4)); //pretty print
            String curName, curNationality, curDOB, curComments;

            String text = "";

            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject curObj = jsonArr.getJSONObject(i);
                String name = curObj.getString("name"),
                        course = curObj.getString("course"),
                        username = curObj.getString("username"),
                        phone = curObj.getString("phone");

                text += " Name= " + name + " Course = " + course + " Username= " + username + " Phone = " + phone ;

                if (curObj.has("modules")){
                JSONArray modules = curObj.getJSONArray("modules");
                    String mods = "";
                    for (int x = 0; x < modules.length(); x++) {
                        mods += modules.getString(x) + ",";
                    }
                    text += " modules="+mods+"\n";
                } else text += "\n";
            }
            System.out.println("result:" + text);
        } catch (JSONException e) {
            e.printStackTrace(System.err);
        }
    }
}