/*
 * Copyright (c) 2012-2013, bad robot (london) ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bad.robot.excel.matchers;

import org.apache.poi.ss.usermodel.Row;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static bad.robot.excel.PoiToExcelCoercions.asExcelRow;

public class RowMissingMatcher extends TypeSafeDiagnosingMatcher<Row> {

    private final Row expected;

    public static RowMissingMatcher rowIsPresent(Row expected) {
        return new RowMissingMatcher(expected);
    }

    private RowMissingMatcher(Row expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(Row actual, Description mismatch) {
        if (actual == null) {
            mismatch.appendText("row ").appendValue(asExcelRow(expected)).appendText(" is missing")
            	.appendText(" in sheet ").appendValue(expected.getSheet().getSheetName());
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("row ").appendValue(asExcelRow(expected)).appendText(" to be present")
        	.appendText(" in sheet ").appendValue(expected.getSheet().getSheetName());
    }
}
