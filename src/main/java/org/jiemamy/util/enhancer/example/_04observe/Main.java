/*
 * Copyright 2009 Jiemamy Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.jiemamy.util.enhancer.example._04observe;

import java.awt.GridLayout;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jiemamy.utils.enhancer.Enhance;
import org.jiemamy.utils.enhancer.FactoryEnhancer;

/**
 * このパッケージのプログラムエントリ。
 * @version $Date: 2009-09-21 02:27:46 +0900 (月, 21  9 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class Main {
    
    /**
     * プログラムエントリ。
     * @param args 無視される
     * @throws Exception うまくいかない場合
     */
    public static void main(String...args) throws Exception {
        
        Observer observer = new Observer(new Observer.Callback() {
            public void handle(Method source, Object value) {
                // とりあえずコンソールへ
                System.out.println("from: " + source);
                System.out.println("value: " + value);
            }
        });
        
        FactoryEnhancer<Factory> enhancer = new FactoryEnhancer<Factory>(
                Factory.class, // ファクトリのインターフェース
                FactoryImpl.class, // 実装クラス
                // セッタをオブザーバで監視する
                new Enhance(new SetterPointcut(), observer));
        
        System.out.println("ファクトリを生成");
        Factory factory = enhancer.getEnhanced().newInstance();
        
        System.out.println("プロダクトを生成");
        
        // ファクトリを利用してウィンドウでも作ってみる
        JFrame frame = factory.newFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 1));
        final JLabel label = factory.newLabel();
        final JTextField text = factory.newText();
        label.setText("Label:");
        text.setText("Input Text");
        frame.getContentPane().add(label);
        frame.getContentPane().add(text);
        text.getDocument().addDocumentListener(new DocumentListener() {
            public void removeUpdate(DocumentEvent e) {
                label.setText(text.getText());
            }
            public void insertUpdate(DocumentEvent e) {
                label.setText(text.getText());
            }
            public void changedUpdate(DocumentEvent e) {/*ignore*/}
        });
        frame.pack();
        frame.setVisible(true);
    }
}
