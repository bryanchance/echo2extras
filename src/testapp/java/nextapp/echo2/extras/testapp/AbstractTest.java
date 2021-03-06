/* 
 * This file is part of the Echo2 Extras Project.
 * Copyright (C) 2002-2009 NextApp, Inc.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */

package nextapp.echo2.extras.testapp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.FillImage;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.SplitPane;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;

public class AbstractTest extends SplitPane {

    private Component testComponentParent;
    private Component testComponent;
    protected TestControlPane testControlsPane;
    
    public AbstractTest(String testName) {
        this(testName, null);
    }
    
    public AbstractTest(String testName, ImageReference icon) {
        super(SplitPane.ORIENTATION_HORIZONTAL, new Extent(300, Extent.PX));
        setStyleName("DefaultResizable");
        
        testControlsPane = new TestControlPane(testName, icon);
        add(testControlsPane);
    }
    
    protected void setTestComponent(Component testComponentParent, Component testComponent) {
        this.testComponentParent = testComponentParent;
        this.testComponent = testComponent;
    }
    
    protected void addBooleanPropertyTests(String category, final String propertyName) {
        testControlsPane.addButton(category, propertyName + ": true", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, boolean.class, Boolean.TRUE);
            }
        });
        testControlsPane.addButton(category, propertyName + ": false", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, boolean.class, Boolean.FALSE);
            }
        });
    }
    
    protected void addBorderPropertyTests(String category, final String propertyName) {
        testControlsPane.addButton(category, propertyName + ": Randomize", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Border.class, StyleUtil.randomBorder());
            }
        });
        testControlsPane.addButton(category, propertyName + ": Set Color", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Border border = (Border) getTestComponentProperty(propertyName);
                if (border == null) {
                    border = new Border(new Extent(1), Color.BLUE, Border.STYLE_SOLID);
                }
                setTestComponentProperty(propertyName, Border.class, 
                        new Border(border.getSize(), StyleUtil.randomColor(), border.getStyle()));
            }
        });
        testControlsPane.addButton(category, propertyName + ": Set Size", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Border.class, 
                        StyleUtil.nextBorderSize((Border) getTestComponentProperty(propertyName)));
            }
        });
        testControlsPane.addButton(category, propertyName + ": Set Style", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Border.class, 
                        StyleUtil.nextBorderStyle((Border) getTestComponentProperty(propertyName)));
            }
        });
        testControlsPane.addButton(category, propertyName + ": null", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Border.class, null);
            }
        });
    }
    
    protected void addFillImagePropertyTests(String category, final String propertyName, final FillImage[] fillImageValues) {
        for (int i = 0; i < fillImageValues.length; ++i) {
            final int index = i;
            String name = fillImageValues[i] == null ? "null" : Integer.toString(i);
            testControlsPane.addButton(category, propertyName + ": " + name, new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    setTestComponentProperty(propertyName, FillImage.class, fillImageValues[index]);
                }
            });
        }
    }
    
    protected void addExtentPropertyTests(String category, final String propertyName, final Extent[] extentValues) {
        for (int i = 0; i < extentValues.length; ++i) {
            String extentString = extentValues[i] == null ? "null" : extentValues[i].toString();
            final int index = i;
            testControlsPane.addButton(category, propertyName + ": " + extentString, new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    setTestComponentProperty(propertyName, Extent.class, extentValues[index]);
                }
            });
        }
    }
    
    protected void addColorPropertyTests(String category, final String propertyName) {
        testControlsPane.addButton(category, propertyName + ": Randomize", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Color.class, StyleUtil.randomColor());
            }
        });
        testControlsPane.addButton(category, propertyName + ": null", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Color.class, null);
            }
        });
    }
    
    protected void addFontPropertyTests(String category, final String propertyName) {
        testControlsPane.addButton(category, propertyName + ": Randomize", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Font.class, StyleUtil.randomFont());
            }
        });
        testControlsPane.addButton(category, propertyName + ": null", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Font.class, null);
            }
        });
    }
    
    protected void addIntegerPropertyTests(String category, final String propertyName, final int[] values) {
        for (int i = 0; i < values.length; ++i) {
            final int value = values[i];
            testControlsPane.addButton(category, propertyName + ": " + values[i], new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    setTestComponentProperty(propertyName, int.class, new Integer(value));
                }
            });
        }
    }
    
    protected void addInsetsPropertyTests(String category, final String propertyName) {
        
        testControlsPane.addButton(category, propertyName + ": 0px", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Insets.class, new Insets(0));
            }
        });
        testControlsPane.addButton(category, propertyName + ": 1px", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Insets.class, new Insets(1));
            }
        });
        testControlsPane.addButton(category, propertyName + ": 2px", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Insets.class, new Insets(2));
            }
        });
        testControlsPane.addButton(category, propertyName + ": 5px", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Insets.class, new Insets(5));
            }
        });
        testControlsPane.addButton(category, propertyName + ": 10/20/30/40px", 
                new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Insets.class, new Insets(10, 20, 30, 40));
            }
        });
        testControlsPane.addButton(category, propertyName + ": null", new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                setTestComponentProperty(propertyName, Insets.class, null);
            }
        });
    }
    
    protected void addStandardIntegrationTests() {

        testControlsPane.addButton(TestControlPane.CATEGORY_INTEGRATION, "Add Component", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (testComponentParent.indexOf(testComponent) == -1) {
                    testComponentParent.add(testComponent);
                }
            }
        });

        testControlsPane.addButton(TestControlPane.CATEGORY_INTEGRATION, "Remove Component", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testComponentParent.remove(testComponent);
            }
        });
        
        testControlsPane.addButton(TestControlPane.CATEGORY_INTEGRATION, "Enable Component", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testComponent.setEnabled(true);
            }
        });

        testControlsPane.addButton(TestControlPane.CATEGORY_INTEGRATION, "Disable Component", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testComponent.setEnabled(false);
            }
        });
    }
    
    private Object getTestComponentProperty(String propertyName) {
        try {
            String methodName = "get" + Character.toUpperCase(propertyName.charAt(0)) +  propertyName.substring(1);
            Method setPropertyMethod = testComponent.getClass().getMethod(methodName, new Class[]{});
            return setPropertyMethod.invoke(testComponent, new Object[]{});
        } catch (NoSuchMethodException ex) {
            InteractiveApp.getApp().consoleWrite(ex.toString());
        } catch (IllegalArgumentException ex) {
            InteractiveApp.getApp().consoleWrite(ex.toString());
        } catch (IllegalAccessException ex) {
            InteractiveApp.getApp().consoleWrite(ex.toString());
        } catch (InvocationTargetException ex) {
            InteractiveApp.getApp().consoleWrite(ex.toString());
        }
        return null;
    }
    
    private void setTestComponentProperty(String propertyName, Class propertyClass, Object newValue) {
        try {
            String methodName = "set" + Character.toUpperCase(propertyName.charAt(0)) +  propertyName.substring(1);
            Method setPropertyMethod = testComponent.getClass().getMethod(methodName, new Class[]{propertyClass});
            setPropertyMethod.invoke(testComponent, new Object[]{newValue});
        } catch (NoSuchMethodException ex) {
            InteractiveApp.getApp().consoleWrite(ex.toString());
        } catch (IllegalArgumentException ex) {
            InteractiveApp.getApp().consoleWrite(ex.toString());
        } catch (IllegalAccessException ex) {
            InteractiveApp.getApp().consoleWrite(ex.toString());
        } catch (InvocationTargetException ex) {
            InteractiveApp.getApp().consoleWrite(ex.toString());
        }
    }
}
