/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ai.beans;

/**
 *
 * @author justyna
 */
public class ColorBean {
 private String foregroundColor;
 private String backgroundColor;
 private String check;

 public ColorBean() {
 }
 
  public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }
    
    public String getCheck() {
        return check;
    }

    public String setCheck(String check) {
        return this.check = check;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
}
