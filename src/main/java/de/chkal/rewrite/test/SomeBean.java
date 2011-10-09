package de.chkal.rewrite.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SomeBean {

    private String value;
    
    public String getValue() {
        System.out.println("getter called:" +value);
        return value;
    }

    public void setValue(String value) {
        System.out.println("setter called:" +value);
        this.value = value;
    }

    public String getMessage() {
        return "Hello Spring";
    }
    
}
