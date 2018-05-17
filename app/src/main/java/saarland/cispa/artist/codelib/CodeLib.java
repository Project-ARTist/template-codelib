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

import android.util.Log;

public class CodeLib {

    @interface Inject {}

    // Instance variable for singleton usage ///////////////////////////////////////////////////////
    public static CodeLib INSTANCE = new CodeLib();

    // <Constants> /////////////////////////////////////////////////////////////////////////////////
    private static final String TAG = "ArtistCodeLib";
    private static final String VERSION = TAG + " # 1.0.0";

    final String MSG_NOT_FOUND = "<Not Found>";
    // </Constants> ////////////////////////////////////////////////////////////////////////////////

    /**
     * Static Class Constructor
     */
    static {
        // <Code>
    }

    /**
     * Private Class Constructor
     * => Forbidden Class Initialisation (Singleton)
      */
    private CodeLib() {
        Log.v(TAG, TAG + " CodeLib() " + VERSION);
    }

    /** Get the name of the calling method
     *
     * The name is probed from the current Thread's stacktrace.
     *
     * @return the name of the calling method
     */
    private String getCallingMethodName() {
        // CallStack depth of calling function.
        final int CALLING_METHOD_STACK_LEVEL = 4;

        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callingMethodName;
        try {
            final StackTraceElement callingMethod = stackTrace[CALLING_METHOD_STACK_LEVEL];
            callingMethodName = callingMethod.toString();
        } catch (final NullPointerException e) {
            callingMethodName = MSG_NOT_FOUND;
        } catch (final ArrayIndexOutOfBoundsException e) {
            callingMethodName = MSG_NOT_FOUND;
        }
        return callingMethodName;
    }

    /**
     *  Tracelog method, prints the method name of the calling method.
     */
    @Inject
    public void traceLog() {
        final String callingMethodName = getCallingMethodName();
        Log.d(TAG, "Caller -> " + callingMethodName);
    }
}
