/**
 * The ARTist Project (https://artist.cispa.saarland)
 *
 * Copyright (C) 2017 CISPA (https://cispa.saarland), Saarland University
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
 *
 * @author "Oliver Schranz <oliver.schranz@cispa.saarland>"
 * @author "Sebastian Weisgerber <weisgerber@cispa.saarland>"
 *
 */
package saarland.cispa.artist.codelib;

import android.content.Context;
import android.util.Log;


/**
 * While the CodeLib project can be considered a companion Android library for ARTist modules,
 * the `CodeLib` class in particular represents the facade/api to this library. It defines
 * methods that are available to ARTist instrumentation passes and provides access through a
 * singleton instance stored in a public field.
 */
public class CodeLib {

    /**
     * This annotation lets you define those APIs that will be made available in target apps,
     * hence in ARTist you can only inject calls to those CodeLib methods that are annotated properly.
     */
    @interface Inject {}

    /**
     * Instance variable for singleton usage. Using the singleton pattern ensures we can have
     * shared state when calling multiple CodeLib methods from different parts of a target app.
     *
     * It HAS to be a static field with exactly this name b/c ARTist expects this field to be present.
     */
    public static CodeLib INSTANCE = new CodeLib();

    // Constants
    private static final String TAG = CodeLib.class.toString();
    private static final String VERSION = TAG + " # 1.0.0";

    @SuppressWarnings("WeakerAccess")
    public  final static String MSG_NOT_FOUND = "<Not Found>";


    /**
     * Private class constructor to forbid further instance creation.
     */
    private CodeLib() {
        Log.v(TAG, TAG + " CodeLib v" + VERSION + " initialized.");
    }



    /**
     *  Injection target for an injection artist instrumentation pass.
     *
     *  Invocations of this method will be added to the target by ARTist.
     * @param fortytwo expected to be the constant 42.
     */
    @SuppressWarnings("unused")
    @Inject
    public void injectionArtistTarget(int fortytwo) {
        if (fortytwo != 42) {
            Log.e(TAG, "Error! Our artist pass provided " + fortytwo + " instead of 42");
        } else {
            Log.d(TAG, "Injection successfull");
        }
    }

    /**
     * Injection target for an artist instrumentation pass.
     *
     * Invocations of this method will be added to the target by ARTist.
     *
     * @param leet expected to be 1337.
     * @param thiz the object from which this method was called.
     */
    @SuppressWarnings("unused")
    @Inject
    public void basicArtistTarget(int leet, Object thiz) {
        if (leet != 1337) {
            Log.e(TAG, "Error! Our artist pass provided " + leet + " instead of 1337");
            return;
        }
        // now you can do sth meaningful with the `this` pointer of the object from which we are called
        if (thiz instanceof Context) {
            Log.i(TAG, "Found a context object, maybe store it for later?");
            // ...
        }
        // ...
    }
}
