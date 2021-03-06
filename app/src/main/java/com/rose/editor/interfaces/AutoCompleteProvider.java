/*
 Copyright 2020 Rose2073

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.rose.editor.interfaces;

import java.util.List;

import com.rose.editor.struct.ResultItem;
import com.rose.editor.text.TextAnalyzer;

/**
 * Interface for auto completion analysis
 * @author Rose
 */
public interface AutoCompleteProvider
{

    /**
     * Analyze auto complete items
     * @param prefix The prefix of input to match
     * @param isInCodeBlock Whether auto complete position is in code block
     * @param colors Last analyze result
     * @param line The line of cursor
     * @return Analyzed items
     */
    List<ResultItem> getAutoCompleteItems(String prefix, boolean isInCodeBlock, TextAnalyzer.TextColors colors, int line);

}

