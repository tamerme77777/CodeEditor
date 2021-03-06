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

import com.rose.editor.text.Content;
import com.rose.editor.text.TextAnalyzer;

/**
 * Interface for analyzing highlight
 * @author Rose
 */
public interface CodeAnalyzer {

    /**
     * Analyze spans for the given input
     * @see TextAnalyzer#analyze(Content)
     * @see TextAnalyzer.AnalyzeThread.Delegate#shouldAnalyze()
     * @param content The input text
     * @param colors Result dest
     * @param delegate Delegate between thread and analyzer
     */
    void analyze(CharSequence content, TextAnalyzer.TextColors colors, TextAnalyzer.AnalyzeThread.Delegate delegate);

}
